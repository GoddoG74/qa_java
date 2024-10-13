package tests;

import static org.junit.Assert.*;

import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.List;

public class LionTest {
    private Lion lion;
    private Feline mockFeline;

    @Before
    public void setUp() throws Exception {
        mockFeline = Mockito.mock(Feline.class);
        lion = new Lion("Самец"); // Используем "Самец" для тестирования

        // Установим mockFeline для Lion с использованием рефлексии
        Field field = Lion.class.getDeclaredField("feline");
        field.setAccessible(true);
        field.set(lion, mockFeline);
    }

    @Test
    public void testDoesHaveMane() {
        assertTrue(lion.doesHaveMane()); // Проверка, что самец имеет гриву
    }

    @Test(expected = Exception.class)
    public void testInvalidSex() throws Exception {
        new Lion("Неверный пол"); // Проверка на выбрасывание исключения
    }

    @Test
    public void testGetKittens() {
        Mockito.when(mockFeline.getKittens()).thenReturn(3);
        assertEquals(3, lion.getKittens()); // Проверка, что возвращается 3 котенка
    }

    @Test
    public void testGetFood() throws Exception {
        Mockito.when(mockFeline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы"));
        List<String> food = lion.getFood();
        assertNotNull(food); // Проверка на null
        assertEquals(2, food.size()); // Проверка размера списка
        assertTrue(food.contains("Животные")); // Проверка содержимого
        assertTrue(food.contains("Птицы")); // Проверка содержимого
    }
}

