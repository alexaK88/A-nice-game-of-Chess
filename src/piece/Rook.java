package piece;

import main.GamePanel;

public class Rook extends Piece{

    public Rook(int colour, int col, int row) {
        super(colour, col, row);

        if(colour == GamePanel.WHITE) {
            image = getImage("/piece/w-rook");
        }
        else {
            image = getImage("/piece/b-rook");
        }
    }
}
