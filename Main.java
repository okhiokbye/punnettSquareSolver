import static java.lang.System.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
class Main {
  public static void main(String[] args) {
     String g1,g2 = "";
     boolean doAgain = true;
    Scanner kb = new Scanner(in);
    out.println("currently only supports monohybrid and dihybrid");
    out.println("intended to support pretty much any cross, will update if i unlazy myself");

    do{
    out.println("enter genotype one");
    g1 = kb.next();
    out.println("enter genotype two");
    g2 = kb.next();
    //determine allele length (1 for monohybrid, 2 for dihybrid...)
    int crossLength = g1.length()/2;
    if(crossLength == 0)
        crossLength = 1;

  // sorts the alleles using hypercomplex way intended to be scalable for any arbitrarily large cross
      // ex RrYy into RY Ry rY ry ... AaBbCc into stuff i wont list cuz no
  ArrayList<String> g1Sort = new ArrayList<String>();
  ArrayList<String> g2Sort = new ArrayList<String>();
   if(crossLength != 1){
    for(int i =0; i<g1.length();i++){
      for(int s = i; s<g1.length();s++){
        if(Character.toUpperCase(g1.charAt(i)) != Character.toUpperCase(g1.charAt(s))){
          for(int a =0; a<g1Sort.size(); a++){
            String temp = g1.substring(i,i+1) + g1.substring(s,s+1); // rip code from internet to make applicable look at link in line 103 but now your problem is to remove the duplicates
            if(temp != g1Sort.get(a))
              continue;
          }
            g1Sort.add(g1.substring(i,i+1) + g1.substring(s,s+1));
        }
      }
    }
    for(int i =0; i<g2.length();i++){
      for(int s = i; s<g2.length();s++){
        if(Character.toUpperCase(g2.charAt(i)) != Character.toUpperCase(g2.charAt(s))){
          for(int a =0; a<g2Sort.size(); a++){
            String temp = g2.substring(i,i+1) + g2.substring(s,s+1);
            if(temp != g2Sort.get(a))
              continue;
          }
          g2Sort.add(g2.substring(i,i+1) + g2.substring(s,s+1));
        }  
      }
    }

   }
   else{ // if its just a monohybrid then its very very easy to sort it
    g1Sort.add(g1.substring(0,1));
    g1Sort.add(g1.substring(1,2));
    g2Sort.add(g2.substring(0,1));
    g2Sort.add(g2.substring(1,2));
   }

    // the punnett
    String[][] punnett = new String[g1Sort.size()][g2Sort.size()];
    
    // g1 distribution
    for(int i = 0; i<g1Sort.size();i++){
      for(int row = 0;row<g2Sort.size();row++){
        punnett[row][i] = g1Sort.get(i);
      }
    }
    // g2 distribution
    for(int i = 0; i<g2Sort.size();i++){
      for(int col = 0;col<g1Sort.size();col++){
        punnett[i][col] += g2Sort.get(i);
      }
    }   
    //print results
    for(int i = 0; i<g2.length();i++){
      System.out.println();
      for(int col = 0;col<g2.length();col++){
        String tempy[] = punnett[i][col].split("");
        List<String>list = Arrays.asList(tempy);
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER); //sorts it so the letters are together
        for(String yeehaw : tempy)
            out.print(yeehaw);
        out.print(" ");
      }
    }
      //do u wanna do it again
      out.println();
    out.println("do you wanna do another punishment square? Y/N");
      String yee = kb.nextLine();
      if(yee == "Y" ||yee == "y")
        doAgain = true;
      else
        doAgain = false; // clearly not (right choice)
    }
    while(doAgain != false);
    
  }
}

/*
https://codereview.stackexchange.com/questions/41510/calculate-all-possible-combinations-of-given-characters 
*/