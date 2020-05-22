package study.bitmap;

/**
 * @author zyf
 */
public class BitMap {
    private byte[] flags;

    public BitMap(int defaultSize) {
        if (defaultSize > 8) {
            defaultSize = (int) Math.ceil(defaultSize >> 3);
        }
        this.flags = new byte[defaultSize];
    }

    public boolean exist(int value) {
        //获取下标 即除以8
        int index = value >> 3;
        //获取位数(求余) 相当于value % 8
        int bit = value & 0x07;
        return (flags[index] & (1 << bit)) != 0;
    }


    public void set(int value) {
        //获取下标 即除以8
        int index = value >> 3;
        //获取位数(求余) 相当于value % 8
        int bit = value & 0x07;
        flags[index] |= 1 << bit;
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(1024);
        bitMap.set(1);
        bitMap.set(3);
        bitMap.set(23);
        bitMap.set(41);

        System.out.println(bitMap.exist(44));
    }
}
