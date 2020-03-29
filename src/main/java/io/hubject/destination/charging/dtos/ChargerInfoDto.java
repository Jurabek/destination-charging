package io.hubject.destination.charging.dtos;

public class ChargerInfoDto {
    private String id;
    private String postalCode;
    private LocationDto location;

    public ChargerInfoDto() {
    }

    public ChargerInfoDto(String id, String postalCode, LocationDto location) {
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

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }
}
