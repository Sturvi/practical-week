import java.util.ArrayList;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!errorCheck(chessBoard, line, column, toLine, toColumn))
            return false;

        // We check whether the rook can make such a move at all.
        if (line != toLine && column != toColumn)
            return false;

        // check if there is an obstacle on the way to the end point.
        if (column == toColumn) {
            for (int i = Math.min(line + 1, toLine + 1); i < Math.max(line, toLine); i++) {
                if (chessBoard.board[i][column] != null)
                    return false;
            }
        } else if (line == toLine) {
            for (int i = Math.min(column + 1, toColumn + 1); i < Math.max(column, toColumn); i++) {
                if (chessBoard.board[line][i] != null)
                    return false;
            }
        }

        check = false;
        return true;
    }

    public void searchAttackedRoad (int line, int column, int toLine, int toColumn, ArrayList<Integer> attackingRoadLine, ArrayList<Integer> attackingRoadColumn){
        if (column == toColumn) {
            for (int i = Math.min(line + 1, toLine + 1); i < Math.max(line, toLine); i++) {
                attackingRoadLine.add(i);
                attackingRoadColumn.add(column);
            }
        } else if (line == toLine) {
            for (int i = Math.min(column + 1, toColumn + 1); i < Math.max(column, toColumn); i++) {
                attackingRoadLine.add(line);
                attackingRoadColumn.add(i);
            }
        }
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
