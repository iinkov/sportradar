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
}
