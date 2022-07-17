package com.marcfearby.sfgpetclinic.repositories;

import com.marcfearby.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
