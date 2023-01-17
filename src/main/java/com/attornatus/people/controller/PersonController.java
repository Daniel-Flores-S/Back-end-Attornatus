package com.attornatus.people.controller;

import com.attornatus.people.dto.PersonDTO;
import com.attornatus.people.dto.UserDTO;
import com.attornatus.people.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
@Api(value = "Api Rest para Pessoas")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna uma pessoa")
    public ResponseEntity<PersonDTO> getById(@PathVariable Long id) {
        PersonDTO personDTO = personService.getById(id);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Cria uma nova pessoa")
    public ResponseEntity<PersonDTO> createPerson(@RequestBody UserDTO user) {
        PersonDTO savedPersonDTO = personService.createPerson(user);
        return new ResponseEntity<>(savedPersonDTO, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Retorna uma lista de pessoas")
    public List<PersonDTO> getAll() {
        List<PersonDTO> personDTOS = personService.getAll();
        return personDTOS;
    }

    @PutMapping
    @ApiOperation(value = "Atualiza uma pessoa")
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO) {
        PersonDTO updatedPersonDTO = personService.updatePerson(personDTO);
        return new ResponseEntity<>(updatedPersonDTO, HttpStatus.OK);
    }


}