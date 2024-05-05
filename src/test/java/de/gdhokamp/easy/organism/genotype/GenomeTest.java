package de.gdhokamp.easy.organism.genotype;

import org.junit.jupiter.api.Test;

class GenomeTest {

    @Test
    public void testToString() {
        Genome genome = new Genome();
        genome.getBitstring();
        System.out.println(genome.getBitstring());
    }

}