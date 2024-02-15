package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.StatisticsDto;
import com.example.sports_participant_be_main.models.Exercise;
import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.models.Role;
import com.example.sports_participant_be_main.repositories.OwnerRepo;
import com.example.sports_participant_be_main.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/health") // http://localhost:8080/health
public class HealthCheckController {

    private final RoleService roleService;
    private final OwnerRepo ownerRepo;
    private final AppointmentService appointmentService;
    private final ExerciseService exerciseService;
    private final StatisticsService statisticsService;

    @GetMapping
    public String health() {
//        List<LocalTime> p = appointmentService.getTimeListWithStep(15, LocalTime.MIDNIGHT, LocalTime.MIDNIGHT.minusMinutes(15));
//        p.forEach(System.out::println);

//        List<LocalTime> p = this.appointmentService.getListOfAvailableHours(LocalDate.now(), UUID.fromString("a41ed93c-6656-43a4-a230-19b4ad13cb1f"), UUID.fromString("703985d3-e6e2-4f42-b010-845b1fcc89b8"));
//        p.forEach(System.out::println);
//        Role r1 = new Role();
//        Role r2 = new Role();
//        Role r3 = new Role();
//        Role r4 = new Role();
//
//        r1.setName("OWNER");
//        r2.setName("ADMIN");
//        r3.setName("COACH");
//        r4.setName("RECEPTIONIST");
//
//        roleService.save(r1);
//        roleService.save(r2);
//        roleService.save(r3);
//        roleService.save(r4);

//        Role r = this.roleService.findByName("OWNER").get();
//        Owner o = this.ownerRepo.findOwnerByEmail("s.kytsara@gmail.com").get();
//        o.setRole(null);
////        o.removeRole(r);
//        this.ownerRepo.delete(o);

//        for (int i = 1; i <= 10; ++i) {
//            exerciseService.save(Exercise.builder()
//                    .title("Title " + 1)
//                    .text("Lorem ipsum o kurwa ==========================qklfvmlkdmvlkmflkfdmgglkml  fldf vdl ldfd df  fd ldffl ldf d f d fdsdfsdf")
//                    .disability(Exercise.Disability.ABSENCE_OF_HAND)
//                    .urls(List.of("https://www.google.com.ua/?hl=uk", "https://www.google.com.ua/?hl=uk", "https://www.google.com.ua/?hl=uk"))
//                    .build());
//        }
//
//        for (int i = 11; i <= 20; ++i) {
//            exerciseService.save(Exercise.builder()
//                    .title("Title " + 1)
//                    .text("Lorem ipsum o kurwa ==========================qklfvmlkdmvlkmflkfdmgglkml  fldf vdl ldfd df  fd ldffl ldf d f d fdsdfsdf")
//                    .disability(Exercise.Disability.ABSENCE_OF_LEG)
//                    .urls(List.of("https://www.google.com.ua/?hl=uk", "https://www.google.com.ua/?hl=uk", "https://www.google.com.ua/?hl=uk"))
//                    .build());
//        }
//
//        for (int i = 21; i <= 30; ++i) {
//            exerciseService.save(Exercise.builder()
//                    .title("Title " + 1)
//                    .text("Lorem ipsum o kurwa ==========================qklfvmlkdmvlkmflkfdmgglkml  fldf vdl ldfd df  fd ldffl ldf d f d fdsdfsdf")
//                    .disability(Exercise.Disability.NO_HANDS)
//                    .urls(List.of("https://www.google.com.ua/?hl=uk", "https://www.google.com.ua/?hl=uk", "https://www.google.com.ua/?hl=uk"))
//                    .build());
//        }
        StatisticsDto s = this.statisticsService.getStatistics(UUID.fromString("c52096a0-9ec6-4d06-b1af-63b3cccd84bb"), UUID.fromString("50b87025-6a94-47a2-b1be-0e11b68c72ff"));
        System.out.println(s);
        return "alive";
    }


}
