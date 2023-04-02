package com.example.extra;

import java.util.Set;

public class LoopCounter implements Counter {
    private int count = 1;
    private int maximum;
    private boolean [] check = new boolean[maximum];

    Set<Integer> unconcerned;
    public LoopCounter(int maximum){
        this.maximum = maximum;
    }
    @Override
    public int value() {
        return 0;
    }

    @Override
    public void increase() {
        count++;
        if (count == maximum){
            count = getMin();
        }
    }

    public void addConcernedNum(int n){
        unconcerned.add(n);
        check[n - 1] = true;
    }
    public int getMin(){
        int min = 0;
        for (int i = 0; i < check.length; i++){
            if(check[i] == false){
                min = i+1;
                break;
            }
        }
        return min;
    }

}
