package GApackage;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class FitnessCalc {

    public static Rule[] DataSet = new Rule[Main.rulesetSize];
    private static int MaxFitness = Main.rulesetSize;
    private static String data = "data" + Main.data + ".txt";

    public void collectData() throws FileNotFoundException {

        Scanner inFile1 = new Scanner(FitnessCalc.class.getResourceAsStream(data));
        int i = 0;
        while (inFile1.hasNext() && i < DataSet.length) {

            String rule1 = inFile1.nextLine();

            Rule newRule = new Rule();
            newRule.inputRule(rule1);
            DataSet[i] = newRule;
            i++;
        }
    }

    public static int getMaxFitness() {
        return MaxFitness;
    }

    public static Rule getRule(int index) {
        return DataSet[index];
    }

    public static int getFitness(RuleSet ruleSet) {
        int fitness = 0;
        for (int i = 0; i < Main.rulesetSize; i++) {
            for (int j = 0; j < Main.rulesetSize; j++) {
                if (matchCond(DataSet[j], ruleSet.getRule(j))) {
                    if (matchOut(DataSet[i], ruleSet.getRule(i))) {
                        fitness++;
                    }
                    break; //it is important to get the next data item after a match.
                }
            }
        }
        return fitness;
    }

    public static boolean matchCond(Rule dataExample, Rule ruleBase) {
        return (dataExample.getCondition().equals(ruleBase.getCondition()));
    }

    public static boolean matchOut(Rule dataExample, Rule ruleBase) {
        return (dataExample.getOutput().equals(ruleBase.getOutput()));
    }

    public static void printDataSet() {
        System.out.println("Data Set from imported file:");
        for (int i = 0; i < Main.rulesetSize; i++) {
            System.out.println("Rule" + (i + 1) + ": " + getRule(i).getRuleString());
        }
    }
}
