package com.home.api.rewardpointsservice.exeption;

import com.home.api.rewardpointsservice.util.MessageHelper;

public class TransactionNotFoundException extends RuntimeException
{
    private static final String ERROR_CODE = "message.error.transaction.not.found";

    public TransactionNotFoundException()
    {
        super( MessageHelper.getMessage( ERROR_CODE, null ) );
    }
}