package org.allbinary.animation;

public class PolygonRasterizer {
    
    private static final PolygonRasterizer instance = new PolygonRasterizer();

    /**
     * @return the instance
     */
    public static PolygonRasterizer getInstance() {
        return instance;
    }
    
    public int[] getPixelBuffer(final String svgPath, final int[] pathX, final int[] pathY, final int[] pixelBuffer, final int width, final int height) { 
        
        final int pointCount = PathParser.parsePath(svgPath, pathX, pathY, pathX.length);

        for (int i = 0; i < pixelBuffer.length; i++) {
            pixelBuffer[i] = 0xFFFFFFFF; 
        }

        PolygonRasterizer.getInstance().fillPolygon(pathX, pathY, pointCount, pixelBuffer, width, height, 0xFFFF00FF);
        
        return pixelBuffer;
    }
    
    public void fillPolygon(final int[] polyX, final int[] polyY, final int numPoints, final int[] buffer, final int width, final int height, final int colorRGB) {
        if (numPoints < 3) return;

        final FixedPoint fixedPoint = FixedPoint.getInstance();

        // Convert coordinates from fixed-point back to integers
        int[] x = new int[numPoints];
        int[] y = new int[numPoints];
        int minY = height, maxY = 0;
        
        for (int i = 0; i < numPoints; i++) {
            x[i] = fixedPoint.toInt(polyX[i]);
            y[i] = fixedPoint.toInt(polyY[i]);
            if (y[i] < minY) minY = y[i];
            if (y[i] > maxY) maxY = y[i];
        }
        
        // Bounds checking against target canvas size
        if (minY < 0) minY = 0;
        if (maxY >= height) maxY = height - 1;

        int[] scanlineX = new int[numPoints]; // storage for X intersections

        // Loop through every scanline row
        for (int scanY = minY; scanY <= maxY; scanY++) {
            int intersectCount = 0;

            // Find intersections with all polygon edges
            for (int i = 0; i < numPoints; i++) {
                int next = (i + 1) % numPoints;
                int x1 = x[i], y1 = y[i];
                int x2 = x[next], y2 = y[next];

                if ((y1 < scanY && y2 >= scanY) || (y2 < scanY && y1 >= scanY)) {
                    // Avoid division by zero
                    if (y2 != y1) {
                        int interX = x1 + (scanY - y1) * (x2 - x1) / (y2 - y1);
                        scanlineX[intersectCount++] = interX;
                    }
                }
            }

            // Simple bubble sort for the X intersections
            for (int i = 0; i < intersectCount - 1; i++) {
                for (int j = i + 1; j < intersectCount; j++) {
                    if (scanlineX[i] > scanlineX[j]) {
                        int temp = scanlineX[i];
                        scanlineX[i] = scanlineX[j];
                        scanlineX[j] = temp;
                    }
                }
            }

            // Fill spans between pairs of intersections (Even-Odd Fill)
            for (int i = 0; i < intersectCount; i += 2) {
                if (i + 1 >= intersectCount) break;
                int startX = scanlineX[i];
                int endX = scanlineX[i + 1];

                if (startX < 0) startX = 0;
                if (endX >= width) endX = width - 1;

                int rowOffset = scanY * width;
                for (int pixelX = startX; pixelX <= endX; pixelX++) {
                    buffer[rowOffset + pixelX] = colorRGB;
                }
            }
        }
    }

    public void main(final String[] args) {
        final int width = 100;
        final int height = 100;
        final int[] pathX = new int[200];
        final int[] pathY = new int[200];
        final int[] pixelBuffer = new int[width * height];

        final String svgPath = "M 32.139812,81.554575 14.450128,74.294372 V 50.000034 25.705697 l 17.73231,-7.268329 C 41.935209,14.439788 49.953112,11.168898 50,11.168723 c 0.04689,-1.74e-4 8.064791,3.270238 17.817562,7.267583 l 17.73231,7.267899 V 50 74.295795 l -17.73231,7.2679 c -9.752771,3.997344 -17.789855,7.264116 -17.860188,7.259492 -0.07033,-0.0046 -8.088235,-3.275499 -17.817562,-7.268612 z";
        
        PolygonRasterizer.getInstance().getPixelBuffer(svgPath, pathX, pathY, pixelBuffer, width, height);
    }

}
