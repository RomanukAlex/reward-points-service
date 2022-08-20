package com.home.api.rewardpointsservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRewardPointsInfoVO
{
    private String customerName;
    private List<RewardPointVO> rewardPoints;
    private String totalRewardPoints;
}
