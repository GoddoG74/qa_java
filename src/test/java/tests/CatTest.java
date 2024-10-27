package tests;

import static org.junit.Assert.*;
import com.example.Cat;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class CatTest {
    private Cat cat;
    private Feline mockFeline;

    @Before
    public void setUp() {
        mockFeline = Mockito.mock(Feline.class);
        cat = new Cat(mockFeline);
    }

    @Test
    public void testGetSound() {
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    public void testGetFood() throws Exception {
        Mockito.when(mockFeline.eatMeat()).thenReturn(List.of("Животные", "Птицы"));

        List<String> food = cat.getFood();
        assertNotNull(food);
        assertEquals(2, food.size());
        assertTrue(food.contains("Животные"));
        assertTrue(food.contains("Птицы"));
    }
}
