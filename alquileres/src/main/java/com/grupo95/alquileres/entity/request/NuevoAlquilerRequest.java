package com.grupo95.alquileres.entity.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class NuevoAlquilerRequest {
    @NotNull
    @NotEmpty
    private String cliente;
    @NotNull
    @Positive
    private Integer estacionRetiro;
}
