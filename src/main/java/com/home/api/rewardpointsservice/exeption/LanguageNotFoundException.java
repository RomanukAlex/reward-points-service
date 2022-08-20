package com.home.api.rewardpointsservice.exeption;

import com.home.api.rewardpointsservice.util.MessageHelper;
import org.springframework.stereotype.Component;

@Component
public class LanguageNotFoundException extends RuntimeException
{
    private static final String ERROR_CODE = "message.error.validation.language.not.found";

    public LanguageNotFoundException( final MessageHelper messageHelper )
    {
        super( messageHelper.getMessage( ERROR_CODE, null ) );
    }
}