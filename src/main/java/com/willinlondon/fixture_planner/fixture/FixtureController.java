package com.willinlondon.fixture_planner.fixture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FixtureController {

    private final FixtureRepository fixtureRepository;

    public FixtureController(FixtureRepository fixtureRepository) {
        this.fixtureRepository = fixtureRepository;
    }

    @GetMapping("/fixtures")
    public List<Fixture> getFixtures() {
        return fixtureRepository.findAll();
    }

    @PostMapping("/fixtures")
    public ResponseEntity<Fixture> addFixture(@RequestBody Fixture fixture) {
        fixtureRepository.save(fixture);
        return ResponseEntity.status(HttpStatus.CREATED).body(fixture);
    }
}
