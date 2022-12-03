public abstract class ChessPiece {
    String color;
    boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor (){
        return color;
    };

    public abstract boolean canMoveToPosition (ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();

    protected boolean checkBoard (int check){
        if (check >=0 && check <= 7) return true;
        return false;
    }

    protected boolean errorCheck (ChessBoard chessBoard, int line, int column, int toLine, int toColumn){
        //Checking that the coordinates are on the chessboard.
        if (!checkBoard(line) || !checkBoard(column) || !checkBoard(toLine) || !checkBoard(toColumn)) return false;
        //Checking that the beginning and end are not the same, that the starting position is not empty,
        // and also the color of the end point does not match the current color.
        if ((line == toLine && column == toColumn) || chessBoard.board[line][column] == null ||
                (chessBoard.board[toLine][toColumn] != null &&
                        chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())))
            return false;

        return true;
    }
}
