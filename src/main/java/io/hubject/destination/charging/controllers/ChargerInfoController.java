package io.hubject.destination.charging.controllers;

import io.hubject.destination.charging.dtos.ChargerInfoDto;
import io.hubject.destination.charging.services.ChargerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/chargers")
public class ChargerInfoController {
    private final ChargerInfoService chargerInfoService;

    @Autowired
    public ChargerInfoController(ChargerInfoService chargerInfoService) {
        this.chargerInfoService = chargerInfoService;
    }

    @GetMapping("getByLocationWithinRadius")
    public List<ChargerInfoDto> getByRadius(@RequestParam("lat") double latitude,
                                            @RequestParam("long") double longitude,
                                            @RequestParam("radius") double radius) {
        return chargerInfoService.getByLocationWithinRadius(latitude, longitude, radius);
    }

    @GetMapping("getByPostalCode")
    public List<ChargerInfoDto> getByPostalCode(@RequestParam("postalCode") String postalCode) {
        return chargerInfoService.getByPostalCode(postalCode);
    }

    @GetMapping("/{id}")
    public ChargerInfoDto getById(@PathVariable() String id) {
        return chargerInfoService.getById(id);
    }
}
