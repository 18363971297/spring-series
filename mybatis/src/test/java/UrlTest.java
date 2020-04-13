import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;

/**
 * @Author: liushoulong
 * @Date: 2019/11/20 15:11
 */
public class UrlTest {
    public static void main(String[] agrs){
      // 指定包下的资源
        //getPackagePath();

        // 指定文件夹
        String path = "file://C:\\Users\\shmily\\Desktop\\青啤项目\\020迁移\\迁移方案";
        String filePath = "";
        if(path.startsWith("file://")){
            filePath = path.substring(6);
        }
        File file = new File(filePath);
        boolean isDir = file.isDirectory();
        if(isDir){
            File[] childFiles = file.listFiles();
            for(File f:childFiles){
                System.out.println("" +  f.getName());
                System.out.println("" +  f.getPath());
                try(FileReader fw = new FileReader(f)){
                    BufferedReader br = new BufferedReader(fw);
                    for(String line; (line = br.readLine()) != null;){
                        System.out.println("" +  line);
                    }
                }catch (IOException i){
                    i.printStackTrace();
                }

                URL url = getURL("file://"+f.getPath());

            }
        }
    }

    public static URL getURL(String path){
        try{
            // 是一个完整的url  file://c:\a\b\c
            URL url = new URL(path);
            String urlPath = url.getPath();
            System.out.println("" + urlPath);
            return url;
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }


    public static void getPackagePath(){
        String path = "com.lsl.mybatis";
        path =  path.replace(".", "/");
        try{
            // 获取资源url
            Enumeration<URL> urls =  Thread.currentThread().getContextClassLoader().getResources(path);

            while(urls.hasMoreElements()){
                URL url = urls.nextElement();
                InputStream is = url.openStream();
                // 字符读取
                InputStreamReader reader = new InputStreamReader(is);
                // 将字符流存入缓存区
                BufferedReader bufferedReader = new BufferedReader(reader);
                // 遍历缓冲区的数据
                for(String line; (line = bufferedReader.readLine()) != null;){
                    System.out.println("--" + line);
                }

            }
        } catch (IOException io){
            io.printStackTrace();
        }
    }
}
