package com.shenlan.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by YANG on 2016/12/30.
 */
public class AutoCutImage {

    protected Logger logger	= LoggerFactory.getLogger(getClass());

    public void cut(String sourceFileUri, String targetDirUri,String targetFileName, int width, int height)
            throws Exception {

        String sourceFilePath = this.getPropertiesValue("resourceUri")+sourceFileUri;
        String targetDir = this.getPropertiesValue("resourceUri")+targetDirUri;
        File sourceFile = new File(sourceFilePath);
        BufferedImage source = ImageIO.read(sourceFile);
        int sWidth = source.getWidth(); // 图片宽度
        int sHeight = source.getHeight(); // 图片高度

        String fileName = null;
        File file = new File(targetDir);
        if (!file.exists()) { // 存储目录不存在，则创建目录
            file.mkdirs();
        }
        BufferedImage tag = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        BufferedImage image = null;

//        int beginW = sWidth - width >=0 ? sWidth/2 - width/2 : 0;
//        int beginH = sHeight - height >=0 ? sHeight/2 - height/2 : 0;
//        int endW = sWidth - width >=0 ? width : sWidth;
//        int endH = sHeight - height >= 0 ? height : sHeight;

        if((sWidth < width || sHeight < height) && (sWidth - 1.5*width )<=0 && (sHeight - 1.5*height)<=0 )
        {
            image = source;
        }else{
            int beginW = sWidth - sHeight >=0 ? sWidth/2 - sHeight/2 : 0;
            int beginH = sWidth - sHeight >=0 ? 0 : sHeight/2 - sWidth/2;
            int endW = sWidth - sHeight >=0 ? sHeight : sWidth;
            int endH = sWidth - sHeight >=0 ? sHeight : sWidth;
            image = source.getSubimage(beginW, beginH, endW,endH);
        }
        tag.getGraphics().drawImage(image,0,0,width,height,null);
        fileName = targetDir + "/"+targetFileName;
        file = new File(fileName);
        ImageIO.write(tag, "JPG", file);
    }

    public String getPropertiesValue(String key){
        Properties properties = new Properties();
        InputStream is = null;
        String configFilePath = "/conf.properties";
        try {
            logger.info("配置文件所在路径：" + getClass().getResource(configFilePath).getPath());
            is = getClass().getResourceAsStream(configFilePath);
            properties.load(is);
        } catch (Exception ex) {
            logger.error("读取配置conf.properties文件出错! 请检查文件路径是否有问题!ex=" + ex.toString());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception ex) {
                logger.error("读取配置文件conf.properties文件出错! 请检查文件路径是否有问题!ex=" + ex.toString());
            }
        }
        return properties.getProperty(key);
    }
}
