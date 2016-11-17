package com.huberlulu.jassapp;



import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;



/**
 * Created by huber on 14-Oct-16.
 */
public class Match implements Parcelable {

    ArrayList<Trick>    tricks = new ArrayList<>();
    ArrayList<Player>  players = new ArrayList<>();
    int        matchScoreTeam1 = 0;
    int        matchScoreTeam2 = 0;
    Trick         currentTrick;
    int        nextPlayerOrder;
    Player         mNextPlayer;
    CardSet       matchCardSet = new CardSet();
    String               Trump;


    Match(ArrayList<Player> players){
        this.players = players;
    }

    public void playCard(String card){
        currentTrick.playCard(matchCardSet.getCard(card));
    }



    public void startNewTrick(){
        tricks.add(currentTrick);
        Trick newTrick = new Trick(this);
        currentTrick = newTrick;
    }

    public void setLeadPower(String color) {
        matchCardSet.setLeadColor(color);
    }

    private void setTrump(String color) {
        matchCardSet.setTrumpColor(color);
    }

    public String getTrumpColor() {
        return Trump;
    }

    public int getNextPlayerOrder(){
        return nextPlayerOrder;
    }

    public void setNextPlayerOrder(int nextPlayerOrder){
        this.nextPlayerOrder = nextPlayerOrder;
        for (Player player: players) {
            if(player.getOrder()==nextPlayerOrder){
                this.mNextPlayer= player;
            }
        }
    }

    public Player getNextPlayer() {
        return mNextPlayer;
    }

    public void setNextPlayer(Player pPlayer) {
        mNextPlayer     = pPlayer;
        nextPlayerOrder = pPlayer.getOrder();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.tricks);
        dest.writeList(this.players);
        dest.writeInt(this.matchScoreTeam1);
        dest.writeInt(this.matchScoreTeam2);
        dest.writeParcelable(this.currentTrick, flags);
        dest.writeParcelable(this.mNextPlayer, flags);
        dest.writeParcelable(this.matchCardSet, flags);
        dest.writeString(this.Trump);
    }

    protected Match(Parcel in) {
        this.tricks = new ArrayList<Trick>();
        in.readList(this.tricks, Trick.class.getClassLoader());
        this.players = new ArrayList<Player>();
        in.readList(this.players, Player.class.getClassLoader());
        this.matchScoreTeam1 = in.readInt();
        this.matchScoreTeam2 = in.readInt();
        this.currentTrick = in.readParcelable(Trick.class.getClassLoader());
        this.mNextPlayer = in.readParcelable(Player.class.getClassLoader());
        this.matchCardSet = in.readParcelable(CardSet.class.getClassLoader());
        this.Trump = in.readString();
    }

    public static final Parcelable.Creator<Match> CREATOR = new Parcelable.Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel source) {
            return new Match(source);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };
}
