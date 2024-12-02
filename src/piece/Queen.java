package piece;

import main.GamePanel;
import main.Type;

public class Queen extends Piece{
    public Queen(int colour, int col, int row) {
        super(colour, col, row);

        type = Type.QUEEN;

        if(colour == GamePanel.WHITE) {
            image = getImage("/piece/w-queen");
        }
        else {
            image = getImage("/piece/b-queen");
        }
    }

    public boolean canMove(int targetColumn, int targetRow) {
        if (isWithinBoard(targetColumn, targetRow) && !isSameSquare(targetColumn, targetRow)) { // Vertical & Horizontal
            int targetCol;
            if (targetColumn == preCol || targetRow == preRow) {
                if (isValidSquare(targetColumn, targetRow) && !pieceIsOnStraightLine(targetColumn, targetRow)) {
                    return true;
                }
            }

            // Diagonal
            if (Math.abs(targetColumn - preCol) == Math.abs(targetRow - preRow)) {
                if (isValidSquare(targetColumn, targetRow) && !pieceIsOnDiagonalLine(targetColumn, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}
