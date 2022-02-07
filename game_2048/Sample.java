//build 2048 game
//1.generate random 2 or 4
//2.add to emptycells
//3.add to board
//4.check if game is over
//5.print board
//6.ask user for move
//7.check if move is valid
//8.if
import java.util.*;
class Sample{
    public static void print(List<Integer> a){
        for(int val:a){
            System.out.print(val+" ");
        }
        System.out.println();
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
            for(int i=0;i<4;i++){
                if( i+1 <n && arr[i]==arr[i+1]  && move<2){
                    arr[i]=2*arr[i];
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
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        sc.nextLine();
        while(t>0){
            int[] arr=new int[4];
            for(int i=0;i<4;i++){
                arr[i]=sc.nextInt();
            }
            sc.nextLine();
           List<Integer> res= solve(arr);
            print(res);
            t--;
        }
    }
}
