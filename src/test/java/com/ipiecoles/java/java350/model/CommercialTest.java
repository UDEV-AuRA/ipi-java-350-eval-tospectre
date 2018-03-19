package com.ipiecoles.java.java350.model;

import org.junit.Test;
import org.assertj.core.api.Assertions;

public class CommercialTest {

    @Test
    public void testPrimeAnnuelleWithCAnull() {
        //Given
        Commercial commercial = new Commercial();
        commercial.setCaAnnuel(null);

        //When
        Double prime = commercial.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(500d);
    }

    @Test
    public void testPrimeAnnuelleWithCA0(){
        //Given
        Commercial commercial = new Commercial();
        commercial.setCaAnnuel(0d);

        //When
        Double prime = commercial.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(500d);
    }

    @Test
    public void testPrimeAnnuelleWithCA100000(){
        //Given
        Commercial commercial = new Commercial();
        commercial.setCaAnnuel(100000d);

        //When
        Double prime = commercial.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(5000d);
    }
}
