package com.home.api.rewardpointsservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RewardPointConfiguration
{

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

}
