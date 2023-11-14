package com.grupo95.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
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
            .pathMatchers(HttpMethod.GET, pathAlquileres)
            .hasRole("ADMINISTRADOR")
            .pathMatchers(HttpMethod.POST, pathEstaciones)
            .hasRole("ADMINISTRADOR")

            .pathMatchers(HttpMethod.GET, pathEstaciones)
            .hasRole("CLIENTE")
            .pathMatchers(HttpMethod.POST, pathAlquileres)
            .hasRole("CLIENTE")
            .pathMatchers(HttpMethod.PUT, pathAlquileres + "/finalizar")
            .hasRole("CLIENTE")

            .pathMatchers(pathEstaciones + "/**", pathAlquileres + "/**")
            .hasAnyRole("ADMINISTRADOR", "CLIENTE")

            .anyExchange()
            .authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
        .csrf(ServerHttpSecurity.CsrfSpec::disable);
    return serverHttpSecurity.build();
  }

  @Bean
  public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
    ReactiveJwtAuthenticationConverter reactiveJwtAuthenticationConverter =
        new ReactiveJwtAuthenticationConverter();
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter =
        new JwtGrantedAuthoritiesConverter();

    grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
    grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

    reactiveJwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
        new ReactiveJwtGrantedAuthoritiesConverterAdapter(grantedAuthoritiesConverter));

    return reactiveJwtAuthenticationConverter;
  }
}
