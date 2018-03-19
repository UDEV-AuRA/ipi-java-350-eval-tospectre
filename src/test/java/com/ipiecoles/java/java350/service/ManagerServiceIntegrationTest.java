package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.MySpringApplication;
import com.ipiecoles.java.java350.model.Manager;
import com.ipiecoles.java.java350.model.Technicien;
import com.ipiecoles.java.java350.repository.ManagerRepository;
import com.ipiecoles.java.java350.repository.TechnicienRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest/*(classes = MySpringApplication.class)*/
public class ManagerServiceIntegrationTest {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ManagerService managerService;

    @Autowired
    TechnicienRepository technicienRepository;

    @Before
    public void setUp() {
        technicienRepository.deleteAll();
        managerRepository.deleteAll();
    }

    @Test
    public void testAddTechnicien(){
        //Given
        Manager manager = new Manager("Durand", "Jean", "M12345",
                null, 2000d, new HashSet<>());
        manager = managerRepository.save(manager);

        Technicien tech1 = new Technicien("Honoré", "boile", "T98445", null,
                1000d, 2);
        tech1 = technicienRepository.save(tech1);

        //When
        managerService.addTechniciens(manager.getId(), tech1.getMatricule());

        //Then
        Manager finalManager = managerRepository.findOneWithEquipeById(manager.getId());
        Technicien finalTech = technicienRepository.findOne(tech1.getId());

        Assertions.assertThat(finalManager.getEquipe()).contains(finalTech);
        Assertions.assertThat(finalTech.getManager()).isNotNull();
        Assertions.assertThat(finalTech.getMatricule()).isNotNull();
    }

    @Test
    public void testAddTechnicienWithNoManager(){
        //Given
        Technicien tech1 = new Technicien("Honoré", "boile", "T98445", null,
                1000d, 2);

        //When
        try {
            managerService.addTechniciens(6L, tech1.getMatricule());
            Assertions.fail("Cela aurait du planter");
        } catch (Exception e){
            //Then
            Assertions.assertThat(e).isInstanceOf(EntityNotFoundException.class);
            Assertions.assertThat(e).hasMessage("Impossible de trouver le manager d'identifiant 6");
        }
    }
}
