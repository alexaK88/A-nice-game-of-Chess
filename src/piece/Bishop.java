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
}
