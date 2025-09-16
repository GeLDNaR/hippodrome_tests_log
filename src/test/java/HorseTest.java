import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    void checkNameNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 1, 2);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }


    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n"})
    void checkBlank(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, 1, 2);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void checkNegativeSpeed() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Horse", -1, 1);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void checkNegativeDistance() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Horse", 1, -1);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void checkGetName() {
        Horse horse = new Horse("Horse", 1, 1);
        assertEquals("Horse", horse.getName());
    }

    @Test
    void checkGetSpeed() {
        Horse horse = new Horse("Horse", 1, 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    void checkGetDistance() {
        Horse horse = new Horse("Horse", 1, 1);
        assertEquals(1, horse.getDistance());
    }

    @Test
    void checkGetEmptyDistance() {
        Horse horse = new Horse("Horse", 1);
        assertEquals(0, horse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.9})
    void checkMove(double value) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Horse", 1, 1);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(value);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2,0.9));
            assertEquals(1+1*value, horse.getDistance());
        }
    }



}
