package com.yangshj.test_demo.ssh;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.*;

/**
 * Create by yangshijie on 3/18/22
 */
public class JschLinuxUtil {





    private List<String> getCpuInfo(String command) {
        Session session = null;
        ChannelExec channel = null;

        try {
            session = new JSch().getSession("root", "172.31.16.47", 22);
            session.setPassword("passwd");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }



            String responseString = new String(responseStream.toByteArray());

            System.out.println(responseString);

            List<String> strList = new ArrayList<>();
            String[] str = responseString.split("\n");
            for (int i = 0; i < str.length; i++) {
                if (i > 2) {
                    strList.add(str[i].trim());
                }
            }

            return strList;
        } catch (JSchException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
        return new ArrayList<>();
    }


    public static void main(String[] args) {

        JschLinuxUtil util = new JschLinuxUtil();

//        List<String> strings = util.getCpuInfo("free -m");
//        List<String> strings = util.getCpuInfo("uptime");
//        List<String> strings = util.getCpuInfo("df -h");
        List<String> strings = util.getCpuInfo("lscpu");

        if (strings.size() > 0) {
            for (String str : strings) {
                System.out.println(str);
            }
        } else {
            System.out.println("000000");
        }
    }

}
