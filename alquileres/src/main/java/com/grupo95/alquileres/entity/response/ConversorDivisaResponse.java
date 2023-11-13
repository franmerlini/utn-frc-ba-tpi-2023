package com.grupo95.alquileres.entity.response;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ConversorDivisaResponse {
     private String moneda;
     private double importe;


}
