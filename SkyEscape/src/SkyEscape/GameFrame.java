package SkyEscape;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static SkyEscape.Tool.SIZEX_FRAME;
import static SkyEscape.Tool.SIZEY_FRAME;

/**
 * 游戏窗口
 */
public class GameFrame extends Frame {
    public static final String GAME_NAME = "飞弹漩涡：战空逃生";
    //int posixPlane = 100,posiyPlane = 100;      //飞机x轴位置与y轴位置
    int posixFrame = 700, posiyFrame = 300;      //窗口x轴位置与y轴位置
    Image backGround = Tool.getImage("Pictures/Background.jpg");        //加载背景
    Image planePic = Tool.getImage("Pictures/Plane.png");                  //加载飞机
    int keyPress, keyReleased;

    Plane plane = new Plane(planePic, 100, 100, 75, 75, 2);       //新建飞机
    Shell[] shells = new Shell[150];      //新建炮弹
    Explosion explosion;            //声明一个爆炸
    private Image offScreenImage = null;
    public static void main(String[] args) {
        GameFrame skyEscape = new GameFrame();
        skyEscape.launchFrame();
    }

    /**
     * 初始化窗口
     * 宽:700,高：500;
     * 位置:700,300;
     */
    public void launchFrame() {
        this.setTitle(GAME_NAME);
        this.setVisible(true);  //窗口默认不可见，需要设置为可见
        this.setSize(SIZEX_FRAME, SIZEY_FRAME);      //设置窗口大小
        this.setLocation(posixFrame, posiyFrame);      //设置窗口位置
        this.addWindowListener(             //窗口监听增加（关闭）
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
        //启动窗口绘制线程
        new PaintThread().start();
        //启动键盘监听
        this.addKeyListener(new KeyBoard());
        //初始化炮弹
        for (int i = 0; i < shells.length; i++) {
            shells[i] = new Shell();
        }
    }

    /**
     * 显示内容
     */
    @Override
    public void paint(Graphics pen) {
        //System.out.println("Test");
        Color old = pen.getColor();
        pen.setColor(new Color(76, 255, 113));
        pen.drawImage(backGround, 0, 0, 700, 500, null);        //加载背景
        plane.drawSelf(pen);            //加载飞机
        for (Shell shell : shells) {        //加载炮弹，并判断有关死亡
            if (shell != null) {
                shell.drawSelf(pen);
                if(shell.getRec().intersects(plane.getRec())){
                    plane.die(pen);
                    explosion = new Explosion(plane.posix,plane.posiy);
                }
            }
        }           //加载炮弹
        pen.drawLine(SIZEX_FRAME/2-25,0,SIZEX_FRAME/2-25,SIZEY_FRAME);
        pen.setColor(old);
    }

    /**
     * 后台缓冲画布
     *
     * @param pen the specified Graphics window
     */
    public void update(Graphics pen) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(SIZEX_FRAME, SIZEY_FRAME);       //传入游戏窗口宽度与高度
        }
        Graphics penOff = offScreenImage.getGraphics();
        paint(penOff);
        pen.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 监测键盘输入
     */
    class KeyBoard extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent key) {
            //System.out.println(key.getKeyCode());
            if (key.getKeyCode() == KeyEvent.VK_SHIFT) {
                plane.addSpeed();
            } else if (key.getKeyCode() == KeyEvent.VK_CONTROL) {
                plane.reduceSpeed();
            }
            keyPress = key.getKeyCode();
            plane.setKeyPress(keyPress);
        }

        @Override
        public void keyReleased(KeyEvent key) {
            keyReleased = key.getKeyCode();
            plane.setKeyReleased(keyReleased);
        }
    }

    /**
     * 重画线程
     */
    /*class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(1000 / 140);       //millis单位：毫秒(ms)
                    //Thread.sleep(1000/100);       //millis单位：毫秒(ms)
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }*/
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(1000 / 140);       //millis单位：毫秒(ms)
                    //Thread.sleep(1000/100);       //millis单位：毫秒(ms)
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}







/*      位于类GameFrame下的普通方法，供学习时使用
@Override
public void paint(Graphics pen) {
    //System.out.println("Running");
    Color old = pen.getColor();
    pen.drawImage(backGround, 0, 0, 700, 500, null);
    plane.drawSelf(pen);
        /*en.drawImage(plane, posixPlane, posiyPlane, 100, 100, null);
        /posixPlane++;
        pen.drawImage(plane,300,200,100,100,null);
        pen.drawImage(plane,500,100,100,100,null);
        pen.drawImage(plane,100,350,100,100,null);
        pen.drawImage(plane,500,350,100,100,null);
        pen.setColor(new Color(84, 225, 48));
        pen是一只画笔
        pen.drawLine(185,100,400,300);
        pen.drawRect(185,100,215,200);
        pen.setColor(new Color(81, 161, 239));
        pen.drawString("</>心向太阳",550,450);
        pen.drawString("未经许可不得商用",580,465);
    pen.setColor(old);
}*/