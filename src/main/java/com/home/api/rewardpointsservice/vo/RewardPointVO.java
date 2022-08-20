package com.home.api.rewardpointsservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardPointVO
{
    private Month month;
    private Double rewardPointsPerMonth;
}
