package com.grupo95.alquileres.service;

import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class conversorDivisasService {
    float tasaCambioPeso= 1F;
    float tasaCambioDolar = 0.00286F;
    float tasaCambioEuro = 0.00270F;

    float tasaCambioYuan = 0.02091F;

    public Float conversor(Float monto, String divisa){
        if (Objects.equals(divisa, "USD")){
            return monto * tasaCambioDolar;
        } else if (Objects.equals(divisa, "CNY")) {
            return monto * tasaCambioYuan;
        } else if (Objects.equals(divisa, "EUR")) {
            return monto * tasaCambioEuro;
        } else if (Objects.equals(divisa, "ARS")) {
            return monto * tasaCambioPeso;
        } else {
            return monto * tasaCambioPeso;
        }


    }



}
