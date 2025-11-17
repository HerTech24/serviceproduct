package com.kairoscoffee.serviceproduct.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROVEEDOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROVEEDOR")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 150)
    private String nombre;

    @Column(name = "EMAIL", length = 150)
    private String email;

    @Column(name = "TELEFONO", length = 20)
    private String telefono;
}
