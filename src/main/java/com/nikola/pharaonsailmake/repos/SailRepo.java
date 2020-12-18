package com.nikola.pharaonsailmake.repos;

import com.nikola.pharaonsailmake.domain.Sail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SailRepo extends CrudRepository<Sail, Integer> {

    @Query(value = "SELECT sum(Price) FROM Sail ")
    Double allCostSail();

    @Query(value = "SELECT sum(ElapsedLength) FROM Sail ")
    Double howClothUsed();

   // @Query (value = "SELECT Sail.Id FROM Sail where Sail.deliverieCloth_id.cloth_id.Type_id.Type = 'Лавировочная' ")
    //void idSailClothName();

   //SELECT sails.id FROM sails join deliverie_cloths dc on sails.deliverie_cloth_id = dc.id join cloths c on dc.cloth_id = c.id join cloths_type ct on c.type_id = ct.id where ct.type = 'Лавировочная'
}
