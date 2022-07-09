package ru.turaev.animalservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.turaev.animalservice.securityutil.JwtTokenFilter;
import ru.turaev.animalservice.service.AuthorizationService;
import ru.turaev.animalservice.service.impl.AuthorizationServiceImpl;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private static final String BASE_URL = "http://localhost:8081/userservice/api";

    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtFilter() {
        FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtTokenFilter());
        registrationBean.addUrlPatterns("/animalservice/api/animals/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(authorizationService());
    }

    @Bean
    public AuthorizationService authorizationService() {
        return new AuthorizationServiceImpl(webClient());
    }
}
