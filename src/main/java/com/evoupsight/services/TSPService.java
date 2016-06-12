package com.evoupsight.services;

import com.evoupsight.domain.TSPChromosome;

import java.util.Vector;

/**
 * Created by evoup on 16-6-12.
 */
public class TSPService {
    Vector chromosomeList;
    TSPChromosome[] worm;

    public TSPService(Vector list) {
        this.chromosomeList = list;
        worm[0] = null;
        worm[1] = null;
        worm[2] = null;
        worm[3] = null;
        worm[4] = null;
        worm[5] = null;
    }

    public void start() {
        // 创建第一代种群
        for (int i = 0; i < worm.length; i++) {
            worm[i] = new TSPChromosome();
        }
    }

    public String getBestSolution() {
        return "";
    }
}
