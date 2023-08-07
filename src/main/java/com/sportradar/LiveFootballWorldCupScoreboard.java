package com.sportradar;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LiveFootballWorldCupScoreboard {
    private final List<Match> matches;

    public LiveFootballWorldCupScoreboard() {
        this.matches = new ArrayList<>();
    }

    public void startNewMatch(String homeTeam, String awayTeam) {
        findMatch(homeTeam, awayTeam).orElseGet(() ->
                {
                    matches.add(new Match(String.valueOf(homeTeam), String.valueOf(awayTeam)));
                    return null;
                }
        );
    }

    public List<String> getMatchesSummary() {
        matches.sort(Comparator.comparingInt(Match::getTotalScore).reversed().thenComparing(Comparator.comparing(Match::getStartTime)
                .reversed()));
        return matches.stream().map(Match::toString).collect(Collectors.toList());
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Optional<Match> maybeMatch = findMatch(homeTeam, awayTeam);
        maybeMatch.ifPresentOrElse(match ->
                {
                    match.setHomeScore(homeScore);
                    match.setAwayScore(awayScore);
                },
                () -> {
                    startNewMatch(homeTeam, awayTeam);
                    updateScore(homeTeam, awayTeam, homeScore, awayScore);
                }
        );
    }

    private Optional<Match> findMatch(String homeTeam, String awayTeam) {
        return matches.stream().
                filter(match -> String.valueOf(match.getHomeTeam()).equals(String.valueOf(homeTeam)) && String.valueOf(match.getAwayTeam()).
                        equals(String.valueOf(awayTeam))).findFirst();
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        matches.removeIf(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));
    }

    private static class Match {
        private final String homeTeam;
        private final String awayTeam;
        private int homeScore;
        private int awayScore;
        private final long startTime;

        public Match(String homeTeam, String awayTeam) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.homeScore = 0;
            this.awayScore = 0;
            this.startTime = System.nanoTime();
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

        public long getStartTime() {
            return startTime;
        }

        @Override
        public String toString() {
            return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
        }
    }
}
