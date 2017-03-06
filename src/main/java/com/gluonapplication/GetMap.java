package com.gluonapplication;

public class GetMap {
    public int adam =123;
    public static String chapter = null;
    public String section;
    public int quest;
  
    
    public int getName(){
        return adam;
    }
    
    public static void setChapter(String a){
        chapter = a;
        
    }
    
    public void setSection(String a){
        section = a;
    }
    
    public void setQuest(int a){
        quest = a;
    }
    
    public static String getChapter(){
        return chapter;
    }
    
}
