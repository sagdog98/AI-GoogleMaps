import java.util.HashSet;

public class GreedySearch {
	Map graph; 
	String initLocation, destination;
	int limit; 
	public int expansionCount; 
	GreedySearch(){
		
	}
	GreedySearch(Map graph, String location, String destination, int limit ) {
		this(); 
		this.graph = graph; 
		this.initLocation = location; 
		this.destination = destination;
		this.limit = limit; 
	}
	
	
	Waypoint search(boolean useRepeatedStateChecking){
		Location initLoc = graph.findLocation(initLocation);
		
		if(initLoc == null) {
			return null; 
		}
		
		Waypoint node = new Waypoint(initLoc);
		
		SortedFrontier fringe = new SortedFrontier(SortBy.h);
		fringe.addSorted(node);	
		
		HashSet<String> closedList = null;
		
		if (useRepeatedStateChecking) {
		    closedList = new HashSet<String>();
		}
		expansionCount = 0; 
		
		Location dest = graph.findLocation(destination); 
		
		GoodHeuristic hValue = new GoodHeuristic(dest, graph); 
		
		while(!fringe.isEmpty()) {
			if (node.depth >= limit) {
				return (null);
			}
			node = fringe.removeTop();
			if (node.isFinalDestination(destination)) {
				return (node);
			}
			if(useRepeatedStateChecking) {
				closedList.add(node.loc.name);
			}
			
			node.expand(hValue);
			
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
