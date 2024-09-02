import java.util.Scanner;

class TicTacToe{
    static char board[][];
    TicTacToe(){
        board=new char[3][3];
    }

    void initialboard(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=' ';
            }
        }
    }
    static void displayboard(){
        System.out.println("-------------");
        for(int i=0;i<board.length;i++){
            System.out.print("| ");
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]+" | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
        System.out.println();
    }

    static void placemark(int row,int col,char c){
        if(row>=0 && row<=2 && col>=0 && col<=2){
            board[row][col]=c;
            displayboard();
        }
        else{
            System.out.println("Invalid position");
        }
    }

    static boolean colcheck(){
            for(int j=0;j<board.length;j++){
                if( board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j]){
                    return true;
                }
            }
            return false;
    }
    static boolean rowcheck(){
        for(int i=0;i<board.length;i++){
            if(board[i][0]!=' '&& board[i][0]==board[i][1] && board[i][1]==board[i][2]){
                return true;
            }
        }
        return false;
    }
    static boolean diagonalcheck(){
        if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2]){
            return true;
        } else if (board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]) {
            return true;
        }
        return false;
    }

    static boolean checkdraw(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                if(board[i][j]==' ')
                    return false;
            }
        }
        return true;
    }

}

class Humanplayer{
    String name;
    char mark;

    Humanplayer(String name,char mark){
        this.name=name;
        this.mark=mark;
    }

    void makemove(){
        Scanner sc=new Scanner(System.in);
        int row;
        int col;

        do
        {
         row=sc.nextInt();
         col=sc.nextInt();
        }
        while (!validmove(row,col));

        TicTacToe.placemark(row,col,mark);

    }
    boolean validmove(int row,int col){
        if(row>=0 && row<=2 && col>=0 && col<=2){
            if(TicTacToe.board[row][col]==' ')
                return true;
        }

        return false;
    }
}


public class Main {
    public static void main(String[] args) {
        TicTacToe T=new TicTacToe();
        T.initialboard();

        Humanplayer p1=new Humanplayer("varun",'X');
        Humanplayer p2=new Humanplayer("deva",'O');

        Humanplayer cp;
        cp=p1;

        while (true) {
            System.out.println(cp.name +" Turn");
            cp.makemove();
            if (TicTacToe.rowcheck() || TicTacToe.colcheck() || TicTacToe.diagonalcheck()) {
                System.out.println(cp.name + " W O N ");
                break;
            }

            else if (TicTacToe.checkdraw()) {
                System.out.println("Match D R A W");
                break;
            }

            else {
                if (cp == p1)
                    cp = p2;
                else
                    cp = p1;
            }
        }


    }
}