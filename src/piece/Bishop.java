package piece;

import main.GamePanel;

public class Bishop extends Piece{
    public Bishop(int colour, int col, int row) {
        super(colour, col, row);

        if(colour == GamePanel.WHITE) {
            image = getImage("/piece/w-bishop");
        }
        else {
            image = getImage("/piece/b-bishop");
        }
    }

    public boolean canMove(int targetColumn, int targetRow) {
        if (isWithinBoard(targetColumn, targetRow) && !isSameSquare(targetColumn, targetRow)) {
            if (Math.abs(targetColumn - preCol) == Math.abs(targetRow - preRow)) {
                return isValidSquare(targetColumn, targetRow) && !pieceIsOnDiagonalLine(targetColumn, targetRow);
            }
        }
        return false;
    }
}
