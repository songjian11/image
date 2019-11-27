package com.bbg.purchaseplan.utils;

import com.lowagie.text.*;
import com.lowagie.text.Image;
import com.lowagie.text.rtf.RtfWriter2;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ImageUtil {
    private BufferedImage image;
    void createImage(String fileLocation) {
        try {
            FileOutputStream fos = new FileOutputStream(fileLocation);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
            encoder.encode(image);
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void graphicsGeneration(String name, String id, String classname, String imgurl,String qrimg) {

        int imageWidth = 295;// 图片的宽度

        int imageHeight =333;// 图片的高度

        image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = image.getGraphics();

        graphics.setColor(Color.WHITE);

        graphics.fillRect(0, 0, imageWidth, imageHeight);

        graphics.setColor(Color.BLACK);

        graphics.drawString(name, 80, 220);

        graphics.drawString(id, 130,260);

        graphics.drawString(classname,130,290);

        BufferedImage bimg = null;
        try {
            bimg = javax.imageio.ImageIO.read(new java.io.File(imgurl));
        } catch (Exception e) {
        }
        if (bimg != null){
            graphics.drawImage(bimg,80, 20, null);
        }
        BufferedImage qimg =null;
        try {
            qimg = javax.imageio.ImageIO.read(new java.io.File(qrimg));
        } catch (Exception e) {
        }
        if (qimg != null){
            graphics.drawImage(qimg,20, 220, null);
        }
        graphics.dispose();
        createImage("E:\\55.jpg");
    }

    public void gG(String[] imgurl) {
        int imageWidth =1240;// 图片的宽度
        int imageHeight =1754;// 图片的高度
        image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(Color.BLACK);
        int i=0;
        int j=0;
        int p=0;
        for (String img : imgurl) {
            BufferedImage bimg = null;
            try {
                bimg = javax.imageio.ImageIO.read(new java.io.File(img));
            } catch (Exception e) {
            }
            if (bimg != null){
                graphics.drawImage(bimg,20+i, 20+p, null);
            }
            i=i+320;
            j++;
            if(j%4==0){
                p=p+350;
                i=0;
            }
        }
        graphics.dispose();
        createImage("E:\\100.jpg");
    }

    public static void createDoc(String[] str) throws Exception{
        //创建word文档,并设置纸张的大小
        String fileName="D:/照片.doc";
        FileOutputStream fos = new FileOutputStream(fileName);
        Document document = new Document(PageSize.A4);
        RtfWriter2.getInstance(document,fos);
        document.open();
        Table table = new Table(4);
        table.setBorderWidth(0);
        for(int i=0;i<str.length;i++){
            //添加图片
            File file = new File(str[i]);
            Image img = null;
            if(file.exists()) {
                img= Image.getInstance(str[i]);
                img.scaleAbsolute(100, 100);
                table.addCell(new Cell(img));
            }
        }
        document.add(table);
        document.close();
    }

    public static void main(String[] args) {
        String[] imageArr = {"C:\\Users\\1000129886\\Desktop\\1.png",
                "C:\\Users\\1000129886\\Desktop\\2.png",
                "C:\\Users\\1000129886\\Desktop\\3.png",
                "C:\\Users\\1000129886\\Desktop\\4.png",};
        try {
            createDoc(imageArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
