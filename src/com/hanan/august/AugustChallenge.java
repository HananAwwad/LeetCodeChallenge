package com.hanan.august;

public class AugustChallenge {

    public static void main(String[] aa) {
        System.out.println("USA......"+new AugustChallenge().detectCapitalUse("USA"));
        System.out.println("Capital.."+new AugustChallenge().detectCapitalUse("Capital"));
        System.out.println("small...."+new AugustChallenge().detectCapitalUse("small"));


    }

    public boolean detectCapitalUse(String word) {
        return word.matches("([A-Z]*|).[a-z]*");


        //return word.matches("[A−Z]∗∣[a−z]∗∣[A−Z][a−z]∗");
    }
}
