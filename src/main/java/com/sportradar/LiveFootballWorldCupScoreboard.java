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
        updateScore(homeTeam, awayTeam, 0, 0);
    }

    public List<String> getMatchesSummary() {
        matches.sort(Comparator.comparingInt(Match::getTotalScore).reversed().thenComparing(Comparator.comparing(Match::getStartTime)
                .reversed()));
        return matches.stream().map(Match::toString).collect(Collectors.toList());
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Optional<Match> maybeMatch = findMatch(homeTeam, awayTeam);
        maybeMatch.ifPresentOrElse(match ->
                        updateScore(homeScore, awayScore, match),
                () -> {
                    Match match = new Match(String.valueOf(homeTeam), String.valueOf(awayTeam));
                    updateScore(homeScore, awayScore, match);
                    matches.add(match);
                }
        );
    }

    private void updateScore(int homeScore, int awayScore, Match match) {
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    }

    private Optional<Match> findMatch(String homeTeam, String awayTeam) {
        return matches.stream().
                filter(match -> isTheMatch(homeTeam, awayTeam, match)).findFirst();
    }

    private boolean isTheMatch(String homeTeam, String awayTeam, Match match) {
        return String.valueOf(match.getHomeTeam()).equals(String.valueOf(homeTeam)) && String.valueOf(match.getAwayTeam()).
                equals(String.valueOf(awayTeam));
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        matches.removeIf(match -> isTheMatch(homeTeam, awayTeam, match));
    }
}
