package com.evoupsight.domain;

import com.google.gson.Gson;

/**
 * Created by evoup on 16-6-12.
 */
public class TSPChromosome {
    // TODO gene到底是多少
    char[] gene = new char[6];
    public static int[][] distance = {
            {12, 14, 21, 31, 76, 22, 14, 32, 12, 6},
            {2, 10, 21, 30, 41, 25, 1, 13, 28, 12},
            {34, 12, 21, 55, 31, 12, 3, 14, 21, 32},
            {21, 14, 5, 15, 3, 7, 31, 42, 15, 32},
            {41, 24, 34, 17, 21, 0, 5, 12, 30, 17},
            {36, 48, 12, 65, 0, 12, 31, 6, 31, 2},
            {17, 4, 21, 42, 30, 8, 6, 24, 1, 20},
            {28, 8, 40, 30, 4, 2, 3, 1, 2, 5},
            {14, 10, 8, 31, 22, 3, 1, 9, 10, 23},
            {12, 30, 14, 7, 14, 2, 13, 4, 12, 1}
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
