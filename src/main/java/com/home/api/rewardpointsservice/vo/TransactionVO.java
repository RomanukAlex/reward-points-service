package com.home.api.rewardpointsservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionVO
{
    private String transactionName;
    private String customerName;
    private LocalDate dateCreate;
    private Double price;
}
