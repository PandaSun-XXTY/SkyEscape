package SkyEscape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * 工具类
 */
public class Tool {
    /**
     * 构造方法私有，防止外部创建对象
     */
    private Tool(){}
    static int SIZEX_FRAME = 700,SIZEY_FRAME = 500;      //窗口大小（宽，高）
    /**
     * 加载图片
     * @param path
     * String类型 图片路径
     * @return Image对象
     */
    public static Image getImage(String path){
        Image image = null;
        URL url = Tool.class.getClassLoader().getResource(path);
        try {
            if (url != null) {
                image = ImageIO.read(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        return image;
    }
}
