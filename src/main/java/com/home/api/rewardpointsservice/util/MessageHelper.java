package com.home.api.rewardpointsservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageHelper
{
    private final ResourceBundleMessageSource messageSource;

    public String getMessage( final String messageCode, final Object[] parameters )
    {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage( messageCode, parameters, locale );
    }
}
