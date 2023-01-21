

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
		Edge<Character> ab = new Edge<>(a, b, 0);
		Edge<Character> ba = new Edge<>(b, a, 0);
		Edge<Character> ac = new Edge<>(a, c, 0);
		Edge<Character> ca = new Edge<>(c, a, 0);
		Edge<Character> ad = new Edge<>(a, d, 0);
		Edge<Character> da = new Edge<>(d, a, 0);
		Edge<Character> dg = new Edge<>(d, g, 0);
		Edge<Character> gd = new Edge<>(g, d, 0);
		Edge<Character> be = new Edge<>(b, e, 0);
		Edge<Character> eb = new Edge<>(e, b, 0);
		Edge<Character> ec = new Edge<>(e, c, 0);
		Edge<Character> ce = new Edge<>(c, e, 0);
		Edge<Character> ei = new Edge<>(e, i, 0);
		Edge<Character> ie = new Edge<>(i, e, 0);
		Edge<Character> eh = new Edge<>(e, h, 0);
		Edge<Character> he = new Edge<>(h, e, 0);
		Edge<Character> hk = new Edge<>(h, k, 0);
		Edge<Character> kh = new Edge<>(k, h, 0);
		Edge<Character> kj = new Edge<>(k, j, 0);
		Edge<Character> jk = new Edge<>(j, k, 0);
		Edge<Character> jg = new Edge<>(j, g, 0);
		Edge<Character> gj = new Edge<>(g, j, 0);
		Edge<Character> gi = new Edge<>(g, i, 0);
		Edge<Character> ig = new Edge<>(i, g, 0);
		Edge<Character> cf = new Edge<>(c, f, 0);
		Edge<Character> fc = new Edge<>(f, c, 0);
		Edge<Character> cg = new Edge<>(c, g, 0);
		Edge<Character> gc = new Edge<>(g, c, 0);
		Edge<Character> fi = new Edge<>(f, i, 0);
		Edge<Character> if1 = new Edge<>(i, f, 0);
		Edge<Character> ik = new Edge<>(i, k, 0);
		Edge<Character> ki = new Edge<>(k, i, 0);
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
	}
}
