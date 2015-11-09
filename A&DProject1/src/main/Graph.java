package main;

import java.util.ArrayList;

public class Graph {

	private ArrayList<Node> nodes;
	private int nrOfIntersections, nrOfStreets, nrOfBins;

	public Graph(ArrayList<Integer> metadata, ArrayList<ArrayList> pairs) {
		nodes = new ArrayList<Node>();
		nrOfIntersections = metadata.get(1);
		nrOfStreets = metadata.get(0);
		nrOfBins = metadata.get(2);
		constructGraph(pairs);

	}

	private void constructGraph(ArrayList<ArrayList> pairs) {
		for (int i = 1; i <= nrOfIntersections; i++) {
			nodes.add(new Node(i));
		}
		addNeighbours(pairs);
	}

	private void addNeighbours(ArrayList<ArrayList> pairs) {
		Node node1, node2;
		for (ArrayList<Integer> pair : pairs) {
			node1 = getNode(pair.get(0));
			node2 = getNode(pair.get(1));
			node1.addNeighbour(node2);
			node2.addNeighbour(node1);
		}
	}

	public int getNrOfIntersections() {
		return nrOfIntersections;
	}

	public int getNrOfBins() {
		return nrOfBins;
	}

	public Node getNode(int name) {
		for (Node node : nodes) {
			if (node.getName() == name)
				return node;
		}
		return null;
	}

}
