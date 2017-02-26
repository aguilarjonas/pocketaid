package com.example.jonas.pocketaid.InteractiveModules;

/**
 * Created by Raeven on 26 Feb 2017.
 */

public class InteractiveModel {

    private static InteractiveModel uniqInstance;
    private static int numberOfError = 0;
    private static int numberOfTries = 0;

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
    }

    public static int getNumberOfError() {
        return numberOfError;
    }

    public static void setNumberOfError(int numberOfError) {
        InteractiveModel.numberOfError = numberOfError;
    }

    public static int getNumberOfTries() {
        return numberOfTries;
    }

    public static void setNumberOfTries(int numberOfTries) {
        InteractiveModel.numberOfTries = numberOfTries;
    }
}
