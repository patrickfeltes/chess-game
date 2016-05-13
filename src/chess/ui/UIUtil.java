package chess.ui;

import java.awt.Rectangle;

import chess.game.Chess;

public class UIUtil {

    public static boolean isMouseInsideRectangle(int x, int y, int width, int height) {
        return isMouseInsideRectangle(new Rectangle(x, y, width, height));
    }

    public static boolean isMouseInsideRectangle(Rectangle r) {
        int mx = Chess.mouseX;
        int my = Chess.mouseY;

        return (mx >= r.x && mx <= r.x + r.width && my >= r.y && my <= r.y + r.height);
    }

}
