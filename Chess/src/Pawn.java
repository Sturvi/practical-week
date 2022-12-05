import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends ChessPiece {

    private boolean possibleToTakeOnThePassage = false;

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!errorCheck(chessBoard, line, column, toLine, toColumn))
            return false;

        if (getColor().equals("White")) {
            //In cases where the pawn goes forward.
            if (column == toColumn && chessBoard.board[toLine][toColumn] == null) {
                switch (toLine - line) {
                    case (1):
                        return true;
                    case (2):
                        if (chessBoard.board[line + 1][column] != null) return false;
                        if (line == 1) {
                            setPossibleToTakeOnThePassage(true);
                            return true;
                        }
                        return false;
                    default:
                        return false;
                }
            }

            //Can a pawn make a capture? if not, we check whether the pawn can take on the pass.
            if (Math.abs(column - toColumn) == 1 && toLine - line == 1 && chessBoard.board[toLine][toColumn] != null){
                return true;
            } else if (Math.abs(column - toColumn) == 1 && toLine - line == 1 && chessBoard.board[toLine][toColumn] == null) {
                if (chessBoard.board[toLine-1][toColumn] != null &&
                        chessBoard.board[toLine-1][toColumn].getSymbol().equals("P") &&
                        ((Pawn)(chessBoard.board[toLine-1][toColumn])).isPossibleToTakeOnThePassage()){
                    return true;
                }
            }
        } else { //the same check, only for black pieces.
            if (column == toColumn && chessBoard.board[toLine][toColumn] == null) {
                switch (line - toLine) {
                    case (1):
                        return true;
                    case (2):
                        if (chessBoard.board[line - 1][column] != null) return false;
                        if (line == 6) {
                            setPossibleToTakeOnThePassage(true);
                            return true;
                        }
                        return false;
                    default:
                        return false;
                }
            }

            if (Math.abs(column - toColumn) == 1 && line - toLine == 1 && chessBoard.board[toLine][toColumn] != null){
                return true;
            } else if (Math.abs(column - toColumn) == 1 && line - toLine == 1 && chessBoard.board[toLine][toColumn] == null) {
                if (chessBoard.board[toLine+1][toColumn] != null &&
                        chessBoard.board[toLine+1][toColumn].getSymbol().equals("P") &&
                        ((Pawn)(chessBoard.board[toLine+1][toColumn])).isPossibleToTakeOnThePassage()){
                    return true;
                }
            }
        }

        return false;
    }

    public void searchAttackedRoad (int line, int column, int toLine, int toColumn, ArrayList<Integer> attackingRoadLine, ArrayList<Integer> attackingRoadColumn){}

    @Override
    public String getSymbol() {
        return "P";
    }

    public void setPossibleToTakeOnThePassage(boolean possibleToTakeOnThePassage) {
        this.possibleToTakeOnThePassage = possibleToTakeOnThePassage;
    }

    public boolean isPossibleToTakeOnThePassage() {
        return possibleToTakeOnThePassage;
    }
}
