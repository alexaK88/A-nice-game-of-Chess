package piece;

import main.GamePanel;
import main.Type;

public class Knight extends Piece{
    public Knight(int colour, int col, int row) {
        super(colour, col, row);
        type = Type.KNIGHT;

        if(colour == GamePanel.WHITE) {
            image = getImage("/piece/w-knight");
        }
        else {
            image = getImage("/piece/b-knight");
        }
    }

    public boolean canMove(int targetColumn, int targetRow) {
        if (isWithinBoard(targetColumn, targetRow)) {
            // Knight can move if its; movement ration of col and row is 1:2 or 2:1
            if (Math.abs(targetColumn - preCol) * Math.abs(targetRow - preRow) == 2) {
                if (isValidSquare(targetColumn, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}
