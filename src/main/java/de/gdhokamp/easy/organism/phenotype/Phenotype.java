package de.gdhokamp.easy.organism.phenotype;

import de.gdhokamp.easy.organism.genotype.Genome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Phenotype {

    private int stateStart = 0;
    private List<StateTransitionRow> stateTransitionTable = new ArrayList<>();

    // the organism always faces south at the beginning of the simulation


    private StateTransitionRow decode(String bitstring) {
        StateTransitionRow stateTransitionRow = new StateTransitionRow();
        stateTransitionRow.setEndState(Integer.parseInt(bitstring.substring(0,5),2));
        stateTransitionRow.setAction(StateAction.values()[Integer.parseInt(bitstring.substring(5),2)]);
        return stateTransitionRow;
    }

    public Phenotype makeStateTransitionTable(Genome genome) {
        // decode the genome to a state transition table
        // the decoding for the start state is not necessary because it's implicit in the list
        stateTransitionTable = new ArrayList<>();
        stateStart = Integer.parseInt(genome.getBitstring().substring(0,4),2);
        List<String> splittedGenomeList = Arrays.asList(genome.getBitstring().substring(5).split("(?<=\\G.{" + 7 + "})"));


        for (String genes : splittedGenomeList) {
            stateTransitionTable.add(decode(genes));
        }
        return this;
    }


    public List<StateTransitionRow> getStateTransitionTable() {
        return stateTransitionTable;
    }

    public int getStateStart() {
        return this.stateStart;
    }
}
