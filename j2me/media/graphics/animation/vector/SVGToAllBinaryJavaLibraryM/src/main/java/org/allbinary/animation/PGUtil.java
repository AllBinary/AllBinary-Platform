package org.allbinary.animation;


public class PGUtil {

    private static final PGUtil instance = new PGUtil();

    /**
     * @return the instance
     */
    public static PGUtil getInstance() {
        return instance;
    }
    
    //De Casteljau's algorithm
    // Interpolates a cubic bezier curve and adds linear points to the array
    public void flattenCubicBezier(int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3,
        int[] outX, int[] outY, int[] count, int maxPoints) {

        final FixedPoint fixedPoint = FixedPoint.getInstance();

        int steps = 10; // Increase steps for smoother curves
        for (int i = 1; i <= steps; i++) {
            int t = fixedPoint.div(fixedPoint.toFixed((double) i), fixedPoint.toFixed(steps));
            int mt = fixedPoint.ONE - t;

            int mt3 = fixedPoint.mul(fixedPoint.mul(mt, mt), mt);
            int mt2t3 = 3 * fixedPoint.mul(fixedPoint.mul(mt, mt), t);
            int mtt23 = 3 * fixedPoint.mul(fixedPoint.mul(mt, t), t);
            int t3 = fixedPoint.mul(fixedPoint.mul(t, t), t);

            int x = fixedPoint.mul(mt3, x0) + fixedPoint.mul(mt2t3, x1) + fixedPoint.mul(mtt23, x2) + fixedPoint.mul(t3, x3);
            int y = fixedPoint.mul(mt3, y0) + fixedPoint.mul(mt2t3, y1) + fixedPoint.mul(mtt23, y2) + fixedPoint.mul(t3, y3);

            if (count[0] < maxPoints) {
                outX[count[0]] = x;
                outY[count[0]] = y;
                count[0]++;
            }
        }
    }
 
}
