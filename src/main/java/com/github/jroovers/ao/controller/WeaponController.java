package com.github.jroovers.ao.controller;

import com.github.jroovers.ao.model.entities.Weapon;
import com.github.jroovers.ao.repository.WeaponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/weapon")
@RequiredArgsConstructor
public class WeaponController {

    private final WeaponRepository weaponRepository;

    @GetMapping("")
    public List<Weapon> getAllWeapons() {
        return weaponRepository.findAll();
    }

    @GetMapping("/{aoid}")
    public Weapon getByAoid(@PathVariable Long aoid) {
        return weaponRepository.findFirstByAoid(aoid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND) {
        });
    }
}
