package com.home.api.rewardpointsservice.helper;

import com.home.api.rewardpointsservice.entity.Transaction;
import com.home.api.rewardpointsservice.vo.CustomerRewardPointsInfoVO;
import com.home.api.rewardpointsservice.vo.RewardPointVO;
import com.home.api.rewardpointsservice.vo.TransactionVO;

import java.time.LocalDate;
import java.util.List;

public class RewardPointBuilder
{
    public static final String GET_CUSTOMER_REWARD_POINTS_INFO_URL = "/v1/customers/reward-points-info";

    public static final String VALID_LANGUAGE_EN = "EN";
    public static final String VALID_LANGUAGE_ES = "ES";
    public static final String VALID_LANGUAGE_FR = "FR";
    public static final String INVALID_LANGUAGE = "error";
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String LANGUAGE = "language";
    public static final String MESSAGE_CODE = "message.number.reward.points.per.month";
    public static final String MESSAGE_REWARD_POINTS_PER_MONTH_0 = "The number of reward points per month: 0";
    public static final String MESSAGE_REWARD_POINTS_PER_MONTH_1 = "The number of reward points per month: 1.";
    public static final String MESSAGE_REWARD_POINTS_PER_MONTH_90 = "The number of reward points per month: 90";
    public static final Double NUMBER_OF_REWARD_POINTS_PER_MONTH_0 = 1.0;
    public static final Double NUMBER_OF_REWARD_POINTS_PER_MONTH_1 = 1.0;
    public static final Double NUMBER_OF_REWARD_POINTS_PER_MONTH_90 = 90.0;
    public static final String DECEMBER = "December";

    public static final String MESSAGE_NO_LANGUAGE_FOUND = "No language found. Valid languages:[EN, ES, FR]";

    public static final Transaction TRANSACTION = Transaction.builder()
                                                             .id( 1 )
                                                             .transactionName( "transation1" )
                                                             .customerName( "Alex" )
                                                             .dateCreate( LocalDate.of( 2022, 12, 12 ) )
                                                             .price( 120.0 )
                                                             .build();

    public static final TransactionVO TRANSACTION_VO_WHEN_PRICE_MORE_100 = TransactionVO.builder()
                                                                                        .transactionName( "transation1" )
                                                                                        .customerName( "Alex" )
                                                                                        .dateCreate( LocalDate.of( 2022, 12, 12 ) )
                                                                                        .price( 120.0 )
                                                                                        .build();

    public static final TransactionVO TRANSACTION_VO_WHEN_PRICE_MORE_50 = TransactionVO.builder()
                                                                                       .transactionName( "transation1" )
                                                                                       .customerName( "Alex" )
                                                                                       .dateCreate( LocalDate.of( 2022, 12, 12 ) )
                                                                                       .price( 51.0 )
                                                                                       .build();

    public static final TransactionVO TRANSACTION_VO_WHEN_PRICE_LESS_50 = TransactionVO.builder()
                                                                                       .transactionName( "transation1" )
                                                                                       .customerName( "Alex" )
                                                                                       .dateCreate( LocalDate.of( 2022, 12, 12 ) )
                                                                                       .price( 49.0 )
                                                                                       .build();

    public static final List<CustomerRewardPointsInfoVO> CUSTOMER_REWARD_POINTS_INFO_RESPONSE_EN = List.of(
        CustomerRewardPointsInfoVO.builder()
                                  .customerName( "Alex" )
                                  .totalRewardPoints( "The total number of reward points: 158.0." )
                                  .rewardPoints( List.of(
                                      RewardPointVO.builder()
                                                   .month( "January" )
                                                   .rewardPointsPerMonth( "The number of reward points per month: 0." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "December" )
                                                   .rewardPointsPerMonth( "The number of reward points per month: 156." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "February" )
                                                   .rewardPointsPerMonth( "The number of reward points per month: 2." )
                                                   .build()
                                  ) )
                                  .build(),
        CustomerRewardPointsInfoVO.builder()
                                  .customerName( "Mike" )
                                  .totalRewardPoints( "The total number of reward points: 53.0." )
                                  .rewardPoints( List.of(
                                      RewardPointVO.builder()
                                                   .month( "April" )
                                                   .rewardPointsPerMonth( "The number of reward points per month: 52." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "February" )
                                                   .rewardPointsPerMonth( "The number of reward points per month: 0." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "March" )
                                                   .rewardPointsPerMonth( "The number of reward points per month: 1." )
                                                   .build()
                                  ) )
                                  .build(),
        CustomerRewardPointsInfoVO.builder()
                                  .customerName( "Nick" )
                                  .totalRewardPoints( "The total number of reward points: 270.0." )
                                  .rewardPoints( List.of(
                                      RewardPointVO.builder()
                                                   .month( "April" )
                                                   .rewardPointsPerMonth( "The number of reward points per month: 90." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "February" )
                                                   .rewardPointsPerMonth( "The number of reward points per month: 90." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "March" )
                                                   .rewardPointsPerMonth( "The number of reward points per month: 90." )
                                                   .build()
                                  ) )
                                  .build()
    );

    public static final List<CustomerRewardPointsInfoVO> CUSTOMER_REWARD_POINTS_INFO_RESPONSE_ES = List.of(
        CustomerRewardPointsInfoVO.builder()
                                  .customerName( "Alex" )
                                  .totalRewardPoints( "El n�mero total de puntos de recompensa: 158.0." )
                                  .rewardPoints( List.of(
                                      RewardPointVO.builder()
                                                   .month( "Febrero" )
                                                   .rewardPointsPerMonth( "El n�mero de puntos de recompensa por mes: 2." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Enero" )
                                                   .rewardPointsPerMonth( "El n�mero de puntos de recompensa por mes: 0." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Diciembre" )
                                                   .rewardPointsPerMonth( "El n�mero de puntos de recompensa por mes: 156." )
                                                   .build()
                                  ) )
                                  .build(),
        CustomerRewardPointsInfoVO.builder()
                                  .customerName( "Mike" )
                                  .totalRewardPoints( "El n�mero total de puntos de recompensa: 53.0." )
                                  .rewardPoints( List.of(
                                      RewardPointVO.builder()
                                                   .month( "Febrero" )
                                                   .rewardPointsPerMonth( "El n�mero de puntos de recompensa por mes: 0." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Abril" )
                                                   .rewardPointsPerMonth( "El n�mero de puntos de recompensa por mes: 52." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Marzo" )
                                                   .rewardPointsPerMonth( "El n�mero de puntos de recompensa por mes: 1." )
                                                   .build()
                                  ) )
                                  .build(),
        CustomerRewardPointsInfoVO.builder()
                                  .customerName( "Nick" )
                                  .totalRewardPoints( "El n�mero total de puntos de recompensa: 270.0." )
                                  .rewardPoints( List.of(
                                      RewardPointVO.builder()
                                                   .month( "Febrero" )
                                                   .rewardPointsPerMonth( "El n�mero de puntos de recompensa por mes: 90." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Abril" )
                                                   .rewardPointsPerMonth( "El n�mero de puntos de recompensa por mes: 90." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Marzo" )
                                                   .rewardPointsPerMonth( "El n�mero de puntos de recompensa por mes: 90." )
                                                   .build()
                                  ) )
                                  .build()
    );

    public static final List<CustomerRewardPointsInfoVO> CUSTOMER_REWARD_POINTS_INFO_RESPONSE_FR = List.of(
        CustomerRewardPointsInfoVO.builder()
                                  .customerName( "Alex" )
                                  .totalRewardPoints( "Le nombre total de points de r�compense: 158.0." )
                                  .rewardPoints( List.of(
                                      RewardPointVO.builder()
                                                   .month( "Fevrier" )
                                                   .rewardPointsPerMonth( "Le nombre de points de fid�lit� par mois: 2." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Janvier" )
                                                   .rewardPointsPerMonth( "Le nombre de points de fid�lit� par mois: 0." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Decembre" )
                                                   .rewardPointsPerMonth( "Le nombre de points de fid�lit� par mois: 156." )
                                                   .build()
                                  ) )
                                  .build(),
        CustomerRewardPointsInfoVO.builder()
                                  .customerName( "Mike" )
                                  .totalRewardPoints( "Le nombre total de points de r�compense: 53.0." )
                                  .rewardPoints( List.of(
                                      RewardPointVO.builder()
                                                   .month( "Fevrier" )
                                                   .rewardPointsPerMonth( "Le nombre de points de fid�lit� par mois: 0." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Avril" )
                                                   .rewardPointsPerMonth( "Le nombre de points de fid�lit� par mois: 52." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Mars" )
                                                   .rewardPointsPerMonth( "Le nombre de points de fid�lit� par mois: 1." )
                                                   .build()
                                  ) )
                                  .build(),
        CustomerRewardPointsInfoVO.builder()
                                  .customerName( "Nick" )
                                  .totalRewardPoints( "Le nombre total de points de r�compense: 270.0." )
                                  .rewardPoints( List.of(
                                      RewardPointVO.builder()
                                                   .month( "Fevrier" )
                                                   .rewardPointsPerMonth( "Le nombre de points de fid�lit� par mois: 90." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Avril" )
                                                   .rewardPointsPerMonth( "Le nombre de points de fid�lit� par mois: 90." )
                                                   .build(),
                                      RewardPointVO.builder()
                                                   .month( "Mars" )
                                                   .rewardPointsPerMonth( "Le nombre de points de fid�lit� par mois: 90." )
                                                   .build()
                                  ) )
                                  .build()
    );
}
