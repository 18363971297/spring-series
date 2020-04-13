import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;

/**
 * @Author: liushoulong
 * @Date: 2020/1/21 13:29
 */
public class ResouceTest implements Serializable{

    @Test
    public void classpathResouce(){

        String str  = ResouceTest.class.getSimpleName();

        char[] bStr = str.toCharArray();
        if(bStr.length>0){
            char c = bStr[0];
            if(c>='A' && c<='Z'){
                c+=32;
            }else if(c>='a' && c<='z'){
                c-=32;
            }
            bStr[0] = c;
        }
        System.out.println("" +  new String(bStr));
        /*for(int i=0;i<bStr.length;i++){
            char n = bStr[i];
            if(n>='A' && n<='Z'){
                n+=32;
                System.out.println("" + n);
            }else if(n<='z' && n>='a'){
                n-=32;
                System.out.println("" + n);
            }
        }*/

    }

    @Test
    public void supperClass(){
        Type type = ResouceTest.class.getGenericSuperclass();
        System.out.println("" +  type.getTypeName());

        Type[] ts = ResouceTest.class.getGenericInterfaces();
        for(int i=0;i<ts.length;i++){
            System.out.println("" +  ts[i].getTypeName());
        }

    }
}
