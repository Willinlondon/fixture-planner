package com.willinlondon.fixture_planner.fixture;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FixtureController.class)
class FixtureControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FixtureController fixtureController;

    @BeforeEach
    void setUp() {
        fixtureController.fixtures.clear();
    }

    @Test
    @DisplayName("Returns 200 OK for GET Fixtures List")
    void returnsOkStatusForFixturesList() throws Exception {
        String arsenal = "Arsenal";
        String manCity = "Man City";
        String premierLeague = "Premier League";
        LocalDateTime kickoff = LocalDateTime.of(2026, 9, 20, 15, 30, 45);

        fixtureController.fixtures.add(new Fixture(arsenal, manCity, premierLeague, kickoff));

        String expectedKickoff = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(kickoff);

        String filter = String.format(
                "$[?(@.homeTeam == '%s' && @.awayTeam == '%s' && @.competition == '%s' && @.kickoff == '%s')]",
                arsenal, manCity, premierLeague, expectedKickoff
        );

        mockMvc.perform(get("/fixtures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(filter).exists());
    }

    @Test
    @DisplayName("Returns 201 OK for POST then returns it in the GET list")
    void addsFixtureThenReturnsItInList() throws Exception {
        String liverpool = "Liverpool";
        String chelsea = "Chelsea";
        String premierLeague = "Premier League";
        LocalDateTime kickoff = LocalDateTime.of(2026, 9, 20, 15, 30, 45);
        Fixture newFixture = new Fixture(liverpool, chelsea, premierLeague, kickoff);
        String requestBody = objectMapper.writeValueAsString(newFixture);

        String expectedKickoff = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(kickoff);

        String filter = String.format(
                "$[?(@.homeTeam == '%s' && @.awayTeam == '%s' && @.competition == '%s' && @.kickoff == '%s')]",
                liverpool, chelsea, premierLeague, expectedKickoff
        );

        mockMvc.perform(post("/fixtures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/fixtures"))
                .andExpect(jsonPath(filter).exists());

    }
}
