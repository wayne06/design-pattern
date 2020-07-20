package designpattern.behavioral.template.demo8;

import android.view.View;
import android.widget.Button;

/**
 * 在 Android 应用开发中，给 Button 控件的点击事件注册监听器
 *
 * 事件监听器很像回调，即传递一个包含回调函数（onClick()）的对象给另一个函数。
 * 从应用场景上来看，它又很像观察者模式，即事先注册观察者（OnClickListener），当用户点击按钮的时候，发送点击事件给观察者，并且执行相应的 onClick() 函数
 *
 * 这里的回调算是异步回调，往 setOnClickListener() 函数中注册好回调函数之后，并不需要等待回调函数执行。这也印证了前面讲的，异步回调比较像观察者模式。
 */
public class ClickListenerDemo {

    public static void main(String[] args) {

        //Button button = (Button) findViewById(R.id.button);
        //button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        System.out.println("clicked.");
        //    }
        //});

    }

}
