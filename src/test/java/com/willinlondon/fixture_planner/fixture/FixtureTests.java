package com.willinlondon.fixture_planner.fixture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FixtureTests {

    @Test
    @DisplayName("Returns a Premier League fixture.")
    void canReturnFixtureAsPremierLeague() {
        String arsenal = "Arsenal";
        String manCity = "Man City";
        String premierLeague = "Premier League";
        LocalDateTime kickoff = LocalDateTime.now().plusDays(2);
        Fixture fixture = new Fixture(arsenal, manCity, premierLeague, kickoff);

        assertAll(
                () -> assertEquals(arsenal, fixture.getHomeTeam()),
                () -> assertEquals(manCity, fixture.getAwayTeam()),
                () -> assertEquals(premierLeague, fixture.getCompetition()),
                () -> assertEquals(kickoff, fixture.getKickoff())
        );
    }

    @Test
    @DisplayName("Returns a La Liga fixture.")
    void canReturnFixtureAsLaLiga() {
        String barcelona = "Barcelona";
        String realMadrid = "Real Madrid";
        String laLiga = "La Liga";
        LocalDateTime kickoff = LocalDateTime.now().plusDays(3);
        Fixture fixture = new Fixture(barcelona, realMadrid, laLiga, kickoff);

        assertAll(
                () -> assertEquals(barcelona, fixture.getHomeTeam()),
                () -> assertEquals(realMadrid, fixture.getAwayTeam()),
                () -> assertEquals(laLiga, fixture.getCompetition()),
                () -> assertEquals(kickoff, fixture.getKickoff())
        );
    }
}
