package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.models.Role;
import com.example.sports_participant_be_main.repositories.OwnerRepo;
import com.example.sports_participant_be_main.services.OwnerService;
import com.example.sports_participant_be_main.services.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/health") // http://localhost:8080/health
public class HealthCheckController {

    private final RoleService roleService;
    private final OwnerRepo ownerRepo;

    @GetMapping
    public String health() {
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


        return "alive";
    }


}
