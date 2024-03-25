package br.com.gabriel.tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestsApplicationTests {

	@DisplayName("Verificando se são igauis")
	@Test
	void contextLoads() {

		var names = List.of("Gabriel", null, "José")
			.stream().map(TestsApplicationTests::getExecutable);

		assertAll(names);
		
	}

	public static Executable getExecutable(String value) {
		return () -> {
			assertNotNull(value, () -> "Não pode ser nulo");
		};
	}

}
