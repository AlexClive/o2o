package com.zhangxuerong.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.imageio.*;

public class ImageUtil {
    private static  String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmm_ss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile CFile){
        File newFile = new File(CFile.getOriginalFilename());
        try{
            CFile.transferTo(newFile);
        }catch (IOException e){
            logger.error(e.toString());
        }
        return  newFile;
    }
    /**
     * 处理缩略图，返回新生成图片的地址
     * @param thumbnailInputStream
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(InputStream thumbnailInputStream,String fileName, String targetAddr){
        String realFileName = getRealFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName+extension;
        File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
        logger.debug("current complete addr is:"+relativeAddr);
        logger.debug("basePath is:"+PathUtil.getImgBasePath()+relativeAddr);
        try {
            Thumbnails.of(thumbnailInputStream).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/watermark.png")),0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        }catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath()+targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String getRealFileName() {
        // 获取随机5位数
        int rannum = r.nextInt(8999)+10000;
        String nowtimeStr = sDateFormat.format(new Date());
        return nowtimeStr+rannum;
    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("F:/lear/lanhua.png"))
                .size(200,200).watermark(Positions.BOTTOM_RIGHT,
                        ImageIO.read(new File(basePath+"/watermark.png")),
                        0.25f).outputQuality(0.8f)
                .toFile("F:/lear/lanhua1.png");
    }
}
