package practice.projects.accommodations_api.domain;

import javax.persistence.*;
import java.util.List;
@Entity
@Table ( name = "accommodation_orm" )
@IdClass(AccommodationKey.class)
public class Accommodation {
    @Id
    private String name;
    @Column
    private String description;
    @Id
    private AccommodationTown town;
    @Column
    private Double price;
    @Enumerated( EnumType.STRING )
    private AccommodationType type;
    @Column
    private int review;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> remarks;

    public Accommodation() {}

    public Accommodation(String name, String description, AccommodationTown town, Double price, AccommodationType type, int review, List<String> remarks) {
        this.name = name;
        this.description = description;
        this.town = town;
        this.price = price;
        this.type = type;
        this.review = review;
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AccommodationTown getTown() {
        return town;
    }

    public Double getPrice() {
        return price;
    }

    public AccommodationType getType() {
        return type;
    }

    public int getReview() {
        return review;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public void setRemarks(List<String> remarks) {
        for(String r: remarks)
            this.remarks.add(r);
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", town=" + town +
                ", price=" + price +
                ", type=" + type +
                ", review=" + review +
                ", remarks=" + remarks +
                '}';
    }
}