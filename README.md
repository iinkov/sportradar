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

note: Sorry I didn't finish TDD Refactoring step last year. Also, I was to lazy to handle exceptions this time. Ideal result take times. So I just did the api the way it handle all of the values. 

- ~~Is under-modelled. Rather see a Team class which encapsulates name and score, separate Match class composed of Teams, and possibly a Repository for Matches.~~
 Sorry, I'll skip it this time
- ~~Match should be a separate class rather than being defined in LiveFootballWorldCupScoreboard.~~
- ~~Match name field should be immutable.~~
  I think it is already immutable
- ~~Match contains unused code getHomeScore() and getAwayScore().~~
- ~~LiveFootballWorldCupScoreboard.updateScore() method contains multiple redundant String.valueOf().~~
- ~~LiveFootballWorldCupScoreboard.isTheMatch() method contains multiple redundant String.valueOf().~~
- ~~LiveFootballWorldCupScoreboard.isTheMatch() method could be designed away with correct use of equals and hashcode on Match.~~
- ~~Use of System.nanoTime() for Match start time doesn’t guarantee uniqueness and could lead to Match sorting errors. A simple AtomicInteger would have solved this.~~
    Debatable. Some of big data approaches admit small amount of errors. AtomicInteger is effective, but perhaps it has some overheads. Ordering looks not so critical. Also Keep it simple principle could be applied from both hands there  
- ~~Attempting to finish a non-existent Match does not throw an exception.~~
  I didn't see any initial requirements around this. Of course thrown the exception is better behaviour. But this is just a test task. Skip it  
- ~~No validation logic. It’s possible to start a match with null/empty team names, or same teams e.g. Germany vs. Germany.~~
  Yes I cut corners here. But from other hand I am focused on show and TDD approach. I decided my lib behaviour this way. Skip
- ~~Has test case for starting same match, but this should really throw an exception as we don’t want to reset the score of an ongoing match.~~
  A matter of taste (=. Again this is just free test task. I am not looking Product Owner position this time  
- ~~Not keen on the ability to create a new Match by updating the score of a non-existing set of teams. Instead of being flexible it makes the startNewMatch() feel redundant, and causes confusion to the client.~~
  Yes you are right. But I skipp it this time. I had 2 hours. So perhaps I didn't focus on usability enough
- ~~Too much duplication in tests.~~
  This is also very Debatable topic. There is opinion, that tests should be as simple as possible and duplications are ok. I don't have enough to fix it this time  
- ~~Contains tests for null/empty team names but this should never be allowed.~~
  I just wanted to show true tdd approach. If this is not accepted I'll redo it in a year