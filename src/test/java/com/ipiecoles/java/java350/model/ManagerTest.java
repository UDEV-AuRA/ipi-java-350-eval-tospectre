package com.ipiecoles.java.java350.model;


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
    public Double salaire;
    @Parameter(value = 1)
    public Integer nbequipe;
    @Parameter(value = 2)
    public Double excpectedSalarie;

    @Parameterized.Parameters(name = "test salaire {0} avec {1} dans l'équipe")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2000.0, 2, 3000.0}, {2000.0, 3, 3200.0}
        });
    }

    @Test
    public void testSetSalaire() {
        //Given (Manager et son equipe)
        Manager manager = new Manager();
        Set<Technicien> equipe = new HashSet<>();
        Integer i;
        i = 0;
        while (i < nbequipe) {
            Technicien t1 = new Technicien();
            t1.setGrade(i);
            equipe.add(t1);
            i++;
        }
        manager.setEquipe(equipe);

        //When
        manager.setSalaire(salaire);
        Double salaireFinal = manager.getSalaire();

        //Then
        Assertions.assertThat(manager.getSalaire()).isEqualTo(excpectedSalarie);
        Assertions.assertThat(manager.getSalaire()).isNotNegative();
        Assertions.assertThat(manager.getSalaire()).isGreaterThanOrEqualTo(salaire);

    }
}