package hyungkispring.hellospring.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class Payment {

    private Long orderId;

    private String currency;

    private BigDecimal foreignCurrencyAmount;

    private BigDecimal exRate;

    private BigDecimal convertedAmount;

    private LocalDateTime validUntil;

    public Payment(Long orderId,
                   String currency,
                   BigDecimal foreignCurrencyAmount,
                   BigDecimal exRate,
                   BigDecimal convertedAmount,
                   LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    public boolean isValid(Clock clock) {
        return LocalDateTime.now(clock).isBefore(this.validUntil);
    }

    public static Payment createPrepared(Long orderId,
                                         String currency,
                                         BigDecimal foreignCurrencyAmount,
                                         BigDecimal exRate,
                                         LocalDateTime now) {
        // 외화 환산 금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        // 언제까지 유효한지 시간을 계산한다.
        LocalDateTime validUntil = now.plusMinutes(30);

        // 데이터 케리어 ( 데이터를 전달하는 오브젝트 )
        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getForeignCurrencyAmount() {
        return foreignCurrencyAmount;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "orderId=" + orderId +
                ", currency='" + currency + '\'' +
                ", foreignCurrencyAmount=" + foreignCurrencyAmount +
                ", exRate=" + exRate +
                ", convertedAmount=" + convertedAmount +
                ", validUntil=" + validUntil +
                '}';
    }

}
