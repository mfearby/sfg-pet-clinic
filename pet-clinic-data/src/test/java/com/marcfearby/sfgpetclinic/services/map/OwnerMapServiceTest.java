package com.marcfearby.sfgpetclinic.services.map;

import com.marcfearby.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    long ownerId = 1L;
    String lastName = "Fearby";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        Owner testOwner = Owner.builder().id(ownerId).lastName(lastName).build();
        ownerMapService.save(testOwner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long existingId = 2L;
        Owner existingOwner = Owner.builder().id(existingId).build();
        Owner saved = ownerMapService.save(existingOwner);
        assertEquals(existingId, saved.getId());
    }

    @Test
    void saveNoId() {
        Owner newOwner = Owner.builder().build();
        Owner saved = ownerMapService.save(newOwner);
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner found = ownerMapService.findByLastName(lastName);
        assertNotNull(found);
        assertEquals(ownerId, found.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner notFound = ownerMapService.findByLastName("fubar");
        assertNull(notFound);
    }
}