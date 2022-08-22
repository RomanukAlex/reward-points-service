package com.home.api.rewardpointsservice.services;

import com.home.api.rewardpointsservice.entity.Transaction;
import com.home.api.rewardpointsservice.exeption.TransactionNotFoundException;
import com.home.api.rewardpointsservice.mapper.TransactionMapper;
import com.home.api.rewardpointsservice.repositories.RewardPointRepository;
import com.home.api.rewardpointsservice.util.LanguageValidator;
import com.home.api.rewardpointsservice.util.MessageHelper;
import com.home.api.rewardpointsservice.vo.CustomerRewardPointsInfoVO;
import com.home.api.rewardpointsservice.vo.RewardPointVO;
import com.home.api.rewardpointsservice.vo.TransactionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
@Transactional( readOnly = true )
@Slf4j
public class RewardPointServiceImpl implements RewardPointService
{
    private final RewardPointRepository rewardPointRepository;
    private final TransactionMapper mapper;
    private final LanguageValidator languageValidator;
    private final MessageHelper messageHelper;
    private final String rewardPointsPerMonthMessageCode = "message.number.reward.points.per.month";
    private final String totalRewardPointsMessageCode = "message.total.number.reward.points";
    @Value( "${reward.point.over.50}" )
    private Double countOfPointOver50;
    @Value( "${reward.point.over.100}" )
    private Double countOfPointOver100;

    @Override
    public List<CustomerRewardPointsInfoVO> getCustomerRewardPointsInfo( final String language )
    {
        log.error( "Using language: {}.", language );
        languageValidator.validate( language );

        final List<Transaction> transactions = rewardPointRepository.findAll();
        if( transactions.isEmpty() )
        {
            log.error( "No transactions found." );
            throw new TransactionNotFoundException( messageHelper );
        }

        final List<TransactionVO> transactionsVO = mapper.convertToListTransactionVO( transactions );

        final Map<String, HashMap<Month, Double>> groupingMap = groupingByCustomerNameAndMonth( transactionsVO );

        return prepareCustomerRewardPointsInfoResponse( groupingMap );
    }

    private Map<String, HashMap<Month, Double>> groupingByCustomerNameAndMonth( final List<TransactionVO> transactionsVO )
    {
        return transactionsVO.stream()
                             .collect(
                                 groupingBy( TransactionVO::getCustomerName,
                                     groupingBy( transaction -> Month.from( transaction.getDateCreate() ),
                                         HashMap::new,
                                         Collectors.summingDouble( ( this::calculateRewardPoints
                                         ) ) ) ) );
    }

    private List<CustomerRewardPointsInfoVO> prepareCustomerRewardPointsInfoResponse( final Map<String, HashMap<Month, Double>> groupingMap )
    {
        return groupingMap.entrySet().stream().map( entry -> {
            final CustomerRewardPointsInfoVO customerVO = new CustomerRewardPointsInfoVO();
            customerVO.setCustomerName( entry.getKey() );

            final List<RewardPointVO> rewardPoints = new ArrayList<>();
            AtomicReference<Double> totalRewardPoints = new AtomicReference<>( 0.0 );
            List<RewardPointVO> rewardPointsVO = prepareRewardPointsByMonth( entry.getValue(), rewardPoints, totalRewardPoints );

            customerVO.setRewardPoints( rewardPointsVO );
            customerVO.setTotalRewardPoints( messageHelper.getMessage( totalRewardPointsMessageCode, new Object[] { totalRewardPoints } ) );
            return customerVO;

        } ).collect( Collectors.toList() );
    }

    private Double calculateRewardPoints( final TransactionVO transaction )
    {
        Double price = transaction.getPrice();
        if( price <= 50 )
        {
            return 0.0;
        }
        else if( price > 50 && price <= 100 )
        {
            return ( transaction.getPrice() - 50 ) * countOfPointOver50;
        }
        else
        {
            return ( transaction.getPrice() - 50 ) * countOfPointOver50 + ( transaction.getPrice() - 100 ) * countOfPointOver100;
        }
    }

    private List<RewardPointVO> prepareRewardPointsByMonth( Map<Month, Double> map, List<RewardPointVO> rewardPoints, AtomicReference<Double> totalRewardPoints )
    {
        return map.entrySet().stream().map( entrySet -> {
            final Month month = entrySet.getKey();
            final RewardPointVO rewardPointVO = new RewardPointVO(
                messageHelper.getMessage( month.toString(), null ),
                messageHelper.getMessage( rewardPointsPerMonthMessageCode, new Object[] { entrySet.getValue() } ) );

            rewardPoints.add( rewardPointVO );
            totalRewardPoints.updateAndGet( value -> value + entrySet.getValue() );

            return rewardPointVO;
        } ).collect( Collectors.toList() );
    }
}
