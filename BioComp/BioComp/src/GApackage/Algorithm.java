package GApackage;

public class Algorithm {
    static int ruleSetSize = Main.rulesetSize;
    
    /*GA Parameters */
    private static final double uniformRate = Main.uniformRate;
    private static final double mutationRate = Main.mutationRate;
    private static final int tournamentSize = Main.tournamentSize;
    private static final boolean elitism = Main.elitism;
    
    //Public methods
    
    //Evolve the population
    public static Population evolvePopulation (Population pop){
        Population newPopulation = new Population(pop.size(), false);
        //Keep best individual
        if(elitism){
            newPopulation.saveRuleSet(0, pop.getFittest());
        }
        
        int elitismOffset;
        if(elitism){
            elitismOffset =1;
        }else{
            elitismOffset =0;
        }
        //Crossover population
        //Loop over the population size and create a new individuals
        
        //Crossover
        for(int i = elitismOffset; i < pop.size(); i++){
            RuleSet ruleSet1 = tournamentSelection(pop);
            RuleSet ruleSet2 = tournamentSelection(pop);
            RuleSet newRuleSet = crossover(ruleSet1, ruleSet2);
            newPopulation.saveRuleSet(i, newRuleSet);
        }
        
        //Mutate 
        for(int i = elitismOffset; i <newPopulation.size(); i++){
            newPopulation.population[i] =  mutate(newPopulation.getRuleSet(i));
        }
        return newPopulation;
    }
    
    //Crossover
    private static RuleSet crossover(RuleSet ruleSet1, RuleSet ruleSet2){
        RuleSet newSol = new RuleSet(ruleSetSize, false);
        //Loop through genes
        for(int i = 0; i < ruleSet1.size(); i++){
            //Crossover
            if(Math.random() <= uniformRate){
                newSol.saveRule(i, ruleSet1.getRule(i)); //not sure if this is how it could work
            }else { 
                newSol.saveRule(i, ruleSet2.getRule(i));
            }
        }
        return newSol;
    }
    
    //Mutation - figure out how to adapt mutation to work with it.
    private static RuleSet mutate(RuleSet ruleSet){
        //Loop through genes
        for(int i=0; i<ruleSet.size(); i++){
            if(Math.random() <= mutationRate){
                //Mutate random gene -- Possibly a solution to mutation, but double check with Larry and whoever.
                Rule mutant = new Rule();
                mutant.generateRule();
                ruleSet.saveRule(i, mutant);
            }
        }
        return ruleSet;
    }
    
    private static RuleSet tournamentSelection(Population pop){
        //Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        //For each place in the tournament get  a random individual
        for(int i=0; i < tournamentSize; i++){
            int randomID = (int) (Math.random() * pop.size());
            tournament.saveRuleSet(i, pop.getRuleSet(randomID));
        }
        //Get the fittest
        RuleSet fittest = tournament.getFittest();
        return fittest;
    }
        
}
    

