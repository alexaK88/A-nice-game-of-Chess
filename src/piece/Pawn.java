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
}
