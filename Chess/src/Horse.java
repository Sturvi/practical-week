import java.util.ArrayList;

public class Horse extends ChessPiece {


    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!errorCheck(chessBoard, line, column, toLine, toColumn))
            return false;

        if ((Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1) ||
                (Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 2))
            return true;
        else
            return false;
    }

    public void searchAttackedRoad (int line, int column, int toLine, int toColumn, ArrayList<Integer> attackingRoadLine, ArrayList<Integer> attackingRoadColumn){}

    @Override
    public String getSymbol() {
        return "H";
    }


}
