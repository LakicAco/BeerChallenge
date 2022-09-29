package com.aco.beerchallenge.model.dto;


import java.util.Objects;


public class BeerDto {
    private Long internalId;
    private String name;
     private String description;
    private Double meanValueTemp;

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalId, getName(), getDescription(), meanValueTemp);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BeerDto)) {
            return false;
        }

        final BeerDto that = (BeerDto) o;
        final boolean result = Objects.equals(getName(), that.getInternalId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getMeanValueTemp(), that.getMeanValueTemp()) &&
                Objects.equals(getDescription(), that.getDescription());
        return result;
    }
    public Double getMeanValueTemp() {
        return meanValueTemp;
    }

    public void setMeanValueTemp(Double meanValueTemp) {
        this.meanValueTemp = meanValueTemp;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder()
                .append("BeerEntity(")
                .append("internal_id=").append(getInternalId()).append(",")
                .append("name=").append(getName()).append(",")
                .append("description=").append(getDescription()).append(",")
                .append("mean_value_temp=").append(getMeanValueTemp()).append(",")
                .append(")");
        return stringBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
