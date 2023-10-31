package com.grupo95.alquileres.service;

import com.grupo95.alquileres.entity.AlquilerEntity;
import com.grupo95.alquileres.repository.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlquilerService {
    private final AlquilerRepository alquilerRepository;

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
            conversorDivisasService conversorDivisasService = new conversorDivisasService();
            float montoPesos = alquiler.getMonto();
            float montoConvertido = conversorDivisasService.conversor(montoPesos, moneda);

            alquilerRepository.finalizarAlquiler(id, estado, fechaHoraDevolucion);
            System.out.println(alquiler);
            System.out.println("Monto en " + moneda + ": "+ montoConvertido);
        } else {
            System.out.println("No se encontro el alquiler con id: " + id);
        }
    }

}