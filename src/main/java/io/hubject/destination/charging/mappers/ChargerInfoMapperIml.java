package io.hubject.destination.charging.mappers;

import io.hubject.destination.charging.dtos.ChargerInfoDto;
import io.hubject.destination.charging.dtos.LocationDto;
import io.hubject.destination.charging.model.ChargerInfoEntity;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

@Component
public class ChargerInfoMapperIml implements ChargerInfoMapper {
    @Override
    public LocationDto fromGeoJsonPoint(GeoJsonPoint point) {
        return new LocationDto(point.getX(), point.getY());
    }

    @Override
    public ChargerInfoDto fromChargerInfoEntity(ChargerInfoEntity entity) {
        return new ChargerInfoDto(entity.getId(), entity.getPostalCode(), fromGeoJsonPoint(entity.getLocation()));
    }

    @Override
    public ChargerInfoEntity fromChargerInfoDto(ChargerInfoDto dto) {
        return new ChargerInfoEntity(dto.getId(), dto.getPostalCode(),
                new GeoJsonPoint(dto.getLocation().getLongitude(), dto.getLocation().getLatitude()));
    }
}
