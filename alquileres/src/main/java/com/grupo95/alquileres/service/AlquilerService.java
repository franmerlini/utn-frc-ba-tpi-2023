package com.grupo95.alquileres.service;

import com.grupo95.alquileres.RestTemplate.EstacionesRestTemplate;
import com.grupo95.alquileres.entity.AlquilerEntity;
import com.grupo95.alquileres.entity.EstacionEntity;
import com.grupo95.alquileres.entity.TarifaEntity;
import com.grupo95.alquileres.repository.AlquilerRepository;
import com.grupo95.alquileres.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AlquilerService {
    @Autowired
    private AlquilerRepository alquilerRepository;
    @Autowired
    private TarifaRepository tarifaRepository;


    public List<AlquilerEntity> obtenerAlquileres() {
        return (List<AlquilerEntity>) alquilerRepository.findAll();
    }

    public void agregarAlquiler(AlquilerEntity alquiler) {
        System.out.println(alquiler);
        alquilerRepository.insertarAlquiler(alquiler.getEstado(), alquiler.getEstacionRetiro(), alquiler.getEstacionDevolucion(), alquiler.getFechaHoraRetiro(), alquiler.getFechaHoraDevolucion(), alquiler.getMonto(), alquiler.getTarifa());
    }


    public void finalizarAlquilerConMoneda(int id, double latitud, double longitud, String moneda){

        AlquilerEntity alquiler = alquilerRepository.findById(id).orElse(null);
        if(alquiler == null) return;

        TarifaEntity tarifa = tarifaRepository.findById(alquiler.getTarifa()).orElse(null);

        if(tarifa == null) return;

        EstacionesRestTemplate estacionesRestTemplate = new EstacionesRestTemplate();

        EstacionEntity estacionPartida = estacionesRestTemplate.getEstacionById(alquiler.getEstacionRetiro());
        EstacionEntity estacionDevolucion = estacionesRestTemplate.getEstacionByCoordenadas(latitud,longitud);

        if(estacionPartida == null || estacionDevolucion == null) return;

        double distancia = Math.floor(
                Math.sqrt(
                    Math.pow(
                            (estacionDevolucion.getLatitud() - estacionPartida.getLatitud())*110000,2) +
                    Math.pow(
                            (estacionDevolucion.getLongitud() - estacionPartida.getLongitud())*110000,2)
                    )/1000);
        double montoKms = tarifa.getMontoKM() * distancia;
        LocalDateTime fechaDevolucion = LocalDateTime.now();
        double montoTotal = tarifa.getMontoFijoAlquiler() + montoKms;
        Duration duracion = Duration.between(alquiler.getFechaHoraRetiro(),fechaDevolucion);
        if(duracion.toMinutes() >= 31) {
            montoTotal += duracion.toHours()*tarifa.getMontoHora();
        } else {
            montoTotal += duracion.toMinutes()*tarifa.getMontoMinutoFraccion();
        }


        /*conversorDivisasService conversorDivisasService = new conversorDivisasService();
            float montoPesos = alquiler.getMonto();
            float montoConvertido = conversorDivisasService.conversor(montoPesos, moneda);

            alquilerRepository.finalizarAlquiler(id, estado, fechaHoraDevolucion);
            System.out.println(alquiler);
            System.out.println("Monto en " + moneda + ": "+ montoConvertido);
*/
    }

}