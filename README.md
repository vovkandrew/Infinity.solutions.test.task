"# Infinity.solutions.test.task"
For the person who is gonna review my work: unfortunately, I am not familiar with Front-end 
development, therefore, I didn't manage to add UI part and decided to skip it, sorry for that.
But, before starting working on this task, I asked your company's HR manager if I can do that, and
she said that it's fine. Thank you in advance for the understanding.

To run the application, please copy this command:
"java -jar {path.to.project}/target/demo-0.0.1-SNAPSHOT.jar" 
and paste it into the terminal command line.
To check the GameController please follow this link once you successfully run the application: 
http://localhost:8081/bowling/play?hit={hit.amount} 
You can set a preferable port at the application.properties file.

Test task description:
http://slocums.homestead.com/gamescore.html

Input: number of pins hit by the roll. 
Checks: pin number check for every roll, end of the game check.
Output: Scoreboard displaying frames, total score by frames, hits within the frame, 
        including Strikes, Spares and Misses (see the link above for reference).
Displaying Splits and Fouls is NOT required.
Scoreboard is to be refreshed after every roll.
UI: Simple web-application
Technologies: Groovy/Grails (preferably) or Spring, Java
Unit-Tests: yes
Code should be well structured, readable, clean and testable. Comments should not be necessary. 
        Minimalistic but clean styling for the Scoreboard would be appreciated.

