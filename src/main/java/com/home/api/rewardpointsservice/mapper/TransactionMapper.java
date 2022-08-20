package com.home.api.rewardpointsservice.mapper;

import com.home.api.rewardpointsservice.entity.Transaction;
import com.home.api.rewardpointsservice.vo.TransactionVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionMapper
{
    private final ModelMapper mapper;

    public List<TransactionVO> convertToListTransactionVO( List<Transaction> transactions )
    {
        return Arrays.asList( mapper.map( transactions, TransactionVO[].class ) );
    }
}
