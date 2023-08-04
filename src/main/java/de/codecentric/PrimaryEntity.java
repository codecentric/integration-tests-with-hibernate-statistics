package de.codecentric;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class PrimaryEntity {

    @Id
    private Long id;
    private String category;
    @OneToOne
    private SecondaryEntity secondaryEntity;

}
