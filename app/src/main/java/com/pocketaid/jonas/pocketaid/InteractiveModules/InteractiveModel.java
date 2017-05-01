package com.pocketaid.jonas.pocketaid.InteractiveModules;

import java.util.ArrayList;

/**
 * Created by Raeven on 26 Feb 2017.
 */

public class InteractiveModel {

    private static InteractiveModel uniqInstance;

    private static int numberOfError = 0;
    private static int numberOfTries = 0;
    private static int numberOfCorrect = 0;
    private static int numberOfMaterials = 0;
    double stageOnePercentage;
    double stageTwoPercentage;
    double stageThreePercentage;
    double averagePercentage;

    private static ArrayList<Integer> stage1Stats = new ArrayList<Integer>();
    private static ArrayList<Integer> stage2Stats = new ArrayList<Integer>();
    private static ArrayList<Integer> stage3Stats = new ArrayList<Integer>();

    private static ArrayList<Integer> correctMaterials = new ArrayList<Integer>();

    private InteractiveModel() {
    }

    public static synchronized InteractiveModel getInstance() {

        if (uniqInstance == null)
            uniqInstance = new InteractiveModel();

        return uniqInstance;
    }

    public void assignFirstStageStats(int correct, int wrong, int tries){
        InteractiveModel.stage1Stats.set(0, correct);
        InteractiveModel.stage1Stats.set(1, wrong);
        InteractiveModel.stage1Stats.set(2, tries);
    }

    public void assignSecondStageStats(int correct, int wrong, int tries){
        InteractiveModel.stage2Stats.set(0, correct);
        InteractiveModel.stage2Stats.set(1, wrong);
        InteractiveModel.stage2Stats.set(2, tries);
    }

    public void assignThirdStageStats(int correct, int wrong, int tries){
        InteractiveModel.stage3Stats.set(0, correct);
        InteractiveModel.stage3Stats.set(1, wrong);
        InteractiveModel.stage3Stats.set(2, tries);
    }

    public void resetValues(){
        InteractiveModel.numberOfError = 0;
        InteractiveModel.numberOfTries = 0;
        InteractiveModel.numberOfCorrect = 0;
        InteractiveModel.numberOfMaterials = 0;
        InteractiveModel.correctMaterials.clear();

        InteractiveModel.stage1Stats.clear();
        InteractiveModel.stage1Stats.add(0);
        InteractiveModel.stage1Stats.add(0);
        InteractiveModel.stage1Stats.add(0);

        InteractiveModel.stage2Stats.clear();
        InteractiveModel.stage2Stats.add(0);
        InteractiveModel.stage2Stats.add(0);
        InteractiveModel.stage2Stats.add(0);

        InteractiveModel.stage3Stats.clear();
        InteractiveModel.stage3Stats.add(0);
        InteractiveModel.stage3Stats.add(0);
        InteractiveModel.stage3Stats.add(0);
    }

    public ArrayList<Integer> getStage3Stats() {
        return stage3Stats;
    }

    public static void setStage3Stats(ArrayList<Integer> stage3Stats) {
        InteractiveModel.stage3Stats = stage3Stats;
    }

    public ArrayList<Integer> getStage1Stats() {
        return stage1Stats;
    }

    public static void setStage1Stats(ArrayList<Integer> stage1Stats) {
        InteractiveModel.stage1Stats = stage1Stats;
    }

    public ArrayList<Integer> getStage2Stats() {
        return stage2Stats;
    }

    public static void setStage2Stats(ArrayList<Integer> stage2Stats) {
        InteractiveModel.stage2Stats = stage2Stats;
    }

    public int getNumberOfCorrect() {
        return numberOfCorrect;
    }

    public static void setNumberOfCorrect(int numberOfCorrect) {
        InteractiveModel.numberOfCorrect = numberOfCorrect;
    }

    public int getNumberOfError() {
        return numberOfError;
    }

    public static void setNumberOfError(int numberOfError) {
        InteractiveModel.numberOfError = numberOfError;
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }

    public static void setNumberOfTries(int numberOfTries) {
        InteractiveModel.numberOfTries = numberOfTries;
    }

    public int getNumberOfMaterials() {
        return numberOfMaterials;
    }

    public void setNumberOfMaterials(int numberOfMaterials) {
        this.numberOfMaterials = numberOfMaterials;
    }

    public static ArrayList<Integer> getCorrectMaterials() {
        return correctMaterials;
    }

    public static void setCorrectMaterials(ArrayList<Integer> correctMaterials) {
        InteractiveModel.correctMaterials = correctMaterials;
    }

    public double getStageOnePercentage() {
        return (((double)getStage1Stats().get(0)/(double)getStage1Stats().get(2))*100);
    }

    public void setStageOnePercentage(double stageOnePercentage) {
        this.stageOnePercentage = stageOnePercentage;
    }

    public double getStageTwoPercentage() {
        return (((double)getStage2Stats().get(0)/(double)getStage2Stats().get(2))*100);
    }

    public void setStageTwoPercentage(double stageTwoPercentage) {
        this.stageTwoPercentage = stageTwoPercentage;
    }

    public double getStageThreePercentage() {
        return (((double)getStage3Stats().get(0)/(double)getStage3Stats().get(2))*100);
    }

    public void setStageThreePercentage(double stageThreePercentage) {
        this.stageThreePercentage = stageThreePercentage;
    }

    public double getAveragePercentage() {
        return (getStageOnePercentage() + getStageTwoPercentage() + getStageThreePercentage())/3;
    }

    public void setAveragePercentage(double averagePercentage) {
        this.averagePercentage = averagePercentage;
    }
}
