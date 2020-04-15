/*
 * CS2010 HT: Assignment2
 * author Kazuhiro Tobita(18308725)
 */

import java.io.File;
import java.util.Scanner;

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
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {
	public String filename;
	public int userSpeedA;
	public int userSpeedB;
	public int userSpeedC;
	public int vertices;
	public int edgeCount; 
	public double[][] matrix;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
	
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC) {
    	this.userSpeedA = sA;
        this.userSpeedB = sB;
        this.userSpeedC = sC;
    	try {
    		File cityfile = new File(filename);
			Scanner scanner = new Scanner(cityfile);
			
            for(int i=0; scanner.hasNextLine(); i++) {
            	String line = scanner.nextLine();
            	if (i==0) {
					vertices = Integer.parseInt(line);	
					matrix = new double[vertices][vertices];
					for (int row=0; row<vertices; row++) {
						for (int col=0; col<vertices; col++) {
							if(row==col) matrix[row][col] = 0;
							else matrix[row][col] = Integer.MAX_VALUE;
						}
					}
				}	

				else if (i==1) edgeCount = Integer.parseInt(line);
				
				else if (i>1) {
					int counter = 0;
					while(line.charAt(counter)==' ') {
						counter++;	
					}
					line = line.substring(counter);
					String[] splitted = line.split("\\s+");
					int edgeV = Integer.parseInt(splitted[0]);
					int edgeW = Integer.parseInt(splitted[1]);
					double weight = Double.parseDouble(splitted[2]);
					matrix[edgeV][edgeW]=weight;
				}
            }
            for(int j=0; j<matrix.length; j++) {
            	for(int k=0; k<matrix.length; k++) {
					for(int l=0; l<matrix.length; l++) {
						if(matrix[k][j] + matrix[j][l] < matrix[k][l]) matrix[k][l] = matrix[k][j] + matrix[j][l];		
					}
				}
			}
            scanner.close();
    	} catch(Exception x) {
    		matrix = new double[0][0];
    		return;
    	}
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    
    public int timeRequiredforCompetition(){
    	//error pattern: 3 speeds must be b/w 50 & 100
    	if((this.userSpeedA > 100 || this.userSpeedA < 50) || 
    	   (this.userSpeedB > 100 || this.userSpeedB < 50) || 
    	   (this.userSpeedC > 100 || this.userSpeedC < 50)) return -1;
    	if(edgeCount == 0 || vertices == 0) return -1;
 
    	int slowestSpeed = Math.min(userSpeedC, Math.min( userSpeedA, userSpeedB));
    			
    	double maxDistance = 0.0;
    	for(int i=0; i<vertices; i++) {
    		for(int j=0; j<vertices; j++) {
    			if(matrix[i][j] == Integer.MAX_VALUE) return -1;
    			if(matrix[i][j] > maxDistance) maxDistance = matrix[i][j];
    		}
    	}
    	Double maxMins = Math.ceil((maxDistance * 1000) / slowestSpeed);
    			
    	if(slowestSpeed <= 0 || maxDistance == 0) return - 1;
        return maxMins.intValue();
    }

}