package hyungkispring.hellospring;

import hyungkispring.hellospring.api.ApiTemplate;
import hyungkispring.hellospring.api.ErApiExRateExtractor;
import hyungkispring.hellospring.api.SimpleApiExecutor;
import hyungkispring.hellospring.exrate.CachedExRateProvider;
import hyungkispring.hellospring.exrate.RestTemplateExRateProvider;
import hyungkispring.hellospring.payment.ExRateProvider;
import hyungkispring.hellospring.exrate.WebApiExRateProvider;
import hyungkispring.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock () {
        return Clock.systemDefaultZone();
    }

}
