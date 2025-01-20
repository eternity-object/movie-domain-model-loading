package org.eternity.domainmodel.generic;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

@Embeddable
@AllArgsConstructor @NoArgsConstructor
public class Money {
    public static final Money ZERO = Money.wons(0);

    private BigDecimal fee;

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static <T> Money sum(Collection<T> bags, Function<T, Money> monetary) {
        return bags.stream().map(bag -> monetary.apply(bag)).reduce(Money.ZERO, Money::plus);
    }

    public Money plus(Money amount) {
        return new Money(this.fee.add(amount.fee));
    }

    public Money minus(Money amount) {
        return new Money(this.fee.subtract(amount.fee));
    }

    public Money times(double percent) {
        return new Money(this.fee.multiply(BigDecimal.valueOf(percent)));
    }

    public Money divide(double divisor) {
        return new Money(fee.divide(BigDecimal.valueOf(divisor)));
    }

    public Money ceil(double percent) {
        return new Money(this.fee.multiply(BigDecimal.valueOf(percent)).setScale(0, RoundingMode.CEILING));
    }

    public boolean isLessThan(Money other) {
        return fee.compareTo(other.fee) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return fee.compareTo(other.fee) >= 0;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public Long longValue() {
        return fee.longValue();
    }

    public Double doubleValue() {
        return fee.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(fee.longValue(), money.fee.longValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fee);
    }

    public String toString() {
        return fee.toString() + "원";
    }
}
