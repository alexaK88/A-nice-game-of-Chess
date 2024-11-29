package piece;

import main.GamePanel;

public class Queen extends Piece{
    public Queen(int colour, int col, int row) {
        super(colour, col, row);

        if(colour == GamePanel.WHITE) {
            image = getImage("/piece/w-queen");
        }
        else {
            image = getImage("/piece/b-queen");
        }
    }
}
