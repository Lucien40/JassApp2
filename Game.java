package com.huberlulu.jassapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by huber on 14-Nov-16.
 */

public class Game implements Parcelable {

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    ArrayList<Team>       teams = new ArrayList<>();
    ArrayList<Match>    matches = new ArrayList<>();
    ArrayList<Player>   players = new ArrayList<>();
    int          team1GameScore = 1;
    int          team2GameScore = 0;
    Match          currentMatch;
    String                 name;

    Game(ArrayList<String> team1PlayerNames, String team1Name, ArrayList<String> team2PlayerNames, String team2Name, Date name) {


        //Create Team 1
        Player team1Player1 = new Player(team1PlayerNames.get(0));
        team1Player1.setOrder(0);
        Player team1Player2 = new Player(team1PlayerNames.get(1));
        team1Player2.setOrder(2);
        ArrayList<Player> team1players = new ArrayList<>();
        team1players.add(team1Player1);
        team1players.add(team1Player2);
        Team team1 = new Team(team1Name, team1players);

        //Create Team 2
        Player team2Player1 = new Player(team2PlayerNames.get(0));
        team2Player1.setOrder(2);
        Player team2Player2 = new Player(team2PlayerNames.get(1));
        team2Player2.setOrder(3);
        ArrayList<Player> team2players = new ArrayList<>();
        team1players.add(team2Player1);
        team1players.add(team2Player2);
        Team team2 = new Team(team2Name, team2players);

        Log.i("Team 1 players",team1.getPlayers().get(0).getName());

        teams.add(team1);
        teams.add(team2);
        players.add(team1Player1);
        players.add(team1Player2);
        players.add(team2Player1);
        players.add(team2Player2);

        //Start a new Match
        Match startMatch = new Match(players);
        currentMatch = startMatch;

        //Set Name
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss");
        this.name = df.format(name);
    }

    protected Game(Parcel in) {
        Log.i("HI","HI");

        this.teams = new ArrayList<Team>();
        in.readList(this.teams, Team.class.getClassLoader());
        this.matches = new ArrayList<Match>();
        in.readList(this.matches, Match.class.getClassLoader());
        this.players = new ArrayList<Player>();
        in.readList(this.players, Player.class.getClassLoader());
        this.team1GameScore = in.readInt();
        this.team2GameScore = in.readInt();
        this.currentMatch = in.readParcelable(Match.class.getClassLoader());
        this.name = in.readString();
    }

    public void startNewMatch(){
        matches.add(currentMatch);
        Match newMatch = new Match(players);
        currentMatch = newMatch;
    }

    public void addToGameScore(int score,int team){
        if (team == 0){
            this.team1GameScore += score;
        }
        else {
            this.team2GameScore += score;
        }
    }

    public void playCard(String card){
        currentMatch.playCard(card);
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
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
        dest.writeList(this.teams);
        dest.writeList(this.matches);
        dest.writeList(this.players);
        dest.writeInt(this.team1GameScore);
        dest.writeInt(this.team2GameScore);
        dest.writeParcelable(this.currentMatch, flags);
        dest.writeString(this.name);
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public int getTeam2GameScore() {
        return team2GameScore;
    }

    public int getTeam1GameScore() {
        return team1GameScore;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
}
