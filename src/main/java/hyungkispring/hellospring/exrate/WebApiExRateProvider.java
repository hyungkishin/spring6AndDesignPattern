package hyungkispring.hellospring.exrate;

import hyungkispring.hellospring.api.ApiTemplate;
import hyungkispring.hellospring.api.ErApiExRateExtractor;
import hyungkispring.hellospring.api.HttpClientApiExecutor;
import hyungkispring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {

    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor()); // 콜백
    }

}
