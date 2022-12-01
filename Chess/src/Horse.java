public class Horse extends ChessPiece {


    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkBoard(toLine) && checkBoard(toColumn)){
            if ((Math.abs(line-toLine) == 2 && Math.abs(column-toColumn) == 1) ||
                    (Math.abs(line-toLine) == 1 && Math.abs(column-toColumn) == 2))
                return true;
            else
                return false;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    private boolean checkBoard (int chek){
        if (chek >=0 && chek < 7) return true;
        return false;
    }
}
