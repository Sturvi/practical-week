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
        if (check >=0 && check < 7) return true;
        return false;
    }
}
