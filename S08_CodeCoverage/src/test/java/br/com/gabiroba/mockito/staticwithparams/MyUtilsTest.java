package br.com.gabiroba.mockito.staticwithparams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import br.com.gabiroba.mockito.staticwithparams.MyUtils;

class MyUtilsTest {

    @Test // Mockando método static
    void shouldMockStaticMethodWithParams() {
        try(MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)){
            mockedStatic.when(
                () -> MyUtils.getWelcomeMessage(
                    eq("Erudio"), // primeiro parametro
                    anyBoolean() // segundo parametro
                    )).thenReturn("Howdy Erudio!");
            
            String result = MyUtils.getWelcomeMessage("Erudio", false);
            
            assertEquals("Howdy Erudio!", result);
        }
    }

}
