package com.attornatus.people.service;

import com.attornatus.people.dto.AddressDTO;
import com.attornatus.people.entity.Address;
import com.attornatus.people.entity.Person;
import com.attornatus.people.repository.AddressRepository;
import com.attornatus.people.repository.PersonRepository;
import com.attornatus.people.service.exceptions.EntityNotFoundException;
import com.attornatus.people.util.Mapper.AddressMapper;
import com.attornatus.people.util.Mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private AddressMapper addressMapper;


    public AddressDTO createAndUpdatedAddressForPerson(Long personId, AddressDTO addressDTO) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            Address address = addressMapper.convertToEntity(addressDTO);
            address.setPerson(person.get());
            address = addressRepository.save(address);
            return addressMapper.convertToDTO(address);
        } else {
            throw new EntityNotFoundException("Person not found for id " + personId);
        }
    }

    public List<AddressDTO> getAddressesByPersonId(Long personId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            List<Address> addresses = addressRepository.findByPerson(person.get());
            return addresses.stream().map(addressMapper::convertToDTO).collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("Person not found for id " + personId);
        }
    }

    public AddressDTO setPrimaryAddress(Long personId, Long addressId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            List<Address> addresses = person.get().getAddresses();
            for (Address address : addresses) {
                if (address.getId().equals(addressId)) {
                    for (Address a : addresses) {
                        a.setPrimaryAddress(false);
                    }
                    address.setPrimaryAddress(true);
                    Address savedAddress = addressRepository.save(address);
                    return addressMapper.convertToDTO(savedAddress);
                }
            }
            throw new EntityNotFoundException("Address not found for id " + addressId);
        } else {
            throw new EntityNotFoundException("Person not found for id " + personId);
        }
    }


}

