package com.willinlondon.fixture_planner.fixture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FixtureController {

    final List<Fixture> fixtures = new ArrayList<>();

    @GetMapping("/fixtures")
    public List<Fixture> getFixtures() {
        return fixtures;
    }

    @PostMapping("/fixtures")
    public ResponseEntity<Fixture> addFixture(@RequestBody Fixture fixture) {
        fixtures.add(fixture);
        return ResponseEntity.status(HttpStatus.CREATED).body(fixture);
    }
}
