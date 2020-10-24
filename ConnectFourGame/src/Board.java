/**
 * Created by DevM on 06/03/2017.
 */



public class Board{
    private int height,width;
    private char boardState[][];

    public Board(int height, int width){

        this.height=height;
        this.width=width;

        boardState = new char[height][width];

        fillMatrix();

    }

    private void fillMatrix(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width;j++){
                boardState[i][j]='-';
            }
        }
    }
    /* X -> player 0  O -> player 1 */
    public void dropPiece(int column, int player){
        if ( (column < 0 || column > height ) || (boardState[0][column]!='-' ) ){
          System.out.println("Invalid Column or Column is full");
        }
        else {

            for (int i = height - 1; i >= 0; i--) {
                if (boardState[i][column] == '-') {
                    if (player == 0) {
                        boardState[i][column] = 'X';
                    } else {
                        boardState[i][column] = 'O';
                    }
                    break;
                }

            }
        }
    }

    public void printBoard(){
        for(int i=0; i < width;i++)
            System.out.print(i + " ");
        System.out.println();
        for(int i = 0; i < height ; i++){
            for(int j = 0 ; j < width ; j ++){
                System.out.print(boardState[i][j] + " ");
            }
            System.out.println();
        }
    }

    /* return one if game is over and X won, returns minus one if game is over and O won, 0 if it is not over  */

    public int isGameOver () {
        int count = 0;

        /* checks row*/
        for( int i = 0 ; i < height; i++){
            for(int j = 0 ; j < width; j++){


                    if (boardState[i][j] == 'X') {
                        count++;
                    }
                    else {
                        count = 0;
                    }
                    if(count == 4){
                        return 1;
                    }

            }
            count = 0;
        }
        /* checks column */
        for( int i = 0 ; i < height; i++){
            for(int j = 0 ; j < width; j++){


                    if (boardState[i][j] == 'O')
                        count++;
                    else {
                        count = 0;
                    }
                    if(count == 4 ){
                        return -1;
                    }

            }
            count = 0;
        }
        for( int i = 0 ; i < width; i++){
            for(int j = 0 ; j < height; j++){


                    if (boardState[j][i] == 'X')
                        count++;
                    else {
                        count = 0;
                    }
                    if(count == 4){
                        return 1;
                    }


            }
            count = 0;
        }

        for( int i = 0 ; i < width; i++){
            for(int j = 0 ; j < height; j++){


                    if (boardState[j][i] == 'O')
                        count++;
                    else {
                        count = 0;
                    }
                    if(count == 4){
                        return -1;
                    }

            }
            count = 0;
        }

        //check diagonal /
        for(int i=3;i<height;i++){
            for(int j=0;j<width-3;j++){
                if ( boardState[i][j] == 'X' && boardState[i-1][j+1] == 'X' &&
                        boardState[i-2][j+2] == 'X' && boardState[i-3][j+3] == 'X') {
                    return 1;
                }
                else if ( boardState[i][j] == 'O' && boardState[i-1][j+1] == 'O' &&
                        boardState[i-2][j+2] == 'O' && boardState[i-3][j+3] == 'O') {
                    return -1;
                }
            }
        }

        //check diagonal \
        for(int i=3;i<height;i++){
            for(int j=width-1;j>=width-4;j--){
                //System.out.println(i + " " + j);
                //System.out.println(boardState[i-1][j+1]);
                if (   boardState[i][j] == 'X' && boardState[i-1][j-1] == 'X' &&
                        boardState[i-2][j-2] == 'X' && boardState[i-3][j-3] == 'X') {
                    return 1;
                }
                else if (   boardState[i][j] == 'O' && boardState[i-1][j-1] == 'O' &&
                        boardState[i-2][j-2] == 'O' && boardState[i-3][j-3] == 'O') {
                    return -1;
                }
            }
        }

        //Draw
        int countX=0;
        for(int i = 0 ; i< height ; i++){
            for(int j=0; j < width ; j++){
                if(boardState[i][j]=='-')
                    countX++;
            }
        }
        if(countX==0)
            return 2;
        return 0;

    }



    public boolean isMoveValid(int column){
        if ( (column < 0 || column > width ) || (boardState[0][column]!='-' ) ){
            return false;
        }
        return true;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


    public void setValue(int x, int y, int currentPlayer){
        if(currentPlayer==0)
        boardState[x][y]='X';
        else{
            boardState[x][y]='O';
        }
    }

    public int evaluateTest(int player){
        int sum=0;

        int a = evaluteRows(player);
        int b = evaluteColumns(player);
        int c = evaluateLeftDiagonal(player);
        int d = evaluateRightDiagonal(player);

        sum = a + b + c + d;

        return sum;

    }

    private int evaluteRows(int currentPlayer){
        int countX = 0;
        int countO = 0;
        int count = 0;
        int best = 0;
        for( int i = 0 ; i < height; i++){
            for(int j = 0 ; j < width-3; j++){
                if (boardState[i][j] == 'X')
                    countX++;
                if(boardState[i][j+1] == 'X')
                    countX++;
                if(boardState[i][j+2] == 'X')
                    countX++;
                if(boardState[i][j+3] == 'X')
                    countX++;

                if (boardState[i][j] == 'O')
                    countO++;
                if(boardState[i][j+1] == 'O')
                    countO++;
                if(boardState[i][j+2] == 'O')
                    countO++;
                if(boardState[i][j+3] == 'O')
                    countO++;

                count = count + evaluateValue(countX, countO);
                countX = 0;
                countO = 0;
            }
        }
        int result;
        if(currentPlayer == 0) { //cases where its X
            result = count + 16;
        }
        else{
            result = count - 16;
        }

        return result;

    }

    private int evaluteColumns(int currentPlayer){
        int countX = 0;
        int countO = 0;
        int count = 0;
        int best = 0;
        for( int i = 0 ; i < width-4; i++){ //lin-6
            for(int j = 0 ; j < height+1; j++){  //col-7
                if (boardState[i][j] == 'X')
                    countX++;
                if(boardState[i+1][j] == 'X')
                    countX++;
                if(boardState[i+2][j] == 'X')
                    countX++;
                if(boardState[i+3][j] == 'X')
                    countX++;

                if (boardState[i][j] == 'O')
                    countO++;
                if(boardState[i+1][j] == 'O')
                    countO++;
                if(boardState[i+2][j] == 'O')
                    countO++;
                if(boardState[i+3][j] == 'O')
                    countO++;

                count = count + evaluateValue(countX, countO);
                countX = 0;
                countO = 0;
            }
        }
        int result;
        if(currentPlayer == 0) { //cases where its X
            result = count + 16;
        }
        else{
            result = count - 16;
        }
        //System.out.println(result);
        return result;
    }

    private int evaluateRightDiagonal(int currentPlayer){
        int countX = 0;
        int countO = 0;
        int count = 0;
        int best = 0;
        for(int i=3;i<height;i++){
            for(int j=0;j<width-3;j++){
                if(boardState[i][j] == 'X')
                    countX++;
                if( boardState[i-1][j+1] == 'X')
                    countX++;
                if(boardState[i-2][j+2] == 'X')
                    countX++;
                if(boardState[i-3][j+3] == 'X')
                    countX++;

                if(boardState[i][j] == 'O')
                    countO++;
                if( boardState[i-1][j+1] == 'O')
                    countO++;
                if(boardState[i-2][j+2] == 'O')
                    countO++;
                if(boardState[i-3][j+3] == 'O')
                    countO++;

                count = count + evaluateValue(countX, countO);
                countX = 0;
                countO = 0;
            }
        }
        int result;
        if(currentPlayer == 0) { //cases where its X
            result = count + 16;
        }
        else{
            result = count - 16;
        }
        return result;
    }

    private int evaluateLeftDiagonal(int currentPlayer){
        int countX = 0;
        int countO = 0;
        int count = 0;
        int best = 0;
        for(int i=3;i<height;i++){
            for(int j=width-1;j>=width-4;j--){
                if(boardState[i][j] == 'X')
                    countX++;
                if( boardState[i-1][j-1] == 'X' )
                    countX++;
                if( boardState[i-2][j-2] == 'X')
                    countX++;
                if(boardState[i-3][j-3] == 'X')
                    countX++;

                if( boardState[i][j] == 'O' )
                    countO++;
                if( boardState[i-1][j-1] == 'O')
                    countO++;
                if(boardState[i-2][j-2] == 'O')
                    countO++;
                if(boardState[i-3][j-3] == 'O')
                    countO++;

                count = count + evaluateValue(countX, countO);
                countX = 0;
                countO = 0;
            }
        }
        int result;
        if(currentPlayer == 0) { //cases where its X
            result = count + 16;
        }
        else{
            result = count - 16;
        }
        return result;
    }

    private int evaluateValue(int x, int o){
        if(x == 1 && o == 0)
            return 1;
        if(x == 2 && o == 0)
            return 10;
        if(x == 3 && o == 0)
            return 50;
        if(x == 4 && o == 0)
            return 512;

        if(x == 0 && o == 1)
            return -1;
        if(x == 0 && o == 2)
            return -10;
        if(x == 0 && o == 3)
            return -50;
        if(x == 0 && o == 4)
            return -512;

        else
            return 0;

    }


    public void copyBoard(Board x){
        this.width=x.getWidth();
        this.height=x.getHeight();
        //this.boardState=x.boardState;
        for(int i = 0;i<x.boardState.length;i++){
            for(int j=0; j < x.boardState[i].length;j++){
                this.boardState[i][j]=x.boardState[i][j];
            }
        }

    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

