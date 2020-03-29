package io.hubject.destination.charging.model;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chargers")
public class ChargerInfoEntity {
    private String id;
    private String postalCode;
    private GeoJsonPoint location;

    public ChargerInfoEntity() {
    }

    public ChargerInfoEntity(String id, String postalCode, GeoJsonPoint location) {
        this.id = id;
        this.postalCode = postalCode;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }
}
