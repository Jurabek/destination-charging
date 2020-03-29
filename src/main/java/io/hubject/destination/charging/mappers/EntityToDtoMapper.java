package io.hubject.destination.charging.mappers;

import io.hubject.destination.charging.dtos.ChargerInfoDto;
import io.hubject.destination.charging.dtos.LocationDto;
import io.hubject.destination.charging.model.ChargerInfoEntity;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public interface EntityToDtoMapper {
    LocationDto fromGeoJsonPoint(GeoJsonPoint point);
    ChargerInfoDto fromChargerInfoEntity(ChargerInfoEntity entity);
}
