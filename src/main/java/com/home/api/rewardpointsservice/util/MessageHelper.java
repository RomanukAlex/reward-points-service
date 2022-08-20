package com.home.api.rewardpointsservice.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageHelper
{
    private static ResourceBundleMessageSource messageSource;

    MessageHelper( ResourceBundleMessageSource messageSource )
    {
        MessageHelper.messageSource = messageSource;
    }

    public static String getMessage( final String messageCode, final Object[] parameters )
    {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage( messageCode, parameters, locale );
    }
}
