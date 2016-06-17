package com.evoupsight.domain;

import com.google.gson.Gson;

/**
 * Created by evoup on 16-6-12.
 */
public class TSPChromosome {
    // TODO gene到底是多少
    char[] gene = new char[6];
    public static int[][] distance = {
            {0, 20, 25, 30, 60, 10, 15, 22, 6, 16},
            {20, 0, 2, 10, 50, 5, 10, 20, 8, 20},
            {25, 2, 0, 3, 40, 7, 13, 25, 11, 19},
            {30, 10, 3, 0, 55, 9, 11, 19, 7, 18},
            {60, 50, 40, 55, 0, 12, 15, 16, 10, 21},
            {10, 5, 7, 9, 12, 0, 20, 7, 13, 20},
            {15, 10, 13, 11, 15, 20, 0, 30, 5, 17},
            {22, 20, 25, 19, 16, 7, 30, 0, 6, 8},
            {6, 8, 11, 7, 10, 13, 5, 6, 0, 13},
            {16, 20, 19, 18, 21, 20, 17, 8, 13, 0}
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
