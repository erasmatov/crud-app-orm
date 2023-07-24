package net.erasmatov.crudapp.service;

import net.erasmatov.crudapp.model.Skill;
import net.erasmatov.crudapp.repository.SkillRepository;
import net.erasmatov.crudapp.repository.hibernate.HibernateSkillRepositoryImpl;

import java.util.List;


public class SkillService {
    private final SkillRepository skillRepository = new HibernateSkillRepositoryImpl();

    public List<Skill> getSkills() {
        return skillRepository.getAll();
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill getSkillById(Integer id) {
        return skillRepository.getById(id);
    }

    public Skill updateSkill(Skill skill) {
        return skillRepository.update(skill);
    }

    public void deleteSkillById(Integer id) {
        skillRepository.deleteById(id);
    }

}
