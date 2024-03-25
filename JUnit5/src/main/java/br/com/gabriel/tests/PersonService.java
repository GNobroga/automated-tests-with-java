package br.com.gabriel.tests;

public class PersonService implements IPersonService {

    @Override
    public Person create(Person person) {
       return person;
    }

}
