package com.nikola.pharaonsailmake.repos;

import com.nikola.pharaonsailmake.domain.DeliverieHardware;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DeliverieHardwareRepo extends CrudRepository<DeliverieHardware, Integer> {
    @Query(value = "SELECT sum(Price) FROM DeliverieHardware ")
    Double allBuyHardware();
}
