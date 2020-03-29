package io.hubject.destination.charging.services;

import io.hubject.destination.charging.dtos.ChargerInfoDto;
import io.hubject.destination.charging.exceptions.ChargerNotFoundException;
import io.hubject.destination.charging.mappers.ChargerInfoMapper;
import io.hubject.destination.charging.model.ChargerInfoEntity;
import io.hubject.destination.charging.repositories.ChargerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargerInfoServiceIml implements ChargerInfoService {
    private final MongoOperations mongoOperations;
    private final ChargerInfoRepository chargerInfoRepository;
    private final ChargerInfoMapper chargerInfoMapper;

    @Autowired
    public ChargerInfoServiceIml(MongoOperations mongoOperations,
                                 ChargerInfoRepository chargerInfoRepository,
                                 ChargerInfoMapper chargerInfoMapper) {
        this.mongoOperations = mongoOperations;
        this.chargerInfoRepository = chargerInfoRepository;
        this.chargerInfoMapper = chargerInfoMapper;
    }

    @Override
    public ChargerInfoDto create(ChargerInfoDto dto) {
        ChargerInfoEntity entity = chargerInfoMapper.fromChargerInfoDto(dto);
        ChargerInfoEntity createdEntity = chargerInfoRepository.save(entity);
        return chargerInfoMapper.fromChargerInfoEntity(createdEntity);
    }

    public List<ChargerInfoDto> getByLocationWithinRadius(double latitude, double longitude, double radius) {
        Circle circle = new Circle(new Point(longitude, latitude), new Distance(radius, Metrics.KILOMETERS));
        Criteria geoCriteria = Criteria.where("location").withinSphere(circle);

        return mongoOperations.find(Query.query(geoCriteria), ChargerInfoEntity.class)
                .stream()
                .map(chargerInfoMapper::fromChargerInfoEntity)
                .collect(Collectors.toList());
    }

    public List<ChargerInfoDto> getByPostalCode(String postalCode) {
        return chargerInfoRepository.findByPostalCode(postalCode).stream()
                .map(chargerInfoMapper::fromChargerInfoEntity)
                .collect(Collectors.toList());
    }

    public ChargerInfoDto getById(String id) {
        ChargerInfoEntity chargerInfo = chargerInfoRepository.findById(id)
                .orElseThrow(() -> new ChargerNotFoundException("Charger with id: {" + id + "} not found!"));
        return chargerInfoMapper.fromChargerInfoEntity(chargerInfo);
    }
}
