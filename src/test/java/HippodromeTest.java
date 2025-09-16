import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {
    @Test
    void checkHippodromeNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void checkHippodromeEmpty() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(new ArrayList<>(0));
                }
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void checkGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i=0;i<30;i++) {
            String name = Integer.toString(i);
            double speed = i/2;
            horses.add(new Horse(name, speed));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }


    @Test
    void checkMoveHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i=0;i<50;i++) {
            String name = Integer.toString(i);
            double speed = i/2;
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse:horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void checkGetWinner() {
        List<Horse> horses = new ArrayList<>();
        double distance = 50;
        for (int i=0;i<50;i++) {
            String name = Integer.toString(i);
            double speed = i/2;
            horses.add(new Horse(name, speed, distance));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winnerHorse = hippodrome.getWinner();
        assertEquals(distance, winnerHorse.getDistance());
    }
}
