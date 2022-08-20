package com.home.api.rewardpointsservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.api.rewardpointsservice.vo.CustomerRewardPointsInfoVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static com.home.api.rewardpointsservice.helper.RewardPointBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application-test.properties" )
class RewardPointControllerTest
{
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    private static Stream<Arguments> rewardPointsTestData()
    {
        return Stream.of(
            Arguments.of( VALID_LANGUAGE_EN, CUSTOMER_REWARD_POINTS_INFO_RESPONSE_EN ),
            Arguments.of( VALID_LANGUAGE_ES, CUSTOMER_REWARD_POINTS_INFO_RESPONSE_ES ),
            Arguments.of( VALID_LANGUAGE_FR, CUSTOMER_REWARD_POINTS_INFO_RESPONSE_FR )
        );
    }

    @ParameterizedTest
    @MethodSource( "rewardPointsTestData" )
    public void shouldReturnCustomerRewardPointsInfo( final String language, List<CustomerRewardPointsInfoVO> response ) throws Exception
    {
        this.mockMvc.perform( get( GET_CUSTOMER_REWARD_POINTS_INFO_URL )
                .contentType( MediaType.APPLICATION_JSON )
                .param( LANGUAGE, language ) )
                    .andDo( print() )
                    .andExpect( status().isOk() )
                    .andExpect( content().json( mapper.writeValueAsString( response ) ) );
    }

    @Test
    public void shouldReturnLanguageNotFoundException() throws Exception
    {
        this.mockMvc.perform( get( GET_CUSTOMER_REWARD_POINTS_INFO_URL )
                .contentType( MediaType.APPLICATION_JSON )
                .param( LANGUAGE, INVALID_LANGUAGE ) )
                    .andDo( print() )
                    .andExpect( status().isBadRequest() )
                    .andExpect( jsonPath( "$.status" ).value( BAD_REQUEST ) )
                    .andExpect( jsonPath( "$.message" ).value( MESSAGE_NO_LANGUAGE_FOUND ) );
    }
}