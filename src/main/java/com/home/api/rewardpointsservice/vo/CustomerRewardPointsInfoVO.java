package com.home.api.rewardpointsservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class CustomerRewardPointsInfoVO
{
    private String customerName;
    private List<RewardPointVO> rewardPoints;
    private String totalRewardPoints;
}
