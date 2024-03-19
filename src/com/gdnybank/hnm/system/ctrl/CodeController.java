package com.gdnybank.hnm.system.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成验证码
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/code")
public class CodeController {

	@RequestMapping(params="txcode=code001")
	public void createCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 //设置不缓存图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        //指定生成的响应图片,一定不能缺少这句话,否则错误.
        response.setContentType("image/jpeg");
        int width=86,height=30;     //指定生成验证码的宽度和高度
        //创建BufferedImage对象,其作用相当于一图片
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g=image.getGraphics();     //创建Graphics对象,其作用相当于画笔
        Graphics2D g2d=(Graphics2D)g;       //创建Grapchics2D对象
        Random random=new Random();
        Font mfont=new Font("楷体",Font.BOLD,22); //定义字体样式
        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);    //绘制背景
        g.setFont(mfont);                   //设置字体
        g.setColor(getRandColor(180,200));
      //绘制100条颜色和位置全部为随机产生的线条,该线条为2f
        for(int i=0;i<100;i++){
            int x=random.nextInt(width-1);
            int y=random.nextInt(height-1);
            int x1=random.nextInt(6)+1;
            int y1=random.nextInt(12)+1;
          //定制线条样式
            BasicStroke bs=new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
            Line2D line=new Line2D.Double(x,y,x+x1,y+y1);
            g2d.setStroke(bs);
          //绘制直线
            g2d.draw(line);
        }
          //输出由英文，数字，随机组成的验证文字，具体的组合方式根据生成随机数确定。
            String sRand="";
            String ctmp="";
            int itmp=0;
          //制定输出的验证码为四位
            for(int i=0;i<4;i++){
                switch(random.nextInt(3)){
                    case 1:     //生成A-Z的字母
                         itmp=random.nextInt(26)+65;
                         ctmp=String.valueOf((char)itmp);
                         break;
                    case 2:     //生成随机数字
                    	 itmp=random.nextInt(10);
                    	 ctmp=itmp+"";
                         break;
                    default:
                         itmp=random.nextInt(10)+48;
                         ctmp=String.valueOf((char)itmp);
                         break;
                }
                sRand+=ctmp;
                Color color=new Color(20+random.nextInt(110),20+random.nextInt(110),random.nextInt(110));
                g.setColor(color);
                //将生成的随机数进行随机缩放并旋转制定角度 PS.建议不要对文字进行缩放与旋转,因为这样图片可能不正常显示
                /*将文字旋转制定角度*/
                Graphics2D g2d_word=(Graphics2D)g;
                AffineTransform trans=new AffineTransform();
                trans.rotate((30)*3.14/180,15*i+8,7);
                /*缩放文字*/
                float scaleSize=random.nextFloat()+0.8f;
                if(scaleSize>1f) scaleSize=1f;
                trans.scale(scaleSize, scaleSize);
                g2d_word.setTransform(trans);
                g.drawString(ctmp, 15*i+18, 14);
        }
            HttpSession session=request.getSession();
            if(session.getAttribute("randCheckCode")!=null) session.removeAttribute("randCheckCode");
            session.setAttribute("randCheckCode", sRand.toLowerCase());
            //这里直接设置session的有效时间,使验证码失效,3分钟
            session.setMaxInactiveInterval(180);
            g.dispose();    //释放g所占用的系统资源
            ImageIO.write(image,"JPEG",response.getOutputStream()); //输出图片

    		//把图片转成base64格式的字符串
            /*Map<String, Object> responseMap = new HashMap<String, Object>();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(image, "jpeg", out);
            byte[] b = out.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            String verImage = encoder.encode(b);
    		out.close();
    		responseMap.put("verimage", verImage);
    		return responseMap;*/

	}
	/*该方法主要作用是获得随机生成的颜色*/
    public Color getRandColor(int s,int e){
        Random random=new Random ();
        if(s>255) s=255;
        if(e>255) e=255;
        int r,g,b;
        r=s+random.nextInt(e-s);    //随机生成RGB颜色中的r值
        g=s+random.nextInt(e-s);    //随机生成RGB颜色中的g值
        b=s+random.nextInt(e-s);    //随机生成RGB颜色中的b值
        return new Color(r,g,b);
    }
}
