import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void differWhere(String str1, String str2) {
        if(str1.length() != str2.length()) {
            System.out.println("oh crap");
            return;
        }
        int cnt  = 0;
        for(int i=0; i<str1.length();i++){
            char c = str1.charAt(i);
            char d = str2.charAt(i);
            
            if(c != d) {
                System.out.format("\nPos = %s, str1 = %s, str2 = %s\n", i, c, d);
                cnt++;
            }
        }
        System.out.format("Total Differences = %s\n", cnt);
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
        for(int i=0; i<str.length();i++){
            char c = str.charAt(i);
            ct[c -'a'] += 1;
        }
        //System.out.println(Arrays.toString(ct) );
        for(int i=0; i<26;i++){
            ori[i] = ct[i]/2;
        }
        //System.out.println(Arrays.toString(ori) );
        //printCount(actAns);
        PriorityQueue<Character> q = new PriorityQueue<>(); // to find the current minimum each time
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
            boolean toAdd = false;
            if(c == q.peek()) {
                toAdd = true;
            }                 
            // we cannot neglect this
            else if(ct[c -'a'] == ori[c -'a']) {
                toAdd = true;
            }
            // select a character "c" if next critical character turns out to be larger than "c". 
            else if(ct[c -'a'] > ori[c -'a']) {
                int[] tempct = Arrays.copyOf(ct, 26); // temporary array to store count, to find criticality, because it needs to subtracted when visited each time
                // look ahead in the string
                for(int j = i-1; j>=0; j--){
                    char cj = str.charAt(j);
                    if (ori[cj -'a'] == 0) continue;
                    if( (cj-c) < 0 ) {break;}// further ahead there is a lesser(selectable) character so no need to select this
                    // a crtical character futher ahead is great than this character so may need to select this 
                    if ((cj-c) > 0) {
                        if( (ori[cj -'a'] == tempct[cj -'a']) ) {
                            toAdd = true;
                            break;
                        }
                        tempct[cj -'a']--;
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
        //differWhere(actAns, sb.toString());
        //printCount(sb.toString());
        
    }
}
