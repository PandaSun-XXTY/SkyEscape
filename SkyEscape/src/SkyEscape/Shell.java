package SkyEscape;

import java.awt.*;

import static SkyEscape.Tool.*;

/**
 * 炮弹
 */
public class Shell extends GameObject {
    public static final double PI = Math.PI;
    public static final int SPEED = 3;
    public static final int SIZE = 10;
    double degree;

    /**
     * 构造方法
     */
    public Shell() {
        this(200, 200);
        setDegree(Math.random() * PI * 2);
    }

    public Shell(int moveSpeed) {
        this(200, 200);
        this.moveSpeed = moveSpeed;
        setDegree(Math.random() * 2 * PI);
    }

    public Shell(int posix, int posiy) {
        this.posix = posix;
        this.posiy = posiy;
        this.moveSpeed = SPEED;
        sizex = sizey = SIZE;
        setDegree(Math.random() * 2 * PI);
    }

    public Shell(int posix, int posiy, int moveSpeed) {
        this.posix = posix;
        this.posiy = posiy;
        this.moveSpeed = moveSpeed;
        sizex = sizey = SIZE;
        setDegree(Math.random() * 2 * PI);
    }

    private void setDegree(double degree) {
        this.degree = degree;
    }

    /**
     * 加载自身
     *
     * @param pen Graphics
     */
    @Override
    public void drawSelf(Graphics pen) {
        //System.out.println("Degree = "+degree);
        //System.out.println("posix="+posix+",posiy="+posiy);
        //System.out.println("sizex="+sizex+",sizey="+sizey);
        Color old = pen.getColor();
        pen.setColor(new Color(0, 191, 255));
        //炮弹沿着角度飞行
        posix += moveSpeed * Math.cos(degree);
        posiy += moveSpeed * Math.sin(degree);
        /*if(boundary()){
            pen.fillOval(posix,posiy,sizex,sizey);
        }*/
        boundaryResilience();
        pen.fillOval(posix, posiy, sizex, sizey);
        pen.setColor(old);
    }

    /**
     * 判断是否在界内
     * 内为true,外为false
     *
     * @return 边界内外 boolean
     */
    private boolean boundary(boolean back) {         //boundary n.边界
        if ((posix + sizex > SIZEX_FRAME) || (posiy + sizey > SIZEY_FRAME) || (posix < 0) || (posiy < 0)) {
            return !back;
        } else {
            return back;
        }
    }

    private void boundaryResilience() {
        if ((posix + sizex > SIZEX_FRAME) || (posix < 0)) {
            degree = PI - degree;
        } else if ((posiy + sizey > SIZEY_FRAME) || (posiy < 0)) {
            degree = -degree;
        }
        degreeZero();
    }

    private void degreeZero() {
        boolean end = true;
        while (end) {
            if (((int) (Math.sin(degree)*moveSpeed) == 0)||((int) (Math.sin(degree)*moveSpeed) == moveSpeed)) {
                degree = Math.random() * 2 * PI;
            } else {
                end = false;
            }
        }
    }
}

