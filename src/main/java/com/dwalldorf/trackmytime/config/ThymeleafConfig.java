package com.dwalldorf.trackmytime.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import uk.co.gcwilliams.jodatime.thymeleaf.JodaTimeDialect;

@Configuration
@ConditionalOnClass(SpringTemplateEngine.class)
@AutoConfigureAfter(WebMvcConfig.class)
public class ThymeleafConfig {

    @Bean
    public JodaTimeDialect jodaTimeDialect() {
        return new JodaTimeDialect();
    }
}