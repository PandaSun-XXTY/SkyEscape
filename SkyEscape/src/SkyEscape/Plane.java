package SkyEscape;

import java.awt.*;
import java.awt.event.*;

/**
 * 飞机
 */
public class Plane extends GameObject {

    public static final int SPEED = 1;
    public static final int BLOOD = 3;
    boolean left, right, up, down;
    int keyPress;
    int keyReleased;
    boolean live = true;
    Explosion explo;

    public Plane() {
    }

    public Plane(Image img, int posix, int posiy, int sizex, int sizey, int moveSpeed) {
        super(img, posix, posiy, sizex, sizey, moveSpeed);
        this.blood = BLOOD;
    }

    public Plane(Image img, int moveSpeed) {
        super(img, moveSpeed);
        this.blood = BLOOD;
    }

    public Plane(Image img, int posix, int posiy) {
        super(img, posix, posiy, SPEED);
        this.blood = BLOOD;
    }

    public Plane(Image img) {
        super(img, SPEED);
        this.blood = BLOOD;
    }

    public Plane(Image img, int posix, int posiy, int moveSpeed) {
        super(img, posix, posiy, moveSpeed);
        this.blood = BLOOD;
    }

    /**
     * 画出自己
     *
     * @param pen Graphics
     */
    @Override
    public void drawSelf(Graphics pen) {
        drawSelf(pen, this.keyPress, this.keyReleased);
        if (true) {
            return;
        }
        super.drawSelf(pen);
        calibrate();
    }

    public void setKeyPress(int keyPress) {
        this.keyPress = keyPress;
    }

    /**
     * 加速
     *
     * @param add
     */
    public void addSpeed(int add) {
        this.moveSpeed += add;
    }

    public void addSpeed() {
        moveSpeed++;
    }

    /**
     * 减速
     */
    public void reduceSpeed() {
        if (moveSpeed > 1) {
            moveSpeed--;
        }
    }

    public void reduceSpeed(int reduce) {
        moveSpeed -= reduce;
        if (moveSpeed < 0) {
            moveSpeed = 0;
        }
    }

    public void setKeyReleased(int keyReleased) {
        this.keyReleased = keyReleased;
    }


    public void setDirection(int key, boolean set) {
        switch (key) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                up = set;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left = set;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                down = set;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right = set;
                break;
        }
    }

    private void setDirection(boolean set) {
        left = right = down = up = set;
    }

    public void drawSelf(Graphics pen, int keyPress, int keyReleased) {
        if (!live) {
            return;
        }
        boolean equal = keyPress == keyReleased;
        setDirection(keyPress, true);
        if (!equal) {
            setDirection(keyReleased, false);
        }
        if (left) {
            posix -= moveSpeed;
        }
        if (right) {
            posix += moveSpeed;
        }
        if (up) {
            posiy -= moveSpeed;
        }
        if (down) {
            posiy += moveSpeed;
        }
        if (equal) {
            setDirection(keyReleased, false);
        }
        calibrate();
        super.drawSelf(pen);
        this.keyPress = 0;
        this.keyReleased = 0;
    }

    /**
     * 校准飞机位于边界外时的位置
     */
    private void calibrate() {
        if ((posix + sizex) > Tool.SIZEX_FRAME) {
            posix = Tool.SIZEX_FRAME - sizex;
        } else if (posix < 0) {
            posix = 0;
        }
        if ((posiy + sizey) > Tool.SIZEY_FRAME) {
            posiy = Tool.SIZEY_FRAME - sizey;
        } else if (posiy < 0) {
            posiy = 0;
        }
    }

    private void setPosition(int x, int y) {
        this.posix = x;
        this.posiy = y;
    }

    public void die(Graphics pen) {
        System.out.println("Plane is Die!");
        long dieTime = System.currentTimeMillis();
        live = false;
        explo = new Explosion(posix + (sizex / 2), posiy + (sizey / 2));
        explo.load();
        explo.draw(pen,dieTime);
        setPosition(-500, -500);
    }
}
