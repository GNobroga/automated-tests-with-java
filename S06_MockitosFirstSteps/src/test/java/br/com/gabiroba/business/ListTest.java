package br.com.gabiroba.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ListTest {
    
    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10() {
        
        // Given / Arrange
        List<?> list = mock(List.class); // Mockando uma lista
        when(list.size()).thenReturn(10); // Quando for chamado vai retornar 10
        
        // When / Act & Then / Assert
        assertEquals(10, list.size());
        assertEquals(10, list.size());
        assertEquals(10, list.size());
    }
    
    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        
        // Given / Arrange
        List<?> list = mock(List.class); // Mockando uma lista
        when(list.size()).thenReturn(10).thenReturn(20); // Multiplo retorno, a primeira execução é 10 depois é 20.
        
        // When / Act & Then / Assert
        assertEquals(10, list.size());
        assertEquals(20, list.size());
        assertEquals(20, list.size());
    }
    
    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnErudio() {
        
        // Given / Arrange
        var list = mock(List.class);
        when(list.get(0)).thenReturn("Erudio");
        
        // When / Act & Then / Assert
        assertEquals("Erudio", list.get(0));
        assertNull(list.get(1)); // A posição 1 não foi mockada.
    }
    
    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnErudio() {
        
        // Given / Arrange
        var list = mock(List.class);
        
        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        when(list.get(anyInt())).thenReturn("Erudio"); // Pra qualquer valor inteiro el vai retornar Erudio, isso se chama casador de argumentos.
        
        // When / Act & Then / Assert
        assertEquals("Erudio", list.get(anyInt()));
        assertEquals("Erudio", list.get(100));
    }

    
    @Test
    void testMockingList_When_ThrowsAnException() {
        
        // Given / Arrange
        var list = mock(List.class);
        
        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        when(list.get(anyInt())).thenThrow(new RuntimeException("Foo Bar!!")); // Mockando uma execeção
        
        // When / Act & Then / Assert
        assertThrows(RuntimeException.class,
            () -> {
                // When / Act
                list.get(anyInt());},
            () -> "Should have throw an RuntimeException");
    }

}
