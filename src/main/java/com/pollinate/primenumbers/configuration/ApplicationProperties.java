package com.pollinate.primenumbers.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * This class loads custom properties required for the application.
 * Value of this properties can be changed in application.properties file in resource folder.
 * Lombok annotations are used here to generate getters, setters and constructors.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "prime.number")
public class ApplicationProperties {

    private String chunkSize;

}
