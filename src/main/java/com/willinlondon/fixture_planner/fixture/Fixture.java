package com.willinlondon.fixture_planner.fixture;

import java.time.LocalDateTime;

public class Fixture{

    private final String homeTeam;
    private final String awayTeam;
    private final String competition;
    private final LocalDateTime kickoff;

    public Fixture(String homeTeam, String awayTeam, String competition, LocalDateTime kickoff) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.competition = competition;
        this.kickoff = kickoff;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getCompetition() {
        return competition;
    }

    public LocalDateTime getKickoff() {
        return kickoff;
    }
}
