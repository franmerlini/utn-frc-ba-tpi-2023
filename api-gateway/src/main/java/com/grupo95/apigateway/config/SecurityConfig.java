package com.grupo95.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity
public class SecurityConfig {
  @Value("${uri_estaciones}")
  private String uriEstaciones;

  @Value("${path_estaciones}")
  private String pathEstaciones;

  @Value("${uri_alquileres}")
  private String uriAlquileres;

  @Value("${path_alquileres}")
  private String pathAlquileres;

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
    return routeLocatorBuilder.routes()
        .route(p -> p.path(pathEstaciones + "/**").uri(uriEstaciones))
        .route(p -> p.path(pathAlquileres + "/**").uri(uriAlquileres))
        .build();
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
    serverHttpSecurity
        .authorizeExchange(exchange -> exchange
            .pathMatchers(pathEstaciones + "/**", pathAlquileres + "/**")
            .hasAnyRole("ADMIN", "CLIENT")
            .anyExchange()
            .authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
        .csrf(ServerHttpSecurity.CsrfSpec::disable);
    return serverHttpSecurity.build();
  }

  @Bean
  public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
    var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
    var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    // Se especifica el nombre del claim a analizar
    grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
    // Se agrega este prefijo en la conversión por una convención de Spring
    grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

    // Se asocia el conversor de Authorities al Bean que convierte el token JWT a un objeto Authorization
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
        new ReactiveJwtGrantedAuthoritiesConverterAdapter(grantedAuthoritiesConverter));

    return jwtAuthenticationConverter;
  }
}
