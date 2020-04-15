/*
 * CS2010 HT: Assignment2
 * author Kazuhiro Tobita(18308725)
 */


public class DirectedEdge {
	private final int edgeV;
	private final int edgeW;
	private final double weight;
	
	public DirectedEdge(int v, int w, double weight) {
		this.edgeV = v;
		this.edgeW = w;
		this.weight = weight;
	}
	public double weight() {
		return weight;
	}
	public int from() {
		return edgeV;
	}
	public int to() {
		return edgeW;
	}
}
