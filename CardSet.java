package com.huberlulu.jassapp;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by huber on 29-Oct-16.
 */

public class CardSet implements Parcelable {

    HashMap<String,Card> cardMap = new HashMap<>();
    String[] color;
    String[] name;
    int[] power;
    int[] points;
    int[] trumpPower;
    int[] trumpPoint;


    CardSet() {
        Context ctx = App.getContext();
        color = ctx.getResources().getStringArray(R.array.Color);
        power = ctx.getResources().getIntArray(R.array.power);
        points = ctx.getResources().getIntArray(R.array.value);
        name = ctx.getResources().getStringArray(R.array.Card);
        trumpPower =ctx.getResources().getIntArray(R.array.TrumpPower);
        trumpPoint = ctx.getResources().getIntArray(R.array.trumpValue);
        setCardMap();
    }
    
    public Card getBestCard(ArrayList<Card> cards){
        Card BestCard = cards.get(0);
        for (Card card:cards) {
            if(card.getPower()>BestCard.getPower()){
                BestCard = card;
            }
        }
        return BestCard;
    }

    private void setCardMap() {
        for (int i = 0; i < name.length; i++) {
            for (int j = 0; j < color.length; j++) {
                String cardName = name[i] + "of" + color[j];
                Card c = new Card(cardName);
                c.setColor(color[j]);
                c.setPoint(points[i]);
                c.setPower(power[i]);
                c.setName(name[i]);
                c.setPlayed(false);
                c.setLead(false);
                c.setTrump(false);
                cardMap.put(cardName,c);
            }
        }
    }



    public void reSetCardMap(){
        setCardMap();
    }

    public Card getCard(String name){
        return cardMap.get(name);
    }

    public void setLeadColor(String color){
        for (String key:cardMap.keySet()){
            cardMap.get(key).setLead(false);
        }
        for (int i =0; i< name.length; i++){
            String cardName = name[i] + "of" + color;
            cardMap.get(cardName).setLead(true);
        }
    }

    public void setTrumpColor(String color){
        for (int i =0; i< name.length; i++){
            String cardName = name[i] + "of" + color;
            Card c =this.getCard(cardName);
            c.setPower(trumpPower[i]);
            c.setPoint(trumpPoint[i]);
            cardMap.put(cardName,c);
        }
    }

    public HashMap getCardMap(){
        return cardMap;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.cardMap);
        dest.writeStringArray(this.color);
        dest.writeStringArray(this.name);
        dest.writeIntArray(this.power);
        dest.writeIntArray(this.points);
        dest.writeIntArray(this.trumpPower);
        dest.writeIntArray(this.trumpPoint);
    }

    protected CardSet(Parcel in) {
        this.cardMap = (HashMap<String, Card>) in.readSerializable();
        this.color = in.createStringArray();
        this.name = in.createStringArray();
        this.power = in.createIntArray();
        this.points = in.createIntArray();
        this.trumpPower = in.createIntArray();
        this.trumpPoint = in.createIntArray();
    }

    public static final Parcelable.Creator<CardSet> CREATOR = new Parcelable.Creator<CardSet>() {
        @Override
        public CardSet createFromParcel(Parcel source) {
            return new CardSet(source);
        }

        @Override
        public CardSet[] newArray(int size) {
            return new CardSet[size];
        }
    };
}
