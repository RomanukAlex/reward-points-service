package com.home.api.rewardpointsservice.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionVO
{
    private String transactionName;
    private String customerName;
    private LocalDate dateCreate;
    private Double price;
}
