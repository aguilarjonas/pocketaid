package com.example.jonas.pocketaid.InteractiveModules;

import java.util.ArrayList;

/**
 * Created by Raeven on 26 Feb 2017.
 */

public class InteractiveModel {

    private static InteractiveModel uniqInstance;

    private static int numberOfError = 0;
    private static int numberOfTries = 0;
    private static int numberOfCorrect = 0;

    private static ArrayList<Integer> correctMaterials = new ArrayList<Integer>();

    private InteractiveModel() {
    }

    public static synchronized InteractiveModel getInstance() {

        if (uniqInstance == null)
            uniqInstance = new InteractiveModel();

        return uniqInstance;
    }

    public void resetValues(){
        InteractiveModel.numberOfError = 0;
        InteractiveModel.numberOfTries = 0;
        InteractiveModel.numberOfCorrect = 0;
        InteractiveModel.correctMaterials.clear();
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

    public static ArrayList<Integer> getCorrectMaterials() {
        return correctMaterials;
    }

    public static void setCorrectMaterials(ArrayList<Integer> correctMaterials) {
        InteractiveModel.correctMaterials = correctMaterials;
    }
}
