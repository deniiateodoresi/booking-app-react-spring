package codemart.projects.accommodations_api.domain;

import java.util.List;

public class Accommodation {
    private String name;
    private String description;
    private AccommodationTown town;
    private Double price;
    private AccommodationType type;
    private int review;
    private List<String> remarks;

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