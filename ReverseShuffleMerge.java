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
        }
    }
    
    public static void printCount(String str) {
        int[] ct = new int[26];
        for(int i=0; i<str.length();i++){
            char c = str.charAt(i);
            ct[c-'a'] += 1;
        }
        System.out.println(Arrays.toString(ct) );
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        //System.out.format("string length: %s\n", str.length());
        //in.nextLine();
        //String actAns = in.nextLine();
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
        //printCount(actAns);
        PriorityQueue<Character> q = new PriorityQueue<>();
        for(int i=0; i<26;i++){
            if(ori[i] > 0) {
                for(int j=0; j<ori[i]; j++)
                    q.add( (char) (i + 'a') );
            } 
        }
        List<Character> ans = new ArrayList<>();
        int oriLen = str.length()/2;
        StringBuilder sb = new StringBuilder(oriLen);
        for(int i=str.length()-1; i>=0;i--){
            char c = str.charAt(i);
            if (ori[c -'a'] == 0) continue;
            if (oriLen == 0) break;
            // if current smallest use it
            //int size = loc.get(c).size();
            boolean toAdd = false;
            if(c == q.peek()) {
                toAdd = true;
                //loc.get(c).remove(i);
            }                 
            // we cannot neglect this
            else if(ct[c -'a'] == ori[c -'a']) {
                toAdd = true;
                //loc.get(c).remove(i);
            }
            // select a character "c" if next critical character turns out to be larger than "c". 
            else if(ct[c -'a'] > ori[c -'a']) {
                // look ahead in the string
                for(int j = i-1; j>=0; j--){
                    char cj = str.charAt(j);
                    if (ori[cj -'a'] == 0) continue;
                    if( (((int)cj) < ((int)c)) ) { break; } // further ahead there is a lesser(selectable) character so no need to select this
                    if( (ori[cj -'a'] == ct[cj -'a']) ) {
                        if(((int)cj) > ((int)c) ) {
                            toAdd = true;
                            break;
                        }
                    }
                }
            }
            if(toAdd) {
                q.remove(c);
                sb.append(c);
                oriLen--;
                ori[c -'a']--;
            }
            ct[c -'a']--;
        }
        System.out.println(sb.toString());
        //printCount(sb.toString());
        /*
        for(char c: ans)
            System.out.print(c);
            */
    }
}
