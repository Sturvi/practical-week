public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // If the methods of checking the Bishop or the rook are correct, the Queen can walk like this.
        if (line == toLine  || column == toColumn) {
            return new Rook(color).canMoveToPosition(chessBoard, line, column, toLine, toColumn);
        } else {
            return new Bishop(color).canMoveToPosition(chessBoard, line, column, toLine, toColumn);
        }
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
