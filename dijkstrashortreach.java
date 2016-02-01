import java.io.*;
import java.util.*;

public class Solution {
    
    public class Pair<L,R> {
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
         // long l = left.hashCode() * 2654435761L; 
          //return (int)l + (int)(l >>> 32) + right.hashCode();
      }

      @Override
      public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.left.equals(pairo.getLeft()) &&
               this.right.equals(pairo.getRight());
      }

    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
