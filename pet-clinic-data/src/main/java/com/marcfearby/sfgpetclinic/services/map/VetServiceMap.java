package com.marcfearby.sfgpetclinic.services.map;

import com.marcfearby.sfgpetclinic.model.Speciality;
import com.marcfearby.sfgpetclinic.model.Vet;
import com.marcfearby.sfgpetclinic.services.SpecialityService;
import com.marcfearby.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        object.getSpecialities().forEach(speciality -> {
            if (speciality.getId() == null) {
                Speciality savedSpeciality = specialityService.save(speciality);
                speciality.setId(savedSpeciality.getId());
            }
        });

        /* Streams solution by Hayden Mercer from the Udemy Q&A for this video (#126)
        object.getSpecialities()
            .stream()
            .filter(specialty -> specialty.getId() == null)
            .forEach(specialty -> specialty.setId(specialityService.save(specialty).getId())); */

        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
