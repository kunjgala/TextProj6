package project6;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class LCS {
  public String find(String A, String B) {
    // Implement the Hirschberg LCS algorithm 
    int n = A.length();
    int m = B.length();
    int[][] t = new int[n+1][m+1];
    for(int i=0;i<n+1;i++)
    for(int j=0;j<m+1;j++)
    if(j==0 || i==0) t[i][j]=0;

    for(int i=1;i<n+1;i++) {
      for(int j=1;j<m+1;j++) {
        if(A.charAt(i-1)==B.charAt(j-1)) {
          t[i][j]=1+t[i-1][j-1];
        }
        else {
          t[i][j] = Math.max(t[i][j-1],t[i-1][j]);
        }
      }
    }
    String ans = "";
    int i = n;
    int j = m;
    while(i>0 && j>0) {
      if(A.charAt(i-1)==B.charAt(j-1)) {
        ans=A.charAt(i-1)+ans;
        i--;
        j--;
      }
      else {
        if(t[i-1][j]<t[i][j-1]) j--;
        else i--;
      }
    }

    return ans;

  }

}
