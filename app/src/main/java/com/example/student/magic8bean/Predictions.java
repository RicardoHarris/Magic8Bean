package com.example.student.magic8bean;

import java.util.Random;

public class Predictions{
    //private Random rand;
    private static Predictions predictions;
    private String[] answers;

    private Predictions(){
        answers = new String[]{
                "No",
                "Yes",
                "Unfortunately",
                "That's a Question for your Doctor..."
        };
    }

    public static Predictions get(){
        //Random rand = new Random();
        //int n = rand.nextInt(20);
        if(predictions == null) {
            predictions = new Predictions();
        }
        return predictions;
    }

    //public String getPrediction(){
    //    return answers[rand];
    //}
}