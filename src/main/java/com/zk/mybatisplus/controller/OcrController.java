package com.zk.mybatisplus.controller;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;
import com.tencentcloudapi.tia.v20180226.models.Model;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ocr")
public class OcrController {

    @GetMapping("/index")
    public String index(){
        return "ocr";
    }

    @PostMapping("uploadFile")
    @ResponseBody
    public IDCardOCRResponse OCRIdCardTest(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "card_side") String cardSize, Model model){
        try {
            Credential cred = new Credential("XXX", "XXX");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);
            Map<String, String> params = new HashMap<>();
           // params.put("ImageBase64", getBase64FromInputStream(file.getInputStream()));
            params.put("ImageBase64", file2Base64(file.getInputStream()));
            params.put("CardSide", cardSize);
            System.out.println(getBase64FromInputStream(file.getInputStream()));

            IDCardOCRRequest req = IDCardOCRRequest.fromJsonString(JSONObject.fromObject(params).toString(), IDCardOCRRequest.class);
            IDCardOCRResponse resp = client.IDCardOCR(req);
            return resp;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;

    }
    public static String file2Base64(InputStream in ) {
        String base64 = null;
        try {
            byte[] buff = new byte[in.available()];
            in.read(buff);
            base64 = new String(Base64.encodeBase64(buff));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }
    public static String getBase64FromInputStream(InputStream in) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new String(Base64.encodeBase64(data));
    }
}
