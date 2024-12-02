package piece;

import main.GamePanel;
import main.Type;

public class King extends Piece{
    public King(int colour, int col, int row) {
        super(colour, col, row);
        type = Type.KING;

        if(colour == GamePanel.WHITE) {
            image = getImage("/piece/w-king");
        }
        else {
            image = getImage("/piece/b-king");
        }
    }

    public boolean canMove(int targetColumn, int targetRow) {
        if (isWithinBoard(targetColumn, targetRow)) {
            //Movement
            if (Math.abs(targetColumn - preCol) + Math.abs(targetRow - preRow) == 1
                    || Math.abs(targetColumn - preCol) * Math.abs(targetRow - preRow) == 1) {

                if (isValidSquare(targetColumn, targetRow)) {
                    return true;
                }
            }

            // Castling
            if(!moved){

                // Right castling
                if (targetColumn == preCol+2 && targetRow == preRow && !pieceIsOnStraightLine(targetColumn, targetRow)) {
                    for(Piece piece : GamePanel.simPieces){
                        if(piece.col == preCol+3 && piece.row == preRow && !piece.moved){
                            GamePanel.castlingPiece = piece;
                            return true;
                        }
                    }
                }

                // Left Castling
                if (targetColumn == preCol-2 && targetRow == preRow && !pieceIsOnStraightLine(targetColumn, targetRow)){
                    Piece pieces[] = new Piece[2];
                    for(Piece piece : GamePanel.simPieces){
                        if (piece.col == preCol-3 && piece.row == targetRow) {
                            pieces[0] = piece;
                        }
                        if (piece.col == preCol-4 && piece.row == targetRow) {
                            pieces[1] = piece;
                        }
                        if (pieces[0] == null && pieces[1] != null  && pieces[1].moved == false) {
                            GamePanel.castlingPiece = piece;
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
}
