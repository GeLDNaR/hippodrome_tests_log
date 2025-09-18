import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;


public class MainTest {

    @Disabled("Отключил по условию задачи")
    @Test
    @Timeout(value=22, unit = TimeUnit.SECONDS)
    void checkMainCompleteInTime() throws Exception {
        Main.main(new String[]{});
    }
}
