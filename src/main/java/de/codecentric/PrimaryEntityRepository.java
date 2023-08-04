package de.codecentric;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface PrimaryEntityRepository extends JpaRepository<PrimaryEntity, Long> {

    List<PrimaryEntity> findByCategory(String category);

    @Query("FROM PrimaryEntity p JOIN FETCH p.secondaryEntity s WHERE p.category=:category")
    List<PrimaryEntity> findByCategoryJoinFetch(String category);

}
