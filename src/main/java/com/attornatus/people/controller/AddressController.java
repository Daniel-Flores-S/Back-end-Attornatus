package com.attornatus.people.controller;

import com.attornatus.people.dto.AddressDTO;
import com.attornatus.people.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/address")
@Api(value = "Api Rest para Endereços")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @PostMapping("/{personId}/address")
    @ApiOperation(value = "Cria um novo endereço")
    public ResponseEntity<AddressDTO> createAddressForPerson(@PathVariable Long personId, @RequestBody AddressDTO addressDTO) {
        AddressDTO createdAddress = addressService.createAndUpdatedAddressForPerson(personId, addressDTO);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @GetMapping("/{personId}/address")
    @ApiOperation(value = "Retorna uma lista de endereços")
    public ResponseEntity<List<AddressDTO>> getAllAddressesForPerson(@PathVariable Long personId) {
        List<AddressDTO> addressDTOS = addressService.getAddressesByPersonId(personId);
        return new ResponseEntity<>(addressDTOS, HttpStatus.OK);
    }

    @PutMapping("/{personId}/{addressId}/primary")
    @ApiOperation(value = "Atualiza o endereço primário")
    public ResponseEntity<AddressDTO> setPrimaryAddress(@PathVariable Long personId, @PathVariable Long addressId) {
        AddressDTO address = addressService.setPrimaryAddress(personId, addressId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }


}