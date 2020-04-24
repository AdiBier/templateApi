package com.ab.templateApi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

public class TomcatSettings implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Value("${server.http.port}")
    private int httPort;

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(httPort);
    }
}
