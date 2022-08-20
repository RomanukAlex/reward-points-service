package com.home.api.rewardpointsservice.services;

import com.home.api.rewardpointsservice.entity.Transaction;
import com.home.api.rewardpointsservice.exeption.TransactionNotFoundException;
import com.home.api.rewardpointsservice.mapper.TransactionMapper;
import com.home.api.rewardpointsservice.repositories.RewardPointRepository;
import com.home.api.rewardpointsservice.util.LanguageValidator;
import com.home.api.rewardpointsservice.util.MessageHelper;
import com.home.api.rewardpointsservice.vo.CustomerRewardPointsInfoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Month;
import java.util.Collections;
import java.util.List;

import static com.home.api.rewardpointsservice.helper.RewardPointBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class )
@TestPropertySource( locations = "classpath:application-test.properties" )
@PropertySource( "classpath:application-test.properties" )
class RewardPointServiceImplTest
{
    @Mock
    private RewardPointRepository rewardPointRepository;
    @Mock
    private TransactionMapper mapper;
    @Mock
    private LanguageValidator languageValidator;
    @Mock
    private MessageHelper messageHelper;
    @InjectMocks
    private RewardPointServiceImpl rewardPointService;

    @BeforeEach
    public void setUp()
    {
        ReflectionTestUtils.setField( rewardPointService, "countOfPointOver50", 1.0 );
        ReflectionTestUtils.setField( rewardPointService, "countOfPointOver100", 1.0 );
    }

    @Test
    void shouldCallLanguageValidator()
    {
        when( rewardPointRepository.findAll() ).thenReturn( Collections.singletonList( new Transaction() ) );
        rewardPointService.getCustomerRewardPointsInfo( VALID_LANGUAGE_EN );
        verify( languageValidator ).validate( VALID_LANGUAGE_EN );
    }

    @Test
    void shouldThrowTransactionNotFoundException()
    {
        when( rewardPointRepository.findAll() ).thenReturn( Collections.EMPTY_LIST );
        when( messageHelper.getMessage( anyString(), any() ) ).thenReturn( MESSAGE_NO_LANGUAGE_FOUND );

        final Exception expectedEx = assertThrows( TransactionNotFoundException.class,
            () -> rewardPointService.getCustomerRewardPointsInfo( VALID_LANGUAGE_EN ) );

        assertTrue( expectedEx.getMessage().contains( MESSAGE_NO_LANGUAGE_FOUND ) );
        verify( mapper, never() ).convertToListTransactionVO( Collections.singletonList( TRANSACTION ) );

    }

    @Test
    void shouldCallTransactionMapper()
    {
        when( rewardPointRepository.findAll() ).thenReturn( Collections.singletonList( TRANSACTION ) );
        rewardPointService.getCustomerRewardPointsInfo( VALID_LANGUAGE_EN );
        verify( mapper ).convertToListTransactionVO( Collections.singletonList( TRANSACTION ) );
    }

    @Test
    void shouldReturnCustomerRewardPointsInfoWhenPriceLess50()
    {
        when( rewardPointRepository.findAll() ).thenReturn( Collections.singletonList( TRANSACTION ) );
        when( mapper.convertToListTransactionVO( Collections.singletonList( TRANSACTION ) ) )
            .thenReturn( Collections.singletonList( TRANSACTION_VO_WHEN_PRICE_MORE_50 ) );

        when( messageHelper.getMessage( Month.DECEMBER.toString(), null ) ).thenReturn( DECEMBER );
        when( messageHelper.getMessage( eq( MESSAGE_CODE ), any() ) ).thenReturn( MESSAGE_REWARD_POINTS_PER_MONTH_0 );
        final List<CustomerRewardPointsInfoVO> customerRewardPointsInfoList = rewardPointService.getCustomerRewardPointsInfo( VALID_LANGUAGE_EN );

        assertEquals( MESSAGE_REWARD_POINTS_PER_MONTH_0,
            customerRewardPointsInfoList.get( 0 ).getRewardPoints().get( 0 ).getRewardPointsPerMonth() );
    }

    @Test
    void shouldReturnCustomerRewardPointsInfoWhenPriceMore50()
    {
        when( rewardPointRepository.findAll() ).thenReturn( Collections.singletonList( TRANSACTION ) );
        when( mapper.convertToListTransactionVO( Collections.singletonList( TRANSACTION ) ) )
            .thenReturn( Collections.singletonList( TRANSACTION_VO_WHEN_PRICE_MORE_50 ) );
        when( messageHelper.getMessage( Month.DECEMBER.toString(), null ) ).thenReturn( DECEMBER );
        when( messageHelper.getMessage( eq( MESSAGE_CODE ), eq( new Object[] { NUMBER_OF_REWARD_POINTS_PER_MONTH_1 } ) ) ).thenReturn(
            MESSAGE_REWARD_POINTS_PER_MONTH_1 );

        final List<CustomerRewardPointsInfoVO> customerRewardPointsInfoList = rewardPointService.getCustomerRewardPointsInfo( VALID_LANGUAGE_EN );

        assertEquals( MESSAGE_REWARD_POINTS_PER_MONTH_1,
            customerRewardPointsInfoList.get( 0 ).getRewardPoints().get( 0 ).getRewardPointsPerMonth() );
    }

    @Test
    void shouldReturnCustomerRewardPointsInfoWhenPriceMore100()
    {
        when( rewardPointRepository.findAll() ).thenReturn( Collections.singletonList( TRANSACTION ) );
        when( mapper.convertToListTransactionVO( Collections.singletonList( TRANSACTION ) ) )
            .thenReturn( Collections.singletonList( TRANSACTION_VO_WHEN_PRICE_MORE_100 ) );

        when( messageHelper.getMessage( Month.DECEMBER.toString(), null ) ).thenReturn( DECEMBER );
        when( messageHelper.getMessage( eq( MESSAGE_CODE ), eq( new Object[] { NUMBER_OF_REWARD_POINTS_PER_MONTH_90 } ) ) ).thenReturn( MESSAGE_REWARD_POINTS_PER_MONTH_90 );
        final List<CustomerRewardPointsInfoVO> customerRewardPointsInfoList = rewardPointService.getCustomerRewardPointsInfo( VALID_LANGUAGE_EN );

        assertEquals( MESSAGE_REWARD_POINTS_PER_MONTH_90,
            customerRewardPointsInfoList.get( 0 ).getRewardPoints().get( 0 ).getRewardPointsPerMonth() );
    }

}