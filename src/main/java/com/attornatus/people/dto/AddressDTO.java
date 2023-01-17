package com.attornatus.people.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String publicPlace;
    private String cep;
    private String number;
    private boolean primaryAddress;
}

