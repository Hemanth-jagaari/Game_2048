/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
class Main {
    static int[] spon;
    static List<int[]> emptycells=new ArrayList<>();
    static int[][] board;
    static int gmax=0;
    static int score=0;
    static int n=4;
    public static  void respon(){
        Random random=new Random();
        int r=random.nextInt(emptycells.size());
        int[] cell=emptycells.get(r);
        int j=random.nextInt(2);
        board[cell[0]][cell[1]]=j==0?2:4;
        emptycells.remove(r);
    }
    public static void initGrid(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                emptycells.add(new int[]{i,j});
            }
        }
        for(int i=0;i<2;i++){
            respon();
            //System.out.println(cell[0]+" "+cell[1]);
        }
        print();
    }
    public static void handleupmove(){
        for(int j=0;j<4;j++){
        List<Integer> store=new ArrayList<>();
        List<int[]> empty=new ArrayList<>();
        List<int[]> fill=new ArrayList<>();
            for(int i=0;i<4;i++){
                if(board[i][j]!=0){
                    store.add(board[i][j]);
                    fill.add(new int[]{i,j});
                }
                else{
                    empty.add(new int[]{i,j});
                }
            }
            if(store.size()>0){

            List<Integer> removeindeces=new ArrayList<>();
            for(int p=0;p<emptycells.size();p++){
                int[] x=emptycells.get(p);
                for(int q=0;q<empty.size();q++){
                    if(x[0]==empty.get(q)[0] && x[1]==empty.get(q)[1]) removeindeces.add(p);
                }
                for(int q=0;q<fill.size();q++){
                    if(x[0]==fill.get(q)[0] && x[1]==fill.get(q)[1]) removeindeces.add(p);
                }
            }
            
            remove_indices_emptycells(removeindeces);
            removeindeces.clear();
            fill.clear();
            empty.clear();
            //solve by moving and change empty and fill states
            //solve(store.to)
            int[] arr=convert(store);
            List<Integer> moved_row=solve(arr);
            int k=0;
            int r=0;
            while(k<moved_row.size()){
                fill.add(new int[]{r,j});
                board[r++][j]=moved_row.get(k++);
                
            }
            for(;r<4;){
                emptycells.add(new int[]{r,j});
                board[r++][j]=0;
            }
            for(int p=0;p<emptycells.size();p++){
                int[] x=emptycells.get(p);
                for(int q=0;q<fill.size();q++){
                    if(x[0]==fill.get(q)[0] && x[1]==fill.get(q)[1]) removeindeces.add(p);
                }
            }
            remove_indices_emptycells(removeindeces);
            removeindeces.clear();
            store.clear();
            fill.clear();
            empty.clear();
           }
        }
       // print();
        respon();
        print();
        return;
    }
    public static int[] convert(List<Integer> lst){
        int[] arr=new int[lst.size()];
        for(int i=0;i<lst.size();i++){
            arr[i]=lst.get(i);
        }
        return arr;
    }
     public static List<Integer> solve(int[] arr){
        List<Integer> ans=new ArrayList<>();
        List<Integer> indices=new ArrayList<>();
        int n=arr.length;
        int[] temparr=Arrays.copyOfRange(arr, 0, arr.length);
        boolean[] visited=new boolean[n];
        if(arr.length>=2){
            int move=0;
            int reach=0;
            for(int i=0;i<arr.length;i++){
                if( i+1 <n && arr[i]==arr[i+1]  && move<2){
                    arr[i]=2*arr[i];
                    score+=arr[i];
                    gmax=Math.max(gmax,arr[i]);
                    indices.add(i);
                    move=move+1;
                    i=i+1;
                    visited[i]=true;
                    if(i+1<n )visited[i+1]=true;
                    reach=i;
                }
                else{
                    indices.add(i);
                }
            }
            if(move==1 && visited[n-2]==false && visited[n-1]==false){
                indices.add(n-1);
            }
            for(int index:indices){
                ans.add(arr[index]);
            }
        }
        else{
            ans.add(arr[0]);
        }
        return ans;

    }
    public static void remove_indices_emptycells(List<Integer> indices){
        List<int[]> dummyemptycells=new ArrayList<>(emptycells);
        emptycells.clear();
        for(int i=0;i<dummyemptycells.size();i++){
            if(!indices.contains(i)){
                emptycells.add(dummyemptycells.get(i));
            }
        }
        return;
    }
    public static void handlerightmove(){
        // solve similar to the handle up
        System.out.println(" in handlerightmove");
        for(int i=0;i<4;i++){
            List<Integer> store=new ArrayList<>();
            List<int[]> empty=new ArrayList<>();
            List<int[]> fill=new ArrayList<>();
            for(int j=0;j<4;j++){
                if(board[i][j]!=0){
                    store.add(board[i][j]);
                    fill.add(new int[]{i,j});
                }
                else{
                    empty.add(new int[]{i,j});
                }
            }
            if(store.size()>0){
            List<Integer> removeindeces=new ArrayList<>();
            for(int p=0;p<emptycells.size();p++){
                int[] x=emptycells.get(p);
                for(int q=0;q<empty.size();q++){
                    if(x[0]==empty.get(q)[0] && x[1]==empty.get(q)[1]) removeindeces.add(p);
                }
                for(int q=0;q<fill.size();q++){
                    if(x[0]==fill.get(q)[0] && x[1]==fill.get(q)[1]) removeindeces.add(p);
                }
            }
            remove_indices_emptycells(removeindeces);
            fill.clear();
            empty.clear();
            removeindeces.clear();
            //solve by moving and change empty and fill states
            //solve(store.to)
            int[] arr=convert(store);
            List<Integer> moved_row=solve(arr);
            int k=moved_row.size()-1;
            int c=n-1;
            while(k>=0){
                fill.add(new int[]{i,c});
                board[i][c--]=moved_row.get(k--);
            }
            for(;c>=0;){
                emptycells.add(new int[]{i,c});
                board[i][c--]=0;
            }

            for(int p=0;p<emptycells.size();p++){
                int[] x=emptycells.get(p);
                for(int q=0;q<fill.size();q++){
                    if(x[0]==fill.get(q)[0] && x[1]==fill.get(q)[1]) removeindeces.add(p);
                }
            }
            remove_indices_emptycells(removeindeces);
            removeindeces.clear();
            store.clear();
            fill.clear();
            empty.clear();

            }
            
        }
        //print();
        respon();
        print();
        return;
    }
    public static void print_list(List<Integer> lst){
        System.out.println();
        for(int i=0;i<lst.size();i++){
            System.out.print(lst.get(i)+" ");
        }
        System.out.println();
    }
    public static void handledownmove(){
        //solve similar to the handle up
        for(int j=0;j<4;j++){
            List<Integer> store=new ArrayList<>();
            List<int[]> empty=new ArrayList<>();
            List<int[]> fill=new ArrayList<>();
            for(int i=0;i<4;i++){
                if(board[i][j]!=0){
                    store.add(board[i][j]);
                    fill.add(new int[]{i,j});
                }
                else{
                    empty.add(new int[]{i,j});
                }
            }
            if(store.size()>0){
            List<Integer> removeindeces=new ArrayList<>();
            for(int p=0;p<emptycells.size();p++){
                int[] x=emptycells.get(p);
                for(int q=0;q<empty.size();q++){
                    if(x[0]==empty.get(q)[0] && x[1]==empty.get(q)[1]) removeindeces.add(p);
                }
                for(int q=0;q<fill.size();q++){
                    if(x[0]==fill.get(q)[0] && x[1]==fill.get(q)[1]) removeindeces.add(p);
                }
            }
            remove_indices_emptycells(removeindeces);
            fill.clear();
            empty.clear();
            removeindeces.clear();
            //solve by moving and change empty and fill states
            //solve(store.to)
            int[] arr=convert(store);
           /* System.out.println("before move");
            print_list(store);*/
            List<Integer> moved_row=solve(arr);

            int k=0;
            int r=n-1;
            System.out.println("after move");
            print_list(moved_row);
            while(k<moved_row.size()){
                fill.add(new int[]{r,j});
                board[r--][j]=moved_row.get(k++);
            }
            for(;r>=0;){
                emptycells.add(new int[]{r,j});
                board[r--][j]=0;
            }
            for(int p=0;p<emptycells.size();p++){
                int[] x=emptycells.get(p);
                for(int q=0;q<fill.size();q++){
                    if(x[0]==fill.get(q)[0] && x[1]==fill.get(q)[1]) removeindeces.add(p);
                }
            }
            remove_indices_emptycells(removeindeces);
            removeindeces.clear();
            store.clear();
            fill.clear();
            empty.clear();

            }
        }
        respon();
        print();
        return;
    }
    public static void handleleftmove(){
        //similar to handlerightmove
       for(int i=0;i<4;i++){
            List<Integer> store=new ArrayList<>();
            List<int[]> empty=new ArrayList<>();
            List<int[]> fill=new ArrayList<>();
            for(int j=0;j<4;j++){
                if(board[i][j]!=0){
                    store.add(board[i][j]);
                    fill.add(new int[]{i,j});
                }
                else{
                    empty.add(new int[]{i,j});
                }
            }
            if(store.size()>0){
            List<Integer> removeindeces=new ArrayList<>();
            for(int p=0;p<emptycells.size();p++){
                int[] x=emptycells.get(p);
                for(int q=0;q<empty.size();q++){
                    if(x[0]==empty.get(q)[0] && x[1]==empty.get(q)[1]) removeindeces.add(p);
                }
                for(int q=0;q<fill.size();q++){
                    if(x[0]==fill.get(q)[0] && x[1]==fill.get(q)[1]) removeindeces.add(p);
                }
            }
            remove_indices_emptycells(removeindeces);
            fill.clear();
            empty.clear();
            removeindeces.clear();
            //solve by moving and change empty and fill states
            //solve(store.to)
            int[] arr=convert(store);
            List<Integer> moved_row=solve(arr);
            int k=0;
            int c=0;
            while(k<moved_row.size()){
                fill.add(new int[]{i,c});
                board[i][c++]=moved_row.get(k++);
            }
            for(;c<n;){
                emptycells.add(new int[]{i,c});
                board[i][c++]=0;
            }

            for(int p=0;p<emptycells.size();p++){
                int[] x=emptycells.get(p);
                for(int q=0;q<fill.size();q++){
                    if(x[0]==fill.get(q)[0] && x[1]==fill.get(q)[1]) removeindeces.add(p);
                }
            }
            remove_indices_emptycells(removeindeces);
            removeindeces.clear();
            store.clear();
            fill.clear();
            empty.clear();

            }
            
        }
        //print();
        respon();
        print();
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
            int move=Integer.parseInt(sc.nextLine());
            handlemove(move);
            if(gmax==2048){
                System.out.println("you won !!!!!!!!!!!!!!");
                return;
            }
        }
        System.out.println("you lost !!!!!!!!!!!!!!!!");
        //1-> up move 2->right move  3-> down move 4-> left move 
        return;
    }
    public static void  startGame(){
        initGrid();
        //implement initalization of grid
       // print(grid);
        //handleIterations
        print();
        handleIterations();
    }
    public static void print(){
        for(int i=0;i<board[0].length;i++){
            System.out.print(" _");
        }
        for(int i=0;i<board.length;i++){
            System.out.println();
            for(int j=0;j<board[0].length;j++){
                System.out.print("|"+board[i][j]);
            }
            System.out.println("|");
            for(int j=0;j<board[0].length;j++){
                System.out.print(" _");
            }
            //System.out.println();
        }
        System.out.println("\nmax ="+gmax+"\n"+"score="+score);
        return;
    }
	public static void main (String[] args) {
		board=new int[4][4];
        spon=new int[2];
        spon[0]=2;
        spon[1]=4;
		startGame();
		print();
	}
}