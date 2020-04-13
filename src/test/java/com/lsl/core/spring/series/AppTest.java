package com.lsl.core.spring.series;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        // 字符分解器
        String st = "1,2,3,4,5,6";
        String s[] = StringUtils.tokenizeToStringArray(st,",");

        Stream<String> stream = Arrays.stream(s);
        stream.forEach(t -> {
            System.out.println(""+t);
        });
    }
}
