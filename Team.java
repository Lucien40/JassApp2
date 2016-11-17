package com.huberlulu.jassapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by huber on 14-Nov-16.
 */

public class Team implements Parcelable {

    String Name;
    ArrayList<Player> players;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    Team(String name, ArrayList<Player> players){
        this.Name = name;
        this.players = players;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeList(this.players);
    }

    protected Team(Parcel in) {
        this.Name = in.readString();
        this.players = new ArrayList<Player>();
        in.readList(this.players, Player.class.getClassLoader());
    }

    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
