package com.nikola.pharaonsailmake.repos;

import com.nikola.pharaonsailmake.domain.Sail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SailRepo extends CrudRepository<Sail, Integer> {

    @Query(value = "SELECT sum(Price) FROM Sail ")
    Double allCostSail();

    @Query(value = "SELECT sum(ElapsedLength) FROM Sail ")
    Double howClothUsed();
}
