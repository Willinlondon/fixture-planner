package com.willinlondon.fixture_planner.fixture;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Fixture{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

    private String homeTeam;
    private String awayTeam;
    private String competition;
    private LocalDateTime kickoff;

    protected Fixture() {

    }

    public Fixture(String homeTeam, String awayTeam, String competition, LocalDateTime kickoff) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.competition = competition;
        this.kickoff = kickoff;
    }

    public UUID getId() {
        return id;
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
