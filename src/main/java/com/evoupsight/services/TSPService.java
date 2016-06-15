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

    // 进行染色体交叉
    private void doCrossover() {
        double pc = 0.8; // 假设交叉概率为0.8
        // 两两交叉配对库中的染色体
        // 因为有6个染色体，因而要执行3次交叉运算
        for (int i = 0; i < 6; i += 2) {
            TSPChromosome worm1 = matingPool[i];
            TSPChromosome worm2 = matingPool[i + 1];
            // 取得染色体的数据内容
            String wormStr1 = worm1.getGeneString();
            String wormStr2 = worm2.getGeneString();
            // 产生大于等于0，且小于1的随机数
            double decide = java.lang.Math.random();
            // 随机数小于交叉概率，且染色体内容不同时，才需要进行交叉
            if (decide <= pc && !wormStr1.equals(wormStr2)) {
                int point1 = 0;
                int point2 = 0;
                do {
                    // 生成2个交叉点
                    point1 = (int) (java.lang.Math.random() * worm1.getChromosomeLength());
                    point2 = (int) (java.lang.Math.random() * worm2.getChromosomeLength());
                    // point1为左交叉点，point2为右交叉点
                    // point2大于point1时，交换位置
                    if (point2 < point1) {
                        int tmp = point1;
                        point1 = point2;
                        point2 = tmp;
                    }
                    // 如果左右交叉点同时指向数据的最两端，没有交叉的意义
                } while (point1 == 0 && point2 == worm1.getChromosomeLength() - 1);
                // 取得染色体的数据内容，并且根据交换点切分3个子区段
                // 第二个区段就是要进行数据交换的区段
                String wormStr1_sec1 = wormStr1.substring(0, point1);
                String wormStr1_sec2 = wormStr1.substring(point1, point2 + 1);
                String wormStr1_sec3 = wormStr1.substring(point2 + 1, wormStr1.length());

                String wormStr2_sec1 = wormStr2.substring(0, point1);
                String wormStr2_sec2 = wormStr2.substring(point1, point2 + 1);
                String wormStr2_sec3 = wormStr2.substring(point2 + 1, wormStr2.length());
                // 判断第一个染色体新的第二个区段（第二个染色体的第二区段）
                // 所有数据内容和旧数据之间的关系
                for (int secInx = 0; secInx < wormStr2_sec2.length(); secInx++) {
                    // 新区段的字符
                    String newChar = "" + wormStr2_sec2.charAt(secInx);
                    // 旧区段的字符
                    String oldChar = "" + wormStr1_sec2.charAt(secInx);
                    // 判断新字符是否存在于其他两个区段中
                    if (wormStr1_sec1.indexOf(newChar) != -1 ||
                            wormStr1_sec3.indexOf(newChar) != -1) {
                        // 如果新字符在其他两个区段重复，必须去掉重复项
                        // 判断旧字符是否存在于新区段中
                        int oldInx = wormStr2_sec2.indexOf(oldChar);
                        if (oldInx != -1) {
                            // 旧字符存在于新区段中
                            // 必须要找到适当的替代字符
                            // oldInx: 旧字符在新区段中的位置
                            do {
                                // 取得对应旧区段中相同位置的字符
                                oldChar = "" + wormStr1_sec2.charAt(oldInx);
                                // 判断该字符是否存在于新区段
                                oldInx = wormStr2_sec2.indexOf(oldChar);
                            } while (oldInx != -1);
                            // 用旧字符取代其他区段中的重复项
                            wormStr1_sec1 = wormStr1_sec1.replace(newChar, oldChar);
                            wormStr1_sec3 = wormStr1_sec3.replace(newChar, oldChar);
                        } else {
                            // 旧字符不存在于新区段中
                            // 用旧字符取代其他区段中的重复项
                            wormStr1_sec1 = wormStr1_sec1.replace(newChar, oldChar);
                            wormStr1_sec3 = wormStr1_sec3.replace(newChar, oldChar);
                        }
                    } else {
                        // 新字符不重复于其他两个区段，因此可以忽略不计
                        // System.out.println("新字符在其他两个区段中不重复");
                    }
                }

                // 判断第一个染色体新的第二个区段（第二个染色提的第二个区段）
                // 所有数据内容和旧数据之间的关系
                for (int secInx = 0; secInx < wormStr1_sec2.length(); secInx++) {
                    // 新区段的字符
                    String newChar = "" + wormStr1_sec2.charAt(secInx);
                    // 旧区段的字符
                    String oldChar = "" + wormStr2_sec2.charAt(secInx);
                    // 判断新字符是否在于其他两个区段中
                    if (wormStr2_sec1.indexOf(newChar) != -1 ||
                            wormStr2_sec3.indexOf(newChar) != -1) {
                        // 新字符在其他两个区段中重复时，必须去掉重复项
                        // 判断旧字符是否存在于新区段中
                        int oldInx = wormStr1_sec2.indexOf(oldChar);
                        if (oldInx != -1) {
                            // 旧字符存在于新区段中
                            // 必须要找到适当的替代字符
                            // oldInx: 旧字符在新区段中的位置
                            do {
                                // 取得对应旧区段中相同位置的字符
                                oldChar = "" + wormStr1_sec2.charAt(oldInx);
                                // 判断该字符是否存在于新区段
                                oldInx = wormStr1_sec2.indexOf(oldChar);
                            } while (oldInx != -1);
                            // 用旧字符取代其他区段中的重复项
                            wormStr2_sec1 = wormStr2_sec1.replace(newChar, oldChar);
                            wormStr2_sec3 = wormStr2_sec3.replace(newChar, oldChar);
                        } else {
                            // 旧字符不存在于新区段中
                            // 用旧字符取代其他区段中的重复项
                            wormStr2_sec1 = wormStr2_sec1.replace(newChar, oldChar);
                            wormStr2_sec3 = wormStr2_sec3.replace(newChar, oldChar);
                        }
                    } else {
                        // 新字符在其他两个区段中不重复，因此可以忽略不计
                        // System.out.println("新字符在其他两个区段中不重复");
                    }
                }

                wormStr1 = wormStr1_sec1 + wormStr2_sec2 + wormStr1_sec3;
                wormStr2 = wormStr2_sec1 + wormStr1_sec2 + wormStr2_sec3;

                worm1.setGeneArray(wormStr1);
                worm2.setGeneArray(wormStr2);
            }
        }
    }

}
