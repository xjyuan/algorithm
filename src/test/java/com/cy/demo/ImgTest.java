package com.cy.demo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @Author chenying
 * @Date 2018/7/18
 */
public class ImgTest {
    public static String getData(String path){
        StringBuilder result = new StringBuilder();
        try{
            BufferedImage bimg = ImageIO.read(new File(path));
            int [][] data = new int[bimg.getHeight()][bimg.getWidth()];
            //此方式为沿Height方向扫描
            for(int i=0;i<bimg.getHeight();i++){
                for(int j=0;j<bimg.getWidth();j++){
                    data[i][j]=bimg.getRGB(j,i);
                    String str = getCharByRGB(String.format("%x",data[i][j]));
                    result.append(str);
                }
                result.append("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return result.toString();
    }

    private static String getCharByRGB(String rgb) {
        rgb = rgb.replace("ff","");
        if(rgb.length()<6){
            return ".";
        }
        int r = Integer.parseInt(rgb.substring(0,2),16);
        int g = Integer.parseInt(rgb.substring(2,4),16);
        int b = Integer.parseInt(rgb.substring(4,6),16);

        int depth = 0;
        if(r<=85) depth++;
        if(g<=85) depth++;
        if(b<=85) depth++;

        if(r<=170) depth++;
        if(g<=170) depth++;
        if(b<=170) depth++;

        Random random = new Random();
        String result = "";
        switch (depth){
            case 0:result=".";break;
            case 1:result="1";break;
            case 2:result="2";break;
            case 3:result="3";break;
            case 4:result="4";break;
            case 5:
            case 6: {
                char c = (char) (65 + random.nextInt(24));
                result = String.valueOf(c);
            }
        }
        return result+" ";
    }


    public static void main(String [] args) throws IOException {
        String data = getData("C:\\Users\\ying.chen2\\Desktop\\img.png");
        File writename = new File("C:\\Users\\ying.chen2\\Desktop\\output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
        writename.createNewFile(); // 创建新文件
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        out.write(data);
        out.flush();
        out.close();

    }
}
