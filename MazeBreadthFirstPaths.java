import edu.princeton.cs.algs4.Queue;

import java.util.Observable;
/**
 *  @author Josh Hug
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int s) {
        Queue<Integer> fringe = new Queue<>();
        fringe.enqueue(s);
        marked[s] = true;
        announce();

        while (!targetFound && !fringe.isEmpty()) {
            int cur = fringe.dequeue();
            for (int w : maze.adj(cur)) {
                if (!marked[w]) {
                    fringe.enqueue(w);
                    edgeTo[w] = cur;
                    marked[w] = true;
                    distTo[w] = distTo[cur] + 1;
                    announce();
                    if (w == t) {
                        targetFound = true;
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
        bfs(s);
    }
}
