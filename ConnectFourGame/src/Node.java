import java.util.ArrayList;

/**
 * Created by DevM on 07/03/2017.
 */
public class Node {



    private Board boardState;
    private ArrayList<Node> children;
    int nextPlayer;
    int move;
    int score;
    int alpha;
    int beta;

    public Node (Board boardState, int nextPlayer, int move){
        this.boardState=new Board(6,7);
        this.boardState.copyBoard(boardState);
        this.nextPlayer=nextPlayer;
        this.move=move;
        this.score = 0;
        this.alpha=Integer.MIN_VALUE;
        this.beta=Integer.MAX_VALUE;
        children = new ArrayList<Node>();

    }
    public Node (Board boardState, int nextPlayer){
        this.boardState=new Board(6,7);
        this.boardState.copyBoard(boardState);
        this.nextPlayer=nextPlayer;
        this.move=0;
        this.score = 0;
        this.alpha=Integer.MIN_VALUE;
        this.beta=Integer.MAX_VALUE;
        children = new ArrayList<Node>();

    }

    public void generateChildren(){
        Board tmp = new Board(6,7);
        tmp.copyBoard(boardState);
        for(int i = 0; i < tmp.getWidth();i++){
            if(tmp.isMoveValid(i)){
                tmp.copyBoard(boardState);
               tmp.dropPiece(i,nextPlayer);

                if(nextPlayer==0) {
                    Node child = new Node(tmp, 1,i);
                    children.add(child);
                }
                else{
                    Node child = new Node(tmp,0,i);
                    children.add(child);
                }
            }
        }
    }

    public void copyNode(Node x){
        this.boardState.copyBoard(x.boardState);
        this.children=x.getChildren();
        this.nextPlayer=x.nextPlayer;
        this.move=x.move;
        this.score=x.score;
        this.alpha=x.alpha;
        this.beta=x.beta;


    }



    public Board getBoardState() {
        return boardState;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }
}
