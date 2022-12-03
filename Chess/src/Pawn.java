public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!errorCheck(chessBoard, line, column, toLine, toColumn))
            return false;

        if (column == toColumn) {
            if (getColor().equals("White")) {
                switch (toLine - line) {
                    case (1):
                        return true;
                    case (2):
                        if (chessBoard.board[line+1][column] != null) return false;
                        return line == 1 ? true : false;
                    default:
                        return false;
                }
            } else {
                switch (line-toLine) {
                    case (1):
                        return true;
                    case (2):
                        if (chessBoard.board[line-1][column] != null) return false;
                        return line == 6 ? true : false;
                    default:
                        return false;
                }
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
