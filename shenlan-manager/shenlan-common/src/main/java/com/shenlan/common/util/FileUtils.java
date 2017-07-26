package com.shenlan.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


/**
 * Created by 赵利锋 on 2016/7/20.
 */
public class FileUtils {

    protected Logger logger	= LoggerFactory.getLogger(getClass());

    public ArrayList<String> upload(CommonsMultipartFile[] files, HttpServletRequest request,String dirType) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime=new Date().getTime()+"";
        String date = sf.format(new Date());
        String baseFileUri="/"+dirType+"/"+ date + "/";
        String dirStr = this.getPropertiesValue("resourceUri") + baseFileUri;

        File fileDir = new File(dirStr);
        // 如果文件夹不存在则创建
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            logger.info("文件目录不存在");
            fileDir.mkdirs();
        } else {
            logger.info("文件目录存在");
        }
        ArrayList<String> fileNames = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            logger.info("fileName---------->" + files[i].getOriginalFilename());
            String name = files[i].getOriginalFilename();
            if (!files[i].isEmpty()) {
                try {
                    String fileName = dirStr + currentTime + files[i].getOriginalFilename();
                    String fileUri=baseFileUri+currentTime + files[i].getOriginalFilename();
                    fileNames.add(fileUri);
                    // 拿到输出流，同时重命名上传的文件
                    FileOutputStream os = new FileOutputStream(fileName);
                    // 拿到上传文件的输入流
                    InputStream in = files[i].getInputStream();
                    // 以写字节的方式写文件
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        os.write(b);
                    }
                    os.flush();
                    os.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("上传出错");
                }
            }
        }
        return fileNames;
    }

    public HashMap<String,String> uploadAndReturnStatus(CommonsMultipartFile[] files, HttpServletRequest request,String dirType) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime=new Date().getTime()+"";
        String date = sf.format(new Date());
        String baseFileUri="/"+dirType+"/"+ date + "/";
        String dirStr = this.getPropertiesValue("resourceUri") + baseFileUri;

        File fileDir = new File(dirStr);
        // 如果文件夹不存在则创建
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            logger.info("文件目录不存在");
            fileDir.mkdirs();
        } else {
            logger.info("文件目录存在");
        }
        HashMap<String,String> fileNames = new  HashMap<String,String>();
        for (int i = 0; i < files.length; i++) {
            logger.info("fileName---------->" + files[i].getOriginalFilename());
            String name = files[i].getOriginalFilename();
            if (!files[i].isEmpty()) {
                try {
                    String fileName = dirStr + currentTime + files[i].getOriginalFilename();
                    String fileUri=baseFileUri+currentTime + files[i].getOriginalFilename();

//                    fileNames.put("name", fileUri);
                    // 拿到输出流，同时重命名上传的文件
                    FileOutputStream os = new FileOutputStream(fileName);
                    // 拿到上传文件的输入流
                    InputStream in = files[i].getInputStream();
                    // 以写字节的方式写文件
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        os.write(b);
                    }
                    os.flush();
                    os.close();
                    in.close();
                    String imagResUriBase = this.getPropertiesValue("imagResUri");
                    fileNames.put("error", "0");
                    fileNames.put("fileName",fileName);
                    fileNames.put("url",imagResUriBase+fileUri);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("上传出错");
                    fileNames.put("error","1");
                }
            }
        }
        return fileNames;
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

    /**
     *
     * @param downloadDir
     * @param response
     * @param batchName 批次名称
     * @throws Exception
     */
    public void downLoadFile(String downloadDir,HttpServletResponse response,String batchName) throws Exception{
        //要下载的文件目录
        String path = downloadDir;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        //设置文件编码。 防止中文乱码。 这里用 org.apache.tools.zip.ZipOutputStream; 而不是java.util.zip 下的类
        zos.setEncoding("GBK");
        File file = new File(path);
        String prefix=batchName;
        FileUtils fileUtils=new FileUtils();
        fileUtils.makeZip(file, prefix, zos);
        zos.flush();
        zos.close();
    }

    /**
     *  制作 zip 文件
     * @param file 文件
     * @param baseName 文件根包名
     * @param zos zip 输出流
     * @throws java.io.IOException
     */
    public void makeZip(File file, String baseName, ZipOutputStream zos)throws IOException {
        if(file.exists()) {
            if (file.isFile()) {
                //如果是文件，写入到 zip 流中
                logger.info("#####fileName###"+file.getName());
//				if(gameFileNames.contains(file.getName())){
                zos.putNextEntry(new ZipEntry(baseName + file.getName()));
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                zos.flush();
                //文件读取完后记得关闭！
                fis.close();
//				}
            } else {
                //如果是目录。递归查找里面的文件
                String dirName = baseName + file.getName() + "/";
                zos.putNextEntry(new ZipEntry(dirName));
                File[] subs = file.listFiles();
                for (File f : subs) {
                    makeZip(f, dirName, zos);
                }
            }
        }
    }

    /**
     * 文章静态化后上传
     * @param dirType
     * @param id
     */
    public void staticContentUpload(String dirType,String id,Map dataMap) {
        String baseFileUri=dirType+"/";
        String templateFileUri="template/";
        String dirStr = this.getPropertiesValue("resourceUri") + baseFileUri;
        String staticFileName = this.getPropertiesValue("resourceUri") + baseFileUri + id+".html";
        String airLineStaticFileName = this.getPropertiesValue("resourceUri") + baseFileUri + "airline_" + id + ".html";
        String articleTemplatePath = this.getPropertiesValue("resourceUri") + templateFileUri+"articleTemplate.html";
        File fileDir = new File(dirStr);
        // 如果文件夹不存在则创建
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            logger.info("文件目录不存在");
            fileDir.mkdirs();
        } else {
            logger.info("文件目录存在");
        }
        File fileName = new File(staticFileName);
        if(!fileName.exists())
        {
            try {
                fileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            logger.info(id+".html" + "文件已经存在，先删除再重建");
            fileName.delete();
            try {
                fileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File airlineFileName = new File(airLineStaticFileName);
        if(!airlineFileName.exists())
        {
            try {
                airlineFileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            logger.info("airline_" + id+".html" + "文件已经存在，先删除再重建");
            airlineFileName.delete();
            try {
                airlineFileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileInputStream fis = new FileInputStream(articleTemplatePath);
            FileOutputStream fos = new FileOutputStream(staticFileName);
            FileOutputStream fos1 = new FileOutputStream(airLineStaticFileName);

            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "UTF-8");

            BufferedReader br = new BufferedReader(isr);
            BufferedWriter bw = new BufferedWriter(osw);
            BufferedWriter bw1 = new BufferedWriter(osw1);

            String line;
            String valueInairLine = "";
            while ((line = br.readLine()) != null) {
                int tagStart=line.indexOf("#{");
                int tagEnd=line.indexOf("}#");
                if(tagStart>=0&&tagEnd>0){
                    String key=line.substring(tagStart+2,tagEnd);
                    String value=(String)dataMap.get(key);
                    if (value!=null){
                        String REGEX="\\#\\{(.+?)\\}\\#";
                        Pattern p = Pattern.compile(REGEX);
                        Matcher m = p.matcher(line);
                        line = m.replaceAll(value);
                    }
                }
                int a = line.indexOf("http://www.dongxingji.cn");
                if(a>=0){
                    valueInairLine = line.replace("http://www.dongxingji.cn","../res");
                } else {
                    valueInairLine = line;
                }
                logger.debug("-------------new----------" + line);
                bw.write(line + "\n");
                bw1.write(valueInairLine + "\n");
            }
            bw1.flush();
            bw1.close();
            bw.flush();
            bw.close();
            osw1.close();
            osw.close();
            fos1.close();
            fos.close();
            br.close();
            isr.close();
            fis.close();
            System.out.println("done");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getIp(){
        String ipv4 = "";
        try {
            ipv4 = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipv4;
    }
}
