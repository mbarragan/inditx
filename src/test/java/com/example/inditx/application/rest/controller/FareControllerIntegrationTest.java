package com.example.inditx.application.rest.controller;

import com.example.inditx.domain.model.FareModel;
import com.example.inditx.domain.service.FareService;
import com.example.inditx.util.Constants;
import com.example.inditx.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FareController.class)
class FareControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FareService fareService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getFareTest_return_OK() throws Exception {

        FareModel fareModelMock = new FareModel(null, Constants.PRODUCT_ID_MOCK,
            Constants.FARE_DATETIME_MOCK_2, null, Constants.BRAND_ID_MOCK, null);
        Mockito.when(fareService.getFareByProductAndBrand(ArgumentMatchers.any(FareModel.class))).thenReturn(Optional.of(fareModelMock));

        mvc.perform(post("/api/v1/fare").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fareModelMock)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId", equalTo(Constants.PRODUCT_ID_MOCK)))
            .andExpect(jsonPath("$.brandId", equalTo(Constants.BRAND_ID_MOCK)))
            .andExpect(jsonPath("$.startDate", equalTo(Util.withSeconds(Constants.FARE_DATETIME_MOCK_2))))
            .andReturn();
    }

    @Test
    void getFareTest_return_notFound() throws Exception {

        FareModel fareModelMock = Util.getFareModelMock();
        Mockito.when(fareService.getFareByProductAndBrand(ArgumentMatchers.any(FareModel.class))).thenReturn(Optional.empty());

        mvc.perform(post("/api/v1/fare").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fareModelMock)))
            .andExpect(status().isNotFound());

    }

}
