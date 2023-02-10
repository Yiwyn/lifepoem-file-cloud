package fun.lifepoem.core.utils;

/**
 * @author Yiwyn
 * @create 2023/2/10 21:47
 */
public class SnowFlakeUtils {
    private static SnowFlakeUtils instance;

    public static SnowFlakeUtils Instance() {
        if (instance == null) {
            instance = new SnowFlakeUtils(1L);
        }
        return instance;
    }

    /**
     * 组成部分
     */
    //最高符号位

    //时间戳

    //workid
    private long wordId;
    //序列号
    private long sequence = 0L;


    /**
     * 占用字节
     */
    //时间戳 41bit
    private final long timestampBit = 41L;
    //workid 10bit 可以拆分为两个5bit 机房id和机器id
    private final long workIdBit = 10L;
    //序列id 12bit
    private final long sequenceBit = 12L;


    /**
     * 位移的位数
     */

    //workId 左移12位 即是 序列id的位数
    private final long wordIdShift = sequenceBit;

    //时间id需要左移 sequenceId bit长度+ workId bit长度
    private final long timestampShift = sequenceBit + workIdBit;


    /**
     * 聚合信息
     */
    //支持最大的workId 10位 1023 --> 2^10 -1
    private final long maxWorkId = -1 ^ (-1 << workIdBit);

    //支持最大的序列id
    private final long maxSequence = ~(-1 << sequenceBit);

    //上一次生成的时间
    private long lastTimestamp = -1L;


    public SnowFlakeUtils(long wordId) {
        if (wordId < 0 || wordId > maxWorkId) {
            throw new IllegalArgumentException("wordId 错误");
        }
        this.wordId = wordId;
    }

    /**
     * 获取当前时间线戳
     *
     * @return
     */
    private long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public synchronized long getSnowId() {
        long currenTimestamp = getCurrentTime();
        if (currenTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                currenTimestamp = getNextMillis();
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = currenTimestamp;

        /**
         * 返回唯一id
         */
        return (currenTimestamp << timestampShift) |
                (wordId << wordIdShift) |
                (sequence);
    }

    private long getNextMillis() {
        long currentTimestamp = getCurrentTime();
        while (currentTimestamp <= lastTimestamp) {
            currentTimestamp = getCurrentTime();
        }
        return currentTimestamp;
    }

}
