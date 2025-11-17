package com.kairoscoffee.serviceproduct.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "OFERTA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OFERTA")
    private Long id;

    @Column(name = "DESCUENTO_PORC")
    private Double descuentoPorc;

    @Column(name = "FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "FECHA_FIN")
    private LocalDate fechaFin;

    @Column(name = "ACTIVA")
    private String activa;  // 'S' o 'N'

    @OneToOne
    @JoinColumn(name = "ID_PRODUCTO")
    private Producto producto;
}