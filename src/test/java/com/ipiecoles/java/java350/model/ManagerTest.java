package com.ipiecoles.java.java350.model;

import com.ipiecoles.java.java350.repository.TechnicienRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.assertj.core.api.Assertions;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import com.ipiecoles.java.java350.model.Technicien;
import org.junit.runner.JUnitCore;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RunWith(value = Parameterized.class)
public class ManagerTest {

    @Parameter(value = 0)
    public Double salaireBase;
    @Parameter(value = 1)
    public Integer nbTechInEquipe;
    @Parameter(value = 2)
    public Double excpectedSalarie;
    @Parameter(value = 3)
    public Double pourcentage;
    @Parameter(value = 4)
    public  Double expectedSalaireTech;

    @Parameterized.Parameters(name = "test salaire {0} avec {1} dans l'équipe")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2000d, 2, 2140d, 0.07, 1763d},
                {2000d, 3, 2120d, 0.06, 1748d}
        });
    }

    @Test
    public void testSetSalaire() {
        //Given (Manager et son equipe)
        Manager manager = new Manager();
        HashSet<Technicien> equipe = AddTechInEquipe();
        manager.setEquipe(equipe);

        //When
        manager.setSalaire(salaireBase);
        Double salaireFinal = manager.getSalaire();

        //Then
        Assertions.assertThat(manager.getSalaire()).isEqualTo(excpectedSalarie);
        Assertions.assertThat(manager.getSalaire()).isNotNegative();
        Assertions.assertThat(manager.getSalaire()).isGreaterThanOrEqualTo(salaireBase);
    }

    @Test
    public void testAugmenterSalaire(){

        //Given
        Manager m1 = new Manager("nom", "prenom", "matr", null, salaireBase, AddTechInEquipe());

        //When
        m1.augmenterSalaire(pourcentage);

        //Then
        Assertions.assertThat(m1.getSalaire()).isNotNegative();
        Assertions.assertThat(m1.getSalaire()).isEqualTo(excpectedSalarie);
        for(Technicien t : m1.getEquipe()){
            Assertions.assertThat(t.getSalaire()).isNotNegative();
            Assertions.assertThat(t.getSalaire()).isGreaterThan(expectedSalaireTech);
        }
    }

    public HashSet<Technicien> AddTechInEquipe() {
        HashSet<Technicien> equipe = new HashSet<>();
        Integer i;
        i = 0;
        while (i < nbTechInEquipe) {
            Technicien t = new Technicien();
            t.setGrade(1);
            equipe.add(t);
            t.setSalaire(1500d);
            i++;
        }
        return equipe;
    }
}