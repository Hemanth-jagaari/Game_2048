/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
class Cell{
    int r;
    int c;
    public Cell(int x,int y){
        this.r=x;
        this.c=y;
    }
}

class Main {
    static int[] spon;
    static List<int[]> emptycells=new ArrayList<>();
    static int[][] board;
    static int gmax=0;
    static int score=0;
    public static void initGrid(int[][] grid){
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                emptycells.add(new int[]{i,j});
            }
        }
        Random random=new Random();
        for(int i=0;i<2;i++){
            int r=random.nextInt(emptycells.size());
            int[] cell=emptycells.get(r);
            int j=random.nextInt(2);
            grid[cell[0]][cell[1]]=j==0?2:4;
            emptycells.remove(r);
            System.out.println(cell[0]+" "+cell[1]+" "+j);
        }
    }
    public static void handleupmove(){
        List<Integer> store=new ArrayList<>();
        List<int[]> empty=new ArrayList<>();
        List<int[]> fill=new ArrayList<>();
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                if(board[i][j]!=0){
                    store.add(board[i][j]);
                    fill.add(new int[]{i,j});
                }
                else{
                    empty.add(new int[]{i,j});
                }
            }
            List<Integer> removeindeces=new ArrayList<>();
            for(int i=0;i<emptycells.size();i++){
                int[] x=emptycells.get(i);
                for(int j=0;j<empty.size();j++){
                    if(x[0]==empty.get(j)[0] && x[1]==empty.get(j)[1]) removeindeces.add(i);
                }
                for(int j=0;j<fill.size();j++){
                    if(x[0]==fill.get(j)[0] && x[1]==fill.get(j)[1]) removeindeces.add(i);
                }
            }
            for(int index:removeindeces){
                emptycells.remove(index);
            }
            removeindeces.clear();
            fill.clear();
            empty.clear();
            //solve by moving and change empty and fill states
            //solve(store.to)
            List<Integer> moved_row=solve(store.arr);
            int k=0;
            int r=0;
            while(k<moved_row.size()){
                board[r++][j]=moved_row.get(k++);
                fill.add(new int[]{r,j});
            }
            for(r<n){
                emptycells.add(new int[]{r,j});
                board[r++][j]=0;
            }

           if(store.size()>0){
            
           }
        }
        return;
    }
    public static void handlerightmove(){
        // solve similar to the handle up
        return;
    }
    public static void handledownmove(){
        //solve similar to the handle up
        return;
    }
    public static void handleleftmove(){
        return;
    }
    public static void handlemove(int move){
        if(move==1){
            handleupmove();
            return;
        }
        else if(move==2){
            handlerightmove();
            return;
        }
        else if(move==3){
            handledownmove();
            return;
        }
        else if(move==4){
            handleleftmove();
            return;
        }
        else{
            if(move==5){
                //init 
            }
            System.out.println("in valid move");
            return;
        }
    }
    public static void handleIterations(){
        Scanner sc=new Scanner(System.in);
        while(emptycells.size()>0){
            int move=sc.nextInt();
            handlemove(move);
            if(gmax==2048){
                System.out.println("you won !!!!!!!!!!!!!!");
                return;
            }
        }
        //1-> up move 2->right move  3-> down move 4-> left move 

    }
    public static void  startGame(int[][] grid){
        initGrid(grid);
        //implement initalization of grid
       // print(grid);
        //handleIterations
        print(grid);
        handleIterations();

    }
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
        System.out.println("\nmax ="+gmax+"\n"+"score="+score);
    }
	public static void main (String[] args) {
		board=new int[4][4];
        spon=new int[2];
        gmax=0;
        score=0;
		startGame(board);
        spon[0]=2;
        spon[1]=4;
		print(board);
	}
}