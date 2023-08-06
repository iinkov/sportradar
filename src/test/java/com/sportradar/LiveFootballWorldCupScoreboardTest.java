package com.sportradar;

import org.junit.Test;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class LiveFootballWorldCupScoreboardTest {

    private LiveFootballWorldCupScoreboard scoreboard;

    @Before
    public void setUp() {
        scoreboard = new LiveFootballWorldCupScoreboard();
    }

    @Test
    public void testEmptySumarry() {
        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(0, summary.size());
    }

    @Test
    public void testStartNewMatch() {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.startNewMatch("Spain", "Brazil");

        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(2, summary.size());
        assertEquals("Mexico 0 - Canada 0", summary.get(0));
        assertEquals("Spain 0 - Brazil 0", summary.get(1));
    }
}