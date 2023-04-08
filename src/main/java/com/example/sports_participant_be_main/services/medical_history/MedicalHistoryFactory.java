package com.example.sports_participant_be_main.services.medical_history;

import com.example.sports_participant_be_main.constants.HealthSupplier;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Data
public class MedicalHistoryFactory {

    private final IMedicalSupplier helsi;

    public MedicalHistoryFactory(
            @Qualifier("HELSI") IMedicalSupplier helsi
    ) {
        this.helsi = helsi;
    }

    public IMedicalSupplier getMedicalHistoryService(HealthSupplier beanName) {
        if (beanName == HealthSupplier.HELSI) {
            return helsi;
        }

        throw new RuntimeException("Bean was not found");
    }
}
