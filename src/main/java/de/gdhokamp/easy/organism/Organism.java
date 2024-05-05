package de.gdhokamp.easy.organism;

import de.gdhokamp.easy.environment.AntPosition;
import de.gdhokamp.easy.environment.Environment;
import de.gdhokamp.easy.organism.genotype.Genome;
import de.gdhokamp.easy.organism.phenotype.Perspective;
import de.gdhokamp.easy.organism.phenotype.Phenotype;
import de.gdhokamp.easy.organism.phenotype.StateAction;

import java.util.concurrent.ThreadLocalRandom;

public class Organism {

    public static final int SIMULATION_STEPS = 200;
    private Genome genotype;
    private Phenotype phenotype;

    private AntPosition antPosition = new AntPosition(0,31);

    private Perspective actualPerspective = Perspective.EAST;


    private int noOfFood = 0;

    public Organism() {
        genotype = new Genome();
        phenotype = new Phenotype();
    }

    public Organism(String bitstring) {
        genotype = new Genome(bitstring);
        phenotype = new Phenotype();
    }

    public Genome getGenotype() {
        return this.genotype;
    }

    public Phenotype getPhenotype() {
        return this.phenotype;
    }

    /**
     * Simulates one run of an ant to collect food
     * @param environment -- defines the trail for the ant
     */
    public void simulate(Environment environment) {
        // build the state transition table from the actual genotype
        this.antPosition = new AntPosition(0,31);
        actualPerspective = Perspective.EAST;
        this.noOfFood = 0;
        this.phenotype.makeStateTransitionTable(this.genotype);
        int state = phenotype.getStateStart();
        AntPosition lookAheadPosition = antPosition.add(actualPerspective);

        for (int step = 0; step < SIMULATION_STEPS; step++) {

            // if there is food at this position, then get ist
            if (environment.hasFood(antPosition)) {
                environment.collectFood(antPosition);
                noOfFood++;
            }
            // do the work in the automaton. Every step is a transition.

            // check if there's food in front of the ant
            if (environment.hasFood(lookAheadPosition)) {
                // 64 entries in the table
                state++;
            }
            StateAction action = phenotype.getStateTransitionTable().get(state).getAction();

            if (action.equals(StateAction.NOTHING)) {
                // do nothing, only a reminder
            }

            if (action.equals(StateAction.MOVE)) {
                antPosition = antPosition.add(actualPerspective);
                lookAheadPosition = antPosition.add(actualPerspective);
            }

            if (action.equals(StateAction.TURNLEFT)) {
                switch (actualPerspective) {
                    case SOUTH -> actualPerspective = Perspective.EAST;
                    case NORTH -> actualPerspective = Perspective.WEST;
                    case EAST -> actualPerspective = Perspective.NORTH;
                    case WEST -> actualPerspective = Perspective.SOUTH;
                }
                lookAheadPosition = antPosition.add(actualPerspective);
            }

            if (action.equals(StateAction.TURNRIGHT)) {
                switch (actualPerspective) {
                    case SOUTH -> actualPerspective = Perspective.WEST;
                    case NORTH -> actualPerspective = Perspective.EAST;
                    case EAST -> actualPerspective = Perspective.SOUTH;
                    case WEST -> actualPerspective = Perspective.NORTH;
                }
                lookAheadPosition = antPosition.add(actualPerspective);
            }

            state = phenotype.getStateTransitionTable().get(state).getEndState();
        }
    }

    public void mutate() {
        this.genotype.mutate();
    }

    public Organism mate(Organism femaleOrganism) {
        int pos = ThreadLocalRandom.current().nextInt(0,this.genotype.getBitstring().length());
        StringBuilder stringBuilder = new StringBuilder(this.genotype.getBitstring().substring(0,pos));
        stringBuilder.append(femaleOrganism.getGenotype().getBitstring().substring(pos, femaleOrganism.getGenotype().getBitstring().length()));
        return new Organism(stringBuilder.toString());
    }

    public Integer getNoOfFood() {
        return this.noOfFood;
    }
}
