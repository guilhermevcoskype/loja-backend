package com.gui.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
     * @ManyToOne(cascade=CascadeType.PERSIST) private Usuario usuario;
     */

    private String itens;

    private String uuid;

    private BigDecimal total;

    @PrePersist
    public void createUUID() {
        this.uuid = UUID.randomUUID().toString();
    }

}