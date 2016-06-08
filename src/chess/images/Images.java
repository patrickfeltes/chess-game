package chess.images;

/**
 * Images class used to load all the image resources needed for the game
 */
public class Images {

    // pieces SpriteSheet
    public static SpriteSheet pieces = new SpriteSheet("/res/pieces.png", 2, 6);

    // White sprites
    public static final Sprite WHITE_KING = pieces.getSprites()[6];
    public static final Sprite WHITE_QUEEN = pieces.getSprites()[7];
    public static final Sprite WHITE_ROOK = pieces.getSprites()[8];
    public static final Sprite WHITE_KNIGHT = pieces.getSprites()[9];
    public static final Sprite WHITE_BISHOP = pieces.getSprites()[10];
    public static final Sprite WHITE_PAWN = pieces.getSprites()[11];

    // Black sprites
    public static final Sprite BLACK_KING = pieces.getSprites()[0];
    public static final Sprite BLACK_QUEEN = pieces.getSprites()[1];
    public static final Sprite BLACK_ROOK = pieces.getSprites()[2];
    public static final Sprite BLACK_KNIGHT = pieces.getSprites()[3];
    public static final Sprite BLACK_BISHOP = pieces.getSprites()[4];
    public static final Sprite BLACK_PAWN = pieces.getSprites()[5];

}
