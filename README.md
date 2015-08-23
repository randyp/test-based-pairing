A simulated annealing pairing algorithm I wrote for a friend a couple years ago. I believe he was using it in a project for KUFC. Their goal was to create a web application to create study pairs within a class. I've kep most of the original algorithm code, the rest has been deleted.

#### Algorithm Premises
1. Pair concept experts with concept beginners, so one can teach the other.
2. Pair people with different (mis)understandings. If people with different misunderstandings are paired, hopefully they will resolve their different understandings, leading to concept mastery.


#### How does it work
Since pairing is a combinatoric problem, we chose [Simulated Annealing](http://en.wikipedia.org/wiki/Simulated_annealing) to reach an approximate solution in reasonable time.

The grouping algorithm defines a cost between each possible pair of students, and then minimizes a global cost using simulated annealing. The global cost is the sum of costs for each of the chosen pairs.

The minimization is performed by randomly swapping partners according to a cooling schedule for some students, and accepting the new global pairing if the global cost is better than the previous global pairing. The temperature determines the number of random pair swaps, and decreases over time.

The cost between any two students is determined by a combination of factors determined their test answers:
* Total Score Difference - difference of overall percent scores. Example: Student A had a total score of 0.4 and student B had a total score of 0.3. Their total score difference is 0.1.
* Category Score Difference - difference of percent scores for a category. Example: Student A had a category score of 0.5 for Kinematics and student B had a total score of 0.3 for Kinematics. Their Kinematics score difference is 0.2.
* Percent Correct XOR - percent of total questions where exactly one student answered correctly. Example: Student A answered 10 questions correctly and student B answered 11 questions correctly. However, they both answered 8 of the same questions correctly. The number of questions student A got correct that student b did not is 2. The number of questions student B got correct but a did not is 3. The count correct xor is 5 (2 + 3). For a 30 question test the percent correct xor is 17%.
* Percent Different - percent of total questions answered differently, where unanswered questions are always counted as different. Example: Student A and B answered 17 questions differently. On a 30 question test they would have answered 57% differently.

The pair cost is then a linear combination of those costs. Since all of the above factors are BENEFITS, we negate the sum of the benefits to get the pair cost:
pairCost(s1, s2) = -(a1 * totalScoreDifference(s1, s2) + ac1 * category1ScoreDifference(s1, s2) + ... + acn * categoryNScoreDifference(s1, s2) + a2 * percentCorrectXor(s1, s2) + a3 * percentDifference(s1, s2))


#### How does it NOT work
I mean "NOT work" figuratively.

What I do mean is that there are many arbitrary decisions made without statistical or scientific significance. We do not know that pairing people with difference misunderstandings will lead to concept mastery. We do not have validation data containing "targets" or "labels" so we can measure performance.

Any measure of performance is relative to the arbitrary decisions coded in the algorithm, and thus just as arbitrary.


#### Room for improvement
* Though the decision we chose are arbitrary, the goal of the algorithm is to maximize variance in understandings. We could define some performance measure, such as comparing the median of all possible pair costs with the media of the chosen pair costs.
* Weight individual questions when contributing to the score, either automatically or manually. Automatic question weighting could give easier questions more weight for some factors, and harder questions more weight for other factors, since answering easy questions incorrectly and answering difficult questions correctly provides more information.
* Have paired students take the FCI at the end of their course so we can perform some statistical tests and alter the algorithm appropriately.
