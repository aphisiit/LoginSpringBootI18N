package com.guy.login.spring.configuration;

import com.guy.login.constant.ConstantVariable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.FixedVersionStrategy;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class MVCConfiguration implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        VersionResourceResolver versionResourceResolver = new VersionResourceResolver()
                .addVersionStrategy(new FixedVersionStrategy(ConstantVariable.APPLICATION_CURRENT_VERSION),"/**");

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/")
                .addResourceLocations("/classpath:/META-INF/web-resources/")
                .resourceChain(true)
                .addResolver(versionResourceResolver);
    }
}
