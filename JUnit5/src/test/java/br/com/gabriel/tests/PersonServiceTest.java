package br.com.gabriel.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public class PersonServiceTest {

    @DisplayName("When create a person with success should return a person")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {
        // Given
        IPersonService service = new PersonService();
        Person person = new Person("Keith", "Moon", "gabrielcardoo@gmail.com", "ES", "Male");

        // When
        Person actual = service.create(person);

        // Then
        assertNotNull(actual, () -> "The person cannot be null"); // Usar assim só executa quando o teste falhar a mensagem
    }


    @DisplayName("When create a person with success should return a person")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsFirstNameInReturnedPersonObject() {
        // Given
        IPersonService service = new PersonService();
        Person person = new Person("Keith", "Moon", "gabrielcardoo@gmail.com", "ES", "Male");

        // When
        Person actual = service.create(person);

        // Then
        assertEquals(actual.getFirstName(), person.getFirstName(), () -> "The person name cannot be change");
    }
    
    

}
