package com.attornatus.people.repository;

import com.attornatus.people.entity.Address;
import com.attornatus.people.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByPerson(Person person);
}
