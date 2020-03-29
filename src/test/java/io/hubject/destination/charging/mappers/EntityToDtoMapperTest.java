package io.hubject.destination.charging.mappers;

import io.hubject.destination.charging.dtos.ChargerInfoDto;
import io.hubject.destination.charging.dtos.LocationDto;
import io.hubject.destination.charging.model.ChargerInfoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EntityToDtoMapperTest {
    @Test
    public void given_Point_should_return_location_dto() {
        // arrange
        EntityToDtoMapper mapper = new EntityToDtoMapperIml();

        // act
        LocationDto dto = mapper.fromGeoJsonPoint(new GeoJsonPoint(20200.3232, 20203.2232));

        // assert
        assertEquals(20200.3232, dto.getLongitude());
        assertEquals(20203.2232, dto.getLatitude());
    }

    @Test
    public void given_ChargerInfoEntity_should_return_dto() {
        // arrange
        EntityToDtoMapper mapper = new EntityToDtoMapperIml();
        ChargerInfoEntity chargerInfoEntity = new ChargerInfoEntity();
        String entityId = UUID.randomUUID().toString();
        chargerInfoEntity.setId(entityId);
        chargerInfoEntity.setPostalCode("12432");
        chargerInfoEntity.setLocation(new GeoJsonPoint(10022,20002));

        // act
        ChargerInfoDto dto = mapper.fromChargerInfoEntity(chargerInfoEntity);

        // assert
        assertEquals(entityId, dto.getId());
        assertEquals("12432", dto.getPostalCode());
        assertEquals(10022, dto.getLocation().getLongitude());
        assertEquals(20002, dto.getLocation().getLatitude());
    }
}
