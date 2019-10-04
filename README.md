# AI-Search-Flight-Route-Planner

todo: add description

## Installation

1. Clone the project: `git clone https://github.com/Adamouization/AI-Search-Flight-Route-Planner`

2. Cd into the directory and compile the files:

```
cd AI-Search-Flight-Route-Planner/src
javac A1Main.java
```

## Usage

`java A1Main <search_type> <world_size> <start_goal> <end_goal>`

where:
* `search_type` is the type of search algorithm to use to find a solution e.g. DFS, BFS, AStar, BestF.
* `world_size` is the size of the world *N* (number of parallels).
* `start_goal` is the starting point of the agent.
* `end_goal` is the goal point that the agent must reach.
    
Examples:

* `java A1Main BFS 5 2,45 3,225`

## Javadocs

1. Generate the Javadocs: `javadoc -d javadoc src/*.java`

2. Open `javadoc/index.html` in your web browser.

## Contact
* email: adam@jaamour.com
* LinkedIn: [www.linkedin.com/in/adamjaamour](https://www.linkedin.com/in/adamjaamour/)
* website: [www.adam.jaamour.com](www.adam.jaamour.com)
* twitter: [@Adamouization](https://twitter.com/Adamouization)
