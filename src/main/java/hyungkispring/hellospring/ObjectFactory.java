package hyungkispring.hellospring;

import hyungkispring.hellospring.exrate.CachedExRateProvider;
import hyungkispring.hellospring.payment.ExRateProvider;
import hyungkispring.hellospring.exrate.WebApiExRateProvider;
import hyungkispring.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }

    @Bean
    public Clock clock () {
        return Clock.systemDefaultZone();
    }

}
