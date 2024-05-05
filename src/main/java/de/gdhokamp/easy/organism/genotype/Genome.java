package de.gdhokamp.easy.organism.genotype;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;



public class Genome {

    private String bitstring;
    public Genome() {
        bitstring = String.join("",
                ThreadLocalRandom.current().ints(453,0,2).boxed().map(i -> String.valueOf(i)).collect(Collectors.toList()));
    }

    public Genome(String bitstring) {
        this.bitstring = bitstring;
    }


    public String getBitstring() {
        return this.bitstring;
    }

    public void mutate() {
        // should i mutate or not
        int rand = ThreadLocalRandom.current().nextInt(0,100);

        if (rand <= 50) {
            pointMutation();
        }
    }

    private void pointMutation() {
        int pos = ThreadLocalRandom.current().nextInt(0,bitstring.length());
        char charAtPos = bitstring.charAt(pos);
        char replacementChar = '0';
        if (charAtPos == '0') {
            replacementChar = '1';
        }
        StringBuilder stringToMutate = new StringBuilder(bitstring);
        stringToMutate.setCharAt(pos, replacementChar);
        this.bitstring = stringToMutate.toString();
    }

    private void deletion() {

    }

    private void duplication() {

    }

}
