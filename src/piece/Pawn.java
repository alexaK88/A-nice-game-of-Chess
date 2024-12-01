package piece;

import main.GamePanel;

public class Pawn extends Piece {
    public Pawn(int colour, int col, int row) {
        super(colour, col, row);

        if(colour == GamePanel.WHITE) {
            image = getImage("/piece/w-pawn");
        }
        else {
            image = getImage("/piece/b-pawn");
        }
    }

    public boolean canMove(int targetColumn, int targetRow) {
        if (isWithinBoard(targetColumn, targetRow) && !isSameSquare(targetColumn, targetRow)) {
            // define the move value based on its colour
            int moveValue;
            if (colour == GamePanel.WHITE) {
                moveValue = -1;
            } else {
                moveValue = 1;
            }
            // check the hitting pieces
            hittingPiece = getHitting(targetColumn, targetRow);
            // 1 square movement
            if (targetColumn == preCol && targetRow == preRow + moveValue && hittingPiece == null) {
                return true;
            }
            // 2 squares movement
            if (targetColumn == preCol && targetRow == preRow + moveValue * 2 && hittingPiece == null && !moved
                    && !pieceIsOnStraightLine(targetColumn, targetRow)) {
                return true;
            }
            // diagonal movement & capture (if a piece is on a square diagonally in front of it)
            if (Math.abs(targetColumn - preCol) == 1 && targetRow == preRow + moveValue && hittingPiece != null && hittingPiece.colour != colour) {
                return true;
            }
            // En Passant
            if (Math.abs(targetColumn - preCol) == 1 && targetRow == preRow + moveValue){
                for (Piece piece : GamePanel.simPieces){
                    if(piece.col == targetColumn && piece.row == preRow && piece.twoStepped){
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
