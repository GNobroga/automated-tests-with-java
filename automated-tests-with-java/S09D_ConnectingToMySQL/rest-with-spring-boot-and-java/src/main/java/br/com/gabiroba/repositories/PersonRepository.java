package br.com.gabiroba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabiroba.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}