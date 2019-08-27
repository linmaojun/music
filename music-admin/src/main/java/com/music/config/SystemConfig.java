package com.music.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author xpq2013@aliyun.com
 * @version 1.0.0
 * @date 2018/12/14
 * @desc
 */
@Configuration
public class SystemConfig {

    @Resource
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;


    @PostConstruct
    public void date() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer)
                requestMappingHandlerAdapter.getWebBindingInitializer();
        GenericConversionService conversionService = (GenericConversionService) initializer.getConversionService();
        conversionService.addConverter(new DateConverter());
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws IOException, TemplateException {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:templates/");
        freemarker.template.Configuration configuration = freeMarkerConfigurer.createConfiguration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setSharedVariable("shiro", new ShiroTags());
        freeMarkerConfigurer.setConfiguration(configuration);
        return freeMarkerConfigurer;
    }
}
