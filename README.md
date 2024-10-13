# Comments
Hope I get "Most recent ordering" right.
I assume that absolute scores could be corrected many times but the last one is valid
I've added more functional style (Not sure if you prefer this way)
I decided not to throw any exception if we try to update not started match and just start it automatically. We initialize new match with 0, so it looks native 

# How to run 
mvn test

# Environment
Maven and Java 17 have to be installed

# Last feedback
Pros:

100% test coverage.
Nice use of injection for list of Matches in LiveFootballWorldCupScoreboard.
Scenario in test spec is tested.
Cons:

- Is under-modelled. Rather see a Team class which encapsulates name and score, separate Match class composed of Teams, and possibly a Repository for Matches.
- ~~Match should be a separate class rather than being defined in LiveFootballWorldCupScoreboard.~~
- Match name field should be immutable.
- ~~Match contains unused code getHomeScore() and getAwayScore().~~
- ~~LiveFootballWorldCupScoreboard.updateScore() method contains multiple redundant String.valueOf().~~
- ~~LiveFootballWorldCupScoreboard.isTheMatch() method contains multiple redundant String.valueOf().~~
- LiveFootballWorldCupScoreboard.isTheMatch() method could be designed away with correct use of
- equals and hashcode on Match.
- Use of System.nanoTime() for Match start time doesn’t guarantee uniqueness and could lead to Match
- sorting errors. A simple AtomicInteger would have solved this.
- Attempting to finish a non-existent Match does not throw an exception.
- No validation logic. It’s possible to start a match with null/empty team names, or same teams e.g. Germany vs. Germany.
- Has test case for starting same match, but this should really throw an exception as we don’t want to reset the score of an ongoing match.
- Not keen on the ability to create a new Match by updating the score of a non-existing set of teams. Instead of being flexible it makes the startNewMatch() feel redundant, and causes confusion to the client.
- Too much duplication in tests.
- Contains tests for null/empty team names but this should never be allowed.