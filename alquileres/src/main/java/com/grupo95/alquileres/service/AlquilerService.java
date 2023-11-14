package com.grupo95.alquileres.service;

import com.grupo95.alquileres.RestTemplate.ConversorDivisasRestTemplate;
import com.grupo95.alquileres.RestTemplate.EstacionesRestTemplate;
import com.grupo95.alquileres.entity.AlquilerEntity;
import com.grupo95.alquileres.entity.EstacionEntity;
import com.grupo95.alquileres.entity.TarifaEntity;
import com.grupo95.alquileres.entity.response.ConversorDivisaResponse;
import com.grupo95.alquileres.entity.response.FinalizarAlquilerDTO;
import com.grupo95.alquileres.repository.AlquilerRepository;
import com.grupo95.alquileres.repository.TarifaRepository;
import com.grupo95.estaciones.repository.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class AlquilerService {
    @Autowired
    private AlquilerRepository alquilerRepository;
    @Autowired
    private TarifaRepository tarifaRepository;


    public List<AlquilerEntity> obtenerAlquileres(String cliente, Integer estado, Integer tarifa) {
        if (cliente == null && estado == null && tarifa == null) return (List<AlquilerEntity>) alquilerRepository.findAll();
        else return alquilerRepository.findAlquilerEntitiesByIdClienteOrTarifaOrEstado(cliente, tarifa, estado);
    }

    public void agregarAlquiler(String cliente, Integer estacionRetiro) {
        LocalDateTime now = LocalDateTime.now();
        EstacionesRestTemplate estacionesRestTemplate = new EstacionesRestTemplate();
        EstacionEntity estacion = estacionesRestTemplate.getEstacionById(estacionRetiro);
        if (estacion == null) throw new NoSuchElementException("Estacion no existente");
        if (!alquilerRepository.findAlquilerEntitiesByIdClienteAndEstado(cliente, 1).isEmpty()) throw new IllegalArgumentException("Cliente con alquileres sin finalizar");

        alquilerRepository.insertarAlquiler(cliente,1, estacionRetiro, now);
    }

    public FinalizarAlquilerDTO finalizarAlquilerConMoneda(int id, double latitud, double longitud, String moneda) throws Exception {

        AlquilerEntity alquiler = alquilerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Alquiler inexistente"));
        if (alquiler.estaFinalizado()) throw new IllegalArgumentException("Alquiler ya finalizado.");

        LocalDateTime fechaDevolucion = LocalDateTime.now();
        TarifaEntity tarifa = tarifaRepository.findTarifaByDefinicionCAndDiaMesAndAnio(
                fechaDevolucion.getDayOfMonth(), fechaDevolucion.getMonthValue(), fechaDevolucion.getYear());

        // Si hay tarifas específicas para hoy, devolverlas
        if (tarifa == null) {
            tarifa = tarifaRepository.findTarifaByDefinicionSAndDiaSemana(fechaDevolucion.getDayOfWeek().getValue());
        }

        if(tarifa == null) throw new Exception("NO HAY TARIFA");

        EstacionesRestTemplate estacionesRestTemplate = new EstacionesRestTemplate();

        EstacionEntity estacionPartida = estacionesRestTemplate.getEstacionById(alquiler.getEstacionRetiro());
        EstacionEntity estacionDevolucion = estacionesRestTemplate.getEstacionByCoordenadas(latitud,longitud);

        if(estacionPartida == null || estacionDevolucion == null) throw new Exception("NO HAY ESTACION");

        double distancia = Math.floor(
                Math.sqrt(
                    Math.pow(
                            (estacionDevolucion.getLatitud() - estacionPartida.getLatitud())*110000,2) +
                    Math.pow(
                            (estacionDevolucion.getLongitud() - estacionPartida.getLongitud())*110000,2)
                    )/1000);
        double montoKms = tarifa.getMontoKM() * distancia;

        double montoTotal = tarifa.getMontoFijoAlquiler() + montoKms;
        Duration duracion = Duration.between(alquiler.getFechaHoraRetiro(),fechaDevolucion);
        if(duracion.toMinutes() > 30) {
            long minutos = duracion.toMinutes();
            long horasRedondeadas = minutos % 60 > 30 ? (minutos / 60) + 1 : minutos / 60;
            montoTotal += (horasRedondeadas)*tarifa.getMontoHora();
        } else {
            montoTotal += duracion.toMinutes()*tarifa.getMontoMinutoFraccion();
        }
        alquiler.setMonto(montoTotal);
        if (moneda == null) moneda = "ARS";
        if(!Objects.equals(moneda, "ARS")) {
            ConversorDivisasRestTemplate conversorDivisasRestTemplate = new ConversorDivisasRestTemplate();

            ConversorDivisaResponse resp = conversorDivisasRestTemplate.getMonedaConvertida(montoTotal, moneda);
            if (resp == null) throw new IllegalArgumentException("Moneda no soportada");
            moneda = resp.getMoneda();
            montoTotal = resp.getImporte();
        }

        alquiler.setTarifa(tarifa.getId());
        alquiler.setEstacionDevolucion(estacionDevolucion.getId());
        alquiler.setFechaHoraDevolucion(fechaDevolucion);
        alquiler.setEstado(2);

        alquilerRepository.save(alquiler);

        return new FinalizarAlquilerDTO(estacionPartida.getNombre(), estacionDevolucion.getNombre(), duracion.toMinutes(), montoTotal, distancia, moneda);
    }

}