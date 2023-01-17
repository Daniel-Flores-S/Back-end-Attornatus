package com.attornatus.people.util.Mapper;

import com.attornatus.people.dto.AddressDTO;
import com.attornatus.people.entity.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    public Address convertToEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setPublicPlace(addressDTO.getPublicPlace());
        address.setCep(addressDTO.getCep());
        address.setNumber(addressDTO.getNumber());
        address.setPrimaryAddress(addressDTO.isPrimaryAddress());
        return address;
    }

    public AddressDTO convertToDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setPublicPlace(address.getPublicPlace());
        addressDTO.setCep(address.getCep());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setPrimaryAddress(address.isPrimaryAddress());
        return addressDTO;
    }

}

