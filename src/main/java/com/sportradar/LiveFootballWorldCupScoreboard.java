package com.sportradar;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LiveFootballWorldCupScoreboard {
    private final List<Match> matches;
    private final AtomicInteger serialNumber = new AtomicInteger();
    public static final int NOT_USED = -1;

    public LiveFootballWorldCupScoreboard() {
        this.matches = new LinkedList<>();
    }

    public void startNewMatch(String homeTeam, String awayTeam) {
        updateScore(homeTeam, awayTeam, 0, 0);
    }

    public List<String> getMatchesSummary() {
        matches.sort(Comparator.comparingInt(Match::getTotalScore).reversed().thenComparing(Comparator.comparing(Match::getSerialNumber)
                .reversed()));
        return matches.stream().map(Match::toString).collect(Collectors.toList());
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Optional<Match> maybeMatch = findMatch(homeTeam, awayTeam);
        maybeMatch.ifPresentOrElse(match ->
                        updateScore(homeScore, awayScore, match),
                () -> {
                    Match match = new Match(homeTeam, awayTeam, serialNumber.getAndIncrement());
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
        Match match = new Match(homeTeam, awayTeam, NOT_USED);
        return matches.stream().filter(m -> m.equals(match)).findFirst();
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        matches.remove(new Match(homeTeam, awayTeam, NOT_USED));
    }
}
