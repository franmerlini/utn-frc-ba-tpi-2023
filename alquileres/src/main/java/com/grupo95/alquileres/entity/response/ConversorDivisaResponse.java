package com.grupo95.alquileres.entity.response;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ConversorDivisaResponse {
    final private String moneda;
    final private Double monto;


}
