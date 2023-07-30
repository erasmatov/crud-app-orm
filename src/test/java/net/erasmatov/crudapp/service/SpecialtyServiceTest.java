package net.erasmatov.crudapp.service;

import net.erasmatov.crudapp.model.Specialty;
import net.erasmatov.crudapp.model.Status;
import net.erasmatov.crudapp.repository.hibernate.HibernateSpecialtyRepositoryImpl;
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
class SpecialtyServiceTest {
    @InjectMocks
    SpecialtyService specialtyService;
    @Mock
    HibernateSpecialtyRepositoryImpl specialtyRepository;

    List<Specialty> specialtyList;
    Specialty specialty1;
    Specialty specialty2;

    @BeforeEach
    void setUp() {
        specialty1 = new Specialty(1, "Java", Status.ACTIVE);
        specialty2 = new Specialty(2, "Javascript", Status.ACTIVE);

        specialtyList = List.of(specialty1, specialty2);
    }

    @Test
    void getSpecialties() {
        when(specialtyRepository.getAll()).thenReturn(List.of(specialty1, specialty2));

        List<Specialty> resultSpecList = specialtyService.getSpecialties();
        assertIterableEquals(specialtyList, resultSpecList,
                "list of specialties is not equals");
    }

    @Test
    void createSpecialty() {
        when(specialtyRepository.save(specialty1)).thenReturn(specialty1);

        Specialty resultSpecialty = specialtyService.createSpecialty(specialty1);
        assertSame(specialty1, resultSpecialty,
                "saved specialty is not same");
    }

    @Test
    void getSpecialtyById() {
        when(specialtyRepository.getById(isA(Integer.class))).thenAnswer(invocation -> {
            switch ((Integer) invocation.getArgument(0)) {
                case 1:
                    return specialty1;
                case 2:
                    return specialty2;
                default:
                    return null;
            }
        });
        Specialty resultSpecialty = specialtyService.getSpecialtyById(2);

        ArgumentCaptor<Integer> requestCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(specialtyRepository, times(1)).getById(requestCaptor.capture());

        assertEquals(resultSpecialty.getId(), requestCaptor.getValue());
        assertSame(specialty2, resultSpecialty, "received specialty is not same");
    }

    @Test
    void updateSpecialty() {
        when(specialtyRepository.update(any(Specialty.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, Specialty.class));
        specialtyService.updateSpecialty(specialty1);
        verify(specialtyRepository, times(1)).update(any(Specialty.class));
    }

    @Test
    void deleteSpecialtyById() {
        doNothing().when(specialtyRepository).deleteById(isA(Integer.class));
        specialtyService.deleteSpecialtyById(2);
        verify(specialtyRepository, times(1)).deleteById(isA(Integer.class));
    }

}