public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!errorCheck(chessBoard, line, column, toLine, toColumn))
            return false;

        if ((Math.abs(line-toLine) == 0 || Math.abs(line-toLine) == 1) &&
                (Math.abs(column-toColumn) == 0 || Math.abs(column-toColumn) == 1)) {
            check = false;
            return true;
        }
        else
            return false;
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        if (!checkBoard(line) || !checkBoard(column))
            return false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.board[i][j] != null && board.board[i][j].canMoveToPosition(board, i, j, line, column))
                    return true;
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
