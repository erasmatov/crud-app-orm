package net.erasmatov.crudapp.service;

import net.erasmatov.crudapp.model.Specialty;
import net.erasmatov.crudapp.repository.SpecialtyRepository;
import net.erasmatov.crudapp.repository.hibernate.HibernateSpecialtyRepositoryImpl;

import java.util.List;

public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService() {
        specialtyRepository = new HibernateSpecialtyRepositoryImpl();
    }

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public List<Specialty> getSpecialties() {
        return specialtyRepository.getAll();
    }

    public Specialty createSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    public Specialty getSpecialtyById(Integer id) {
        return specialtyRepository.getById(id);
    }

    public Specialty updateSpecialty(Specialty specialty) {
        return specialtyRepository.update(specialty);
    }

    public void deleteSpecialtyById(Integer id) {
        specialtyRepository.deleteById(id);
    }

}
