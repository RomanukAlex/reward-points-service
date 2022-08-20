package com.home.api.rewardpointsservice.exeption;

import com.home.api.rewardpointsservice.util.MessageHelper;

public class LanguageNotFoundException extends RuntimeException
{
    private static final String ERROR_CODE = "message.error.validation.language.not.found";

    public LanguageNotFoundException( final String validLanguages )
    {
        super( MessageHelper.getMessage( ERROR_CODE, null ).concat( validLanguages ) );
    }
}