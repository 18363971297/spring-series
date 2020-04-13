package com.lsl.mybatis.builder.xml;

import com.lsl.mybatis.io.Resource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * @Author: liushoulong
 * @Date: 2019/11/25 18:08
 */
public class XMLMapperEntityResolver implements EntityResolver {

    public static final String MYBATIS_CONFIG_DTD = "com/lsl/mybatis/builder/xml/mybatis-3-config.dtd";
    public static final String MYBATIS_MAPPER_DTD = "com/lsl/mybatis/builder/xml/mybatis-3-mapper.dtd";

    public static final String MYBATIS_CONFIG_KEY = "mybatis-3-config.dtd";
    public static final String IBATIS_CONFIG_KEY = "ibatis-3-config.dtd";
    public static final String MYBATIS_MAPPER_KEY = "mybatis-3-mapper.dtd";
    public static final String IYBATIS_MAPPER_KEY = "ibatis-3-mapper.dtd";

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        InputSource is = null;
        // 小写转换
        systemId = systemId.toLowerCase(Locale.ENGLISH);

        if(systemId != null){
            InputStream ii =  null;
            if(systemId.contains(MYBATIS_CONFIG_KEY) || systemId.contains(IBATIS_CONFIG_KEY)){
                ii = Resource.getResourceAsStream(MYBATIS_CONFIG_DTD);
            }else if(systemId.contains(IYBATIS_MAPPER_KEY) || systemId.contains(MYBATIS_MAPPER_KEY)){
                ii = Resource.getResourceAsStream(MYBATIS_MAPPER_DTD);
            }
            if(ii != null){
                is = new InputSource(ii);
                is.setPublicId(publicId);
                is.setSystemId(systemId);
            }
        }
        return is;
    }
}
