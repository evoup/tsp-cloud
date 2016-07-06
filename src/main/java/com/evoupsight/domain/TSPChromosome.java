package com.evoupsight.domain;

import com.google.gson.Gson;

/**
 * Created by evoup on 16-6-12.
 */
public class TSPChromosome {
    // TODO gene到底是多少
    char[] gene = new char[6];
    public static int[][] distance = {
            {0, 33, 27, 54, 23, 17, 13, 3, 17, 24},
            {33, 0, 6, 17, 10, 16, 7, 33, 9, 32},
            {27, 6, 0, 55, 33, 22, 12, 21, 64, 12},
            {54, 17, 55, 0, 14, 42, 26, 16, 38, 14},
            {23, 10, 33, 14, 0, 34, 11, 17, 22, 36},
            {17, 16, 22, 42, 34, 0, 5, 51, 15, 44},
            {13, 7, 12, 26, 11, 5, 0, 6, 21, 33},
            {3, 33, 21, 16, 17, 51, 6, 0, 12, 13},
            {17, 9, 64, 38, 22, 15, 21, 12, 0, 3},
            {24, 32, 12, 14, 36, 44, 33, 13, 3, 0}
    };

    // 计算染色体的适应度
    public int getFitnessValue() {
        int fitnessValue = 0;
        System.out.println("gene:" + new Gson().toJson(gene));
        for (int i = 0; i < gene.length; i++) {
            // 查表取得目前索引值城市和下一个城市之间的距离
            if (i == (gene.length - 1)) {
                // 应对比数组中最后一个城市和数组第一个城市
                System.out.println("i:" + i);
                int rowInx = ((int) gene[i]) - 65;
                int colInx = ((int) gene[0]) - 65;
                fitnessValue += distance[rowInx][colInx];
            } else {
                // 索引值指向目前城市和下一个城市
                // 字符A的ASCII值是65，因而指向第0个元素
                int rowInx = ((int) gene[i]) - 65;
                int colInx = ((int) gene[i + 1]) - 65;
                fitnessValue += distance[rowInx][colInx];
            }
        }
        return fitnessValue;
    }

    // 计算传入染色体的适应度
    public synchronized static int caculateFitnessValue(String geneStr) {
        int fitnessValue = 0;
        char[] localGene = geneStr.toCharArray();
        for (int i = 0; i < localGene.length; i++) {
            // 查表取得目前索引值城市和下一个城市的距离
            if (i == (localGene.length - 1)) {
                int rowInx = ((int) localGene[i]) - 65;
                int colInx = ((int) localGene[0]) - 65;
                fitnessValue += distance[rowInx][colInx];
            } else {
                // 索引值指向目前城市和下一个城市
                // 字符A的ASCII值是65，因而指向第0个元素
                int rowInx = ((int) localGene[i]) - 65;
                int colInx = ((int) localGene[i + 1]) - 65;
                fitnessValue += distance[rowInx][colInx];
            }
        }
        return fitnessValue;
    }

    public void setGeneArray(String chromosome) {
        this.gene = chromosome.toCharArray();
    }

    public String getGeneString() {
        return new String(this.gene);
    }

    // TODO 是否正确？
    public int getChromosomeLength() {
        return new String(this.gene).length();
    }
}
