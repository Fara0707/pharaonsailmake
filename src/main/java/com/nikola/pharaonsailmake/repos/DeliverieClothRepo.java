package com.nikola.pharaonsailmake.repos;

import com.nikola.pharaonsailmake.domain.DeliverieCloth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DeliverieClothRepo extends CrudRepository<DeliverieCloth, Integer> {
    @Query(value = "SELECT sum(Price) FROM DeliverieCloth ")
    Double allBuyCloth();
}
