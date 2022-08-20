package com.home.api.rewardpointsservice.util;

import com.home.api.rewardpointsservice.exeption.LanguageNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class LanguageValidator
{
    @Value( "#{'${messages.languages}'.split(',')}" )
    private List<String> validLanguages;

    private final MessageHelper messageHelper;

    public void validate( final String targetLanguage )
    {
        final Optional<String> validLanguage = validLanguages.stream()
                                                             .filter( lang -> lang.equalsIgnoreCase( targetLanguage ) )
                                                             .findAny();

        if( validLanguage.isEmpty() )
        {
            log.error( "Sent language: {}.", targetLanguage );
            throw new LanguageNotFoundException( messageHelper );
        }
    }
}
