package com.home.api.rewardpointsservice.exeption;

public class TransactionNotFoundException extends RuntimeException
{
    public TransactionNotFoundException()
    {
        super( "Any transactions wasn't found!" );
    }
}
