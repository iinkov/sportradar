package com.sportradar;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LiveFootballWorldCupScoreboard {
    private List<Match> matches;

    public LiveFootballWorldCupScoreboard() {
        this.matches = new ArrayList<>();
    }

    public void startNewMatch(String homeTeam, String awayTeam) {
        matches.add(new Match(homeTeam, awayTeam));
    }

    public List<String> getMatchesSummary() {
        List<String> summary = new ArrayList<>();
        for (Match match : matches) {
            summary.add(match.toString());
        }
        return summary;
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        for (Match match : matches) {
            if (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)) {
                match.setHomeScore(homeScore);
                match.setAwayScore(awayScore);
            }
        }
    }

    private static class Match {
        private final String homeTeam;
        private final String awayTeam;
        private int homeScore;
        private int awayScore;

        public Match(String homeTeam, String awayTeam) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.homeScore = 0;
            this.awayScore = 0;
        }

        public String getHomeTeam() {
            return homeTeam;
        }

        public String getAwayTeam() {
            return awayTeam;
        }

        public int getHomeScore() {
            return homeScore;
        }

        public int getAwayScore() {
            return awayScore;
        }

        public void setHomeScore(int homeScore) {
            this.homeScore = homeScore;
        }

        public void setAwayScore(int awayScore) {
            this.awayScore = awayScore;
        }

        public int getTotalScore() {
            return homeScore + awayScore;
        }

        @Override
        public String toString() {
            return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
        }
    }
}
