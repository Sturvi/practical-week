public class ChessBoard {
    ChessPiece [][] chessPiece;
    private String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
        chessPiece = new ChessPiece[8][8];
    }
}
