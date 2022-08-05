package com.marcfearby.sfgpetclinic.services.jpa;

import com.marcfearby.sfgpetclinic.model.Owner;
import com.marcfearby.sfgpetclinic.repositories.OwnerRepository;
import com.marcfearby.sfgpetclinic.repositories.PetRepository;
import com.marcfearby.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    public static final String LAST_NAME = "Mockington";

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    Owner testOwner;

    @BeforeEach
    void setUp() {
        testOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> found = ownerJpaService.findAll();

        assertNotNull(found);
        assertEquals(2, found.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(testOwner));
        Owner found = ownerJpaService.findById(1L);
        assertNotNull(found);
        assertEquals(LAST_NAME, found.getLastName());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner found = ownerJpaService.findById(1L);
        assertNull(found);
    }

    @Test
    void save() {
        Owner newOwner = Owner.builder().lastName(LAST_NAME).build();
        when(ownerRepository.save(any())).thenReturn(testOwner);

        Owner saved = ownerJpaService.save(newOwner);

        assertEquals(1L, saved.getId());
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        ownerJpaService.delete(testOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(anyString())).thenReturn(testOwner);
        Owner found = ownerJpaService.findByLastName(LAST_NAME);
        assertNotNull(found);
        assertEquals(LAST_NAME, found.getLastName());

    }
}