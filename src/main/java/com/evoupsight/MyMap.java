package com.evoupsight;

import com.evoupsight.services.TSPService;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by evoup on 16-6-12.
 */
public class MyMap extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable longWritable, Text text, OutputCollector<Text, Text> outputCollector, Reporter reporter) throws IOException {
        // 从数据文件中读入数据
        String line = text.toString();
        // 每个数据都是一个种群（第一栏是种群序号）
        String[] datas = line.split(" ");
        // 存储染色体数据的列表
        Vector<String> chromosomeList = new Vector();
        for (int i = 1; i < 7; i++) { // 每个种群6个染色体
            System.out.println("datas[i]:" + datas[i]);
            chromosomeList.addElement(datas[i]);
        }
        // 创建TSP遗传算法的对象
        TSPService tspService = new TSPService(chromosomeList);
        tspService.start();

        // 存储该种群的最优解
        // 使用一个主键送往同一个reduce运算
        outputCollector.collect(new Text("TSPSolution"), new Text(tspService.getBestSolution()));
    }
}