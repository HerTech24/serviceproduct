package com.kairoscoffee.serviceproduct.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorDTO {

    private Long id;
    private String nombre;
    private String email;
    private String telefono;
}
