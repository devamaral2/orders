package br.com.fiap.msentregas.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;
import java.io.IOException;

@Data
public class GoogleMapsResponse {

    @JsonProperty("geocoded_waypoints")
    private List<GeocodedWaypoint> geocodedWaypoints;

    private List<Route> routes;
    private String status;

    // Getters and Setters

    public static class GeocodedWaypoint {
        @JsonProperty("geocoder_status")
        private String geocoderStatus;

        @JsonProperty("place_id")
        private String placeId;

        private List<String> types;

        // Getters and Setters
    }

    @Data
    public static class Route {
        private Bounds bounds;
        private String copyrights;
        private List<Leg> legs;

        // Getters and Setters
    }

    public static class Bounds {
        private Coordinate northeast;
        private Coordinate southwest;

        // Getters and Setters
    }

    public static class Coordinate {
        private double lat;
        private double lng;

        // Getters and Setters
    }

    @Data
    public static class Leg {
        private Distance distance;
        private Duration duration;
        private List<Step> steps;
    }
    @Data
    public  static class Distance {
        private String text;
        private  Integer value;
    }
    @Data
    public  static class Duration {
        private String text;
        private  Integer value;
    }

    @Data
    public static  class Step {
        private Distance distance;
        private Duration duration;
    }

    public static class TimeDetails {
        private String text;
        @JsonProperty("time_zone")
        private String timeZone;
        private long value;

        // Getters and Setters
    }
}
