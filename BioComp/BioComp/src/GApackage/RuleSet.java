package GApackage;

public class RuleSet {
    //RuleSet makes up the population for the test. Consists of 10 Rules
    
    Rule[] ruleSet;
    int fitness;
    
    public RuleSet(int ruleSetSize, boolean initialise){
        ruleSet = new Rule[ruleSetSize];
        fitness = 0;
        //Initialise population
        if(initialise){
            //Loop and create individuals
            for (int i=0;i<ruleSet.length; i++){
                Rule newRule = new Rule();
                newRule.generateRule();
                saveRule(i, newRule);
            }
        }
    }
    
     public Rule getRule(int index){
        return ruleSet[index];
    }
        
    public void saveRule(int index, Rule rule){
        ruleSet[index] = rule;
    }
    
    public int size(){
        return ruleSet.length;
    }
    
     public int getFitness(){
        if(fitness == 0){
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }
}
