package piece;

import main.GamePanel;
import main.Type;

public class Rook extends Piece{

    public Rook(int colour, int col, int row) {
        super(colour, col, row);
        type = Type.ROOK;

        if(colour == GamePanel.WHITE) {
            image = getImage("/piece/w-rook");
        }
        else {
            image = getImage("/piece/b-rook");
        }
    }

    public boolean canMove(int targetColumn, int targetRow) {
        if (isWithinBoard(targetColumn, targetRow) && !isSameSquare(targetColumn, targetRow)) {
            // Rook can move as long as either its col or row is the same
            if (targetColumn == preCol || targetRow == preRow) {
                return isValidSquare(targetColumn, targetRow) && !pieceIsOnStraightLine(targetColumn, targetRow);
            }
        }
        return false;
    }
}
