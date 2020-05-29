package by.bsu.famcs.trucking.back.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class TransportationBack {
    @Id
    public String id;

    String carrierId;

    List<String> route;
    String currentLocation;
    List<String> cargoes;

    public TransportationBack() { }

    public TransportationBack(String carrierId, List<String> route, String currentLocation, List<String> cargoes) {
        this.carrierId = carrierId;
        this.route = route;
        this.currentLocation = currentLocation;
        this.cargoes = cargoes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<String> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<String> cargoes) {
        this.cargoes = cargoes;
    }

    @Override
    public String toString() {
        return "TransportationBack{" +
                "id='" + id + '\'' +
                ", carrierId='" + carrierId + '\'' +
                ", route=" + route +
                ", currentLocation='" + currentLocation + '\'' +
                ", cargoes=" + cargoes +
                '}';
    }
}
