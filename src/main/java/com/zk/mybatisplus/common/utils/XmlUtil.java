package com.zk.mybatisplus.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUtil {
    private static final String PREFIX_XML = "<xml>";
    private static final String SUFFIX_XML = "</xml>";

    private static final String PREFIX_CDATA = "<![CDATA[";

    private static final String SUFFIX_CDATA = "]]>";

    /**
     * 转化成xml, 单层无嵌套
     */
    public static String mapToXml(Map<Object, Object> parm, boolean isAddCDATA) {
        StringBuffer strbuff = new StringBuffer(PREFIX_XML);
        if (null != parm) {
            for (Entry<Object, Object> entry : parm.entrySet()) {
                strbuff.append("<").append(entry.getKey()).append(">");
                if (isAddCDATA) {
                    strbuff.append(PREFIX_CDATA);
                    if (null != entry.getValue()) {
                        strbuff.append(entry.getValue());
                    }
                    strbuff.append(SUFFIX_CDATA);
                } else {
                    if (null != entry.getValue()) {
                        strbuff.append(entry.getValue());
                    }
                }
                strbuff.append("</").append(entry.getKey()).append(">");
            }
        }
        return strbuff.append(SUFFIX_XML).toString();
    }


    /**
     * @param xml
     * @return Map
     * @description 将xml字符串转换成map
     */
    public static Map<String, String> xml2Map(String xml) {
        Map<String, String> map = new HashMap<String, String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            @SuppressWarnings("unchecked")
            List<Element> list = rootElt.elements();// 获取根节点下所有节点
            for (Element element : list) { // 遍历节点
                map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
