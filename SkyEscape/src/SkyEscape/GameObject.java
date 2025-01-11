package SkyEscape;

import java.awt.*;

/**
 * 游戏物体根类
 */
public class GameObject {
    Image img;              //物体图片
    int posix, posiy;        //物体坐标
    int moveSpeed;          //物体移动速度
    int sizex, sizey;        //物体大小
    int blood;              //血量

    /**
     * 构造方法
     */
    public GameObject() {
    }

    public GameObject(Image img, int posix, int posiy, int sizex, int sizey, int moveSpeed) {
        this.img = img;
        this.posix = posix;
        this.posiy = posiy;
        this.moveSpeed = moveSpeed;
        this.sizex = sizex;
        this.sizey = sizey;
    }

    public GameObject(Image img, int moveSpeed) {
        this(img, 200, 200);
        this.moveSpeed = moveSpeed;
    }

    public GameObject(Image img, int posix, int posiy) {
        this(img);
        this.posix = posix;
        this.posiy = posiy;
    }

    public GameObject(Image img) {
        this.img = img;
        moveSpeed = 2;
        if (this.img != null) {
            this.sizex = img.getWidth(null);
            this.sizey = img.getHeight(null);
        }
    }

    private GameObject(int posix, int posiy) {
        this.posix = posix;
        this.posiy = posiy;
    }

    public GameObject(Image img, int posix, int posiy, int moveSpeed) {
        this(img, posix, posiy);
        this.moveSpeed = moveSpeed;
    }

    /**
     * 加载自己
     *
     * @param pen Graphics
     */
    public void drawSelf(Graphics pen) {
        pen.drawImage(img, posix, posiy, sizex, sizey, null);
    }

    /**
     * 返回这个Object的矩形
     *
     * @return Rectangle
     * (n.矩形)
     */
    public Rectangle getRec() {
        return new Rectangle(posix, posiy, sizex, sizey);
    }
}
