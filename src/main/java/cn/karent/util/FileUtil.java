package cn.karent.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by wan on 2017/1/21.
 */
public class FileUtil {

    //将当前时间作为文件名的一部分
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    //生成随机数作为文件名的一部分
    private static Random random = new Random();

    /**
     * 生成文件名
     * @return
     */
    public static String generateFileName(){
        String name = "";
        name = sdf.format(new Date());
        int rand = random.nextInt(10000);
        System.out.println("rand:" + rand);
        name += rand;
        return name;
    }

    /**
     * 从输入流获取文件并保存到本地
     * @param in
     * @param type 文件类型
     * @return 返回文件的路径
     */
    public static String  saveFile(InputStream in, String type) throws Exception{
        String url = FileUtil.class.getResource("").getPath();
System.out.println("path:" + url);
        //路径后面改成跟aijiale项目同级
        String path = url.substring(0, url.indexOf("target")) + "src/main/webapp/uploadImg/";
        //真正运行的时候使用下面这种路径
//        String path = url.substring(0, url.indexOf("blog")) + "/uploadImgs/";
        File folder = new File(path);
        //如果文件不存在
        if( !folder.exists() ) {
            folder.mkdirs();
        }
        String fileName = generateFileName();
        String fileExt = judgeType(type);
        path += fileName + fileExt;
        File file = new File(path);
        //如果文件不存在创建一个新文件
        if( !file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        byte[] bytes = new byte[1024];
        int len = 0;
        while( (len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        out.close();
        in.close();
        return "/uploadImg/" + fileName + fileExt;
    }

    /**
     * 获取文件类型
     * @param contentType
     * @return
     */
    public static String judgeType(String contentType) {
        String fileExt = "";
        if("image/jpeg".equals(contentType)) {
            fileExt = ".jpg";
        } else if( "image/png".equals(contentType)) {
            fileExt = ".png";
        }
        return fileExt;
    }

}
