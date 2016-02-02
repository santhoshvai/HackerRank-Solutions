import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            //it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int[] ct = new int[26];
        int[] ori = new int[26];
        Map<Character, Set<Integer>> loc = new HashMap<Character, Set<Integer>>();
        for(int i=0; i<str.length();i++){
            char c = str.charAt(i);
            ct[c -'a'] += 1;
            if(loc.containsKey(c)) loc.get(c).add(i);
            else { 
                loc.put(c, (new HashSet<Integer>()) ) ;
                loc.get(c).add(i);
            }
        }
        //printMap(loc);
        //System.out.println(Arrays.toString(ct) );
        for(int i=0; i<26;i++){
            ori[i] = ct[i]/2;
        }
        //System.out.println(Arrays.toString(ori) );
        PriorityQueue<Character> q = new PriorityQueue<>();
        for(int i=0; i<26;i++){
            if(ori[i] > 0) {
                for(int j=0; j<ori[i]; j++)
                    q.add( (char) (i + 'a') );
            } 
        }
        List<Character> ans = new ArrayList<>();
        int oriLen = str.length()/2;
        for(int i=str.length()-1; i>=0;i--){
            char c = str.charAt(i);
            if (ori[c -'a'] == 0) continue;
            if (oriLen == 0) break;
            //if (loc.get(c) == null) System.out.println( "null: " + Integer.toString(i));
            // if current smallest use it
            int size = loc.get(c).size();
            if(c == q.peek()) {
                q.remove(c);
                ans.add(c);
                oriLen--;
                ori[c -'a']--;
                loc.get(c).remove(i);
            }                 
            // we cannot neglect this
            else if(size == ori[c -'a']) {
                q.remove(c);
                ans.add(c);
                oriLen--;
                ori[c -'a']--;
                loc.get(c).remove(i);
            }
            // we can neglect
            else if(size > ori[c -'a']) {
                loc.get(c).remove(i);
            }
        }
        for(char c: ans)
            System.out.print(c);
    }
}
