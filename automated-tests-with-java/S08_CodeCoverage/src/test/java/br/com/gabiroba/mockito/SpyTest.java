package br.com.gabiroba.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SpyTest {

    @Test
    void testV1() {
        // Given / Arrange
        List<String> mockArrayList = spy(ArrayList.class);
        
        // When / Act & Then / Assert
        assertEquals(0, mockArrayList.size());
        
        when(mockArrayList.size()).thenReturn(5);
        mockArrayList.add("Foo-Bar");
        
        assertEquals(5, mockArrayList.size());
    }
    
    @Test
    void testV2() {

        /**
         * Um objeto spy é uma instância real de um objeto no qual você pode rastrear chamadas de métodos reais, enquanto ainda pode especificar comportamentos para alguns métodos, se necessário.
Ao contrário de um mock, um spy mantém o estado interno do objeto real. Os métodos reais são executados, a menos que sejam especificamente substituídos por comportamentos simulados.
         */
        // Given / Arrange
        List<String> spyArrayList = spy(ArrayList.class);
        
        // When / Act & Then / Assert
        assertEquals(0, spyArrayList.size());
        
        spyArrayList.add("Foo-Bar");
        assertEquals(1, spyArrayList.size());
        
        spyArrayList.remove("Foo-Bar");
        assertEquals(0, spyArrayList.size());
    }

    @Test
    void testV3() {
        List<String> spyArrayList = spy(ArrayList.class);
        assertEquals(0, spyArrayList.size());
        when(spyArrayList.size()).thenReturn(5);
        assertEquals(5, spyArrayList.size());
    }
    
    @Test
    void testV4() {
        List<String> spyArrayList = spy(ArrayList.class);
        spyArrayList.add("Foo-Bar");
        
        verify(spyArrayList).add("Foo-Bar");
        // verify(spyArrayList, never()).remove("Foo-Bar");
        verify(spyArrayList, never()).remove(anyString());
        verify(spyArrayList, never()).clear();
    }
}
