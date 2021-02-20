package Graph;

/**
 * @author kalpak
 *
 * Implement the Union Find algorithm with Path Compression.
 *
 * Inspired from William Fiset.
 *
 */

public class UnionFind {
    private int numberOfElements;   // number of elements in union find
    private int[] size;             // track size of each component
    private int[] id;               // id[i] points to the parent of i, if id[i] = i, then i is the root node.

    private int numOfComponents;

    public UnionFind(int size) {
        if(size <= 0)
            throw new IllegalArgumentException("Size <= 0 is not allowed.");

        this.numberOfElements = numOfComponents = size;
        this.size = new int[size];
        this.id = new int[size];

        // Initialize the arrays as individual components
        for(int i = 0; i < size; i++) {
            id[i] = i;  // self root
            this.size[i] = 1;
        }
    }

    public int find(int p) {
        // find the root of the component
        int root = p;
        while(root != id[root])
            root = id[root];

        // Path Compression : Gives amortized time complexity
        while(p != root) {
            int next = id[p];   // Store the id of p and make p point to the root
            id[p] = root;       // Compress
            p = next;           // do the same for the rest.
        }

        return root;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public int componentSize(int p) {
        return size[find(p)];
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public int getNumOfComponents() {
        return numOfComponents;
    }

    public void union(int p, int q) {
        if(isConnected(p, q))
            return;

        int root1 = find(p);
        int root2 = find(q);

        // Merge component with smaller size to the component with larger size.
        if(size[root1] < size[root2]) {
            size[root2] += size[root1];
            id[root1] = root2;          // Merge
        } else {
            size[root1] += size[root2];
            id[root2] = root1;
        }

        numOfComponents--;
    }
}
