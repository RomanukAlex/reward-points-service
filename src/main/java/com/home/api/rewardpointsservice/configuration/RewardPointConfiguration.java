package com.home.api.rewardpointsservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class RewardPointConfiguration implements WebMvcConfigurer
{

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    public ResourceBundleMessageSource messageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames( "messages" );
        messageSource.setUseCodeAsDefaultMessage( true );
        messageSource.setDefaultEncoding( "UTF-8" );
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver()
    {
        return new SessionLocaleResolver();
    }

    @Override
    public void addInterceptors( InterceptorRegistry registry )
    {
        registry.addInterceptor( localeChangeInterceptor() );
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor()
    {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName( "language" );
        return localeChangeInterceptor;
    }
}
