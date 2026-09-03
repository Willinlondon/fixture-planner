package com.willinlondon.fixture_planner.fixture;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

    @MockitoBean
    private FixtureRepository fixtureRepository;

    @Test
    @DisplayName("GET fixtures returns 200 OK with a list of all fixtures.")
    void returnsOkStatusForFixturesList() throws Exception {
        String homeTeam = "Arsenal";
        String awayTeam = "Man City";
        String competition = "Premier League";
        LocalDateTime kickoff = LocalDateTime.of(2026, 9, 20, 15, 30, 45);
        Fixture fixture = new Fixture(homeTeam, awayTeam, competition, kickoff);

        when(fixtureRepository.findAll()).thenReturn(List.of(fixture));

        String expectedKickoff = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(kickoff);
        String filter = String.format(
                "$[?(@.homeTeam == '%s' && @.awayTeam == '%s' && @.competition == '%s' && @.kickoff == '%s')]",
                homeTeam, awayTeam, competition, expectedKickoff
        );

        mockMvc.perform(get("/fixtures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(filter).exists());
    }

    @Test
    @DisplayName("POST fixtures returns 201 OK and returns the newly added fixture.")
    void addsFixtureCallsRepositorySave() throws Exception {
        String homeTeam = "Liverpool";
        String awayTeam = "Chelsea";
        String competition = "Premier League";
        LocalDateTime kickoff = LocalDateTime.of(2026, 9, 25, 17, 15, 30);
        Fixture newFixture = new Fixture(homeTeam, awayTeam, competition, kickoff);
        when(fixtureRepository.save(any(Fixture.class))).thenReturn(newFixture);

        String requestBody = objectMapper.writeValueAsString(newFixture);

        mockMvc.perform(post("/fixtures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());

        ArgumentCaptor<Fixture> fixtureCaptor = ArgumentCaptor.forClass(Fixture.class);
        verify(fixtureRepository).save(fixtureCaptor.capture());
        Fixture capturedFixture = fixtureCaptor.getValue();

        assertAll(
                () -> assertEquals(homeTeam, capturedFixture.getHomeTeam()),
                () -> assertEquals(awayTeam, capturedFixture.getAwayTeam()),
                () -> assertEquals(competition, capturedFixture.getCompetition()),
                () -> assertEquals(kickoff, capturedFixture.getKickoff())
        );
    }

    @Test
    @DisplayName("POST fixtures ignores a client-supplied id, always saving a fresh fixture.")
    void addFixtureIgnoresClientSuppliedId() throws Exception {
        String maliciousRequestBody = "{"
                + "\"id\": \"11111111-1111-1111-1111-111111111111\","
                + "\"homeTeam\": \"Liverpool\","
                + "\"awayTeam\": \"Chelsea\","
                + "\"competition\": \"Premier League\","
                + "\"kickoff\": \"2026-09-25T17:15:30\""
                + "}";

        when(fixtureRepository.save(any(Fixture.class)))
                .thenReturn(new Fixture("Liverpool", "Chelsea", "Premier League", LocalDateTime.now()));

        mockMvc.perform(post("/fixtures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(maliciousRequestBody))
                .andExpect(status().isCreated());

        ArgumentCaptor<Fixture> fixtureCaptor = ArgumentCaptor.forClass(Fixture.class);
        verify(fixtureRepository).save(fixtureCaptor.capture());
        Fixture capturedFixture = fixtureCaptor.getValue();

        assertNull(capturedFixture.getId());
    }
}
