package com.willinlondon.fixture_planner.fixture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class FixtureRepositoryTests {

    @Autowired
    private FixtureRepository fixtureRepository;

    @Test
    @DisplayName("Saves a fixture and finds it again by id")
    void savesAndFindsFixtureById() {
        Fixture fixture = new Fixture("Arsenal", "Man City", "Premier League",
                LocalDateTime.of(2026, 9, 20, 15, 30));

        Fixture savedFixture = fixtureRepository.save(fixture);
        Optional<Fixture> foundFixture = fixtureRepository.findById(savedFixture.getId());

        assertTrue(foundFixture.isPresent());
        assertAll(
                () -> assertEquals("Arsenal", foundFixture.get().getHomeTeam()),
                () -> assertEquals("Man City", foundFixture.get().getAwayTeam()),
                () -> assertEquals("Premier League", foundFixture.get().getCompetition())
        );
    }
}