package com.marcfearby.sfgpetclinic.repositories;

import com.marcfearby.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
