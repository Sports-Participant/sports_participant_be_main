package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.ActivityPrice;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ActivityPriceDto {

    private UUID id;

    @NotNull
    private ActivityPrice.PaymentType paymentType;

    private ActivityPrice.Status status;

    @NotNull
    private ActivityPrice.Day day;

    @NotNull
    private Double price;

    @NotNull
    private Double installmentAmount;

    @NotNull
    private Integer installmentPeriodInDays;

    @NotNull
    private Integer installmentPeriodNumber;

    @NotNull
    private UUID activityId;

    public ActivityPrice ofEntity() {
        return ActivityPrice.builder()
                .id(this.id)
                .paymentType(this.paymentType)
                .status(this.status)
                .day(this.day)
                .price(this.price)
                .installmentAmount(this.installmentAmount)
                .installmentPeriodInDays(this.installmentPeriodInDays)
                .installmentPeriodNumber(this.installmentPeriodNumber)
                .build()
                ;
    }
}
