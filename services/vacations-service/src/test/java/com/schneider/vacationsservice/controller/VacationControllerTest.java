package com.schneider.vacationsservice.controller;

import com.schneider.vacationsservice.VacationsServiceApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = VacationsServiceApplication.class)
@ExtendWith(MockitoExtension.class)
class VacationControllerTest {



    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;
//    Gson gson = new Gson();

    @InjectMocks
    @Autowired
    private VacationController vacationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void testGetVacation() throws Exception {
        mockMvc.perform(get("/vacation/all").contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void testValidateStartAndDateWithInvalidDates() throws Exception {
        mockMvc.perform(get("/vacation/duration?startDate=2022-09-23&endDate=2022-09-19").contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void testValidateStartAndDateWithValidDates() throws Exception {
        mockMvc.perform(get("/vacation/duration?startDate=2022-09-18&endDate=2022-09-18").contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }
}