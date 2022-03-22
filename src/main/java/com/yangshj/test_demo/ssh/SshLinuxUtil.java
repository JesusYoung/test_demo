package com.yangshj.test_demo.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * Create by yangshijie on 3/18/22
 */
public class SshLinuxUtil {


    public static void main(String[] args) {
        SshLinuxUtil util = new SshLinuxUtil();

        // 内存利用率
//        double a = util.getMemoryRate();
//        System.out.println("内存利用率: " + a + "%");


        // cpu利用率
//        long[] r1 = util.getCpuRate();
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        long[] r2 = util.getCpuRate();
//
//        long allTime = r2[0] - r1[0];
//        long idle = r2[1] - r1[1];
//        long used = allTime - idle;
//        System.out.println("allTime: " + allTime);
//        System.out.println("idle: " + idle);
//        double f1 = new BigDecimal((float) used / allTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100;
//        System.out.println("cpu利用率： " + f1 + "%");
    }

    private long[] getCpuRate() {
        String shell = String.format("%s", "cat /proc/stat");

        List<String> result = execCmd(shell);
        for (String s : result) {
            System.out.println("result===========> " + s);
        }
        String[] cpu = result.get(0).split("\\s+");
        List<String> cpuList1 = Arrays.asList(cpu);
        List<String> cpuList = new ArrayList<>(cpuList1);
        for (String a : cpuList) {
            System.out.println("=========> " + a);
        }
        cpuList.remove(0);

        long allTime = 0L;
        for (String s : cpuList) {
            allTime += Long.parseLong(s);
        }
        System.out.println(String.format("CPU使用总时间 = %s;idle=%s", allTime, cpuList.get(3)));

        long[] r = new long[2];
        r[0] = allTime;
        r[1] = Long.parseLong(cpuList.get(3));
        return r;
    }

    private double getMemoryRate() {
        String shell = String.format("%s", "free -b");

        List<String> result = execCmd(shell);
        for (String s : result) {
            System.out.println("result===========> " + s);
        }

        String[] aa = result.get(1).split("\\s+");
        Integer usedMemory = Integer.valueOf(aa[2]);
        Integer allMemory = Integer.valueOf(aa[1]);

        double f1 = new BigDecimal((float) usedMemory / allMemory).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100;
        System.out.println("memRate: " + f1);
        return f1;
    }


    private List<String> execCmd(String command) {
        Connection connection = new Connection("172.31.16.47", 22);
        Session ssh = createConnection(connection);
        if (ssh == null) {
            System.out.println("ssh is null");
            return new ArrayList<>();
        }

        List<String> resultList = new ArrayList<>();

        try {
            ssh.execCommand(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream is = new StreamGobbler(ssh.getStdout());
        BufferedReader brs = new BufferedReader(new InputStreamReader(is));
        while (true) {
            String line = null;
            try {
                line = brs.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            System.out.println("out======>  " + line);
            resultList.add(line);
        }
        closeSsh(ssh);
        return resultList;
    }

    private Session createConnection(Connection connection) {
        try {
            connection.connect();
            connection.authenticateWithPassword("root", "passwd");
            return connection.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void closeSsh(Session session) {
        if (session != null) {
            session.close();
        }
    }
}
