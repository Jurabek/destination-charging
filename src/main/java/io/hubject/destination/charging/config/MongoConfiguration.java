package io.hubject.destination.charging.config;

import com.mongodb.client.model.Indexes;
import io.hubject.destination.charging.model.ChargerInfoEntity;
import io.hubject.destination.charging.repositories.ChargerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class MongoConfiguration {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoConfiguration(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void initIndexes() {
        mongoTemplate.getDb().getCollection("chargers")
                .createIndex(Indexes.geo2dsphere("location"));
    }

    @Bean
    CommandLineRunner commandLineRunner(ChargerInfoRepository chargerInfoRepository) {
        return strings -> {
            if (chargerInfoRepository.count() == 0) {
                ChargerInfoEntity point1 = new ChargerInfoEntity("49e893bc-7819-4cf2-92be-d1acc9112e74", "123423", new GeoJsonPoint(38.8993487, -77.0145665));
                ChargerInfoEntity point2 = new ChargerInfoEntity("c3f162ea-c8c5-4b4f-a19e-b291e59ed03e", "435443", new GeoJsonPoint(38.9024593, -77.0388266));
                ChargerInfoEntity point3 = new ChargerInfoEntity("49f13415-5890-4064-bfaf-5e70029080be", "435443", new GeoJsonPoint(38.888684, -77.0047189));
                ChargerInfoEntity point4 = new ChargerInfoEntity("9a805758-b373-425b-bd97-737e16b85066", "214232", new GeoJsonPoint(39.0391718, -76.8216182));
                ChargerInfoEntity point5 = new ChargerInfoEntity("fb10441d-c4e6-452d-8ce2-af6a6180c657", "324342", new GeoJsonPoint(38.871857, -77.056267));
                ChargerInfoEntity point6 = new ChargerInfoEntity("748f519a-5d40-4295-b210-28aaa9382a43", "433343", new GeoJsonPoint(42.360091, -71.09416));
                chargerInfoRepository.saveAll(Arrays.asList(point1, point2, point3, point4, point5, point6));
            }
        };
    }
}

