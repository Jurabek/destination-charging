package io.hubject.destination.charging.controllers;

import io.hubject.destination.charging.dtos.ChargerInfoDto;
import io.hubject.destination.charging.services.ChargerInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ChargerInfoControllerTest {
    @Mock
    private ChargerInfoService chargerInfoService;

    @InjectMocks
    private ChargerInfoController chargerInfoController;

    @Test
    public void given_valid_coordinates_and_radius_should_return_chargers() {
        // arrange
        double longitude = 20102.212;
        double latitude = -99328.299;
        double radius = 10;
        List<ChargerInfoDto> chargers = Collections.singletonList(new ChargerInfoDto());

        when(chargerInfoService.getByLocationWithinRadius(latitude, longitude, radius))
                .thenReturn(chargers);

        // act
        List<ChargerInfoDto> result = chargerInfoController.getByRadius(latitude, longitude, radius);

        // assert
        assertEquals(chargers, result);
    }

    @Test
    public void given_valid_postalCode_should_return_chargers() {
        // arrange
        List<ChargerInfoDto> chargers = Collections.singletonList(new ChargerInfoDto());
        when(chargerInfoService.getByPostalCode("12343")).thenReturn(chargers);

        // act
        List<ChargerInfoDto> result = chargerInfoController.getByPostalCode("12343");

        // assert
        assertEquals(chargers, result);
    }

    @Test
    public void given_valid_id_should_return_charger() {
        // arrange
        ChargerInfoDto charger = new ChargerInfoDto();
        when(chargerInfoService.getById("1773af8e-4c87-407d-9be0-cb2a5fe45f84")).thenReturn(charger);

        // act
        ChargerInfoDto result = chargerInfoController.getById("1773af8e-4c87-407d-9be0-cb2a5fe45f84");

        // assert
        assertEquals(charger, result);
    }
}
