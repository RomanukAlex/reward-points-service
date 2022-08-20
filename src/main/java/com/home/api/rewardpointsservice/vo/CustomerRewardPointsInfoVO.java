package com.home.api.rewardpointsservice.vo;

import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Data
public class CustomerRewardPointsInfoVO
{
    private String customerName;
    private List<RewardPointVO> rewardPoints;
    private AtomicReference<Double> totalRewardPoints;
}
