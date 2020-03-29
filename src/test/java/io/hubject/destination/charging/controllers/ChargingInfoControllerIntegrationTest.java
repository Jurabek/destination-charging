package io.hubject.destination.charging.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.hubject.destination.charging.DestinationChargingApplication;
import io.hubject.destination.charging.dtos.ChargerInfoDto;
import io.hubject.destination.charging.dtos.LocationDto;
import io.hubject.destination.charging.model.ChargerInfoEntity;
import io.hubject.destination.charging.repositories.ChargerInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DestinationChargingApplication.class)
@AutoConfigureMockMvc
public class ChargingInfoControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ChargerInfoRepository chargerInfoRepository;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void given_valid_body_should_create_charger() throws Exception {
        ChargerInfoDto dto = new ChargerInfoDto("98121ca6-5e4e-4c9e-a3ac-8585da3a6c07",
                "234043", new LocationDto(20032.20032, 20022.20032));

        ChargerInfoEntity chargerInfoEntity = createTestCharger();
        given(chargerInfoRepository.save(Mockito.any())).willReturn(chargerInfoEntity);

        mvc.perform(post("/api/v1/chargers/create")
                .content(asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void given_valid_id_should_return_charger() throws Exception {

        String id = "49e893bc-7819-4cf2-92be-d1acc9112e74";
        ChargerInfoEntity chargerInfoEntity = createTestCharger();
        given(chargerInfoRepository.findById(id)).willReturn(Optional.of(chargerInfoEntity));
        String expectedBody = "{\"id\":\"49e893bc-7819-4cf2-92be-d1acc9112e74\",\"postalCode\":\"12432\",\"location\":{\"longitude\":10022.0,\"latitude\":20002.0}}";
        mvc.perform(get("/api/v1/chargers/49e893bc-7819-4cf2-92be-d1acc9112e74")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedBody));
    }

    @Test
    public void given_invalid_id_should_return_Not_Found() throws Exception {
        String id = "49e893bc-7819-4cf2-92be-d1acc9112e74";
        given(chargerInfoRepository.findById(id)).willReturn(Optional.empty());
        mvc.perform(get("/api/v1/chargers/49e893bc-7819-4cf2-92be-d1acc9112e74")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void given_valid_Postal_Code_should_return_chargers() throws Exception {
        ChargerInfoEntity chargerInfoEntity = createTestCharger();
        given(chargerInfoRepository.findByPostalCode("12432"))
                .willReturn(Collections.singletonList(chargerInfoEntity));

        mvc.perform(get("/api/v1/chargers/getByPostalCode")
                .param("postalCode", "12432")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private ChargerInfoEntity createTestCharger() {
        ChargerInfoEntity chargerInfoEntity = new ChargerInfoEntity();
        chargerInfoEntity.setId("49e893bc-7819-4cf2-92be-d1acc9112e74");
        chargerInfoEntity.setPostalCode("12432");
        chargerInfoEntity.setLocation(new GeoJsonPoint(10022, 20002));
        return chargerInfoEntity;
    }
}
