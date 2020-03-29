package io.hubject.destination.charging.repositories;

import io.hubject.destination.charging.model.ChargerInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargerInfoRepository extends MongoRepository<ChargerInfoEntity, String> {
    List<ChargerInfoEntity> findByPostalCode(String postalCode);
}
