package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.ActivityPriceDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
@Table(name = "activity_prices")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class ActivityPrice extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @Column(name = "payment_status")
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private ActivityPrice.Status status;

    @Column(name = "day")
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private ActivityPrice.Day day;

    @Column(name = "price", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    @DecimalMin(value = "0.0", message = "Price cannot be less than 0.0")
    @Digits(integer = 10, fraction = 2, message = "Price can have at most 10 digits and 2 decimals")
    private Double price = 0.0;

    @Column(name = "installment_amount", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    @DecimalMin(value = "0.0", message = "Installment amount cannot be less than 0.0")
    @Digits(integer = 10, fraction = 2, message = "Installment amount can have at most 10 digits and 2 decimals")
    private Double installmentAmount = 0.0; // price / installmentPeriodNumber

    @Column(name = "installment_period_in_days", nullable = false)
    @Min(value = 0, message = "Installment period in days cannot be less than 0")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer installmentPeriodInDays = 0; // period every 7 days

    @Column(name = "installment_period_number", nullable = false)
    @Min(value = 0, message = "Installment period amount cannot be less than 0")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer installmentPeriodNumber = 0; // 4 time for 7 days = 28 days

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Activity activity;

    @AllArgsConstructor
    public enum PaymentType {
        FREE("FREE"),
        PAID("PAID"),
        INSTALLMENT("INSTALLMENT"),
        ;

        private final String value;
    }

    @AllArgsConstructor
    public enum Status {
        ACTIVE("ACTIVE"),
        FROZEN("FROZEN"),
        NOT_ENABLE("NOT_ENABLE"),
        ;

        private final String value;
    }

    @AllArgsConstructor
    public enum Day {
        MONDAY("MONDAY"),
        TUESDAY("TUESDAY"),
        WEDNESDAY("WEDNESDAY"),
        THURSDAY("THURSDAY"),
        FRIDAY("FRIDAY"),
        SATURDAY("SATURDAY"),
        SUNDAY("SUNDAY"),
        ;

        private final String value;
    }

    public ActivityPriceDto ofDto() {
        return ActivityPriceDto.builder()
                .id(this.id)
                .paymentType(this.paymentType)
                .status(this.status)
                .day(this.day)
                .price(this.price)
                .installmentAmount(this.installmentAmount)
                .installmentPeriodInDays(this.installmentPeriodInDays)
                .installmentPeriodNumber(this.installmentPeriodNumber)
                .activityId(this.activity.getId())
                .build()
                ;
    }
}
