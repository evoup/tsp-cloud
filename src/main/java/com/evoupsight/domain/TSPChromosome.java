package com.evoupsight.domain;

/**
 * Created by evoup on 16-6-12.
 */
public class TSPChromosome {
    char[] gene;
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

    public int getFitnessValue() {
        int fitnessValue = 0;
        for (int i = 0; i < 10; i++) {
            // 查表取得目前索引值城市和下一个城市之间的距离
            if (i == 9) {
                // 应对比数组中最后一个城市和数组第一个城市
                //int rowInx = ((int))
            }
        }
        return 0;
    }

    public void setGeneArray(String chromosome) {
        this.gene = chromosome.toCharArray();
    }
}
