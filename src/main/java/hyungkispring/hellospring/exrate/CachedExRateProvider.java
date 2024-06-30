package hyungkispring.hellospring.exrate;

import hyungkispring.hellospring.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;

    private BigDecimal cachedExRate;

    private LocalDateTime cacheExpireTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        if (cachedExRate == null || cacheExpireTime.isBefore(LocalDateTime.now())) {
            cachedExRate = this.target.getExRate(currency);

            cacheExpireTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("Update cache");
            return cachedExRate;
        }

        return cachedExRate;
    }

}
