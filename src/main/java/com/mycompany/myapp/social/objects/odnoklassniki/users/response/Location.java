package com.mycompany.myapp.social.objects.odnoklassniki.users.response;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("city")
    private String city;
    @SerializedName("country")
    private String country;
    @SerializedName("countryCode")
    private String countryCode;
    @SerializedName("countryName")
    private String countryName;

    public Location() {
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (getCity() != null ? !getCity().equals(location.getCity()) : location.getCity() != null) return false;
        if (getCountry() != null ? !getCountry().equals(location.getCountry()) : location.getCountry() != null)
            return false;
        if (getCountryCode() != null ? !getCountryCode().equals(location.getCountryCode()) : location.getCountryCode() != null)
            return false;
        return getCountryName() != null ? getCountryName().equals(location.getCountryName()) : location.getCountryName() == null;
    }

    @Override
    public int hashCode() {
        int result = getCity() != null ? getCity().hashCode() : 0;
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getCountryCode() != null ? getCountryCode().hashCode() : 0);
        result = 31 * result + (getCountryName() != null ? getCountryName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
            "city='" + city + '\'' +
            ", country='" + country + '\'' +
            ", countryCode='" + countryCode + '\'' +
            ", countryName='" + countryName + '\'' +
            '}';
    }
}
