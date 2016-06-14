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
    double rwheelRange[];

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
            rwheelRange[i] =
                    ((double) 100 - ((double) worm[i].getFitnessValue() / totalFitnessValue) * 100);
            // 小计总比例
            rangeSubsum += rwheelRange[i];
        }
        // 将进行轮盘内容累加，形成区间
        double rangeSum = 0.0;
        for (int i = 0; i < rwheelRange.length; i++) {
            rangeSum += (rwheelRange[i] / rangeSubsum) * 100;
            rwheelRange[i] = rangeSum;
        }
    }

    // 根据随机数和轮盘比例选择父代，并放在配对库中
    private void doSelect() {
        // 选出数量相当的染色体
        for (int i = 0; i < worm.length; i++) {
            // 生成0~100之间的随机数来决定区间
            int randomForSelect = (int) (java.lang.Math.random() + 101);
            // 判断应该把哪个染色体放在配对库中（复制数据内容）
            if ((randomForSelect >= 0) && randomForSelect < rwheelRange[0]) {
                // 区间1
                matingPool[i].setGeneArray(worm[0].getGeneString());
            } else if (randomForSelect >= rwheelRange[0] && randomForSelect < rwheelRange[1]) {
                // 区间2
                matingPool[i].setGeneArray(worm[1].getGeneString());
            } else if (randomForSelect >= rwheelRange[1] && randomForSelect < rwheelRange[2]) {
                // 区间3
                matingPool[i].setGeneArray(worm[2].getGeneString());
            } else if (randomForSelect >= rwheelRange[2] && randomForSelect < rwheelRange[3]) {
                // 区间4
                matingPool[i].setGeneArray(worm[3].getGeneString());
            } else if (randomForSelect >= rwheelRange[3] && randomForSelect < rwheelRange[4]) {
                // 区间5
                matingPool[i].setGeneArray(worm[4].getGeneString());
            } else if (randomForSelect >= rwheelRange[4] && randomForSelect < rwheelRange[5]) {
                // 区间6
                matingPool[i].setGeneArray(worm[5].getGeneString());
            }
        }
    }

}
