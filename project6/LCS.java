package project6;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class LCS {

  public String reverse(String str) {
    String revStr = "";
    for(int i=str.length()-1; i>=0; i--) {
      revStr = revStr+str.charAt(i);
    }
    return revStr;
  }

  public int calculateKVal(int[] L1, int[] L2, int n) {
    int M = 0;
    int k = 0;
    for(int j=0; j<n+1; j++) {	
      if(M < (L1[j]+L2[n-j])) {
        M = L1[j]+L2[n-j];
        k = j;
      }
    }
    return k;
  }
  
  public int[] algB(int m, int n, String A, String B) {
    int[][] K = new int[2][n+1];
    for( int j=0; j<n+1; j++) {
      K[1][j] = 0;
    }
    for(int i=1; i<m+1; i++) {
      for(int j=0; j<n+1; j++) {
        K[0][j] = K[1][j];
      }
      for(int j=1; j<n+1; j++) {
        if(A.charAt(i-1) == B.charAt(j-1)) {
          K[1][j] = K[0][j-1] + 1;
        }else{
          K[1][j] = Math.max(K[1][j-1], K[0][j]);
        }
      }
    }
    return K[1];
 }


public String algC(int m, int n, String A, String B) {
  int i=0;
  int j=0;
  String C = "";
  if( n==0 ) {
    C = "";
  } else if( m==1 ) {
    C = "";
    for( j=0; j<n; j++ ) {
      if( A.charAt(0)==B.charAt(j) ) {
        C= ""+A.charAt(0);
        break;
      }
    }
  } 
  else {
    i= (int) Math.floor(((double)m)/2);
    int[] L1 = algB(i, n, A.substring(0,i), B);
    int[] L2 = algB(m-i, n, reverse(A.substring(i)), reverse(B));
    int k = calculateKVal(L1, L2, n);
    String C1 = algC(i, k, A.substring(0, i), B.substring(0, k));
    String C2 = algC(m-i, n-k, A.substring(i), B.substring(k));
    C = C1+C2;
  }
  return C; 
}


  public String find(String A, String B) {
    // Implement the Hirschberg LCS algorithm 
    return algC(A.length(), B.length(), A, B);
  }

}
