/*
 * CS2010 HT: Assignment2
 * author Kazuhiro Tobita(18308725)
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;

public class CompetitionTests {
	public static final String Tiny = "tinyEWD.txt";
	public static final String Null = "tinyEWD.txt";
	public static final String A = "input-A.txt";
	public static final String B = "input-B.txt";
	public static final String C = "input-C.txt";
	public static final String D = "input-D.txt";
	public static final String E = "input-E.txt";
	public static final String F = "input-F.txt";
	public static final String G = "input-G.txt";
	public static final String H = "input-H.txt";
	public static final String I = "input-I.txt";
	public static final String J = "input-J.txt";

    @Test
    public void testDijkstraConstructor() {
    	CompetitionDijkstra competitionDijkstra = new CompetitionDijkstra(Tiny, 55, 60, 90);
    	assertEquals("Normal ", 34, competitionDijkstra.timeRequiredforCompetition());
    	competitionDijkstra = new CompetitionDijkstra("null.txt", 55, 60, 90);
		assertEquals("Null ", -1, competitionDijkstra.timeRequiredforCompetition());
    }

    @Test
    public void testFWConstructor() {
    	CompetitionFloydWarshall competitionFloydWarshall = new CompetitionFloydWarshall(Tiny, 55, 60, 90);
    	assertEquals("Normal ", 34, competitionFloydWarshall.timeRequiredforCompetition());
        competitionFloydWarshall = new CompetitionFloydWarshall("null.txt", 55, 60, 90);
		assertEquals("Null ", -1, competitionFloydWarshall.timeRequiredforCompetition());
    }

    //TODO - more tests  

    @Test
	public void testInputA() {
		CompetitionDijkstra  city1 = new CompetitionDijkstra(A, 55, 60, 90);
		CompetitionFloydWarshall city2= new CompetitionFloydWarshall(A, 50, 60, 90);
		assertEquals("Time Required", -1, city1.timeRequiredforCompetition());
		assertEquals("Time Required", -1, city2.timeRequiredforCompetition()); 	
	}
    
	@Test
	public void testInputB() {
		CompetitionDijkstra  city1 = new CompetitionDijkstra (B, 55, 60, 90);
		CompetitionFloydWarshall city2 = new CompetitionFloydWarshall(B, 55, 60, 90);
		assertEquals("Time Required", 9091, city1.timeRequiredforCompetition());
		assertEquals("Time Required", 9091, city2.timeRequiredforCompetition());
	}
	
	@Test
	public void testInputC() {
		CompetitionDijkstra  city1 = new CompetitionDijkstra (C, 55, 60, 90);
		CompetitionFloydWarshall city2 = new CompetitionFloydWarshall(C, 55, 60, 90);
		assertEquals("Time Required", -1, city1.timeRequiredforCompetition());
		assertEquals("Time Required", -1, city2.timeRequiredforCompetition());
	}
	
	@Test
	public void testInputD() {
		CompetitionDijkstra  city1 = new CompetitionDijkstra (D, 55, 60, 90);
		CompetitionFloydWarshall city2 = new CompetitionFloydWarshall(D, 55, 60, 90);
		assertEquals("Time Required", 34, city1.timeRequiredforCompetition());
		assertEquals("Time Required", 34, city2.timeRequiredforCompetition());
	}
	
	/*
	@Test
	public void testInputE() {
		CompetitionDijkstra  map1 = new CompetitionDijkstra (E, 55,60,92);
		CompetitionFloydWarshall map2 = new CompetitionFloydWarshall(E, 55,60,92);
		assertEquals(-1, map1.timeRequiredforCompetition());
		assertEquals(-1, map2.timeRequiredforCompetition());
	}*/
	
	@Test
	public void testInputF() {
		CompetitionDijkstra  city1 = new CompetitionDijkstra (F, 55, 60, 90);
		CompetitionFloydWarshall city2 = new CompetitionFloydWarshall(F, 55, 60, 90);
		assertEquals("Time Required", -1, city1.timeRequiredforCompetition());
		assertEquals("Time Required", -1, city2.timeRequiredforCompetition());
	}
	
	@Test
	public void testInputG() {
		CompetitionDijkstra  city1 = new CompetitionDijkstra (G, 55, 60, 90);
		CompetitionFloydWarshall city2 = new CompetitionFloydWarshall(G, 55, 60, 90);
		assertEquals("Time Required", -1, city1.timeRequiredforCompetition());
		assertEquals("Time Required", -1, city2.timeRequiredforCompetition());
	}
	
	@Test
	public void testInputJ() {
		CompetitionDijkstra  city1 = new CompetitionDijkstra (J, 55, 60, 90);
		CompetitionFloydWarshall city2 = new CompetitionFloydWarshall(J, 55, 60, 90);
		assertEquals("Time Required", -1, city1.timeRequiredforCompetition());
		assertEquals("Time Required", -1, city2.timeRequiredforCompetition());
	}
}