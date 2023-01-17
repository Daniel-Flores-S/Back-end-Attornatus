package com.attornatus.people.service;

import com.attornatus.people.dto.PersonDTO;
import com.attornatus.people.dto.UserDTO;
import com.attornatus.people.entity.Address;
import com.attornatus.people.entity.Person;
import com.attornatus.people.repository.AddressRepository;
import com.attornatus.people.repository.PersonRepository;
import com.attornatus.people.service.exceptions.BadRequestException;
import com.attornatus.people.service.exceptions.DataIntegrityViolationException;
import com.attornatus.people.service.exceptions.EntityNotFoundException;
import com.attornatus.people.service.exceptions.InternalServerErrorException;
import com.attornatus.people.util.Mapper.AddressMapper;
import com.attornatus.people.util.Mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private AddressMapper addressMapper;

    public PersonDTO createPerson(UserDTO user) {
        try {
            Person person = personMapper.convertToEntity(user);

            if (person.getNome() == null || person.getNome().isEmpty()) {
                throw new BadRequestException("Nome não pode ser nulo ou vazio");
            }

            if (person.getDateBirth() == null) {
                throw new BadRequestException("Data de nascimento não pode ser nula");
            }

            if (person.getAddresses() == null || person.getAddresses().isEmpty()) {
                throw new BadRequestException("Endereço não pode ser nulo ou vazio");
            }

            Address firstAddress = person.getAddresses().get(0);
            firstAddress.setPrimaryAddress(true);

            person = personRepository.save(person);
            return personMapper.convertToDTO(person);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Invalid data for person creation");
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerErrorException("Error while creating person");
        }
    }

    public PersonDTO updatePerson(PersonDTO personDTO) {
        if (personDTO.getId() == null || personDTO.getId() == 0) {
            throw new BadRequestException("Id não pode ser nulo");
        }

        PersonDTO personDTO1 = getById(personDTO.getId());

        if (personDTO1 != null) {
            if (personDTO.getName() != null) personDTO1.setName(personDTO.getName());
            if (personDTO.getDateBirth() != null)  personDTO1.setDateBirth(personDTO.getDateBirth());
            if (personDTO.getAddresses() != null) personDTO1.setAddresses(personDTO.getAddresses());
        }

        System.out.println(personDTO1);

        Person person = personMapper.convertToEntity(personDTO1);
        if (person.getAddresses() != null && !person.getAddresses().isEmpty()) {
            for (Address address : person.getAddresses()) {
                if (address.isPrimaryAddress()) {
                    address.setPrimaryAddress(false);
                    break;
                }
            }
            Address firstAddress = person.getAddresses().get(0);
            firstAddress.setPrimaryAddress(true);
        }

        person = personRepository.save(person);
        return personMapper.convertToDTO(person);

    }




    public PersonDTO getById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found " + id));
        return personMapper.convertToDTO(person);
    }

    public List<PersonDTO> getAll() {
        List<Person> people = personRepository.findAll();
        return personMapper.convertToDTO(people);
    }

}

