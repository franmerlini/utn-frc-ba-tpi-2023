package com.grupo95.alquileres.RestTemplate;

import com.grupo95.alquileres.entity.EstacionEntity;
import com.grupo95.alquileres.entity.response.ConversorDivisaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;

public class ConversorDivisasRestTemplate {
    public ConversorDivisaResponse getMonedaConvertida(Double montoPesos, String moneda) {
        // Creación de una instancia de RestTemplate
        try {
            // Creación de la instancia de RequestTemplate
            RestTemplate template = new RestTemplate();

            String request = String.format("{\"moneda_destino\":\"%s\",\"importe\":%s}", moneda, montoPesos.toString().replace(",", "."));
            System.out.println(request);
            ResponseEntity<ConversorDivisaResponse> res = template.postForEntity("http://34.82.105.125:8080/convertir", request, ConversorDivisaResponse.class);


            // Se comprueba si el código de repuesta es de la familia 200
            if (res.getStatusCode().is2xxSuccessful()) {
                return res.getBody();
            }
            return null;

        } catch (HttpClientErrorException ex) {
            // La repuesta no es exitosa
            System.out.println(ex);
        }
        return null;
    }
}
