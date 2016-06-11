package com.evoupsight;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by evoup on 16-6-10.
 * 云遗传计算类
 */
public class TspCloud {

    Hashtable cityTable;
    boolean isLocalFolderOk = false;
    boolean isHDFSFolderOK = false;
    int group = 10; // 产生种群数，每个种群一个map
    String resStr = ""; // 执行结果字符串
    String folderName = "localFolder"; // 本地工作文件目录
    String fileName = "TSP_" + this.GetCurrentDateTime();
    public TspCloud(Hashtable t) {
        cityTable = t;
    }

    public String doCloudOpertion() {
        ///
        try {
            // 在本地工作目录创建暂存文件的目录
            File foler = new File(folderName);
            foler.mkdir();
            // 在本地文件系统中创建新的数据文件
            FileOutputStream fo = new FileOutputStream("./" + folderName + "/" + fileName);
            DataOutputStream fsOut = new DataOutputStream(fo);
            // 产生指定的种群数
            for (int i = 0; i< group; i++) {
                // 每个种群有６条染色体
                StringBuffer chromosomeStr = new StringBuffer("");
                //　加入种群序号
                chromosomeStr.append(i + " ");
                for (int j = 0; j < 6; j++) {
                    //　随机生成染色体内容
                    chromosomeStr.append(createChromosome() + " ");
                }
                //　将染色体数据写到文件中
                fsOut.writeBytes("" + chromosomeStr.toString() + "\n");
                fsOut.flush();
            }
            fsOut.close();
            fsOut = null;
            System.out.println("create local folder:" + folderName + " and file:" + fileName + " done.");
            isLocalFolderOk = true;
        } catch (Exception e) {
        }
        ///
        return "some result";
    }

    /**
     * 获取当前时间字符串
     */
    private String GetCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    /**
     * 根据传入的城市代码生成染色体数据
     */
    private String createChromosome() {
        int inx = 0;
        // 由前端使用者选定的城市个数决定染色体长度
        char[] gene = new char[cityTable.size()];
        Enumeration enums = cityTable.keys();
        while (enums.hasMoreElements()) {
            gene[inx] = enums.nextElement().toString().charAt(0);
            inx++;
        }

        // 将城市代码按随机数排序
        for (int i = 0; i < 10; i++) {
            int sInx = (int)(Math.random()*cityTable.size());
            int eInx = (int)(Math.random()*cityTable.size());
            //　交换随机数指向的索引数据内容
            char tmp = gene[sInx];
            gene[sInx] = gene[eInx];
            gene[eInx] = tmp;
        }
        // 将基因数据组成字符串，返回上层
        StringBuffer sBuf = new StringBuffer("");
        for (int i = 0; i<gene.length;i++) {
            sBuf.append(gene[i]);
        }
        return sBuf.toString();
    }
}
