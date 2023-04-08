package com.example.sports_participant_be_main.services.medical_history.health_suppliers;

import com.example.sports_participant_be_main.constants.HealthSupplier;
import com.example.sports_participant_be_main.models.Client;
import com.example.sports_participant_be_main.models.Disability;
import com.example.sports_participant_be_main.models.MedicalCard;
import com.example.sports_participant_be_main.models.MedicalRecord;
import com.example.sports_participant_be_main.repositories.*;
import com.example.sports_participant_be_main.services.medical_history.IMedicalSupplier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("HELSI")
@AllArgsConstructor
@Slf4j
public class HelsiService implements IMedicalSupplier {

    private AllergyRepo allergyRepo;
    private DisabilityRepo disabilityRepo;
    private IllnessRepo illnessRepo;
    private MedicalCardRepo medicalCardRepo;
    private MedicalRecordRepo medicalRecordRepo;

    // todo Відкрити транзакцію (ще мб засунути в трайкетч)
    @Override
    public void save(Client client) {
        MedicalCard medicalCard = this.medicalCardRepo.save(MedicalCard
                .builder()
                .client(client)
                .healthSupplier(HealthSupplier.HELSI)
                .build()
        );

        for (int i = 1; i <= 7; i++) {
            this.medicalRecordRepo.save(MedicalRecord
                    .builder()
                    .recordNumber(i)
                    .title("Title " + i)
                    .text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec elementum nibh quis tortor consectetur, rutrum fringilla justo fringilla. Sed sagittis pulvinar enim a eleifend. Vivamus sed massa purus. Nunc ac nisi purus. Aenean elementum mollis orci vel sodales. Etiam quis molestie nulla. Proin ornare scelerisque quam, vel semper ex. Sed pulvinar nec felis at maximus")
                    .date(LocalDate.now())
                    .currentMedications(List.of("Амбробене", "Ібупрофен", "Грипго", "Німесил"))
                    .medicalCard(medicalCard)
                    .build()
            );
        }

        for (int i = 1; i <= 4; i++) {
            this.disabilityRepo.save(Disability
                    .builder()
                    .name("Disability " + i)
                    .severity(Disability.Severity.MEDIUM)
                    .note("Note " + i)
                    .onsetDate(LocalDate.now())
                    .medicalCard(medicalCard)
                    .build()
            );
        }
    }
}
