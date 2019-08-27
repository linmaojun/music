package com.music.controller.weixin;

import com.music.util.CheckUtil;
import com.music.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 类描述:与微信对接登录验证
 * @author linmaojun
 * @date 2019/3/4 17:48
 */
@Controller
@RequestMapping(value = "music")
public class WXController {
    private static Logger logger = LoggerFactory.getLogger(WXController.class);

    /**
     * 验证是否来自微信服务器的消息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "",method=RequestMethod.GET,produces = "application/xml; charset=UTF-8")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
        String signatrue = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        if(CheckUtil.checkSingnature(signatrue,timestamp,nonce)){
            //如果校验成功将返回随机字符串
            out.print(echostr);
            out.flush();
            out.close();
            logger.info("微信接入成功！");
        }else{
            logger.info("微信接入失败！！！");
        }
    }

    @RequestMapping(value = "",method = RequestMethod.POST,produces = "application/xml; charset=UTF-8")
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException ,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String,String> map = MessageUtil.parseRequest(request.getInputStream());
        System.out.println(map);
        //准备回复的数据包
        String respXml = MessageUtil.getRespose(map);
        System.out.println(respXml);
        PrintWriter out = response.getWriter();
        out.print(respXml);
        out.flush();
        out.close();
    }
}
