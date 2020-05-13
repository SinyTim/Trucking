package by.bsu.famcs.trucking.back.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class TransportationBack {
    @Id
    public String id;

    String carrier_id;

    List<String> route;
    String current_location;
    List<String> cargoes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarrier_id() {
        return carrier_id;
    }

    public void setCarrier_id(String carrier_id) {
        this.carrier_id = carrier_id;
    }

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    public String getCurrent_location() {
        return current_location;
    }

    public void setCurrent_location(String current_location) {
        this.current_location = current_location;
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
                ", carrier_id='" + carrier_id + '\'' +
                ", route=" + route +
                ", current_location='" + current_location + '\'' +
                ", cargoes=" + cargoes +
                '}';
    }
}
