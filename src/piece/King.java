package piece;

import main.GamePanel;

public class King extends Piece{
    public King(int colour, int col, int row) {
        super(colour, col, row);

        if(colour == GamePanel.WHITE) {
            image = getImage("/piece/w-king");
        }
        else {
            image = getImage("/piece/b-king");
        }
    }
}
