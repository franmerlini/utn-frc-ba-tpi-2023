package com.grupo95.estaciones.entity.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StationDTO {
    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Latitude is mandatory")
    @Min(value = -10000, message = "Latitude is mandatory")
    private double latitude;
    @NotNull(message = "Longitude is mandatory")
    @Min(value = -10000, message = "Longitude is mandatory")
    private double longitude;
}
