package com.attornatus.people.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_place", nullable = false)
    private String publicPlace;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "primary_address", nullable = false)
    private Boolean primaryAddress;


    @Column(name = "number", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public boolean isPrimaryAddress() {
        return primaryAddress;
    }
}