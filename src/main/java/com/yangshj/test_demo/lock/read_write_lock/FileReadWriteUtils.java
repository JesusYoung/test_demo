package com.yangshj.test_demo.lock.read_write_lock;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 测试文件读写
 *
 * Create by yangshj on 4/13/22
 */
public class FileReadWriteUtils {


    public static void main(String[] args) {

        FileReadWriteUtils utils = new FileReadWriteUtils();

//        utils.readFiles("/Users/yangshijie/Teamsun/test", "java_logs.log");
        utils.readFiles2("/Users/yangshijie/Teamsun/test", "java_logs.log");

//        for (int i = 0; i < 20; i++) {
//            utils.writeFiles("/Users/yangshijie/Teamsun/test", "java_logs.log");
//        }

//        utils.readFiles("/Users/yangshijie/Teamsun/test", "java_logs.log");

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                utils.writeFiles("/Users/yangshijie/Teamsun/test", "java_logs.log");
            }
        }, 0, 3, TimeUnit.SECONDS);


        try {
            Thread.sleep(20000);

            exec.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    private void readFiles(String path, String fileName) {
        try {
            FileReader fileReader = new FileReader(path + "/" + fileName);
            String line = "";

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            while (line != null) {
                System.out.println("日志内容======> " + line);
                line = bufferedReader.readLine();
            }
//            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long startFileSize = 0;
    private void readFiles2(String path, String fileName) {

        File logFile = new File(path + "/" + fileName);

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(logFile, "r");

            ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
            exec.scheduleWithFixedDelay(() -> {
                try {
                    randomAccessFile.seek(startFileSize);
                    String tmp = "";
                    while ((tmp = randomAccessFile.readLine()) != null) {
                        System.out.println("读取日志内容==================> " + tmp);
                    }
                    startFileSize = randomAccessFile.length();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, 0, 1, TimeUnit.SECONDS);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void writeFiles(String path, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(path + "/" + fileName, true);

            fileWriter.write("xxxxxxxxxxxxxxxxxxxxxxxx: xxxxxxadgafasfasaojasj \n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
