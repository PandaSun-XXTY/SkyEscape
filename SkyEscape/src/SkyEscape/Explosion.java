package SkyEscape;

import java.awt.*;

public class Explosion extends Object{
    public static Image[] boom = new Image[16];

    static {
        for (int i = 0; i < boom.length; i++) {
            boom[i] = Tool.getImage("Pictures/Explosion/e" + (i + 1) + ".gif");
        }
    }

    boolean live = true;
    int posix, posiy;
    int count = 0;

    /**
     * 构造方法
     */
    Explosion() {
    }

    Explosion(int x, int y) {
        posix = x;
        posiy = y;
    }

    /**
     * 加载自身
     *
     * @param pen
     */
    public void draw(Graphics pen) {                //加载自身
        if(!live){
            return;
        }
        for (count = 0; count <= 16; count++) {
            if (live) {
                if (count < 16) {
                    pen.drawImage(boom[count], posix, posiy, null);
                    count++;
                    System.out.println("爆炸被加载");
                } else {
                    live = false;
                }
            }else{
                return;
            }
            //try {
            //    this.wait(50);
            //} catch (InterruptedException e) {
            //    throw new RuntimeException(e);
            //}
        }

    }

    public void draw(Graphics pen, long currentTime) {  // 修改draw方法接收当前时间作为参数
        if (!live) return;

        // 计算从开始到现在经过了多少毫秒
        long elapsedTime = System.currentTimeMillis() - currentTime;
        System.out.println(currentTime);

        // 根据经过的时间计算出应该绘制的帧数（假设每帧间隔为50毫秒）
        int frameToShow = (int) Math.min(16, elapsedTime / 50);  // 每50毫秒播放一帧，最多播放到第16帧

        // 绘制对应的帧
        if (frameToShow < 16) {
            pen.drawImage(boom[frameToShow], posix, posiy, null);
        } else {
            live = false;  // 动画播放完毕，标记为不存活
        }
    }



    public void load() {
    }
}
