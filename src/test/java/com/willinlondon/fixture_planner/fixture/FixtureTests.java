package com.willinlondon.fixture_planner.fixture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FixtureTests {

    @Test
    @DisplayName("Returns a Premier League fixture.")
    void canReturnFixtureAsPremierLeague() {
        String premierLeague = "Premier League";
        Fixture fixture = new Fixture(premierLeague);

        assertEquals(premierLeague, fixture.getCompetition());
    }

    @Test
    @DisplayName("Returns a La Liga fixture.")
    void canReturnFixtureAsLaLiga() {
        String laLiga = "La Liga";
        Fixture fixture = new Fixture(laLiga);

        assertEquals(laLiga, fixture.getCompetition());
    }
}
