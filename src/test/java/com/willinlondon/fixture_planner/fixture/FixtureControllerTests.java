package com.willinlondon.fixture_planner.fixture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FixtureController.class)
class FixtureControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Returns 200 OK for Fixtures List")
    void returnsOkStatusForFixturesList() throws Exception {
        mockMvc.perform(get("/fixtures"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Returns a list of Fixtures")
    void returnsListOfFixtures() throws Exception {
        mockMvc.perform(get("/fixtures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].competition").value("Premier League"));
    }
}
