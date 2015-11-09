package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Algorithm {

	/*
	 * This class checks whether it is possible to place K bins in the graph
	 */
	Graph graph;
	PriorityQueue<Node> queue;

	public Algorithm(Graph newGraph) {
		graph = newGraph;
		initQueue();
	}

	public void run() {
		boolean succes = firstAttempt();
		if (succes)
			System.out.println("possible");
		else
			search();
	}

	private boolean firstAttempt() {
		int binsPlaced = 0;
		while ((binsPlaced <= graph.getNrOfBins()) && !queue.isEmpty()) {
			Node node = queue.remove();
			if (node.canPlaceBin()) {
				node.placeBin();
				binsPlaced++;
			}
		}

		if (binsPlaced < graph.getNrOfBins())
			return false;
		else
			return true;
	}

	private void removeBins() {
		for (int i = 1; i <= graph.getNrOfIntersections(); i++)
			if (graph.getNode(i).hasBin())
				graph.getNode(i).removeBin();
	}

	private void search() {
		boolean succes = false;
		int count = 1;
		while (count <= graph.getNrOfIntersections() && !succes) {
			removeBins();
			Node node = graph.getNode(count);
			succes = breathFirstSearch(node);
			count++;
		}
		if (succes)
			System.out.println("possible");
		else
			System.out.println("impossible");
	}

	private boolean breathFirstSearch(Node root) {
		root.placeBin();
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		ArrayList<Node> seen = new ArrayList<Node>();
		queue.add(root);
		seen.add(root);
		while (!queue.isEmpty()) {
			Node current = queue.remove();
			for (Node neighbour : current.getNeighbours()) {
				if (neighbour.canPlaceBin())
					neighbour.placeBin();
				if (!seen.contains(neighbour)) {
					queue.add(neighbour);
					seen.add(neighbour);
				}
			}
		}
		return (graph.nrOfBinsPlaced() >= graph.getNrOfBins());
	}

	public void initQueue() {
		queue = new PriorityQueue<Node>(graph.getNrOfIntersections(), new Comparator<Node>() {
			public int compare(Node node1, Node node2) {
				if (node1.getNrNeighbours() > node2.getNrNeighbours())
					return 1;
				else
					return -1;
			}
		});

		for (int i = 1; i <= graph.getNrOfIntersections(); i++)
			queue.add(graph.getNode(i));
	}
}
