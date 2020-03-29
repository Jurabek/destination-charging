package io.hubject.destination.charging.mappers;

import io.hubject.destination.charging.dtos.ChargerInfoDto;
import io.hubject.destination.charging.dtos.LocationDto;
import io.hubject.destination.charging.model.ChargerInfoEntity;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoMapperIml implements EntityToDtoMapper {
    @Override
    public LocationDto fromGeoJsonPoint(GeoJsonPoint point) {
        return new LocationDto(point.getX(), point.getY());
    }

    @Override
    public ChargerInfoDto fromChargerInfoEntity(ChargerInfoEntity entity) {
        return new ChargerInfoDto(entity.getId(), entity.getPostalCode(), fromGeoJsonPoint(entity.getLocation()));
    }
}
