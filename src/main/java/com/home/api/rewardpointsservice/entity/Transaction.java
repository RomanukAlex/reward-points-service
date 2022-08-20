package com.home.api.rewardpointsservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "transaction" )
public class Transaction
{
    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( name = "transaction_name" )
    private String transactionName;

    @Column( name = "customer_name" )
    private String customerName;

    @Column( name = "date_create" )
    @JsonFormat( pattern = "yyyy-MM-dd" )
    private LocalDate dateCreate;

    @Column( name = "price" )
    private Double price;
}
