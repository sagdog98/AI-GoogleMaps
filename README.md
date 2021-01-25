# AI-GoogleMaps
You are to provide JavaTM classes that implement the following three search algorithms: uniform- cost search, greedy best-first search, and A* search. In fact, all of these classes must have the following features . . .
• a constructor that takes four arguments:
1. a complete Map object, encoding the map to be searched
2. a String providing the name of the starting location
3. a String providing the name of the destination location
4. an integer depth limit for the search — if this depth is ever reached during a search (i.e., a node at this depth or deeper is considered for expansion), the 

Utilities to design and implement throughout the project:

• Location — This object encodes a location on the map. It is a “state” in the “state space” to be searched.

• Road — This object encodes a road segment from one location to another. Each Location records all of the road segments leading away from that location. This list of Road objects provides the “successor function” result for each “state”.
• Map — This object gathers together all of the Location objects on the map. It provides utilities for reading map descriptions from files.
3
• Waypoint — This object encodes a “node” in the search tree. Each Waypoint represents a Location in the context of a particular path from the starting point. Note that these objects also record the partial path cost and the heuristic function value associated with the given search tree node.
• Frontier — This object provides a simple implementation of queues and stacks con- taining Waypoint objects. This can be used to keep track of the nodes in a search tree’s “frontier” or “fringe”. Note that this class is provided only for your reference, and it should not be used for this assignment. Instead, some form of priority queue is needed to implement the search tree’s “frontier” in the search algorithms to be implemented in this assignment, and the next class, described below, implements such a priority queue.
• SortedFrontier – This object provides a simple implementation of priority queues con- taining Waypoint objects. When search tree nodes are to be removed from the “frontier” in an order specified by some real-valued numeric ranking, as is needed for all three of the search algorithms to be implemented here, this class provides an ideal way to keep track of the nodes in a search tree’s “fringe”. Objects of this type maintain a “sorting strategy” field which indicates the statistic of the contained Waypoint objects that should be used for sorting nodes. The nodes can be sorted by partial path cost, by heuristic function value, or by f-cost.
• Heuristic – This object provides a way for heuristic functions to be recorded and passed as arguments to other functions. Heuristic function objects keep track of the current search’s destination location, and they provide a method that assigns a real-valued heuristic value to any given Waypoint object, given that target destination. The basic Heuristic class assigns a zero value to all search tree nodes, which is an admissible heuristic function, but not a very useful one.
• GoodHeuristic – This class extends the Heuristic class, allowing you to override the useless heuristic provided by that parent class with something that is a better estimate of the residual path cost from a given node. You are free to cache any information you like in GoodHeuristic objects, and you should strive to make your heuristic function as accurate as possible while still remaining admissible and quick to calculate. You are required to submit a completed copy of this provided file for evaluation.
• Pone — This object provides a top-level driver that tests your search algorithms, with re- peated state checking both turned on and turned off. Your code must allow Pone to produce correct output.
