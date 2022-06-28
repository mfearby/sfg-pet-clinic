package com.marcfearby.sfgpetclinic.bootstrap;

import com.marcfearby.sfgpetclinic.model.Owner;
import com.marcfearby.sfgpetclinic.model.PetType;
import com.marcfearby.sfgpetclinic.model.Vet;
import com.marcfearby.sfgpetclinic.services.OwnerService;
import com.marcfearby.sfgpetclinic.services.PetTypeService;
import com.marcfearby.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCat = petTypeService.save(cat);
        System.out.println("Added cat with ID " + savedCat.getId());

        Owner owner1 = new Owner();
        owner1.setFirstName("Marc");
        owner1.setLastName("Fearby");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Homer");
        owner2.setLastName("Simpson");
        ownerService.save(owner2);

        System.out.println("Owners loaded.");

        Vet vet1 = new Vet();
        vet1.setFirstName("Billy");
        vet1.setLastName("Robertson");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Samuel");
        vet2.setLastName("Sampson");
        vetService.save(vet2);

        System.out.println("Vets loaded.");

    }
}
