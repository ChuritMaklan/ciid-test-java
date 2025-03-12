package com.example.ciidtestspring.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "quantity_in_stock", nullable = false)
    private Integer quantityInStock;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JsonManagedReference
    @JoinTable(name = "part_category",
            joinColumns = @JoinColumn(name = "part_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;
    public Set<Category> getCategories() {
        return new HashSet<>(categories); // Returns a copy of the collection
    }

    public void setCategories(Set<Category> categories) {
        this.categories = new HashSet<>(categories); // Sets a copy of the collection
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(id, part.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
