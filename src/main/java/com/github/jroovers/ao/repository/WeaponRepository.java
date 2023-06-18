package com.github.jroovers.ao.repository;

import com.github.jroovers.ao.model.entities.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {

    Optional<Weapon> findFirstByAoid(Long aoid);
}
