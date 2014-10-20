import java.util.Scanner;

/**
 *
 * @author Administrator
 */
class Forest2Darr {
  
  public static void main(String[]args){
    
    System.out.println("set width of your Forest :");
    Scanner a = new Scanner(System.in); 
    int width = a.nextInt(); 
    System.out.println("set length of your Forest :");
    Scanner b = new Scanner(System.in); 
    int length = b.nextInt();
    int[][]forest = new int[width][length];
    
    for(int i=0;i<forest.length;i++){
      for(int j=0;j<forest[0].length;j++){
        if(i==0 || j==0 || i==forest.length-1 || j==forest.length-1){
          forest[i][j]=0;
        }else{forest[i][j] = 1;}
      }
    }
    forest[forest.length/2][forest.length/2]=2;
    
    
    
    for(int i = 0; i < forest.length; i++){
      for(int j = 0; j < forest.length; j++){
        System.out.printf("%2d ", forest[i][j]);
      }
      System.out.println();
    }
  }
}