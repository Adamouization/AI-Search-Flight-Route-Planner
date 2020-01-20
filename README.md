# AI-Search-Flight-Route-Planner

Implementation and evaluation of a number of AI search algorithms applied to the task of a flight route planner. Search algorithms include Breadth-First Search, Depth-First Search, Best-First Search and A* Search.

The report, including a summary of features implemented, design & implementation decisions, evaluation and testing, can be read [here](https://github.com/Adamouization/AI-Search-Flight-Route-Planner/blob/master/report/report.pdf).

## Installation

1. Clone the project: `git clone https://github.com/Adamouization/AI-Search-Flight-Route-Planner`

2. Cd into the directory and compile the files:

```
cd AI-Search-Flight-Route-Planner
javac src/A1Main.java
```

## Usage

`java A1Main <search_type> <world_size> <start_goal> <end_goal> [<obstacles>]`

where:
* `search_type` is the type of search algorithm to use to find a solution e.g. DFS, BFS, AStar, BestF.
* `world_size` is the size of the world *N* (number of parallels).
* `start_goal` is the starting point of the agent.
* `end_goal` is the goal point that the agent must reach.
* `obstacles` is a number of points that the search algorithms cannot take when looking for a route.
    
Examples:

* BFS: `java A1Main BFS 5 2,45 3,225`
* DFS: `java A1Main DFS 8 1,315 5,270`
* BestF with 1 obstacle: `java A1Main BestF 4 1,45 3,225 1,90`
* A* with 2 obstacles: `java A1Main AStar 4 1,45 3,225 1,90 1,0`
* No route: `java A1Main BFS 4 1,45 3,225 1,90 1,0 2,45`

## Javadocs

1. Generate the Javadocs: `javadoc -d javadoc src/*.java`

2. Open `javadoc/index.html` in your web browser.

## Contact
* email: adam@jaamour.com
* LinkedIn: [www.linkedin.com/in/adamjaamour](https://www.linkedin.com/in/adamjaamour/)
* website: www.adam.jaamour.com
* twitter: [@Adamouization](https://twitter.com/Adamouization)
