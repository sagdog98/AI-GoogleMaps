import java.util.HashSet;

public class AStarSearch {
	Map graph; 
	String initLocation, destination;
	int limit; 
	public int expansionCount; 
	AStarSearch(){
		
	}
	AStarSearch(Map graph, String location, String destination, int limit ) {
		this(); 
		this.graph = graph; 
		this.initLocation = location; 
		this.destination = destination;
		this.limit = limit; 
	}
	
	
	Waypoint search(boolean useRepeatedStateChecking){
		Location initLoc = graph.findLocation(initLocation);
		Location dest = graph.findLocation(destination); 
		if(initLoc == null) {
			return null; 
		}
		
		Waypoint node = new Waypoint(initLoc);
		
		SortedFrontier fringe = new SortedFrontier(SortBy.f); 
		fringe.addSorted(node);	
		
		HashSet<String> closedList = null;
		if (useRepeatedStateChecking) {
		    closedList = new HashSet<String>();
		}
		expansionCount = 0; 
		
		GoodHeuristic heuristic = new GoodHeuristic(dest, graph);
		
		while(!fringe.isEmpty()) {
			
			if (node.depth >= limit) {
				// Failure to find a solution within the allowed depth ...
				return (null);
			}
			node = fringe.removeTop();
			if (node.isFinalDestination(destination)) {
				// We have found a goal node, so return it from this method ...
				return (node);
			}
			if(useRepeatedStateChecking) {
				closedList.add(node.loc.name);
			}
			
			node.expand(heuristic); 
			expansionCount++; 
			
			for (Waypoint subNode : node.options) {
				if(!useRepeatedStateChecking || (!(fringe.contains(subNode)) && !(closedList.contains(subNode.loc.name)))){
					fringe.addSorted(subNode);
				} else if (fringe.contains(subNode) && fringe.find(subNode).partialPathCost > subNode.partialPathCost) {
					fringe.remove(fringe.find(subNode));
					fringe.addSorted(subNode);
				}
				
			}
			
		}
		return null; 
	}
	
}
