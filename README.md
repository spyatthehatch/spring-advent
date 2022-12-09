# spring-advent
This project is a sandbox for testing Advent of Code solutions while getting
more experience developing with Spring Boot.

### Advent of Code

I thank [Eric Wastl](https://adventofcode.com/2022/about) for creating what has
become one of my favorite times of the year.  His dedication to keeping AoC
fun, fresh, and exciting is truly a gift to the community.  Thank you, Eric!

[Advent of Code](https://adventofcode.com/2022)

My solutions are definitely not elegant and I use a lot of brute force
techniques.  With the loops within loops within loops, I'm certain my
algorithms are approaching O(N^3+).

Maybe when this year is over, I'll go through the previous years' advent
challenges and attempt to find some elegant solutions.

### Spoiler Alert
These are solutions that worked for my input sets.  Be advised that you may
discover answers to questions you don't want answered.

### Compiling
Compiling requires Maven 3.6.3 (or later) and Java 8.
```sh
mvnw spring-boot:run
````

### Javadoc
To generate Javadoc, run the javadoc execution.  Javadoc will be generated in
target/site/apidocs directory.
```sh
mvn clean javadoc:javadoc
```

### Coming Soon

Ability to upload your input sets and return a solution.  It would be rewarding
for me to verify that my algorithm works with other input sets, and vice versa.
This would also give me the opportunity for some front-end development, as most
of my career has been in back-end services.

Unit tests that use the Advent of Code examples to assert methods are correctly
returning expected results. 

### 2022 Personal Stats

Not entirely impressive, but my focus this year is learning a new technology
and getting 50 stars.  We'll see how this goes!
</pre>
      --------Part 1--------   --------Part 2--------
Day       Time   Rank  Score       Time   Rank  Score
  9   00:57:17   8037      0   01:12:28   5329      0
  8   00:39:40   7461      0   01:19:15   8582      0
  7   00:52:49   5183      0   01:09:19   5711      0
  6   00:30:40  14330      0   00:33:38  13564      0
  5   00:28:10   5048      0   00:32:39   4788      0
  4   00:11:33   5502      0   00:25:06   8318      0
  3   00:40:58  12327      0   00:54:48  11355      0
  2   00:15:49   6255      0   00:22:58   5723      0
  1   00:09:16   5005      0   00:13:27   5001      0
</pre>

-S@tH