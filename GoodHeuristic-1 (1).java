//
// GoodHeuristic
//
// This class extends the Heuristic class, providing a reasonable
// implementation of the heuristic function method.  The provided "good"
// heuristic function is admissible.
//
// YOUR NAME -- TODAY'S DATE
//
import java.util.*;


public class GoodHeuristic extends Heuristic {

    // YOU CAN ADD ANYTHING YOU LIKE TO THIS CLASS ... WHATEVER WOULD
    // ASSIST IN THE CALCULATION OF YOUR GOOD HEURISTIC VALUE.

    // heuristicFunction -- Return the appropriate heuristic values for the
    // given search tree node.  Note that the given Waypoint should not be
    // modified within the body of this function.

	double max_velocity = Double.MIN_VALUE; 
	
	GoodHeuristic(Location destination, Map graph){
		this.destination = destination;
		
		HashSet<String> RoadsVisited = new HashSet<String>();
		
		//calculate max velocity
		for(Location loc : graph.locations) {
			for(Road road : loc.roads) {
				// if road is visited
				String name;
				if(road.toLocationName.compareTo(road.fromLocationName) < 0 ){
					name = road.toLocationName + "_" + road.fromLocationName; 
				}else {
					name = road.fromLocationName + "_" + road.toLocationName; 
				}
				
				if(!RoadsVisited.contains(name)){
					//check if vel is greater then max vel
					double lat = road.toLocation.latitude - road.fromLocation.latitude; 
					double lng = road.fromLocation.longitude - road.fromLocation.longitude; 
					
					double distance =  Math.sqrt(Math.pow(lat, 2) + Math.pow(lng, 2));
					
					
					double velocity =  distance / road.cost; // miles per minute
					
					if( velocity > max_velocity) {
						max_velocity = velocity;
					}
					RoadsVisited.add(name); 
				}
				
			}
		}
		
	}
	
    public double heuristicFunction(Waypoint wp) {
	double hVal = 0.0;
	

	double lat = wp.loc.latitude - destination.latitude;
	double lng = wp.loc.longitude - destination.longitude; 
	
	hVal = Math.sqrt(Math.pow(lat, 2) + Math.pow(lng, 2)); // miles
	hVal = hVal / max_velocity; // minutes
	return (hVal);
    }

}
