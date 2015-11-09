package main;

import java.util.ArrayList;

public class Node {

	private int name, nrOfNeighbours;
	private ArrayList<Node> neighbours;
	private boolean hasBin;

	public Node(int newName) {
		neighbours = new ArrayList<Node>();
		hasBin = false;
		nrOfNeighbours = 0;
		name = newName;

	}

	public int getName() {
		return name;
	}

	public void addNeighbour(Node neighbour) {
		neighbours.add(neighbour);
		nrOfNeighbours++;
	}

	public ArrayList<Node> getNeighbours() {
		return neighbours;
	}

	public int getNrNeighbours() {
		return nrOfNeighbours;
	}

	public boolean hasBin() {
		return hasBin;
	}

	public boolean canPlaceBin() {
		if (hasBin)
			return false;
		else
			for (Node neighbour : neighbours)
				if (neighbour.hasBin())
					return false;
		return true;
	}

	public void placeBin() {
		hasBin = true;
	}

	public void removeBin() {
		hasBin = false;
	}

}
