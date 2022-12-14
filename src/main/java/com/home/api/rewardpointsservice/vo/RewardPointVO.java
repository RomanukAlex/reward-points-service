package com.home.api.rewardpointsservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardPointVO
{
    private String month;
    private String rewardPointsPerMonth;
}
