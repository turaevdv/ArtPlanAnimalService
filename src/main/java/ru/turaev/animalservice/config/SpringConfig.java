package ru.turaev.animalservice.config;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private EurekaClient eurekaClient;

    @Value("${user-service.name}")
    private String userServiceName;

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
        String path = eurekaClient
                .getApplication(userServiceName)
                .getInstances().get(0)
                .getHomePageUrl() + "/userservice/api";
        return WebClient.builder()
                .baseUrl(path)
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
