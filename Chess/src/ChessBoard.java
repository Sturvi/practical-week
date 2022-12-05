import java.util.ArrayList;
import java.util.Arrays;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;
    Integer pawnLine;
    Integer pawnColumn;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {

                //creating a backup copy of the state of our board. If at the end of the turn the king is under attack,
                // we return the state of the board back.
                ChessPiece[][] temporaryBoard = cloneBoard(board);

                if (board[startLine][startColumn].getSymbol().equals("K") ||  // check position for castling
                        board[startLine][startColumn].getSymbol().equals("R")) {
                    board[startLine][startColumn].check = false;
                }

                //Checking the capture on the pawn on the pass. else the usual move is being made.
                if (board[startLine][startColumn].getSymbol().equals("P") && startColumn != endColumn) {
                    board[endLine][endColumn] = board[startLine][startColumn];
                    board[startLine][startColumn] = null;

                    if (nowPlayer.equals("White")) {
                        board[endLine - 1][endColumn] = null;
                    } else {
                        board[endLine + 1][endColumn] = null;
                    }
                } else {
                    board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                    board[startLine][startColumn] = null; // set null to previous cell
                }

                //Pawn to Queen Check
                pawnToQueenCheck(endLine, endColumn);

                //If the king is under attack after making a move, we cancel the move.
                if (isKingUnderAttack(temporaryBoard)) return false;

                //If the enemy king is under attack after your turn, we print a message about it.
                if ((nowPlayer.equals("White") && blackKingsUnderAttack()) ||
                        (nowPlayer.equals("Black") && whiteKingsUnderAttack())) {
                    if (checkMatt()) {
                        System.out.println("Matt! " + nowPlayerColor() + " wins!");
                    } else {
                        System.out.println("Check");
                    }
                }

                //if the previous pawn that could have been picked up on the pass is still on the board, we remove
                // from it the possibility of being knocked down in this way.
                resetPossibleToTakeOnThePassage();

                //If a pawn has taken 2 steps forward, theoretically it can be knocked down on the pass.
                if (board[endLine][endColumn].getSymbol().equals("P") &&
                        ((Pawn) (board[endLine][endColumn])).isPossibleToTakeOnThePassage()) {
                    pawnLine = endLine;
                    pawnColumn = endColumn;
                }

                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {              // never moved
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 2)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][2] = new King("White");   // move King
                    board[0][2].check = false;
                    board[0][0] = null;
                    board[0][3] = new Rook("White");   // move Rook
                    board[0][3].check = false;
                    nowPlayer = "Black";  // next turn
                    resetPossibleToTakeOnThePassage();
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {              // never moved
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 2)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][2] = new King("Black");   // move King
                    board[7][2].check = false;
                    board[7][0] = null;
                    board[7][3] = new Rook("Black");   // move Rook
                    board[7][3].check = false;
                    nowPlayer = "White";  // next turn
                    resetPossibleToTakeOnThePassage();
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][5] == null && board[0][6] == null) {              // never moved
                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][7].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 2)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][6] = new King("White");   // move King
                    board[0][6].check = false;
                    board[0][7] = null;
                    board[0][5] = new Rook("White");   // move Rook
                    board[0][5].check = false;
                    nowPlayer = "Black";  // next turn
                    resetPossibleToTakeOnThePassage();
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][5] == null && board[7][6] == null) {              // never moved
                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 2)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][6] = new King("Black");   // move King
                    board[7][6].check = false;
                    board[7][7] = null;
                    board[7][5] = new Rook("Black");   // move Rook
                    board[7][5].check = false;
                    nowPlayer = "White";  // next turn
                    resetPossibleToTakeOnThePassage();
                    return true;
                } else return false;
            } else return false;
        }
    }

    //if the previous pawn that could have been picked up on the pass is still on the board, we remove
    // from it the possibility of being knocked down in this way.
    private void resetPossibleToTakeOnThePassage() {
        if (pawnLine != null) {
            if (board[pawnLine][pawnColumn] != null && board[pawnLine][pawnColumn].getSymbol().equals("P")) {
                ((Pawn) (board[pawnLine][pawnColumn])).setPossibleToTakeOnThePassage(false);
            }
            pawnLine = null;
            pawnColumn = null;
        }
    }

    private boolean whiteKingsUnderAttack() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getSymbol().equals("K") &&
                        board[i][j].getColor().equals("White") &&
                        ((King) (board[i][j])).isUnderAttack(this, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean blackKingsUnderAttack() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getSymbol().equals("K") &&
                        board[i][j].getColor().equals("Black") &&
                        ((King) (board[i][j])).isUnderAttack(this, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private ChessPiece[][] cloneBoard(ChessPiece[][] board) {
        ChessPiece[][] clonedBoard = new ChessPiece[8][8];
        for (int i = 0; i < 8; i++) {
            clonedBoard[i] = Arrays.copyOf(board[i], 8);
        }
        return clonedBoard;
    }

    private boolean isKingUnderAttack(ChessPiece[][] temporaryBoard) {
        if ((nowPlayer.equals("White") && whiteKingsUnderAttack()) ||
                (nowPlayer.equals("Black") && blackKingsUnderAttack())) {
            board = temporaryBoard;
            System.out.println("Your King is under attack");
            return true;
        }
        return false;
    }

    private void pawnToQueenCheck(int line, int column) {
        if ((nowPlayer.equals("White") && line == 7 || (nowPlayer.equals("Black") && line == 0)) &&
                board[line][column].getSymbol().equals("P")) {
            board[line][column] = new Queen(nowPlayer);
        }
    }

    private boolean checkMatt() {
        Integer kingLine = null;
        Integer kingColumn = null;
        ArrayList<Integer> attackingLine = new ArrayList<Integer>();
        ArrayList<Integer> attackingColumn = new ArrayList<Integer>();

        //search for the king
        for (int i = 0; i < 8; i++) {
            boolean exitCheck = false;
            for (int j = 0; j < 8; j++) {

                if (board[i][j] != null && board[i][j].getSymbol().equals("K") && !board[i][j].getColor().equals(nowPlayer)) {
                    kingLine = i;
                    kingColumn = j;
                    exitCheck = true;
                    break;
                }
            }
            if (exitCheck) break;

        }

        //if the king can escape from the attack, there is no checkmate!
        if (canTheKingLeave(kingLine, kingColumn)) return false;

        searchKingForAttackers(attackingLine, attackingColumn, kingLine, kingColumn);

        //If the king cannot escape the attack and is attacked by more than 1 piece at the same time, then this is checkmate!
        if (attackingLine.size() > 1) return true;

        //if the king is attacked by 1 piece and it can be knocked down, then this is not a checkmate.
        if (isUnderAttack(attackingLine.get(0), attackingColumn.get(0), nowPlayer.equals("White") ? "Black" : "White")) {
            return false;
        }

        //if the path of attack can be blocked by another piece, then this is not a checkmate!
        if (isPossibleToBlockTheAttack(attackingLine.get(0), attackingColumn.get(0), kingLine, kingColumn))
            return false;

        return true;
    }

    //search for the coordinates of all the pieces that attack the king.
    private void searchKingForAttackers(ArrayList<Integer> attackingLine, ArrayList<Integer> attackingColumn, Integer kingLine, Integer kingColumn) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getColor().equals(nowPlayer) && board[i][j].canMoveToPosition(this, i, j, kingLine, kingColumn)) {
                    attackingLine.add(i);
                    attackingColumn.add(j);
                }
            }
        }
    }

    private boolean canTheKingLeave(Integer kingLine, Integer kingColumn) {
        for (int i = Math.max(0, kingLine - 1); i < Math.min(7, kingLine + 1); i++) {
            for (int j = Math.max(0, kingColumn - 1); j < Math.min(7, kingColumn + 1); j++) {
                if (board[i][j] == null && !isUnderAttack(i, j, nowPlayer) && !(i == kingLine && j == kingColumn))
                    return true;
            }
        }
        return false;
    }

    private boolean isUnderAttack(int line, int column, String nowPlayer) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getColor().equals(nowPlayer) &&
                        board[i][j].canMoveToPosition(this, i, j, line, column)) {
                    if (board[i][j].getSymbol().equals("K")) {
                        ChessPiece[][] temp = cloneBoard(board);
                        board[line][column] = board[i][j];
                        board[i][j] = null;
                        if (((King) (board[line][column])).isUnderAttack(this, line, column)) {
                            board = temp;
                            continue;
                        }
                        board = temp;
                    } else
                        return true;
                }
            }
        }
        return false;
    }

    private boolean isPossibleToBlockTheAttack(int attackerLine, int attackerColumn, int kingLine, int kingColumn) {
        ArrayList<Integer> attackingRoadLine = new ArrayList<Integer>();
        ArrayList<Integer> attackingRoadColumn = new ArrayList<Integer>();

        board[attackerLine][attackerColumn].searchAttackedRoad(attackerLine, attackerColumn, kingLine, kingColumn, attackingRoadLine, attackingRoadColumn);

        if (attackingRoadLine.size() == 0)
            return false;
        else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] != null && !(i == kingLine && j == kingColumn) && !board[i][j].getColor().equals(nowPlayer)) {
                        for (int k = 0; k < attackingRoadLine.size(); k++) {
                            if (board[i][j].canMoveToPosition(this, i, j, attackingRoadLine.get(k), attackingRoadColumn.get(0)))
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
