package com.marcfearby.sfgpetclinic.services;

import com.marcfearby.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
