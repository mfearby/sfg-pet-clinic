package com.marcfearby.sfgpetclinic.services.map;

import com.marcfearby.sfgpetclinic.model.Owner;
import com.marcfearby.sfgpetclinic.model.Pet;
import com.marcfearby.sfgpetclinic.services.OwnerService;
import com.marcfearby.sfgpetclinic.services.PetService;
import com.marcfearby.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            persistPets(object);
            return super.save(object);
        } else {
            return null;
        }
    }

    // Slightly tidier this way than in the video (lesson #124)
    private void persistPets(Owner object) {
        if (object.getPets() == null) return;

        object.getPets().forEach(pet -> {
            if (pet.getPetType() != null) {
                if (pet.getPetType().getId() == null) {
                    pet.setPetType(petTypeService.save(pet.getPetType()));
                }
            } else {
                throw new RuntimeException("PetType is required!");
            }

            if (pet.getId() == null) {
                Pet savedPet = petService.save(pet);
                pet.setId(savedPet.getId());
            }
        });
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return super.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        // TODO - later, maybe
        return null;
    }
}
