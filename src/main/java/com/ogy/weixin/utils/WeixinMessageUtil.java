package com.ogy.weixin.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeixinMessageUtil {

    // 解析xml to map
    public static Map<String,String> parseXml(HttpServletRequest request){
        SAXReader reader = new SAXReader();
        Map<String,String> map = new HashMap<>();
        try {
            Document document = reader.read(request.getInputStream());
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for(Element element:elements){
                map.put(element.getName(),element.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
