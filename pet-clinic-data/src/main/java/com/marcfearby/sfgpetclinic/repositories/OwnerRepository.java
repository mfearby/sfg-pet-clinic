package com.marcfearby.sfgpetclinic.repositories;

import com.marcfearby.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    // Adding @Param("lastName") fixes the issue found in videos 215 & 216
    // and means I can go back to org.springframework.boot version 2.6.7
    List<Owner> findAllByLastNameLike(@Param("lastName") String lastName);

}
