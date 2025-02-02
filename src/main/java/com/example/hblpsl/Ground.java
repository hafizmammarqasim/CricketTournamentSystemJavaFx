package com.example.hblpsl;

public class Ground {
    private String groundName = "Gadafi Stadium";
    private String city;
    private String capacity;
    private String dimension;

    public Ground(String groundName, String city, String capacity, String dimension) {
        this.groundName = groundName;
        this.city = city;
        this.capacity = capacity;
        this.dimension = dimension;
    }

    public Ground() {}

    public String getGroundName() {
        return groundName;
    }

    public String getCity() {
        return city;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getDimension() {
        return dimension;
    }


    public String toString() {
        return groundName + ", " + city + ", Capacity: " + capacity;
    }
}
