package com.marcfearby.sfgpetclinic.bootstrap;

import com.marcfearby.sfgpetclinic.model.*;
import com.marcfearby.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().size() == 0) {
            loadData();
        }
    }

    private void loadData() {
        System.out.println("Hibernate version" + org.hibernate.Version.getVersionString());

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

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
        owner1.setAddress("1 My Street");
        owner1.setCity("Cityville");
        owner1.setTelephone("555 1234");

        // Can supposedly do this with Lombok but it just puts a null in each field.
        // TODO figure out why I get nulls with Lombok builder
        //Owner owner1 = Owner.builder().address("1 My Street").firstName("Marc").lastName("Fearby").city("Cityville").telephone("555 1234").build();

        Pet pet1 = new Pet();
        pet1.setName("Peanut");
        pet1.setPetType(cat);
        pet1.setBirthDate(LocalDate.of(2005, 1, 1));
        owner1.getPets().add(pet1);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Homer");
        owner2.setLastName("Simpson");
        owner2.setAddress("742 Evergreen Terrace");
        owner2.setCity("Springfield");
        owner2.setTelephone("555 7334");

        Pet pet2 = new Pet();
        pet2.setPetType(dog);
        pet2.setOwner(owner2);
        pet2.setName("Santa's Little Helper");
        pet2.setBirthDate(LocalDate.of(1990, 1, 1));
        owner2.getPets().add(pet2);

        ownerService.save(owner2);

        Visit pet2Visit = new Visit();
        pet2Visit.setPet(pet2);
        pet2Visit.setDate(LocalDate.now());
        pet2Visit.setDescription("Pet is sneezing");
        visitService.save(pet2Visit);

        System.out.println("Owners loaded.");

        Vet vet1 = new Vet();
        vet1.setFirstName("Billy");
        vet1.setLastName("Robertson");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Samuel");
        vet2.setLastName("Sampson");
        vet2.getSpecialities().add(savedSurgery);
        vet2.getSpecialities().add(savedDentistry);
        vetService.save(vet2);

        System.out.println("Vets loaded.");
    }
}
