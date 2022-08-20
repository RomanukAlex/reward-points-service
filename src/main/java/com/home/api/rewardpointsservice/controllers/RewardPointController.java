package com.home.api.rewardpointsservice.controllers;

import com.home.api.rewardpointsservice.services.RewardPointService;
import com.home.api.rewardpointsservice.vo.CustomerRewardPointsInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/v1" )
public class RewardPointController
{
    private final RewardPointService rewardPointService;

    @GetMapping( value = "/customers/reward-points-info" )
    public ResponseEntity<List<CustomerRewardPointsInfoVO>> getCustomerRewardPointsInfo(
        @RequestParam( value = "language", defaultValue = "en" ) final String language )
    {
        return ResponseEntity.ok( rewardPointService.getCustomerRewardPointsInfo( language ) );
    }
}
