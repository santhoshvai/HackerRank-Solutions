import java.io.*;
import java.util.*;

class Pair<L,R> {
    private final L left;
    private final R right;

    public Pair(L left, R right) {
      this.left = left;
      this.right = right;
    }

    public L getLeft() { return left; }
    public R getRight() { return right; }

    @Override
    public int hashCode() {
       return left.hashCode() ^ right.hashCode();
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.left.equals(pairo.getLeft()) &&
               this.right.equals(pairo.getRight());
    }

}

public class Solution {
    
    public static void main(String[] args) {
        Map<Tuple, Set<Pair<Character,Integer>>> adj = new HashMap<Tuple, Set<Pair<Character,Integer>>>();
    }
}
