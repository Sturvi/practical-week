public class Queen extends ChessPiece{
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // If the methods of checking the word or the rook are correct, the Queen can walk like this.
        if (new Bishop(color).canMoveToPosition(chessBoard, line, column, toLine, toColumn) ||
                new Rook(color).canMoveToPosition(chessBoard, line, column, toLine, toColumn))
            return true;
        else
            return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
