/**
 * **********************************************************************
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * <p/>
 * COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 * ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * ***********************************************************************
 */
package com.xc.java7concurrent.chapter3;

import java.util.concurrent.Phaser;

/**
 * 控制并发阶段性任务的改变。
 * <p>
 * 学习如何控制phaser的 phase的改变，通过实现自定义版本的 Phaser类并覆盖 onAdvance() 方法来执行一些每个phase 都会改变的行动。
 * 你将要实现一个模拟测验，有些学生要完成他们的练习。全部的学生都必须完成同一个练习才能继续下一个练习。
 * </p>
 *
 * @author xiachuan at 2016/6/22 16:00。
 */

public class MyPhaser extends Phaser {

    /**
     * 这个练习模拟了有3个测验的真实测试。全部的学生必须都完成同一个测试才能开始下一个测试。
     * 为了实现这个必须使用同步，我们使用了Phaser类，但是你实现了你自己的phaser通过扩展原来的类，并覆盖onAdvance() 方法.
     *
     * 在阶段改变之前和在唤醒 arriveAndAwaitAdvance() 方法中休眠的全部线程们之前，此方法被 phaser 调用。
     * 这个方法接收当前阶段数作为参数，0是第一个phase ，还有注册的参与者数。最有用的参数是actual phase。
     * 如果你要基于不同的当前阶段执行不同的操作，那么你必须使用选择性结构（if/else 或 switch）来选择你想执行的操作。
     * 例子里，我们使用了 switch 结构来为每个phase的改变选择不同的方法。onAdvance() 方法返回 Boolean 值表明 phaser 终结与否。
     * 如果返回 false 值，表示它还没有终结，那么线程将继续执行其他phases。
     * 如果phaser 返回真值，那么phaser将叫醒全部待定的线程们，并且转移phaser到terminated 状态，所以之后的任何对phaser的方法的调用都会被立刻返回，还有isTerminated() 方法将返回真值。
     * 在核心类，当你创建 MyPhaser 对象，在phaser中你不用表示参与者的数量。你为每个 Student 对象调用了 register() 方法创建了phaser的参与者的注册。
     * 这个调用不会在Student 对象或者执行它的线程与phaser之间这个建立任何关系。
     * 说真的，phaser的参与者数就是个数字而已。phaser与参与者之间没有任何关系。
     *
     * @param phase
     * @param registeredParties
     * @return
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }


    public boolean studentsArrived() {
        System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
        System.out.printf("Phaser: We have %d students.\n", getRegisteredParties());
        return false;
    }

    public boolean finishFirstExercise() {
        System.out.printf("Phaser: All the students have finished the first exercise.\n");
        System.out.printf("Phaser: It's time for the second one.\n");
        return false;
    }


    public boolean finishSecondExercise() {
        System.out.printf("Phaser: All the students have finished the second exercise.\n");
        System.out.printf("Phaser: It's time for the third one.\n");
        return false;
    }


    public boolean finishExam() {
        System.out.printf("Phaser: All the students have finished the exam.\n");
        System.out.printf("Phaser: Thank you for your time.\n");
        return true;
    }
}

