package com.aco.beerchallenge.model;


import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "beer_temperatures")
public class BeerTemperatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

     @Column(name = "temp_unit")
     @Enumerated(EnumType.STRING)
    private TemperatureUnit unit;

    @Column(name = "temp_value")
    private Double value;

    @Column(name = "duration")
    private Long duration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = "beer_id", nullable = false)
    private BeerEntity beer;

    @Override
    public int hashCode() {
        return Objects.hash(id, unit, value);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder()
                .append("BeerEntity(")
                .append("id=").append(getId()).append(",")
                .append("unit=").append(getUnit()).append(",")
                .append("value=").append(getValue())
                .append(")");
        return stringBuilder.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TemperatureUnit getUnit() {
        return unit;
    }

    public void setUnit(TemperatureUnit unit) {
        this.unit = unit;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public BeerEntity getBeer() {
        return beer;
    }

    public void setBeer(BeerEntity beer) {
        this.beer = beer;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
