package com.yangshj.test_demo.algorithm.snowflake;

/**
 * 雪花算法工具
 *
 *   结构：
 *      - 1bit符号位
 *      - 41bit时间戳位
 *      - 10bit工作进程位
 *          - 5位datacenterId（数据中心，机房）
 *          - 5位workerID（机器码）
 *      - 12bit序列号位
 *
 * Create by yangshj on 3/14/22
 */
public class SnowflakeUtil {

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 124646745432L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; // 序列号占用的位数
    private final static long MACHINE_BIT = 5; // 机器标识占用的位数
    private final static long DATACENTER_BIT = 5; // 数据中心占用的位数

    /**
     * 每一部分的最大值（位移前，各自单独部分计算）
     */
    private final static long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE_NUM = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT; // 机器码左移位数，在序列号左侧部分
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT; // 数据中心码左移，在序列号和机器码左侧；
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;


    /**
     * 数据中心
     */
    private long dataCenterId;
    /**
     * 机器标识
     */
    private long machineId;

    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private long lastStmp = -1L;

    public SnowflakeUtil(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATACENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 生成下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewStmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
        if (currStmp == lastStmp) {
            // 相同毫秒内，序列号自增
            this.sequence = (sequence + 1) & MAX_SEQUENCE_NUM;
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            // 不同毫秒内，序列号置为0
            this.sequence = 0L;
        }
        this.lastStmp = currStmp;


//        System.out.println("currStmp: " + currStmp);
//        System.out.println("currStmp - START_STMP: " + (currStmp - START_STMP));
//        System.out.println("timestmp: " + ((currStmp - START_STMP) << TIMESTMP_LEFT));
//        System.out.println("dataCenterId: " + (dataCenterId << DATACENTER_LEFT));
//        System.out.println("machineId: " + (machineId << MACHINE_LEFT));
//        System.out.println("sequence: " + sequence);



        return (currStmp - START_STMP) << TIMESTMP_LEFT  // 时间戳部分
                | dataCenterId << DATACENTER_LEFT        // 数据中心部分
                | machineId << MACHINE_LEFT              // 机器标识部分
                | sequence;                              // 序列号部分
    }

    private long getNextMill() {
        long mill = getNewStmp();
        while (mill <= lastStmp) {
            mill = getNewStmp();
        }
        return mill;
    }

    private long getNewStmp() {
        return System.currentTimeMillis();
    }


    public static void main(String[] args) {
//        System.out.println(Long.MAX_VALUE);
        SnowflakeUtil util = new SnowflakeUtil(15, 31);
        for (int i = 0; i < 1; i++) {
            new Thread(() -> System.out.println(util.nextId())).start();
        }


//        System.out.println("aa: "+ (1L << 60));
//        System.out.println("bb: "+ (1L << 41));


//        long a = -1L ^ (-1L << 12);
//        System.out.println("a: " + a);
//        System.out.println("c: " + (1 & a));
//        System.out.println("d: " + (2 & a));
//
//        System.out.println("ddd: " + (4096 & a));
    }
}
