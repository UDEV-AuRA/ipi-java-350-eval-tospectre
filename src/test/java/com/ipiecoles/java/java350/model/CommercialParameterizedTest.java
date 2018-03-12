package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class CommercialParameterizedTest {

    @Parameter(value = 0)
    public Integer performance;
    @Parameter(value = 1)
    public Note expectedNote;

    @Parameterized.Parameters(name = "pref {0} est équivalent à : {1}")
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {0, Note.INSUFFISANT},
                {50, Note.INSUFFISANT},
                {100, Note.PASSABLE},
                {150, Note.BIEN},
                {200, Note.TRES_BIEN},
                {null, null},
                {600, null},
        });
    }

    @Test
    public void testCommercialEquivalenceNotes(){
       //Given
        Commercial commercial = new Commercial();
        commercial.setPerformance(performance);

        //When
       Note note = commercial.equivalenceNote();

       //Then
        Assertions.assertThat(note).isEqualTo(expectedNote);
    }
}
