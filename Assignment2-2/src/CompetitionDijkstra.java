/*
 * CS2010 HT: Assignment2
 * author Kazuhiro Tobita(18308725)
 */

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestantsâ€™
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {
	public String filename;
	public int userSpeedA;
	public int userSpeedB;
	public int userSpeedC;
	public int vertices;
	public int edgeCount; 
	public double[][] distTo;
	public int[][] edgeTo;
	public ArrayList<DirectedEdge>[] adj;

	/**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
	
    @SuppressWarnings("unchecked")
	CompetitionDijkstra (String filename, int sA, int sB, int sC) {
		this.userSpeedA = sA;
		this.userSpeedB = sB;
		this.userSpeedC = sC;
		try {
			File cityfile = new File(filename);
			Scanner scanner = new Scanner(cityfile);
			
			for(int i=0; scanner.hasNextLine(); i++) {
				String line = scanner.nextLine();
				
				if(i==0) {
					vertices = Integer.parseInt(line);
					edgeTo = new int[vertices][vertices];
					distTo = new double[vertices][vertices];
            		adj = new ArrayList[vertices];
            		
            		for (int row=0; row<vertices; row++) {
						adj[row] = new ArrayList<DirectedEdge>(); 
						for (int col=0; col<vertices; col++) {
							if(row==col) {
								edgeTo[row][col] = row;
								distTo[row][col] = 0;
							} else {
								edgeTo[row][col] = -1;
								distTo[row][col] = Integer.MAX_VALUE;
							}
						}
					}
				}
				
				else if(i==1) edgeCount =  Integer.parseInt(line);
				
				
				else { //if(i>1)
					int counter = 0;
					while(line.charAt(counter)==' ') {
						counter++;	
					}
					line = line.substring(counter);
					String[] splitted = line.split("\\s+");
					int edgeV = Integer.parseInt(splitted[0]);
					int edgeW = Integer.parseInt(splitted[1]);
					double weight = Double.parseDouble(splitted[2]);
					DirectedEdge edge = new DirectedEdge(edgeV, edgeW, weight);
					adj[edgeV].add(edge);
				}
			}
			for (int j=0; j<vertices; j++) {
				relax(j,j);
			}
			scanner.close();
		} catch(Exception x) {
			distTo = new double[0][0];
    		edgeTo = new int[0][0];
    		return;
		}
	}
    
    private void relax(int source, int vertex) {
		for(DirectedEdge edge : adj[vertex]) {
			int from = edge.from();
			int to = edge.to();
			if (distTo[source][to]>(distTo[source][from] + edge.weight())) {
				distTo[source][to] = edge.weight() + distTo[source][from];
				edgeTo[source][to] = from;
				relax(source,to);
			}	
		}
    }

   /**
	* @return int: minimum minutes that will pass before the three contestants can meet
	*/
    
	public int timeRequiredforCompetition() {
		//error pattern: 3 speeds must be b/w 50 & 100
		if((this.userSpeedA > 100 || this.userSpeedA < 50) || 
		   (this.userSpeedB > 100 || this.userSpeedB < 50) || 
		   (this.userSpeedC > 100 || this.userSpeedC < 50)) return -1;
		if(edgeCount <= 0 || vertices <= 0) return -1;
		
		int slowestSpeed = Math.min(userSpeedC, Math.min(userSpeedA, userSpeedB));
		
		double maxDistance = 0.0;
		for(int i=0; i<vertices; i++) {
			for(int j=0; j<vertices; j++) {
				if(edgeTo[i][j] == -1) return -1;
				if(distTo[i][j] > maxDistance) maxDistance = distTo[i][j];
			}
		}
		Double maxMins = Math.ceil((maxDistance * 1000) / slowestSpeed);
		
		return maxMins.intValue();
	}
}