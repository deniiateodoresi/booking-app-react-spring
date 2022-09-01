package practice.projects.accommodations_api.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccommodationKey implements Serializable {

    @Column
    private String name;

    @Enumerated( EnumType.STRING )
    private AccommodationTown town;

    public AccommodationKey(String name, AccommodationTown town) {
        this.name = name;
        this.town = town;
    }

    public AccommodationKey() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccommodationKey that = (AccommodationKey) o;
        return Objects.equals(name, that.name) && town == that.town;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, town);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccommodationTown getTown() {
        return town;
    }

    public void setTown(AccommodationTown town) {
        this.town = town;
    }
}
