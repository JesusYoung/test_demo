package com.yangshj.test_demo.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
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

        double a = util.getMemoryRate();

        System.out.println("内存利用率: " + a + "%");
    }

    private double getCpuRate() {
        String shell = String.format("%s", "cat /proc/stat");

        List<String> result = execCmd(shell);
        for (String s : result) {
            System.out.println("result===========> " + s);
        }

        String[] aa = result.get(0).split("\\s+");

        return 0;
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
