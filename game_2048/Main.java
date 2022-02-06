/*package whatever //do not write package name here */

import java.io.*;
class Cell{
    int r;
    int c;
    public Cell(int x,int y){
        this.r=x;
        this.c=y;
    }
}

class Main {
    public static void print(int[][] matrix){
        for(int i=0;i<matrix[0].length;i++){
            System.out.print(" _");
        }
        
        for(int i=0;i<matrix.length;i++){
            System.out.println();
            for(int j=0;j<matrix[0].length;j++){
                System.out.print("|"+matrix[i][j]);
            }
            System.out.println("|");
            for(int j=0;j<matrix[0].length;j++){
                System.out.print(" _");
            }
            //System.out.println();
        }
    }
    public static void  startGame(int[][] grid){
        List<int[]> lst=new ArrayList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                lst.add(new int[]{i,j});
            }
        }
        Random random=new Random();
        int randindex=random.nextInt(lst.size());
        matrix[list.get(randindex)[0]][list.get(randindex)[1]]=2;
        lst.remove(randindex);
        randindex=random.nextInt(lst.size());
        matrix[list.get(randindex)[0]][list.get(randindex)[1]]=2;
        int maxScore=0;
        int points=0;
        lst.remove(randindex);
        while(list.size()>0){
            
            System.out.println("Enter ur choice like left 0 right 1 up 3 down 4");
            int turn =sc.nextInt();
            handlestate(matrix,lst,turn);
            if(maxScore==2048){
                Suystem.out.println("Done it you won");
                return;
            }
            
        }
        System.out.println("<<<<<<<<<<<<<<<GAmE OveR>>>>>>>>>>");
        
    }
	public static void main (String[] args) {
		int[][] matrix=new int[4][4];
		startGame(matrix);
		print(matrix);
	}
}