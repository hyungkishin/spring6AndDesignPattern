package hyungkispring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
public class PaymentTest {

    @Autowired
    private ExRateProvider exRateProvider;

    @Autowired
    private Clock clock;


    @Test
    void createPrepared() throws IOException {
        Payment payment = Payment.createPrepared(
                1L,
                "USD",
                BigDecimal.TEN,
                exRateProvider,
                clock
        );

        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(this.clock).plusMinutes(30));
    }

    @Test
    void isValid() throws IOException {
        Payment payment = Payment.createPrepared(
                1L,
                "USD",
                BigDecimal.TEN,
                exRateProvider,
                clock
        );

        assertThat(payment.isValid(clock)).isTrue();
        assertThat(
                payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES)))).isFalse();
    }

}
