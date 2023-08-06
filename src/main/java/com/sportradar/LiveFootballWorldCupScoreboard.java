package com.sportradar;

import java.util.ArrayList;
import java.util.List;

public class LiveFootballWorldCupScoreboard {

    private List<String> matchesSummary;

    public LiveFootballWorldCupScoreboard() {
        matchesSummary = new ArrayList<>();
    }

    public void startNewMatch(String teamA, String teamB) {
        String matchSummary = teamA + " 0 - " + teamB + " 0";
        matchesSummary.add(matchSummary);
    }

    public List<String> getMatchesSummary() {
        return matchesSummary;
    }
}
