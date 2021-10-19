package com.zk.mybatisplus.controller;

import com.zk.mybatisplus.common.utils.XmlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * XML和map互相转换
 */
@Controller
@Api(value = "xml", tags = "XML")
public class XmlController {

    @ResponseBody
    @RequestMapping(value = "/map2xml", method = RequestMethod.POST)
    @ApiOperation(value = "map转xml")
    public String map2xml() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("userName", "张三");
        map.put("age", 10);
        map.put("weight", "60kg");
        String mapToXml = XmlUtil.mapToXml(map, true);
        return mapToXml;
    }

    @ResponseBody
    @RequestMapping(value = "/xml2map", method = RequestMethod.POST)
    @ApiOperation(value = "xml转map")
    public Map<String, String> xml2map(@ApiParam(name = "xml", value = "xml转为map")
                                       @RequestParam(name = "xml") String xml) {
        Map<String, String> xmlToMap = XmlUtil.xml2Map(xml);
        return  xmlToMap;
    }

}
