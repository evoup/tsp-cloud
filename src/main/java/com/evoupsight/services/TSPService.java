package com.evoupsight.services;

import com.evoupsight.domain.TSPChromosome;

import java.util.Vector;

/**
 * Created by evoup on 16-6-12.
 */
public class TSPService {
    Vector<String> chromosomeList;
    TSPChromosome[] worm;
    TSPChromosome[] matingPool; // 配对库

    public TSPService(Vector<String> list) {
        this.chromosomeList = list;
        worm[0] = null;
        worm[1] = null;
        worm[2] = null;
        worm[3] = null;
        worm[4] = null;
        worm[5] = null;
        matingPool[0] = null;
        matingPool[1] = null;
        matingPool[2] = null;
        matingPool[3] = null;
        matingPool[4] = null;
        matingPool[5] = null;
    }

    public void start() {
        // 创建第一代种群
        for (int i = 0; i < worm.length; i++) {
            worm[i] = new TSPChromosome();
            worm[i].setGeneArray(chromosomeList.get(i));
        }
        // 创建配对库中的对象
        for (int i = 0; i < matingPool.length; i++) {
            matingPool[i] = new TSPChromosome();
        }
        int roop = 0;
        int roopMax = 10;
        do {
            System.out.println("第" + (roop + 1) + "次演化");
            // 计算轮盘区间
            setRange();
            System.out.println("完成轮盘设置");
            //............
            roop++;
        } while (roop < roopMax);
    }

    public String getBestSolution() {
        return "";
    }

    private void setRange() {
        // 对适应度求和，以便取得轮盘赌选择法的分母
        int totalFitnessValue = 0;
        for (int i = 0; i < worm.length; i++) {
            totalFitnessValue += worm[i].getFitnessValue();
        }
        // 计算轮盘赌选择法中每一个染色体所占的比例
        // 由于在TSP问题中，适应度越低越好
        // 因而在计算轮盘比例时，必须先用100去减，以求反转
        double rangeSubsum = 0.0;
        for (int i = 0; i < worm.length; i++) {
            // 暂存反转后的值

        }
    }
}
