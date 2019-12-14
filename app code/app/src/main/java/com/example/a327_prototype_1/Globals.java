package com.example.a327_prototype_1;

public class Globals{
    private static Globals instance;

    // Global variable
    private int dataRecycle;
    private int dataSteps;

    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setDataRecycle(int d){
        this.dataRecycle=d;
    }
    public void setDataSteps(int d){
        this.dataSteps=d;
    }
    public int getDataRecycle(){
        return this.dataRecycle;
    }
    public int getDataSteps(){
        return this.dataSteps;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
