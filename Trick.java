package com.huberlulu.jassapp;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import static com.huberlulu.jassapp.R.array.Color;

/**
 * Created by huber on 14-Oct-16.
 */

public class Trick implements Parcelable {

    ArrayList<Card> trick = new ArrayList<>(4);
    private ArrayList<Card> mCards = new ArrayList();
    private int mPoints;
    private Match mMatch;
    private String mTrumpColor;
    private String mInitColor;
    private int mInitPlayer;

    Trick(Match pMatch) {
        this.mTrumpColor = pMatch.getTrumpColor();
        this.mInitPlayer = pMatch.getNextPlayer().getOrder();
        this.mInitColor = "";
        this.mMatch = pMatch;
    }


    public void playCard(Card card){


        trick.add(card);
        int point = card.getPoint();
        card.setPlayed(true);
        card.setPoint(point);
        //card.setBock(Analysis.isHighestColorCard(this.mMatch, card.getCard()));
        this.mPoints += point;
        if (trick.size() == 1) {
            mInitColor  = card.getColor();
            mMatch.setLeadPower(mInitColor);
        }
        else if(trick.size() == 4){
            
            mMatch.startNewTrick();
        }
        if(mInitPlayer<3){
            mInitPlayer++;
        }
        else{
            mInitPlayer=0;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.trick);
        dest.writeList(this.mCards);
        dest.writeInt(this.mPoints);
        dest.writeParcelable(this.mMatch, flags);
        dest.writeString(this.mTrumpColor);
        dest.writeString(this.mInitColor);
        dest.writeInt(this.mInitPlayer);
    }

    protected Trick(Parcel in) {
        this.trick = new ArrayList<Card>();
        in.readList(this.trick, Card.class.getClassLoader());
        this.mCards = new ArrayList<Card>();
        in.readList(this.mCards, Card.class.getClassLoader());
        this.mPoints = in.readInt();
        this.mMatch = in.readParcelable(Match.class.getClassLoader());
        this.mTrumpColor = in.readString();
        this.mInitColor = in.readString();
        this.mInitPlayer = in.readInt();
    }

    public static final Parcelable.Creator<Trick> CREATOR = new Parcelable.Creator<Trick>() {
        @Override
        public Trick createFromParcel(Parcel source) {
            return new Trick(source);
        }

        @Override
        public Trick[] newArray(int size) {
            return new Trick[size];
        }
    };
}

