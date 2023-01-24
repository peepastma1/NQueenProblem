import java.util.*;

public class NQueenProblem{
    
    public static void main(String args[]){
        
        int N = 0, row = 0, col = 0;
        String mode, conti;
        Scanner input = new Scanner(System.in);
        boolean continueQ = false, inputValid = false, checkch = true;
        System.out.println("\n=== Welcome to N-Queen Problem Solving Program ===");
        do{
        
            do{    
                try {
                    System.out.println("\nEnter N (at least 4) = ");
                    N = input.nextInt();
                }
                catch(InputMismatchException | NumberFormatException e) {
                    System.out.println("Invalid Number, Please try again\n");
                    N=0;
                    input.nextLine();
                }

              if(N<4){
                  System.out.println("Please input number more than or equal 4\n");
              }
            }while(N<4);
        
        do{
            System.out.println("\nPlace first queen manually (y for yes, n for no) :");
            mode = input.next();
            if(mode.equalsIgnoreCase("Y")){
                int[][] tempBoard = new int[N][N];
                do{
                    try{
                        Solve temp = new Solve(N);
                        temp.printSolution(tempBoard);
                        checkch=false;
                        System.out.println("\nEnter row of first queen = ");
                        row = input.nextInt()-1;
                        if(row<0 || row >= N) checkch = false;
                        else checkch = true;
                    }
                    catch(InputMismatchException | NumberFormatException e) {
                        checkch = false;
                        System.out.println("Invalid Number, Please try again");
                        input.nextLine();
                    }
                    if(checkch==false){
                        System.out.printf("Please enter number between 1 - %d \n",N);
                    }
            }while(checkch==false);
                
                do{
                    try{
                        checkch=false;
                        System.out.println("\nEnter col of first queen = ");
                        col = input.nextInt()-1;
                        if(col<0 || col >= N) checkch = false;
                        else checkch = true;
                    }
                    catch(InputMismatchException | NumberFormatException e) {
                        checkch = false;
                        System.out.println("Invalid Number, Please try again");
                        input.nextLine();
                    }
                    if(checkch==false){
                        System.out.printf("Please enter number between 1 - %d \n",N);
                    }
            }while(checkch==false);
                

                Solve Queen = new Solve(N,row,col); 
                Queen.solveNQ("M");
                inputValid = true;

            }else if(mode.equalsIgnoreCase("N")){
                Solve Queen = new Solve(N);
                Queen.solveNQ("A");
                inputValid = true;
            }else{
                System.out.printf("Please enter only y and n\n");
                inputValid = false;
            }
        }while(!inputValid);
        
        boolean answer = false;
        while(!answer){
          System.out.println("Do you want to play again? (y for yes, n for no)) :");
          conti = input.next();
          if(conti.equalsIgnoreCase("Y")){
              continueQ = false;
              inputValid = false;
              answer = true;
          }else if (conti.equalsIgnoreCase("N")){
              continueQ = true;
              answer = true;
          }
          else{
            System.out.println("Please answer again....");
          }
        }
        }while(!continueQ);
      input.close();
    }
}

class Solve{
    
    int N,rowf,colf;
    
    public Solve(int n,int r, int c){
        N = n;
        rowf = r;
        colf = c;
    }
    
    public Solve(int n){
        N = n;
        rowf = 0;
        colf = 0;
    }

    void printSolution(int board[][]){
        System.out.println("\n================================\n");
        for (int i = 0; i < N; i++) {
            if(i==0){
                System.out.printf("   ");
                for(int k=1;k<=N;k++){
                    System.out.printf(" %d ",k);
                }
                System.out.println();
            }
            for (int j = 0; j < N; j++){
                if(j==0){
                System.out.printf(" %d ",i+1);
                }
                if(board[i][j]==1){
                    System.out.print(" Q ");
                }else if(board[i][j]==0){
                    System.out.print(" = ");
                }
            }
            System.out.println();
        }
        System.out.println("\n================================\n");
    }

    boolean solveNQ(String mode){
        
        int n=N;
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 0;
            }
        }
      
        if(mode.equalsIgnoreCase("M")){
            board[rowf][colf] = 1;
            printSolution(board);
        }
        
        if (solveNQUtil(board, 0) == false) {
            System.out.printf("Solution does not exist\n\n");
            return false;
        }
        System.out.printf("\nSolution!!!\n");
        printSolution(board);
        return true;
    }
    
    boolean solveNQUtil(int board[][], int col){        //// Main Algorithm to slove solution
        if (col >= N)
            return true;
 
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;
                if (solveNQUtil(board, col + 1) == true)
                    return true;
                board[i][col] = 0;
            }
            else if(board[i][col] == 1){
                if (solveNQUtil(board, col + 1) == true)
                    return true;
            }
        }
        return false;
    }

    boolean isSafe(int board[][], int row, int col){
        
        int i, j;
        /* Check this row on left side and lower in the same column*/
        for (i = 0; i < N; i++){
            if (board[row][i] == 1){
                return false;
            }
            if(board[i][col] == 1){
                return false;
            }
        }
 
        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--){
            if (board[i][j] == 1)
                return false;
        }
 
        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < N; i++, j--){
            if (board[i][j] == 1)
                return false;
        }

        /* Check upper diagonal on right side */
        for (i = row, j = col; j <N  && i>=0; i--, j++){
            if (board[i][j] == 1)
                return false;
        }
        
         /* Check lower diagonal on right side */
        for (i = row, j = col; j < N && i < N; i++, j++){
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

}