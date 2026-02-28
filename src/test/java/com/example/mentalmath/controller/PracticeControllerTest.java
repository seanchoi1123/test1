package com.example.mentalmath.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PracticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldProvideTwoDigitProblem() throws Exception {
        mockMvc.perform(get("/api/problem"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.left").isNumber())
                .andExpect(jsonPath("$.right").isNumber())
                .andExpect(jsonPath("$.left").value(org.hamcrest.Matchers.allOf(
                        org.hamcrest.Matchers.greaterThanOrEqualTo(10),
                        org.hamcrest.Matchers.lessThanOrEqualTo(99))))
                .andExpect(jsonPath("$.right").value(org.hamcrest.Matchers.allOf(
                        org.hamcrest.Matchers.greaterThanOrEqualTo(10),
                        org.hamcrest.Matchers.lessThanOrEqualTo(99))));
    }

    @Test
    void shouldCheckAnswer() throws Exception {
        mockMvc.perform(post("/api/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "left": 12,
                                  "right": 11,
                                  "answer": 132
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correct").value(true))
                .andExpect(jsonPath("$.expected").value(132));
    }
}
