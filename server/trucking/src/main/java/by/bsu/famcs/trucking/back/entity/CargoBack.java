package by.bsu.famcs.trucking.back.entity;

import org.springframework.data.annotation.Id;


public class CargoBack {
    @Id
    public String id;

    String ownerId;
    String name;
    double weight;
    double width;
    double height;
    double length;

    String source_location;
    String destination;

    double transportation_cost;

    String status;

    public CargoBack() {}

    public CargoBack(String ownerId,
                     String name,
                     double weight,
                     double width,
                     double height,
                     double length,
                     String source_location,
                     String destination,
                     double transportation_cost,
                     String status) {
        this.ownerId = ownerId;
        this.name = name;
        this.weight = weight;
        this.width = width;
        this.height = height;
        this.length = length;
        this.source_location = source_location;
        this.destination = destination;
        this.transportation_cost = transportation_cost;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getSource_location() {
        return source_location;
    }

    public void setSource_location(String source_location) {
        this.source_location = source_location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getTransportation_cost() {
        return transportation_cost;
    }

    public void setTransportation_cost(double transportation_cost) {
        this.transportation_cost = transportation_cost;
    }

    @Override
    public String toString() {
        return "CargoBack{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", width=" + width +
                ", height=" + height +
                ", length=" + length +
                ", source_location='" + source_location + '\'' +
                ", destination='" + destination + '\'' +
                ", transportation_cost=" + transportation_cost +
                ", status='" + status + '\'' +
                '}';
    }
}
