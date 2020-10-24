import java.util.ArrayList;

/**
 * Created by DevM on 07/03/2017.
 */
public class AIMinimax {

    int maxDepth=8;
    public AIMinimax(){

    }

    // ALPHA BETA ------------------------------------------------------------------------------------------------

    public Node alphaBeta(Node node, int depth, int currentPlayer,int alpha,int beta){
        int v;
        if(node.getBoardState().isGameOver() != 0 || depth == maxDepth){
            node.score=node.getBoardState().evaluateTest(currentPlayer);
            return node;
        }

        else if(currentPlayer == 0){ //AI turn
            v = alphaBetaMaxfunction(node, currentPlayer, depth,alpha,beta).score;
            node.score = v;
            return node;
        }

        else{ //MY turn
            v = alphaBetaMinfunction(node, currentPlayer, depth,alpha,beta).score;
            node.score = v;
            return node;
        }
    }

    private Node alphaBetaMaxfunction(Node node,int currentPlayer, int depth,int alpha,int beta){
        int v = Integer.MIN_VALUE;
        int best = Integer.MIN_VALUE;
        int move = 0;
        node.beta=beta;
        node.generateChildren();
        if(currentPlayer==1)
            currentPlayer=0;
        else{
            currentPlayer=1;
        }
        for(Node w : node.getChildren()){
            Node tmp = new Node(w.getBoardState(),currentPlayer);
            tmp.copyNode(alphaBeta(w,depth+1, currentPlayer,node.alpha,node.beta));
            v = Math.max(v,tmp.score);
            if(v>best){
                best = v;
                move = tmp.move;
            }
            if(v >= node.beta){
                node.score=v;
                return node;
            }
            alpha=Math.max(alpha,v);
        }
        node.score=v;
        if(depth==0)
        node.move=move;
        return node;
    }

    private Node alphaBetaMinfunction(Node node, int currentPlayer,int depth, int alpha,int beta) {
        int v = Integer.MAX_VALUE;
        int best = Integer.MAX_VALUE;
        int move = 0;
        node.alpha=alpha;

        node.generateChildren();
        if (currentPlayer == 1)
            currentPlayer = 0;
        else {
            currentPlayer = 1;
        }
        for (Node w : node.getChildren()) {

            Node tmp = new Node(w.getBoardState(),currentPlayer);
            tmp.copyNode(alphaBeta(w,depth+1, currentPlayer,alpha,beta));
            v = Math.min(v, tmp.score);
            if(v<best){
                best = v;
                move = tmp.move;
            }
            if(v <= node.alpha){
                //node.beta=v;
                node.score=v;
                return node;

            }
            beta=Math.min(beta,v);

        }
        node.score=v;
        if(depth==0)
        node.move=move;
        return node;
    }

    //MINIMAX ---------------------------------------------------------------------------------------

    public Node minimax(Node node, int depth, int currentPlayer){
        int v;
        if(node.getBoardState().isGameOver() != 0 || depth == maxDepth){
            node.score=node.getBoardState().evaluateTest(currentPlayer);
            return node;
        }

       else if(currentPlayer == 0){ //AI turn
            v = maxfunction(node, currentPlayer, depth).score;
            node.score = v;
            return node;
        }

        else{ //MY turn
            v = minfunction(node, currentPlayer, depth).score;
            node.score = v;
            return node;
        }
    }

    private Node maxfunction(Node node,int currentPlayer, int depth){
        int v = Integer.MIN_VALUE;
        int best = Integer.MIN_VALUE;
        int move = 0;
        //System.out.println("ola");
        node.generateChildren();
        if(currentPlayer==1)
            currentPlayer=0;
        else{
            currentPlayer=1;
        }

        for(Node w : node.getChildren()){
            Node tmp = new Node(w.getBoardState(),currentPlayer);
            tmp.copyNode(minimax(w,depth+1, currentPlayer));
            v = Math.max(v,tmp.score);
            if(v>best){
                best = v;
                move = tmp.move;
            }
        }
        node.score=v;
        if(depth==0)
        node.move=move;
        return node;
    }

    private Node minfunction(Node node, int currentPlayer,int depth) {
        int v = Integer.MAX_VALUE;
        int best = Integer.MAX_VALUE;
        int move = 0;
        //System.out.println("ola2");
        node.generateChildren();

        if(currentPlayer==1)
            currentPlayer=0;
        else{
            currentPlayer=1;
        }
        for (Node w : node.getChildren()) {
            Node tmp = new Node(w.getBoardState(),currentPlayer);
            tmp.copyNode(minimax(w,depth+1, currentPlayer));
            v = Math.min(v, tmp.score);
            if(v<best){
                best = v;
                move = tmp.move;
            }

        }
        node.score=v;
        if(depth==0)
        node.move=move;
        return node;
    }


}
