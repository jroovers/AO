package com.github.jroovers.ao.parsers.tinker.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jroovers.ao.model.entities.Weapon;
import com.github.jroovers.ao.parsers.tinker.model.DataModel;
import com.github.jroovers.ao.parsers.tinker.model.DataModelWrapper;
import com.github.jroovers.ao.parsers.tinker.model.clusters.ClusterModel;
import com.github.jroovers.ao.repository.WeaponRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TinkerParser {

    private final ConversionService converter;
    private final ObjectMapper mapper;
    private final WeaponRepository weaponRepository;

    @PostConstruct
    public void ParseFile() throws IOException {
        var out = this.mapper.readValue(new ClassPathResource("data.json").getFile(), DataModelWrapper.class);
        if (this.weaponRepository.count() == 0) {
            parseWeapons(out);
        }
    }

    private void parseWeapons(DataModelWrapper out) {
        var weapons = out.getData().getWeapons().entrySet()
                .stream()
                .map(entry -> converter.convert(entry, Weapon.class))
                .toList();
        this.weaponRepository.saveAll(weapons);
        log.info("TinkerParser persisted {} weapons", this.weaponRepository.count());
    }

    private void parseClusters(JsonParser parser, DataModel dataModel) throws IOException {
        List<ClusterModel> clusters = new ArrayList<>();
        while (parser.nextToken() != null) {
            System.out.println(parser.currentToken().toString());
        }
    }
}
