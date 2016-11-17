package com.huberlulu.jassapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by huber on 14-Nov-16.
 */

public class Player implements Parcelable {


    ArrayList<Card> currentHand = new ArrayList<>(9);
    ArrayList<Card>    initHand = new ArrayList<>(9);
    Game                   game;
    String                 name;
    boolean            hasTrump;
    int                   order;


    Player(String name){
        this.name = name;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public void setHand(ArrayList<Card> hand){
        this.currentHand = hand;
        this.initHand = hand;
    }

    public void setOrder(int order){
        this.order = order;
    }

    public int getOrder(){
        return order;
    }

    public void playCard(Card card, Game game){

    }

    public void setName(String Name){
        this.name = Name;
    }

    /*public ArrayList<String> getColor(int color, Match match){
        ArrayList<String> colorSet = new ArrayList<>(9);
        for (int i =0; i< Hand.size(); i++){
            if (match.cardColorSet(color).contains( Hand.get(i))){
                colorSet.add(Hand.get(i));
            }
        }
        return colorSet;
    }*/

    public void setTrump(boolean hasTrump){
        this.hasTrump = hasTrump;
    }

    public boolean hasTrump(){
        return hasTrump;
    }

    public ArrayList<Card> getStartHand(){
        return  this.initHand;
    }

    public ArrayList<Card> getHand() {
        return this.currentHand;
    }


    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.currentHand);
        dest.writeList(this.initHand);
        dest.writeParcelable(this.game, flags);
        dest.writeString(this.name);
        dest.writeByte(this.hasTrump ? (byte) 1 : (byte) 0);
        dest.writeInt(this.order);
    }

    protected Player(Parcel in) {
        this.currentHand = new ArrayList<Card>();
        in.readList(this.currentHand, Card.class.getClassLoader());
        this.initHand = new ArrayList<Card>();
        in.readList(this.initHand, Card.class.getClassLoader());
        this.game = in.readParcelable(Game.class.getClassLoader());
        this.name = in.readString();
        this.hasTrump = in.readByte() != 0;
        this.order = in.readInt();
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
