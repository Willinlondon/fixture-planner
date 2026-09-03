package com.willinlondon.fixture_planner.fixture;

import java.time.LocalDateTime;

public class CreateFixtureRequest {

    private String homeTeam;
    private String awayTeam;
    private String competition;
    private LocalDateTime kickoff;

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