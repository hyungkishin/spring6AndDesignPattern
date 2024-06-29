package hyungkispring.hellospring.learningTest;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class ClockTest {

    @Test
    void clockTest() {
        Clock clock = Clock.systemDefaultZone();

        LocalDateTime d1 = LocalDateTime.now(clock);
        LocalDateTime d2 = LocalDateTime.now(clock);

        Assertions.assertThat(d2).isAfter(d1);
    }

    @Test
    void clockFixedTest() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime d1 = LocalDateTime.now(clock);
        LocalDateTime d2 = LocalDateTime.now(clock);
        LocalDateTime d3 = LocalDateTime.now(clock).plusHours(1);

        Assertions.assertThat(d2).isEqualTo(d1);
        Assertions.assertThat(d3).isEqualTo(d1.plusHours(1));
    }

}
