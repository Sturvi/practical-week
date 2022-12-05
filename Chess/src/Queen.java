import java.util.ArrayList;

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

    public void searchAttackedRoad (int line, int column, int toLine, int toColumn, ArrayList<Integer> attackingRoadLine, ArrayList<Integer> attackingRoadColumn){
        if (line == toLine  || column == toColumn) {
            new Rook(color).searchAttackedRoad (line, column, toLine, toColumn, attackingRoadLine, attackingRoadColumn);
        } else {
            new Bishop(color).searchAttackedRoad (line, column, toLine, toColumn, attackingRoadLine, attackingRoadColumn);
        }
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
