package ua.pidopryhora.mediaconverter.filemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.pidopryhora.mediaconverter.common.jave2.FormatSupplier;

@Configuration
public class FormatSupplierConfig {


    @Bean
    public FormatSupplier formatSupplier() {
        return new FormatSupplier();
    }
}

