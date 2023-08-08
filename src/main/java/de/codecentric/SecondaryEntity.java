package de.codecentric;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SecondaryEntity {

    @Id
    private Long id;

    private String additionalData;

    public String getAdditionalData() {
        return additionalData;
    }
}
