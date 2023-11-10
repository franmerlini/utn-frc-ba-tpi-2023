package com.grupo95.alquileres.service;

import com.grupo95.alquileres.entity.AlquilerEntity;
import com.grupo95.alquileres.repository.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlquilerService {
    private final AlquilerRepository alquilerRepository;
    private final String API_URL = "http://34.82.105.125:8080/convertir";

    @Autowired
    public AlquilerService(AlquilerRepository alquilerRepository) {
        this.alquilerRepository = alquilerRepository;
    }

    public List<AlquilerEntity> obtenerAlquileres() {
        return (List<AlquilerEntity>) alquilerRepository.findAll();
    }

    public void agregarAlquiler(AlquilerEntity alquiler) {
        System.out.println(alquiler);
        alquilerRepository.insertarAlquiler(alquiler.getEstado(), alquiler.getEstacionRetiro(), alquiler.getEstacionDevolucion(), alquiler.getFechaHoraRetiro(), alquiler.getFechaHoraDevolucion(), alquiler.getMonto(), alquiler.getTarifa());
    }


    public void finalizarAlquilerConMoneda(int id, int estado, LocalDateTime fechaHoraDevolucion, String moneda){
        AlquilerEntity alquiler = alquilerRepository.findById(id).orElse(null);
        if (alquiler != null){
            float montoPesos = alquiler.getMonto();
            RestTemplate restTemplate = new RestTemplate();
            String request = String.format("{\"moneda_destino\":\"%s\",\"importe\":%f}", moneda, montoPesos);
            String response = restTemplate.postForObject(API_URL, request, String.class);

            float montoConvertido = Float.parseFloat(response);
            alquilerRepository.finalizarAlquiler(id, estado, fechaHoraDevolucion);
            System.out.println(alquiler);
            System.out.println("Monto en " + moneda + ": "+ montoConvertido);
        } else {
            System.out.println("No se encontro el alquiler con id: " + id);
        }
    }

}