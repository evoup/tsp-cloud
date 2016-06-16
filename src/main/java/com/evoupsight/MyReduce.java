package com.evoupsight;

import com.evoupsight.domain.TSPChromosome;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by evoup on 16-6-12.
 */
public class MyReduce extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterator<Text> value, OutputCollector<Text, Text> outputCollector, Reporter reporter) throws IOException {
        int minValue = 100000000;
        String minGeneStr = "";
        //取得所有map运算的结果，将最小适应度作为最优解
        while (value.hasNext()) {
            // 取得染色体字符串
            String geneStr = value.next().toString();
            // 计算适应度
            // 判断是否是较好的适应度
            if (TSPChromosome.caculateFitnessValue(geneStr) < minValue) {
                minValue = TSPChromosome.caculateFitnessValue(geneStr);
                minGeneStr = geneStr;
            }
        }
        // 存储在负责封装输出数据的对象中
        outputCollector.collect(new Text("TSPSolution"), new Text("result:" + minGeneStr + "_" + minValue));
    }
}
