package com.attornatus.people.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserDTO {
    private String name;
    private LocalDate dateBirth;
    private List<AddressDTO> addresses;
}