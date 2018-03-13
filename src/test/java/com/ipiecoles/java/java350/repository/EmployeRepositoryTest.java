package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.MySpringApplication;
import com.ipiecoles.java.java350.model.Commercial;
import com.ipiecoles.java.java350.model.Employe;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(classes = MySpringApplication.class)
public class EmployeRepositoryTest {

    @Autowired
    private EmployeRepository employeRepository;
    Commercial pierreDurand, rachidDurand, manuelPierre;

    @Before
    public void setUp(){
        employeRepository.deleteAll();
        //Ajouter nos données de test
        //Given
        pierreDurand = new Commercial();
        pierreDurand.setNom("Durand");
        pierreDurand.setPrenom("Pierre");
        pierreDurand = employeRepository.save(pierreDurand);

        rachidDurand = new Commercial();
        rachidDurand.setNom("Durand");
        rachidDurand.setPrenom("Rachid");
        rachidDurand = employeRepository.save(rachidDurand);

        manuelPierre = new Commercial();
        manuelPierre.setNom("Pierre");
        manuelPierre.setPrenom("manuel");
        manuelPierre = employeRepository.save(manuelPierre);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCaseNomPrenom() {
        //Given

        //When
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("pierre");

        //Then
        Assertions.assertThat(employeList).isNotEmpty();
        Assertions.assertThat(employeList).hasSize(2);
        Assertions.assertThat(employeList).contains(pierreDurand);
        Assertions.assertThat(employeList).contains(manuelPierre);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCaseNom(){
        //Given

        //When
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("Durand");

        //Then
        Assertions.assertThat(employeList).isNotEmpty();
        Assertions.assertThat(employeList).hasSize(2);
        Assertions.assertThat(employeList).contains(pierreDurand);
        Assertions.assertThat(employeList).contains(rachidDurand);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCase(){
        //Given

        //When
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("RACHID");

        //Then
        Assertions.assertThat(employeList).isNotEmpty();
        Assertions.assertThat(employeList).hasSize(1);
        Assertions.assertThat(employeList).contains(rachidDurand);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCaseNotEmpty(){
        //Given

        //When
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("Valls");

        //Then
        Assertions.assertThat(employeList).isEmpty();
    }

    @Test
    public void testFindEmployePlusRiches(){

        //Given

        //When

        //Then


    }
}
