package hyungkispring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare Method 가 요구사항을 잘 검증하는지 테스트")
    void prepare() {

        testAmount(valueOf(500), valueOf(5_000), this.clock);
        testAmount(valueOf(1_000), valueOf(10_000), this.clock);
        testAmount(valueOf(3_000), valueOf(30_000), this.clock);
    }

    @Test
    void validUntil() {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // valid until 이 prepare() 30 분 뒤로 설정됬는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율 정보를 가져온다.
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // 원화 환산 금액
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }

}