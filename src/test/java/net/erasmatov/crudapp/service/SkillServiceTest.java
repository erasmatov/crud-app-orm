package net.erasmatov.crudapp.service;

import net.erasmatov.crudapp.model.Skill;
import net.erasmatov.crudapp.model.Status;
import net.erasmatov.crudapp.repository.hibernate.HibernateSkillRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {
    @InjectMocks
    private SkillService skillService;
    @Mock
    HibernateSkillRepositoryImpl skillRepository;

    private List<Skill> skillList;
    private Skill skill1;
    private Skill skill2;

    @BeforeEach
    void setUp() {
        skill1 = new Skill(1, "Smart", Status.ACTIVE);
        skill2 = new Skill(2, "Fast", Status.ACTIVE);

        skillList = List.of(skill1, skill2);
    }

    @Test
    void getSkills() {
        when(skillRepository.getAll()).thenReturn(List.of(skill1, skill2));

        List<Skill> resultSkillList = skillService.getSkills();
        assertIterableEquals(skillList, resultSkillList,
                "list of skills is not equals");
    }

    @Test
    void createSkill() {
        when(skillRepository.save(skill1)).thenReturn(skill1);

        Skill resultSkill = skillService.createSkill(skill1);
        assertSame(skill1, resultSkill,
                "saved skill is not same");
    }

    @Test
    void getSkillById() {
        when(skillRepository.getById(isA(Integer.class))).thenAnswer(invocation -> {
            switch ((Integer) invocation.getArgument(0)) {
                case 1:
                    return skill1;
                case 2:
                    return skill2;
                default:
                    return null;
            }
        });
        Skill resultSkill = skillService.getSkillById(2);

        ArgumentCaptor<Integer> requestCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(skillRepository, times(1)).getById(requestCaptor.capture());

        assertEquals(resultSkill.getId(), requestCaptor.getValue());
        assertSame(skill2, resultSkill, "received skill is not same");
    }

    @Test
    void updateSkill() {
        when(skillRepository.update(any(Skill.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, Skill.class));
        skillService.updateSkill(skill1);
        verify(skillRepository, times(1)).update(any(Skill.class));
    }

    @Test
    void deleteSkillById() {
        doNothing().when(skillRepository).deleteById(isA(Integer.class));
        skillService.deleteSkillById(2);
        verify(skillRepository, times(1)).deleteById(isA(Integer.class));
    }

}