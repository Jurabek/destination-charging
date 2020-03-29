package io.hubject.destination.charging.services;

import io.hubject.destination.charging.dtos.ChargerInfoDto;
import io.hubject.destination.charging.exceptions.ChargerNotFoundException;
import io.hubject.destination.charging.mappers.EntityToDtoMapper;
import io.hubject.destination.charging.model.ChargerInfoEntity;
import io.hubject.destination.charging.repositories.ChargerInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ChargerInfoUnitTest {

    @Mock
    private MongoOperations mongoOperations;
    @Mock
    private ChargerInfoRepository repository;
    @Mock
    private EntityToDtoMapper mapper;
    @InjectMocks
    private ChargerInfoServiceIml chargerInfoService;

    @Test
    public void given_valid_coordinate_and_radius_getByLocationWithinRadius_should_return_chargers() {
        // arrange
        ChargerInfoEntity entity = new ChargerInfoEntity();
        ChargerInfoDto expectedResult = new ChargerInfoDto();
        when(mongoOperations.find(any(), any()))
                .thenReturn(Collections.singletonList(entity));
        when(mapper.fromChargerInfoEntity(entity)).thenReturn(expectedResult);

        // act
        List<ChargerInfoDto> result = chargerInfoService.getByLocationWithinRadius(100, 100, 100);

        // assert
        assertEquals(Collections.singletonList(expectedResult), result);
    }

    @Test
    public void given_valid_PostalCode_getByPostalCode_should_return_chargers() {
        // arrange
        ChargerInfoEntity entity = new ChargerInfoEntity();
        ChargerInfoDto expectedResult = new ChargerInfoDto();
        when(repository.findByPostalCode("12345")).thenReturn(Collections.singletonList(entity));
        when(mapper.fromChargerInfoEntity(entity)).thenReturn(expectedResult);

        // act
        List<ChargerInfoDto> result = chargerInfoService.getByPostalCode("12345");

        // assert
        assertEquals(Collections.singletonList(expectedResult), result);
    }

    @Test
    public void given_valid_id_getById_should_return_charger() {
        // arrange
        UUID id = UUID.randomUUID();
        ChargerInfoEntity entity = new ChargerInfoEntity();
        ChargerInfoDto expectedResult = new ChargerInfoDto();
        when(repository.findById(id.toString())).thenReturn(Optional.of(entity));
        when(mapper.fromChargerInfoEntity(entity)).thenReturn(expectedResult);

        // act
        ChargerInfoDto result = chargerInfoService.getById(id.toString());

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void given_invalid_id_getById_should_throw() {
        // arrange
        ChargerInfoEntity entity = new ChargerInfoEntity();
        ChargerInfoDto expectedResult = new ChargerInfoDto();
        when(repository.findById("invalid")).thenReturn(Optional.empty());
        when(mapper.fromChargerInfoEntity(entity)).thenReturn(expectedResult);

        // assert
        Assertions.assertThrows(ChargerNotFoundException.class, () -> {
            chargerInfoService.getById("invalid_id");
        }, "Charger with id: {invalid_id} not found!");
    }
}
