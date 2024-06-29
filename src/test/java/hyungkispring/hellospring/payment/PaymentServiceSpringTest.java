package hyungkispring.hellospring.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ExRateProviderStub exRateProviderStub;

    @Test
    @DisplayName("prepare Method 가 요구사항을 잘 검증하는지 테스트")
    void prepare() throws IOException {
        // ExRate = 1000
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율 정보를 가져온다.
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));

        // 원화 환산 금액
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

        // ExRate = 500
        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율 정보를 가져온다.
        assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));

        // 원화 환산 금액
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
    }

}