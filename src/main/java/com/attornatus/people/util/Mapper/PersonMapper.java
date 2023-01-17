package com.attornatus.people.util.Mapper;

import com.attornatus.people.dto.AddressDTO;
import com.attornatus.people.dto.PersonDTO;
import com.attornatus.people.dto.UserDTO;
import com.attornatus.people.entity.Address;
import com.attornatus.people.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    @Autowired
    private AddressMapper addressMapper;

    public Person convertToEntity(PersonDTO personDTO) {
        if (personDTO == null) {
            return null;
        }
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setNome(personDTO.getName());
        person.setDateBirth(personDTO.getDateBirth());
        List<Address> addresses = personDTO.getAddresses().stream().map(addressMapper::convertToEntity).collect(Collectors.toList());
        person.setAddresses(addresses);
        return person;
    }

    public Person convertToEntity(UserDTO user) {
        if (user == null) {
            return null;
        }
        Person person = new Person();
        person.setNome(user.getName());
        person.setDateBirth(user.getDateBirth());
        List<Address> addresses = user.getAddresses().stream().map(addressMapper::convertToEntity).collect(Collectors.toList());
        person.setAddresses(addresses);
        return person;
    }

    public PersonDTO convertToDTO(Person person) {
        if (person == null) {
            return null;
        }
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getNome());
        personDTO.setDateBirth(person.getDateBirth());
        List<AddressDTO> addressesDTO = person.getAddresses().stream().map(addressMapper::convertToDTO).collect(Collectors.toList());
        personDTO.setAddresses(addressesDTO);
        return personDTO;
    }

    public List<PersonDTO> convertToDTO(List<Person> people) {
        return people.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
