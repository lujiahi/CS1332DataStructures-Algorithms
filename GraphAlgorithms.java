
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgorithms {

	/**
	 * Performs a breadth first search (bfs) on the input graph, starting at
	 * the parameterized starting vertex.
	 *
	 * When exploring a vertex, explore in the order of neighbors returned by
	 * the adjacency list. Failure to do so may cause you to lose points.
	 *
	 * You may import/use java.util.Set, java.util.List, java.util.Queue, and
	 * any classes that implement the aforementioned interfaces, as long as they
	 * are efficient.
	 *
	 * The only instance of java.util.Map that you should use is the adjacency
	 * list from graph. DO NOT create new instances of Map for BFS
	 * (storing the adjacency list in a variable is fine).
	 *
	 * DO NOT modify the structure of the graph. The graph should be unmodified
	 * after this method terminates.
	 *
	 * You may assume that the passed in start vertex and graph will not be null.
	 * You may assume that the start vertex exists in the graph.
	 *
	 * @param <T>   The generic typing of the data.
	 * @param start The vertex to begin the bfs on.
	 * @param graph The graph to search through.
	 * @return List of vertices in visited order.
	 */
	public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		Set<Vertex<T>> visitedSet = new HashSet<>();	// initialize the visited set
		Queue<Vertex<T>> queue = new ArrayDeque<>();	// initialize the queue
		List<Vertex<T>> visitedList = new ArrayList<>();	// initialize the visited list to return
		Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();	// get the adjacency list
		visitedSet.add(start);
		visitedList.add(start);
		queue.add(start);	// enqueue the starting vertex

		while(!queue.isEmpty()) {
			Vertex<T> v = queue.remove();
			List<VertexDistance<T>> adjEdges = adjList.get(v);
			for(VertexDistance<T> vd : adjEdges) {	// loop through the adjacency list of that vertex
				Vertex<T> u = vd.getVertex();
				if(!visitedSet.contains(u)) {
					visitedSet.add(u);
					visitedList.add(u);
					queue.add(u);
				}
			}
		}
		return visitedList;
	}

	/**
	 * Performs a depth first search (dfs) on the input graph, starting at
	 * the parameterized starting vertex.
	 *
	 * When exploring a vertex, explore in the order of neighbors returned by
	 * the adjacency list. Failure to do so may cause you to lose points.
	 *
	 * NOTE: This method should be implemented recursively. You may need to
	 * create a helper method.
	 *
	 * You may import/use java.util.Set, java.util.List, and any classes that
	 * implement the aforementioned interfaces, as long as they are efficient.
	 *
	 * The only instance of java.util.Map that you may use is the adjacency list
	 * from graph. DO NOT create new instances of Map for DFS
	 * (storing the adjacency list in a variable is fine).
	 *
	 * DO NOT modify the structure of the graph. The graph should be unmodified
	 * after this method terminates.
	 *
	 * You may assume that the passed in start vertex and graph will not be null.
	 * You may assume that the start vertex exists in the graph.
	 *
	 * @param <T>   The generic typing of the data.
	 * @param start The vertex to begin the dfs on.
	 * @param graph The graph to search through.
	 * @return List of vertices in visited order.
	 */
	public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		Set<Vertex<T>> visitedSet = new HashSet<>();
		List<Vertex<T>> visitedList = new ArrayList<>();
		Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
		visitedSet.add(start);
		visitedList.add(start);
		dfsHelper(start, visitedSet, visitedList, adjList);
		return visitedList;
	}

	private static <T> void dfsHelper(Vertex<T> current, Set<Vertex<T>> visitedSet, List<Vertex<T>> visitedList, Map<Vertex<T>, List<VertexDistance<T>>> adjList){	// a recursive method that uses a visited set to terminate
		List<VertexDistance<T>> adjEdges = adjList.get(current);
		for(VertexDistance<T> vd : adjEdges) {
			Vertex<T> u = vd.getVertex();
			if(!visitedSet.contains(u)) {
				visitedSet.add(u);
				visitedList.add(u);
				dfsHelper(u, visitedSet, visitedList, adjList);
			}
		}
	}
	

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin Prims on.
     * @param graph The graph we are applying Prims to.
     * @return The MST of the graph or null if there is no valid MST.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    	Set<Vertex<T>> visitedSet = new HashSet<>();	// initialize visited set to check whether the vertex might form a cycle and thus should be excluded
    	Set<Edge<T>> mst = new HashSet<>();	// the minimum spanning tree to be returned
    	Queue<Edge<T>> pq = new PriorityQueue<>();	// use a priority queue to always return the "shortest" edge
    	Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();	// get the adjacency list
    	
    	for(VertexDistance<T> vd : adjList.get(start)) {	// loop through all edges from the starting vertex and add them to the priority queue
    		Vertex<T> adjVertex = vd.getVertex();
    		Edge<T> edge = new Edge<>(start, adjVertex, vd.getDistance());
    		pq.add(edge);
    	}
    	visitedSet.add(start);	// add starting vertex to the visited set
    	
    	while(!pq.isEmpty() && visitedSet.size() < graph.getVertices().size()) {	// terminating condition 1: priority queue is empty, condition 2: all vertices have been visited
    		Edge<T> currentShortest = pq.remove();	// dequeue from the priority queue
    		Vertex<T> v = currentShortest.getV();
    		if(!visitedSet.contains(v)) {	// check if the adjacent vertex has been visited
    			visitedSet.add(v);	// if not, add it to the visited set
    			mst.add(currentShortest);	// the edge is a valid one to be added to the minimum spanning tree
    			Vertex<T> u = currentShortest.getU();
    			Edge<T> currentShortestReverse = new Edge<T>(v, u, currentShortest.getWeight());	// construct a reverse edge and add it to the minimum spanning tree per requirement
    			mst.add(currentShortestReverse);
    			for(VertexDistance<T> vd : adjList.get(v)) {	// consider all the edges from v
    				if(!visitedSet.contains(vd.getVertex())){
    					Edge<T> edge = new Edge<>(v, vd.getVertex(), vd.getDistance());
    					pq.add(edge);
    				}
    			}
    		}
    	}
    	
    	if(mst.size() != (graph.getVertices().size() - 1) * 2) {	// final step to check whether the MST is valid: it should have (V-1)*2 edges; if not, return null
    		return null;
    	}
    	
    	return mst;
    }

	public static void main(String[] args) {
		Vertex<Character> a = new Vertex<>('A');
		Vertex<Character> b = new Vertex<>('B');
		Vertex<Character> c = new Vertex<>('C');
		Vertex<Character> d = new Vertex<>('D');
		Vertex<Character> e = new Vertex<>('E');
		Vertex<Character> f = new Vertex<>('F');
		Vertex<Character> g = new Vertex<>('G');
		Vertex<Character> h = new Vertex<>('H');
		Vertex<Character> i = new Vertex<>('I');
		Vertex<Character> j = new Vertex<>('J');
		Vertex<Character> k = new Vertex<>('K');
		Vertex<Character> l = new Vertex<>('L');
		Set<Vertex<Character>> vertexSet = new HashSet<>();
		vertexSet.add(a);
		vertexSet.add(b);
		vertexSet.add(c);
		vertexSet.add(d);
		vertexSet.add(e);
		vertexSet.add(f);
		vertexSet.add(g);
		vertexSet.add(h);
		vertexSet.add(i);
		vertexSet.add(j);
		vertexSet.add(k);
		vertexSet.add(l);
		Edge<Character> ab = new Edge<>(a, b, 0);
		Edge<Character> ba = new Edge<>(b, a, 0);
		Edge<Character> ac = new Edge<>(a, c, 1);
		Edge<Character> ca = new Edge<>(c, a, 1);
		Edge<Character> ad = new Edge<>(a, d, 2);
		Edge<Character> da = new Edge<>(d, a, 2);
		Edge<Character> dg = new Edge<>(d, g, 3);
		Edge<Character> gd = new Edge<>(g, d, 3);
		Edge<Character> be = new Edge<>(b, e, 4);
		Edge<Character> eb = new Edge<>(e, b, 4);
		Edge<Character> ec = new Edge<>(e, c, 5);
		Edge<Character> ce = new Edge<>(c, e, 5);
		Edge<Character> ei = new Edge<>(e, i, 6);
		Edge<Character> ie = new Edge<>(i, e, 6);
		Edge<Character> eh = new Edge<>(e, h, 7);
		Edge<Character> he = new Edge<>(h, e, 7);
		Edge<Character> hk = new Edge<>(h, k, 8);
		Edge<Character> kh = new Edge<>(k, h, 8);
		Edge<Character> kj = new Edge<>(k, j, 7);
		Edge<Character> jk = new Edge<>(j, k, 7);
		Edge<Character> jg = new Edge<>(j, g, 6);
		Edge<Character> gj = new Edge<>(g, j, 6);
		Edge<Character> gi = new Edge<>(g, i, 5);
		Edge<Character> ig = new Edge<>(i, g, 5);
		Edge<Character> cf = new Edge<>(c, f, 4);
		Edge<Character> fc = new Edge<>(f, c, 4);
		Edge<Character> cg = new Edge<>(c, g, 3);
		Edge<Character> gc = new Edge<>(g, c, 3);
		Edge<Character> fi = new Edge<>(f, i, 2);
		Edge<Character> if1 = new Edge<>(i, f, 2);
		Edge<Character> ik = new Edge<>(i, k, 1);
		Edge<Character> ki = new Edge<>(k, i, 1);
		Set<Edge<Character>> edgeSet = new HashSet<>();
		edgeSet.add(ab);
		edgeSet.add(ba);
		edgeSet.add(ac);
		edgeSet.add(ca);
		edgeSet.add(ad);
		edgeSet.add(da);
		edgeSet.add(be);
		edgeSet.add(eb);
		edgeSet.add(ce);
		edgeSet.add(ec);
		edgeSet.add(cg);
		edgeSet.add(gc);
		edgeSet.add(dg);
		edgeSet.add(gd);
		edgeSet.add(cf);
		edgeSet.add(fc);
		edgeSet.add(be);
		edgeSet.add(eh);
		edgeSet.add(he);
		edgeSet.add(fi);
		edgeSet.add(if1);
		edgeSet.add(ei);
		edgeSet.add(ie);
		edgeSet.add(gi);
		edgeSet.add(ig);
		edgeSet.add(gj);
		edgeSet.add(jg);
		edgeSet.add(hk);
		edgeSet.add(kh);
		edgeSet.add(ki);
		edgeSet.add(ik);
		edgeSet.add(kj);
		edgeSet.add(jk);
		Graph<Character> graph = new Graph<>(vertexSet, edgeSet);
		System.out.println(bfs(a, graph));
		System.out.println(dfs(a, graph));
		System.out.println(prims(a, graph));	// should be null because vertex L is disconnected
	}
}
