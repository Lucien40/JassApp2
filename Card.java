package com.huberlulu.jassapp;

/**
 * Created by huber on 14-Nov-16.
 */

public class Card {
    private String card;
    private String name;
    private String Color;
    public boolean isLegal;
    private boolean isBock   = false;
    private boolean isLead   = false;
    private boolean isTrump  = false;
    private boolean isPlayed = false;
    private int point;
    private int power;
    private int color;


    Card(String card) {
        setCard(card);
    }

    public void setLead(boolean isLead){
        this.isLead = isLead;
    }

    public void setLegal(boolean legality){
        isLegal = legality;
    }

    public boolean isLegal(){
        return isLegal;
    }

    public String getCard() {
        return this.card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPower(){
        return power;
    }

    public void setPower(int power){
        this.power = power;
    }

    public String getColor(){
        return Color;
    }

    public void setColor(String color){
        this.Color = color;
        switch (color){
            case "h":
                this.color = 1;
                break;
            case "d":
                this.color = 2;
                break;
            case "s":
                this.color = 3;
                break;
            case "c":
                this.color = 4;
                break;
        }
    }

    public boolean isPlayed() {
        return this.isPlayed;
    }

    public void setPlayed(boolean isPlayed) {
        this.isPlayed = isPlayed;
    }

    public boolean isTrump(){
        return  this.isTrump;
    }

    public void setTrump(boolean isTrump){
        this.isTrump = isTrump;
    }

    public boolean isBock() {
        return this.isBock;
    }

    public void setBock(boolean isBock) {
        this.isBock = isBock();
    }
}


