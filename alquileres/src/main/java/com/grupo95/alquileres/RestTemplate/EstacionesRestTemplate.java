package com.grupo95.alquileres.RestTemplate;

import com.grupo95.alquileres.entity.EstacionEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class EstacionesRestTemplate {

    public EstacionesRestTemplate() {

    }
    public EstacionEntity getEstacionById(int idEstacion) {
        // Creación de una instancia de RestTemplate
        try {
            // Creación de la instancia de RequestTemplate
            RestTemplate template = new RestTemplate();
            // Se realiza una petición a http://localhost:8082/api/personas/{id}, indicando que id vale 1 y que la
            // respuesta de la petición tendrá en su cuerpo a un objeto del tipo Persona.
            ResponseEntity<EstacionEntity> res = template.getForEntity(
                    "http://localhost:8081/api/estaciones/{id}", EstacionEntity.class, idEstacion
            );
            // Se comprueba si el código de repuesta es de la familia 200
            if (res.getStatusCode().is2xxSuccessful()) {
                return res.getBody();
            }
            return null;

        } catch (HttpClientErrorException ex) {
            // La repuesta no es exitosa
        }
        return null;
    }

    public EstacionEntity getEstacionByCoordenadas(Double latitud, Double longitud) {
        // Creación de una instancia de RestTemplate

        try {
            // Creación de la instancia de RequestTemplate
            RestTemplate template = new RestTemplate();
            ResponseEntity<EstacionEntity> res = template.getForEntity(
                    "http://localhost:8081/api/estaciones/cercano?lat={latitud}&lng={longitud}", EstacionEntity.class, latitud,longitud
            );

            // Se comprueba si el código de repuesta es de la familia 200
            if (res.getStatusCode().is2xxSuccessful()) {
                return res.getBody();
            }
            return null;

        } catch (HttpClientErrorException ex) {
            // La repuesta no es exitosa
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
