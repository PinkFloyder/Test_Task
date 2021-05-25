package main.java;

import java.util.Objects;

public class Entity {

    private String name;
    private String region;
    private String district;
    private int population;
    private String foundation;

    public Entity(String name, String region, String district, int population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getFoundation() {
        return foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation='" + foundation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return population == entity.population && Objects.equals(name, entity.name) && Objects.equals(region, entity.region) && Objects.equals(district, entity.district) && Objects.equals(foundation, entity.foundation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, region, district, population, foundation);
    }
}
