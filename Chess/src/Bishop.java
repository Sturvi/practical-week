public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //Checking that the coordinates are on the chessboard.
        if (!checkBoard(line) || !checkBoard(column) || !checkBoard(toLine) || !checkBoard(toColumn)) return false;
        //Checking that the beginning and end are not the same, that the starting position is not empty,
        // and also the color of the end point does not match the current color.
        if ((line == toLine && column == toColumn) || chessBoard.board[line][column] == null ||
                (chessBoard.board[toLine][toColumn] != null &&
                        chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())))
            return false;
        //Check for an impossible move
        if (Math.abs(line - toLine) != Math.abs(column - toColumn)) return false;
        // In cases when the direction of movement is from the lower left corner towards the upper right. or vice versa.
        // we are looking for whether there are obstacles on the way.
        // In else, the same thing, but for the direction of movement from the lower right corner towards the upper left or vice versa.
        if (line - toLine == column - toColumn) {
            int j = Math.min(column + 1, toColumn + 1);
            for (int i = Math.min(line + 1, toLine + 1); i != Math.max(line, toLine); i++) {
                if (chessBoard.board[i][j] != null) return false;
                j++;
            }
        } else {
            int j = Math.max(column - 1, toColumn - 1);
            for (int i = Math.min(line + 1, toLine + 1); i != Math.max(line, toLine); i++) {
                if (chessBoard.board[i][j] != null) return false;
                j--;
            }
        }
        return true;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
