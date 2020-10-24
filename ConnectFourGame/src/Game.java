/**
 * Created by DevM on 06/03/2017.
 */
import java.util.*;

public class Game {
    static int currentPlayer=0;
    public static void main(String args[]){

        Scanner in = new Scanner(System.in);
        //Initialize game board
        Board gameBoard = new Board(6,7);
        //Int containing the play
        int column;
        //Boolean if its against AI or local
        boolean vsAI;
        //Int containing who will make the first move
        int firstPlayer=0; // X - > 0  O -> 1
        //Computer move
        int gameOver;

        System.out.println("Play local or against AI? (0 - local 1 - AI)");

        if(in.nextInt()==0)
            vsAI=false;
        else{
            vsAI=true;

        }

        if(vsAI){
            System.out.println("Choose who plays first, 0->you 1->AI");
            firstPlayer=in.nextInt();

            if(firstPlayer==0){
                System.out.println("Choose column");
                column = in.nextInt();
                gameBoard.dropPiece(column, currentPlayer);
                gameBoard.printBoard();
                changePlayer();

            }
            else{
                changePlayer();

            }
        }
        AIMinimax AI = new AIMinimax();
        while(gameBoard.isGameOver()==0){
            if(vsAI){
                if(currentPlayer==0){  //player Move
                    System.out.println("Choose column");
                    column = in.nextInt();
                    gameBoard.dropPiece(column, currentPlayer);
                    gameBoard.printBoard();
                    changePlayer();
                }
                else{ //AI Move
                    Node startingNode = new Node(gameBoard,currentPlayer);
                    //Node move = AI.alphaBeta(startingNode, 0, currentPlayer,startingNode.alpha,startingNode.beta);
                    Node move = AI.minimax(startingNode, 0, currentPlayer);
                    gameBoard.dropPiece(move.move, currentPlayer);
                    gameBoard.printBoard();
                    changePlayer();
                }

            }
            else{
                System.out.println("Choose column");
                column = in.nextInt();
                gameBoard.dropPiece(column, currentPlayer);
                gameBoard.printBoard();
                int x = gameBoard.evaluateTest(currentPlayer);
                System.out.println(x);
                changePlayer();

            }

            gameOver = gameBoard.isGameOver();

            if (gameOver == 1) {

                System.out.println("player X won!");
                break;

            }
            else if (gameOver == -1) {

                System.out.println("player O won!");
                break;

            }
            else if(gameOver == -1){
                System.out.println("Its a draw!");
            }
        }
    }

    public static void changePlayer(){
        if(currentPlayer==0)
            currentPlayer=1;
        else{
            currentPlayer=0;
        }
    }
}
