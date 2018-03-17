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
    public Double salaryBase;
    @Parameter(value = 1)
    public Integer nbTechInTeam;
    @Parameter(value = 2)
    public Double expectedSalaryManagerAccordingToNbTech;
    @Parameter(value = 3)
    public Double percentage;
    @Parameter(value = 4)
    public Double expectedSalaryManWithPercentageApplied;
    @Parameter(value = 5)
    public Double expectedSalaryTechWithPercentageApplied;

    @Parameterized.Parameters(name = "test salaire {0} avec {1} dans l'équipe {2}.")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2000d, 0, 2600d, 0.07, 2140d, 1.605d},
                {2000d, 2, 3000d, 0.04, 2080d, 1.560d},
                {2000d, 3, 3200d, 0.06, 2120d, 1.590d}
        });
    }

    @Test
    public void testSetSalaire() {

        //Given (Manager et son equipe)
        HashSet<Technicien> equipe = AddTechInTeam();
        Manager manager = new Manager("Stalone", "Sylvester", "TP524", null, salaryBase, equipe);
        manager.setEquipe(equipe);

        //When
        manager.setSalaire(salaryBase);
        Double finalSalary = manager.getSalaire();

        //Then
        Assertions.assertThat(finalSalary).isNotNegative();
        if (nbTechInTeam == 0) {
            Assertions.assertThat(finalSalary).isEqualTo(2600d);
        } else {
            Assertions.assertThat(finalSalary).isGreaterThan(2600d);
        }
        Assertions.assertThat(finalSalary).isEqualTo(expectedSalaryManagerAccordingToNbTech);
        Assertions.assertThat(finalSalary).isGreaterThanOrEqualTo(salaryBase);
    }

    @Test
    public void testGetPrimeAnnuelle(){
        //Given
        Manager manager = new Manager();

        //When
        Double prime = manager.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isNotNegative();
        Assertions.assertThat(salaryBase + prime).isGreaterThan(salaryBase);
    }

    @Test
    public void testAugmenterSalaire(){

        //Given
        Manager m1 = new Manager("John", "prenom", "matr", null, salaryBase, AddTechInTeam());

        //When
        m1.augmenterSalaire(percentage);
        Double finalSalary = m1.getSalaire();

        //Then
        Assertions.assertThat(finalSalary).isNotNegative();
        Assertions.assertThat(finalSalary).isEqualTo(expectedSalaryManWithPercentageApplied);
        for(Technicien t : m1.getEquipe()){
            Assertions.assertThat(t.getSalaire()).isNotNegative();
            Assertions.assertThat(t.getSalaire()).isGreaterThan(expectedSalaryTechWithPercentageApplied);
        }
    }

    public HashSet<Technicien> AddTechInTeam() {
        HashSet<Technicien> equipe = new HashSet<>();
        Integer i;
        i = 0;
        while (i < nbTechInTeam) {
            Technicien t = new Technicien();
            t.setGrade(i);
            equipe.add(t);
            t.setSalaire(1500d);
            i++;
        }
        return equipe;
    }
}