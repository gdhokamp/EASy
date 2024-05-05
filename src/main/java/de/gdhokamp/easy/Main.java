package de.gdhokamp.easy;

import de.gdhokamp.easy.environment.Environment;
import de.gdhokamp.easy.organism.Organism;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static Integer noOfOrganisms = 65536;
    private static int simulationTime = 100;
    public static void main(String[] args) {
        System.out.println("This is EASy!");

        // initialize the population
        List<Organism> population = IntStream.range(0, noOfOrganisms).mapToObj(i -> new Organism()).collect(Collectors.toList());

        // simulation loop
        Integer overallFood = 0;
        for (int i = 0; i < simulationTime; i++) {
            // inner loop
            for ( Organism organism : population) {
                organism.simulate(new Environment());
                overallFood += organism.getNoOfFood();
            }
            // select all organisms, which collect more or equal food as the average food
            System.out.println("overall collected food: " + overallFood);
            final Integer averageFood = overallFood/noOfOrganisms;
            System.out.println("Average collected food : " + averageFood);
            System.out.println("champ collects: " + population.stream().parallel().mapToInt(o -> o.getNoOfFood()).max());
            System.out.println("population size = " + population.size());
            List<Organism> selectedPopulation = population.stream().parallel().filter(o -> o.getNoOfFood().compareTo(averageFood) > 0).collect(Collectors.toList());
            System.out.println("selected population size = " + selectedPopulation.size());

            int noOfNewOrganisms = noOfOrganisms - selectedPopulation.size();
//            List<Organism> fillPopulation = IntStream.range(0, noOfOrganisms - selectedPopulation.size()).mapToObj(p -> new Organism()).collect(Collectors.toList());
            List<Organism> fillPopulation = new ArrayList<>();


            // This is algorithm is very simple. Select two random organism from the selected population and let them mate.
            int male = 0;
            int female = 0;
            for (int j = 0; j < noOfNewOrganisms; j++) {
                male = ThreadLocalRandom.current().nextInt(0,selectedPopulation.size());
                female = ThreadLocalRandom.current().nextInt(0,selectedPopulation.size());
                fillPopulation.add(selectedPopulation.get(male).mate(selectedPopulation.get(female)));
            }

            System.out.println("fill population size = " + fillPopulation.size());
            overallFood = 0;
            population = selectedPopulation;
            population.addAll(fillPopulation);
            for (Organism organism : population) {
                organism.mutate();
            }

        }
    }
}