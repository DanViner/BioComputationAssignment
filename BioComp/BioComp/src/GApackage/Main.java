package GApackage;

import java.io.FileNotFoundException;

public class Main {
    public final static int data = 2; //1, 2, or 3(NOT IMPLEMENTED)
    
    //GENETIC ALGORITHM VARIABLES
    public final static int popSize = 10;
    public final static double uniformRate = 0.3;
    public final static double mutationRate = 0.01;
    public final static int tournamentSize = 10;
    public final static boolean elitism = true;
    
    
    public static int rulesetSize;
    public static int ruleSize;
    public static int conditionSize;
    
    public static void main(String[] args) throws FileNotFoundException {
        setVariables();
        //Set a solution
        FitnessCalc fitness = new FitnessCalc();
        fitness.collectData();

        //fitness.printDataSet();

        //Create an initial population
        Population pop = new Population(popSize, true);

        //Evolve our population until we reach optimum fitness
        int generationCount = 0;

        while (pop.getFittest().getFitness() < FitnessCalc.getMaxFitness() && generationCount < 500) {
            generationCount++;
            //System.out.println("Generation: " + generationCount + "  Fittest: " + pop.getFittest().getFitness());
            System.out.println(pop.getFittest().getFitness());
            pop = Algorithm.evolvePopulation(pop);

        }
        //System.out.println("Solution found!");
        //System.out.println("Generation: " + generationCount);
        System.out.println(pop.getFittest().getFitness());

    }
    
    public static void setVariables(){
        if(data == 1){
            rulesetSize = 32;
            ruleSize = 6;
            conditionSize = 5;
        } else if(data == 2){
            rulesetSize = 64;
            ruleSize = 7;
            conditionSize = 6;
        }
    }
    
    //INITIALISE GENETIC ALGORITHM VARIABLES

}

