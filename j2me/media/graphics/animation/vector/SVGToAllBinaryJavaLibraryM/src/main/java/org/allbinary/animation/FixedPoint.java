package org.allbinary.animation;


public class FixedPoint {

    private static final FixedPoint instance = new FixedPoint();

    /**
     * @return the instance
     */
    public static FixedPoint getInstance() {
        return instance;
    }
    
    public final int ONE = 1 << 16;

    public int toFixed(double val) {
        return (int) (val * 65536.0);
    }

    public int toFixed(float val) {
        return (int) (val * 65536.0f);
    }

    public int mul(int a, int b) {
        return (int) (((long) a * b) >> 16);
    }

    public int div(int a, int b) {
        return (int) ((((long) a) << 16) / b);
    }

    public int toInt(int fixed) {
        return fixed >> 16;
    }
}
