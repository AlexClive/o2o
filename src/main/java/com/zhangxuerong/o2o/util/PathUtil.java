package com.zhangxuerong.o2o.util;

public class PathUtil {
    public  static String seperator = System.getProperty("file.separator");
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath="";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/projectdev/image/";
        }else {
            basePath = "/home/root/image/";
        }
        basePath = basePath.replace("/",seperator);
        return basePath;
    }
    public static String getShopImagePath(long shopId){
        String imagePath = "upload/item/shop"+shopId+"/";
        return imagePath.replace("/",seperator);
    }
}
