package designpattern.structural.flyweight.demo1;

/**
 * 需求：
 * 开发一个棋牌游戏：一个游戏厅中有成千上万个“房间”，每个房间对应一个棋局。
 * 棋局要保存每个棋子的数据，比如：棋子类型（将、相、士、炮等）、棋子颜色（红方、黑方）、棋子在棋局中的位置。
 * 利用这些数据，就能显示一个完整的棋盘给玩家。
 *
 * 具体的代码如下所示。其中，ChessPiece 类表示棋子，ChessBoard 类表示一个棋局，里面保存了象棋中 32 个棋子的信息。
 */
public class ChessPiece {

    private int id;
    private String text;
    private Color color;
    private int positionX;
    private int positionY;

    public ChessPiece(int id, String text, Color color, int positionX, int positionY) {
        this.id = id;
        this.text = text;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public static enum Color {
        RED, BLACK
    }

    // ...省略其他属性和 getter/setter 方法...
}
