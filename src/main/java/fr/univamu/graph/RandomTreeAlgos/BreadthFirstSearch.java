package fr.univamu.graph.RandomTreeAlgos;


import fr.univamu.graph.Arc;
import fr.univamu.graph.Graph;

import java.util.*;

public class BreadthFirstSearch {

    Graph graph;
    Queue<Arc> frontier;
    ArrayList<Arc> tree;
    BitSet reached;

    private void push(int vertex) {
        for (Arc arc : graph.outNeighbours(vertex)) {
            frontier.offer(arc);
        }
    }

    private void explore(Arc nextArc) {
        if (reached.get(nextArc.getDest())) return;
        reached.set(nextArc.getDest());
        tree.add(nextArc);
        push(nextArc.getDest());
    }

    private void bfs(int startingVertex) {
        reached.set(startingVertex);
        push(startingVertex);
        while (!frontier.isEmpty()) {
            explore(frontier.poll());
        }
    }

    private BreadthFirstSearch(Graph graph) {
        this.graph = graph;
        this.frontier = new LinkedList<>();
        this.tree = new ArrayList<>();
        this.reached = new BitSet(graph.getOrder());
    }

    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        BreadthFirstSearch algo = new BreadthFirstSearch(graph);
        algo.bfs(root);
        return algo.tree;
    }
}
