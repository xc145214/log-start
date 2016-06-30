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
package com.xc.java7concurrent.chapter5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 异步运行任务。
 * <p>
 * 学习如何使用ForkJoinPool和ForkJoinTask类提供的异步方法来管理任务。
 * 你将实现一个程序，在一个文件夹及其子文件夹内查找确定扩展名的文件。
 * 你将实现ForkJoinTask类来处理文件夹的内容。
 * 对于文件夹里的每个子文件夹，它将以异步的方式提交一个新的任务给ForkJoinPool类。
 * 对于文件夹里的每个文件，任务将检查文件的扩展名，如果它被处理，并把它添加到结果列表。
 * </p>
 *
 * @author xiachuan at 2016/6/30 18:43。
 */

public class FolderProcessor extends RecursiveTask<List<String>> {

    private static final long serialVersionUID = 1L;

    private String path;
    private String extension;

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }


    @Override
    protected List<String> compute() {

        List<String> list = new ArrayList<>();
        List<FolderProcessor> tasks = new ArrayList<>();

        File file = new File(path);
        File content[] = file.listFiles();

        if (content != null) {
            for (int i = 0; i < content.length; i++) {
                if (content[i].isDirectory()) {
                    FolderProcessor task = new FolderProcessor(content[i].
                            getAbsolutePath(), extension);
                    task.fork();
                    tasks.add(task);

                } else {
                    if (checkFile(content[i].getName())) {
                        list.add(content[i].getAbsolutePath());
                    }
                }
            }
        }
        if (tasks.size() > 50) {
            System.out.printf("%s: %d tasks ran.\n", file.
                    getAbsolutePath(), tasks.size());
        }
        addResultsFromTasks(list, tasks);
        return list;
    }

    private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
        for (FolderProcessor item : tasks) {
            list.addAll(item.join());
        }
    }

    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FolderProcessor system = new FolderProcessor("C:\\Windows", "log");
        FolderProcessor apps = new
                FolderProcessor("C:\\Program Files", "log");
        FolderProcessor documents = new FolderProcessor("C:\\Documents And Settings", "log");

        pool.execute(system);
        pool.execute(apps);
        pool.execute(documents);

        do {
            System.out.printf("***************************************** *\n");
            System.out.printf("Main: Parallelism: %d\n", pool.
                    getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.
                    getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.
                    getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.
                    getStealCount());
            System.out.printf("***************************************** *\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while ((!system.isDone()) || (!apps.isDone()) || (!documents.isDone()));

        pool.shutdown();

        List<String> results;
        results = system.join();
        System.out.printf("System: %d files found.\n", results.size());
        results = apps.join();
        System.out.printf("Apps: %d files found.\n", results.size());
        results = documents.join();
        System.out.printf("Documents: %d files found.\n", results.size());


    }
}
