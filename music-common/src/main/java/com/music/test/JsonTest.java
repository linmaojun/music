package com.music.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.music.domain.DrugInspectionReport;
import com.music.domain.close.CloseOrderJson;
import com.music.domain.transport.TransportJson;
import com.music.util.ReadFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author linmaojun
 * 类名称: JsonTest
 * 类描述:
 * 创建时间: 2019年07月18日  9:44
 */
public class JsonTest {
    public static void main(String[] args) {
//        String json = ReadFile.readTxt("D:\\关单.txt");
//        JSONObject jsonObject = JSON.parseObject(json);
//        CloseOrderJson closeOrderJson = jsonObject.getObject("xml",CloseOrderJson.class);
//        System.out.println(closeOrderJson);

//        String json = ReadFile.readTxt("D:\\发运.txt");
//        String json = ReadFile.readTxt("D:\\签收.txt");
//        JSONObject jsonObject = JSON.parseObject(json);
//        TransportJson transportJson = jsonObject.getObject("xml",TransportJson.class);
//        System.out.println(transportJson);

        DrugInspectionReport drug = new DrugInspectionReport();
        drug.setSerial("2019070819015020");
        drug.setGoodsid("60000000105132");
        drug.setPackSize("111");
        drug.setLotno("19015012");
        drug.setBusinessType("T601");
        drug.setSender("GSK");
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        drug.setSendtime(dateFormat.format(new Date()));
        String json = JSON.toJSONString(drug);
        try {
            File file = new File("D:\\103_T601_2019070819015020.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(json);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
