import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liushoulong
 * @Date: 2019/10/5 15:14
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.*"})
@RestController
public class BootStrat {
    public static void main(String[] agrs){
        SpringApplication.run(BootStrat.class,agrs);
    }

    /**
     * 分析 如何从httpserveletrequest中获取异步请求上下文
     *
     * @return
     */
    @RequestMapping(value = "getInfo")
    public ResponseEntity<Map<String,Object>> getInfo(HttpServletRequest request, HttpServletResponse response){

        Map<String,Object> result = new HashMap<>();
        // 获取异步请求上下文，其中可以获取到请求和响应对象
        AsyncContext asyncContext =  request.startAsync();
        return ResponseEntity.ok(result);
    }
}
