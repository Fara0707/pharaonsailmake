package com.nikola.pharaonsailmake.repos;

import com.nikola.pharaonsailmake.domain.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepo extends CrudRepository<Worker, Integer> {
   // Worker findByName(@Param("Name") String name);

}
