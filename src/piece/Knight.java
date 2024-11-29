package piece;

import main.GamePanel;

public class Knight extends Piece{
    public Knight(int colour, int col, int row) {
        super(colour, col, row);

        if(colour == GamePanel.WHITE) {
            image = getImage("/piece/w-knight");
        }
        else {
            image = getImage("/piece/b-knight");
        }
    }
}
