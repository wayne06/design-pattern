package designpattern.structural.flyweight.demo2;

import java.util.HashMap;
import java.util.Map;

/**
 * 在下面的代码实现中，利用工厂类来缓存 ChessPieceUnit 信息（也就是 id、text、color）。通过工厂类获取到的 ChessPieceUnit 就是享元。
 * 所有的 ChessBoard 对象共享这 32 个 ChessPieceUnit 对象（因为象棋中只有 32个棋子）。
 *
 * 在使用享元模式之前，记录 1 万个棋局，我们要创建 32 万（32*1 万）个棋子的 ChessPieceUnit 对象。
 * 利用享元模式，我们只需要创建 32 个享元对象供所有棋局共享使用即可，大大节省了内存。
 *
 * 实际上，享元模式的代码实现非常简单，主要是通过工厂模式，在工厂类中，通过一个 Map 来缓存已经创建过的享元对象，来达到复用的目的。
 */
public class ChessPieceUnitFactory {

    private static final Map<Integer, ChessPieceUnit> pieces = new HashMap<>();

    static {
        pieces.put(1, new ChessPieceUnit(1, "車", ChessPieceUnit.Color.BLACK));
        pieces.put(2, new ChessPieceUnit(2, "馬", ChessPieceUnit.Color.BLACK));
        //...省略摆放其他棋子的代码...
    }

    public static ChessPieceUnit getChessPiece(int chessPieceId) {
        return pieces.get(chessPieceId);
    }

}
