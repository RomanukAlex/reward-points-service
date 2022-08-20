package com.home.api.rewardpointsservice.services;

import com.home.api.rewardpointsservice.vo.CustomerRewardPointsInfoVO;

import java.util.List;

public interface RewardPointService
{
    List<CustomerRewardPointsInfoVO> getCustomerRewardPointsInfo();
}
