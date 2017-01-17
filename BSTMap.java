package lab8;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    private class Node {

        private K key;
        private V value;
        private Node left;
        private Node right;
        private int N;

        private Node(K k, V v, int n) {
            key = k;
            value = v;
            N = n;
        }
    }
    // constructor
    public BSTMap() {

    }
    // size
    @Override
    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.N;
    }
    // get
    @Override
    public V get(K key) {
        return get(key, root);
    }
    private V get(K key, Node x) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(key, x.left);
        } else if (cmp > 0) {
            return get(key, x.right);
        } else {
            return x.value;
        }

    }
    // containsKey
    @Override
    public boolean containsKey(K key) {
        V result = get(key);
        return result != null;
    }
    // put
    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }
    private Node put(K key, V value, Node x) {

        if (x == null) {
            Node newNode = new Node(key, value, 1);
            x = newNode;
        } else {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x.left = put(key, value, x.left);
            } else if (cmp > 0) {
                x.right = put(key, value, x.right);
            } else {
                x.value = value;
            }
            x.N = 1 + size(x.left) + size(x.right);
        }
        return x;
    }
    // clear
    @Override
    public void clear() {
        root = null;
    }

    // print in order of increasing key
    public void printInOrder() {
        printInOrderHelper(root);
    }

    private void printInOrderHelper(Node x) {
        if (x == null) {
            System.out.println("");
        } else if (x.left == null && x.right == null) {
            System.out.println(x.key + ", " + x.value);
        } else if (x.left != null && x.right == null) {
            printInOrderHelper(x.left);
        } else if (x.left == null && x.right != null) {
            printInOrderHelper(x.right);
        } else {
            printInOrderHelper(x.left);
            printInOrderHelper(x.right);
        }
    }
    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter();
    }

    private class BSTMapIter implements Iterator<K> {
        private Node cur;
        public BSTMapIter() {
            cur = root;
        }
        @Override
        public boolean hasNext() {
            return cur != null;
        }
        @Override
        public K next() {
            K output = cur.key;
            // can't get the value of cur now   ????
            if (cur.left == null && cur.right == null) {
                cur = null;
            } else if (cur.left == null) {
                cur = cur.right;
            } else if (cur.right == null) {
                cur = cur.left;
            }
            // need to fix the case when left & right are not null  ??
            return output;
        }

    }

    // min
    public Node min(Node X) {
        if (X == null) {
            return null;
        } else if (X.left == null) {
            return X;
        } else {
            return min(X.left);
        }
    }

    // max
    public Node max(Node X) {
        if (X == null) {
            return null;
        } else if (X.right == null) {
            return X;
        } else {
            return max(X.right);
        }
    }

    // remove
    @Override
    public V remove(K key) {
        return remove(key, root);
    }
    private V remove(K key, Node X) {
        V tmp;
        if (X == null) {
            return null;
        }
        int cmp = key.compareTo(X.key);
        if (cmp < 0) {
            return remove(key, X.left);
        } else if (cmp > 0) {
            return remove(key, X.right);
        } else {
            tmp = X.value;
            if (X.left == null && X.right == null) {
                //tmp = X.value;
                X = null;
                //root.N -= 1;
            }
            if (X.left == null) {
                //tmp = X.value;
                X = X.right;
                //root.N -= 1;
            } else if (X.right == null) {
                //tmp = X.value;
                X = X.left;
                //root.N -= 1;
            } else {
                Node replace = max(X.left);
                X.key = replace.key;
                X.value = replace.value;
                replace = null;
            }
            root.N -= 1;
        }
        return tmp;
    }


 /*   @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }  */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
/*    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }  */
}
