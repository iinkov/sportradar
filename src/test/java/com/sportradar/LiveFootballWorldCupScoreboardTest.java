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
    public void testEmptySummary() {
        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(0, summary.size());
    }

    @Test
    public void testStartSameMatch() {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.startNewMatch("Mexico", "Canada");

        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(1, summary.size());
        assertEquals("Mexico 0 - Canada 0", summary.get(0));
    }

    @Test
    public void testStartFinishStart() {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.finishMatch("Mexico", "Canada");
        scoreboard.startNewMatch("Mexico", "Canada");

        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(1, summary.size());
        assertEquals("Mexico 0 - Canada 0", summary.get(0));
    }

    @Test
    public void testStartNewMatch() {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.startNewMatch("Spain", "Brazil");

        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(2, summary.size());
        assertEquals("Spain 0 - Brazil 0", summary.get(0));
        assertEquals("Mexico 0 - Canada 0", summary.get(1));
    }

    @Test
    public void testUpdateScoreWithoutExistingMatch() {
        scoreboard.updateScore("Mexico", "Canada", 1, 5);

        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(1, summary.size());
        assertEquals("Mexico 1 - Canada 5", summary.get(0));
    }

    @Test
    public void testUpdateScore() {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Mexico", "Canada", 1, 5);

        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(1, summary.size());
        assertEquals("Mexico 1 - Canada 5", summary.get(0));
    }

    @Test
    public void testFinishMatchNotExistingMatch() {
        scoreboard.startNewMatch("Spain", "Brazil");

        scoreboard.finishMatch("Mexico", "Canada");

        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(1, summary.size());
        assertEquals("Spain 0 - Brazil 0", summary.get(0));
    }

    @Test
    public void testFinishMatch() {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.startNewMatch("Spain", "Brazil");

        scoreboard.finishMatch("Mexico", "Canada");

        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(1, summary.size());
        assertEquals("Spain 0 - Brazil 0", summary.get(0));
    }

    @Test
    public void testGetMatchesSummaryOrderedByTotalScoreAndMostResentMatch() {
        scoreboard.startNewMatch("Mexico", "Canada");
        scoreboard.startNewMatch("Spain", "Brazil");
        scoreboard.startNewMatch("Germany", "France");
        scoreboard.startNewMatch("Uruguay", "Italy");
        scoreboard.startNewMatch("Argentina", "Australia");

        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(5, summary.size());
        assertEquals("Uruguay 6 - Italy 6", summary.get(0));
        assertEquals("Spain 10 - Brazil 2", summary.get(1));
        assertEquals("Mexico 0 - Canada 5", summary.get(2));
        assertEquals("Argentina 3 - Australia 1", summary.get(3));
        assertEquals("Germany 2 - France 2", summary.get(4));
    }

    @Test
    public void testNullBehaviour() {
        scoreboard.startNewMatch(null, null);

        scoreboard.updateScore(null, null, 0, 5);

        List<String> summary = scoreboard.getMatchesSummary();
        assertEquals(1, summary.size());
        assertEquals("null 0 - null 5", summary.get(0));
    }
}
