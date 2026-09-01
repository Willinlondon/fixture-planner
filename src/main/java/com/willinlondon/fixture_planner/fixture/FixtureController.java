package com.willinlondon.fixture_planner.fixture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FixtureController {

    @GetMapping("/fixtures")
    public List<Fixture> getFixtures() {
        return List.of(new Fixture("Premier League"));
    }
}
