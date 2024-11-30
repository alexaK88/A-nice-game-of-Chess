package piece;

import main.Board;
import main.GamePanel;
import main.Type;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {
    public Type type;
    public Piece hittingPiece;
    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int colour;
    public boolean moved, twoStepped;

    public Piece(int colour, int col, int row) {
        this.colour = colour;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }

    public BufferedImage getImage(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public int getX(int col) {
        return col * Board.SQUARE_SIZE;
    }
    public int getY(int row) {
        return row * Board.SQUARE_SIZE;
    }
    public int getCol(int x) {
        return (x + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
    }
    public int getRow(int y) {
        return (y + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
    }
    public int getIndex() {
        for(int index=0; index < GamePanel.simPieces.size(); index++) {
            if(GamePanel.simPieces.get(index) == this) {
                return index;
            }
        }
        return 0;
    }

    public boolean canMove(int targetColumn, int targetRow) {
        return false;
    }

    public boolean isWithinBoard(int targetColumn, int targetRow) {
        if(targetColumn >= 0 && targetColumn <= 7 && targetRow >= 0 && targetRow <= 7) {
            return true;
        }
        return false;
    }

    public boolean isSameSquare(int targetColumn, int targetRow) {
        if(targetColumn == preCol && targetRow == preRow) {
            return true;
        }
        return false;
    }

    public Piece getHitting(int targetColumn, int targetRow) {
        for(Piece piece : GamePanel.simPieces) {
            if(piece.col == targetColumn && piece.row == targetRow && piece != this) {
                return piece;
            }
        }
        return null;
    }

    public boolean isValidSquare(int targetColumn, int targetRow) {
        hittingPiece = getHitting(targetColumn, targetRow);
        // if the square is vacant
        if (hittingPiece == null) {
            return true;
        } else {
            // the square is occupied
            if(hittingPiece.colour != this.colour) {
                // if the colour is different, then it can be captured
                return true;
            } else {
                hittingPiece = null;
            }
        }
        return false;
    }

    public boolean pieceIsOnStraightLine(int targetColumn, int targetRow) {
        // when this piece is moving to the left
        for (int c = preCol - 1; c > targetColumn; c--) {
            for (Piece piece : GamePanel.simPieces) {
                if (piece.col == c && piece.row == targetRow) {
                    hittingPiece = piece;
                    return true;
                }
            }
        }

        // When this piece is moving to the right
        for (int c = preCol + 1; c < targetColumn; c++) {
            for (Piece piece : GamePanel.simPieces) {
                if (piece.col == c && piece.row == targetRow) {
                    hittingPiece = piece;
                    return true;
                }
            }
        }

        // when this piece is moving to the up
        for (int r = preRow - 1; r > targetRow; r--) {
            for (Piece piece : GamePanel.simPieces) {
                if (piece.col == targetColumn && piece.row == r) {
                    hittingPiece = piece;
                    return true;
                }
            }
        }

        // When this piece is moving to the down
        for (int r = preRow + 1; r < targetRow; r++) {
            for (Piece piece : GamePanel.simPieces) {
                if (piece.col == targetColumn && piece.row == r) {
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pieceIsOnDiagonalLine(int targetColumn, int targetRow) {
        if (targetRow < preRow) {
            // Up left
            for (int c = preCol - 1; c > targetColumn; c--) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.col == c && piece.row == preRow - diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }


            // Up right
            for (int c = preCol + 1; c < targetColumn; c++) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.col == c && piece.row == preRow - diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
        }
        if (targetRow > preRow) {
            // Down left
            for (int c = preCol - 1; c > targetColumn; c--) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.col == c && piece.row == preRow + diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
            // Down right
            for (int c = preCol + 1; c < targetColumn; c++) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.col == c && piece.row == preRow + diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void resetPosition() {
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }

    public void updatePosition() {
        if(type == Type.PAWN) {
            if(Math.abs(row - preRow) == 2) {
                twoStepped = true;
            }
        }

        x = getX(col);
        y = getY(row);
        preCol = getCol(x);
        preRow = getRow(y);
        moved = true;
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }
}
