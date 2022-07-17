package com.marcfearby.sfgpetclinic.repositories;

import com.marcfearby.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
