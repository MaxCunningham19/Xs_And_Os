import java.util.Scanner;
public class Main {
    public static final char BLANK = ' ';
    public static final char NOUGHT = 'O';
    public static final char CROSS = 'X';
    public static final int BOARD_SIZE = 3;
    public static void main(String[] args) {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        clearBoard( board );
        char currentPlayerPiece = CROSS;
        Scanner input = new Scanner(System.in);
        while ((!isBoardFull(board)) && (winner(board) == BLANK))
        {
            printBoard(board);
            int row=-1;
            int column=-1;
            do
            {
                System.out.print("Enter move for " + currentPlayerPiece + ": ");
                char[] inputCharacters = input.next().toCharArray();
                if ((inputCharacters.length >= 2) &&
                        (inputCharacters[0] >= 'A') && (inputCharacters[0] <= 'C') &&
                        (inputCharacters[1] >= '1') && (inputCharacters[1] <= '3'))
                {
                    row = (int) (inputCharacters[0]-'A');
                    column = (int) (inputCharacters[1]-'1');
                    if (!canMakeMove(board,row,column))
                        System.out.println(" Invalid entry. This location is already occupied.");
                }
                else System.out.println(" Invalid entry. You must enter a row and column"
                +"(e.g. B2 is the center piece on the board).");
            } while ((row == -1) || (!canMakeMove(board,row,column)));
            makeMove(board,currentPlayerPiece,row,column);
            currentPlayerPiece = (currentPlayerPiece==CROSS)?NOUGHT:CROSS;
        }
        printBoard(board);
        if (winner(board) == BLANK)
            System.out.println("It was a draw.");
        else System.out.println("The winner was " + winner(board));
        input.close();
    }

    public static void clearBoard(char[][] board){
        for (int r=0;r< board.length;r++){
            for(int c=0;c< board.length;c++){
                board[r][c]=BLANK;
            }
        }
    }

    public static boolean isBoardFull(char[][] board){
        for (int r=0;r< board.length;r++){
            for(int c=0;c< board.length;c++){
                if(board[r][c]==BLANK){
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(char[][] board){
        System.out.println("A  "+board[0][0]+" | "+board[0][1]+" | "+board[0][2]);
        System.out.println("  ---|---|---");
        System.out.println("B  "+board[1][0]+" | "+board[1][1]+" | "+board[1][2]);
        System.out.println("  ---|---|---");
        System.out.println("C  "+board[2][0]+" | "+board[2][1]+" | "+board[2][2]);
        System.out.println("   1   2   3   ");
    }

    public static boolean canMakeMove(char[][] board, int row, int col){
          if (row<board.length && col<board.length && board[row][col] == BLANK) {
              return true;
          } else {
              return false;
          }
    }

    public static void makeMove(char[][] board,char currentPlayerPiece,int row,int col){
        if(currentPlayerPiece == NOUGHT || currentPlayerPiece == CROSS
                && row<board.length && col< board.length && canMakeMove(board,row,col)){
            board[row][col]=currentPlayerPiece;
        }
    }

    public static char winner(char[][] board){
        int count=1;
        for(int col=0;col< board.length;col++ ) {
            char winner = board[0][col];
            char nextChar = BLANK;
            for (int row = 1; row < board.length; row++) {
                nextChar = board[row][col];
                if(nextChar==winner){
                    winner=nextChar;
                    count++;
                }
                else {
                    count=1;
                    break;
                }
                if (count == 3 && winner != BLANK) {
                    return winner;
                }
            }
        }
        count=1;
        for(int row=0;row< board.length;row++ ) {
            char winner = board[row][0];
            char nextChar = BLANK;
            for (int col = 1; col < board.length; col++) {
                nextChar = board[row][col];
                if(nextChar==winner){
                    winner=nextChar;
                    count++;
                }
                else {
                    count=1;
                    break;
                }
                if (count == 3 && winner != BLANK) {
                    return winner;
                }
            }
        }
        if(board[0][0]!= BLANK && board[0][0]==board[1][1]&& board[1][1]==board[2][2]){
            return board[0][0];
        }
        if(board[2][0]!= BLANK && board[2][0]==board[1][1]&& board[1][1]==board[0][2]){
            return board[2][0];
        }

        return BLANK;
    }
}




