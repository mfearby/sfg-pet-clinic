package com.marcfearby.sfgpetclinic.repositories;

import com.marcfearby.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
