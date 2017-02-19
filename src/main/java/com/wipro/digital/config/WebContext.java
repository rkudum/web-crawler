package com.wipro.digital.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by ramesh on 2/19/2017.
 * This is a configuration file which overrides the WebMvcConfigurerAdapter
 */
@Configuration
public class WebContext extends WebMvcConfigurerAdapter{

    private Jackson2ObjectMapperBuilder builder;

    /**
     * This method sets the formatting of the json response.
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        builder = Jackson2ObjectMapperBuilder.json().serializationInclusion(JsonInclude.Include.NON_NULL).serializationInclusion(JsonInclude.Include.NON_EMPTY).indentOutput(true);
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

}
