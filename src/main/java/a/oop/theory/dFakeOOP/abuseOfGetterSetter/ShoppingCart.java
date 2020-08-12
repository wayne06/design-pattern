package a.oop.theory.dFakeOOP.abuseOfGetterSetter;

import java.util.ArrayList;
import java.util.List;

/**
 * 下面的做法非常不推荐：因为它违反了面向对象编程的封装特性，相当于将面向对象编程风格退化成了面向过程编程风格
 *
 * 对于属性 itemsCount 和 totalPrice，虽然将它们定义成 private 私有属性，但是提供了 public 的 getter、setter 方法，
 * 这就跟将这两个属性定义为 public 公有属性，没有什么两样了。外部可以通过 setter 方法随意地修改这两个属性的值。
 *
 * 除此之外，任何代码都可以随意调用 setter 方法，来重新设置 itemsCount、totalPrice 属性的值，这也会导致其跟 items 属性的值不一致。
 * 而面向对象封装的定义是：通过访问权限控制，隐藏内部数据，外部仅能通过类提供的有限的接口访问、修改内部数据。
 *
 * 所以，暴露不应该暴露的 setter 方法，明显违反了面向对象的封装特性。数据没有访问权限控制，任何代码都可以随意修改它，
 * 代码就退化成了面向过程编程风格的了。
 *
 * 对于 items 属性，我们定义了它的 getter 方法和 addItem() 方法，并没有定义它的 setter 方法。
 * 这样的设计貌似看起来没有什么问题，但实际上并不是。
 * 对于 itemsCount 和 totalPrice 这两个属性来说，定义一个 public 的 getter 方法，确实无伤大雅，毕竟 getter 方法不会修改数据。
 * 但是，对于 items 属性就不一样了，这是因为 items 属性的 getter 方法，返回的是一个 List集合容器。
 * 外部调用者在拿到这个容器之后，是可以操作容器内部数据的，也就是说，外部代码还是能修改 items 中的数据。比如像下面这样：
 *
     ShoppingCart cart = new ShoppCart();
     ...
     cart.getItems().clear(); // 清空购物车
 *
 * 清空购物车这样的功能需求看起来合情合理，上面的代码没有什么不妥。
 * 但是这样的代码写法，会导致 itemsCount、totalPrice、items 三者数据不一致。我们不应该将清空购物车的业务逻辑暴露给上层代码。
 * 正确的做法应该是，在 ShoppingCart 类中定义一个 clear() 方法，将清空购物车的业务逻辑封装在里面，透明地给调用者使用。
 * ShoppingCart 类的 clear() 方法的具体代码实现如下：
 *
     public class ShoppingCart {
         // ...省略其他代码...
         public void clear() {
             items.clear();
             itemsCount = 0;
             totalPrice = 0.0;
         }
     }
 *
 * 当需要查看购物车中都买了什么，那这个时候，ShoppingCart 类不得不提供 items 属性的 getter 方法了，那又该怎么办才好呢？
 * Java 语言中解决这个问题的方法还是挺简单的。
 * 可以通过 Java 提供的 Collections.unmodifiableList() 方法，让 getter 方法返回一个不可被修改的 UnmodifiableList 集合容器，
 * 而这个容器类重写了 List 容器中跟修改数据相关的方法，比如 add()、clear() 等方法。
 * 一旦我们调用这些修改数据的方法，代码就会抛出 UnsupportedOperationException 异常，这样就避免了容器中的数据被修改。
 * 具体的代码实现如下所示：
 *
     public class ShoppingCart {
         // ...省略其他代码...
         public List<ShoppingCartItem> getItems() {
             return Collections.unmodifiableList(this.items);
         }
     }

     public class UnmodifiableList<E> extends UnmodifiableCollection<E> implements List<E> {
         public boolean add(E e) {
             throw new UnsupportedOperationException();
         }
         public void clear() {
             throw new UnsupportedOperationException();
         }
         // ...省略其他代码...
     }

     ShoppingCart cart = new ShoppingCart();
     List<ShoppingCartItem> items = cart.getItems();
     items.clear();//抛出UnsupportedOperationException异常
 *
 * 不过，这样的实现思路还是有点问题。
 * 因为当调用者通过 ShoppingCart 的 getItems() 获取到 items 之后，虽然我们没法修改容器中的数据，
 * 但我们仍然可以修改容器中每个对象（ShoppingCartItem）的数据。如下所示：
 *
     ShoppingCart cart = new ShoppingCart();
     cart.add(new ShoppingCartItem(...));
     List<ShoppingCartItem> items = cart.getItems();
     ShoppingCartItem item = items.get(0);
     item.setPrice(19.0); // 这里修改了item的价格属性
 *
 * 在设计实现类的时候，除非真的需要，否则，尽量不要给属性定义 setter 方法。
 * 除此之外，尽管 getter 方法相对 setter 方法要安全些，但是如果返回的是集合容器（比如例子中的 List 容器），
 * 也要防范集合内部数据被修改的危险。
 */
public class ShoppingCart {
    private int itemsCount;
    private double totalPrice;
    private List<Item> items = new ArrayList<>();

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
        itemsCount++;
        totalPrice += item.getPrice();
    }

    private class Item {
        private String name;
        private double price;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public double getPrice() {
            return price;
        }
        public void setPrice(double price) {
            this.price = price;
        }
    }
}
