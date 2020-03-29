package io.hubject.destination.charging.services;

import io.hubject.destination.charging.dtos.ChargerInfoDto;

import java.util.List;

public interface ChargerInfoService {
    List<ChargerInfoDto> getByLocationWithinRadius(double latitude, double longitude, double radius);
    List<ChargerInfoDto> getByPostalCode(String postalCode);
    ChargerInfoDto getById(String id);
}
