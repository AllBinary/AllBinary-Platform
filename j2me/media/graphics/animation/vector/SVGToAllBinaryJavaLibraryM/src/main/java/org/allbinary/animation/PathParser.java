package org.allbinary.animation;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import org.allbinary.logic.StdUtil;

public class PathParser {
    // Basic space/comma string tokenizer
    private static String[] tokenize(String d) {
        final BasicArrayList tokens = new BasicArrayListD();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < d.length(); i++) {
            char c = d.charAt(i);
            if (c == ' ' || c == ',' || c == '\t' || c == '\n' || c == '\r') {
                if (sb.length() > 0) { tokens.add(sb.toString()); sb.setLength(0); }
            } else if (c == 'M' || c == 'm' || c == 'L' || c == 'l' || c == 'V' || c == 'v' || 
                       c == 'C' || c == 'c' || c == 'Z' || c == 'z') {
                if (sb.length() > 0) { tokens.add(sb.toString()); sb.setLength(0); }
                tokens.add(String.valueOf(c));
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) tokens.add(sb.toString());
        final String[] res = new String[tokens.size()];
        tokens.toArrayType(res);
        return res;
    }

    public static int parsePath(String d, int[] outX, int[] outY, int maxPoints) {
        String[] tokens = tokenize(d);
        int[] count = new int[]{0};
        
        final FixedPoint fixedPoint = FixedPoint.getInstance();
        
        int curX = 0, curY = 0;
        int startX = 0, startY = 0;
        int idx = 0;
        
        while (idx < tokens.length) {
            String tok = tokens[idx++];
            char cmd = tok.charAt(0);
            
            if (cmd == 'M' || cmd == 'm') {
                int x = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                int y = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                if (cmd == 'm') { x += curX; y += curY; }
                curX = x; curY = y;
                startX = x; startY = y;
                outX[count[0]] = curX; outY[count[0]] = curY; count[0]++;
            } 
            else if (cmd == 'L' || cmd == 'l') {
                int x = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                int y = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                if (cmd == 'l') { x += curX; y += curY; }
                curX = x; curY = y;
                outX[count[0]] = curX; outY[count[0]] = curY; count[0]++;
            } 
            else if (cmd == 'V' || cmd == 'v') {
                int y = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                if (cmd == 'v') { y += curY; }
                curY = y;
                outX[count[0]] = curX; outY[count[0]] = curY; count[0]++;
            } 
            else if (cmd == 'C' || cmd == 'c') {
                int x1 = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                int y1 = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                int x2 = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                int y2 = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                int x3 = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                int y3 = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                if (cmd == 'c') {
                    x1 += curX; y1 += curY; x2 += curX; y2 += curY; x3 += curX; y3 += curY;
                }
                PGUtil.getInstance().flattenCubicBezier(curX, curY, x1, y1, x2, y2, x3, y3, outX, outY, count, maxPoints);
                curX = x3; curY = y3;
            } 
            else if (cmd == 'Z' || cmd == 'z') {
                curX = startX; curY = startY;
                outX[count[0]] = curX; outY[count[0]] = curY; count[0]++;
            }
            // Implicit next points handler (if next token is a number instead of a command)
            while (idx < tokens.length && !isCommand(tokens[idx])) {
                // If it was lineTo/MoveTo parameters following without explicit token
                if (cmd == 'M' || cmd == 'm' || cmd == 'L' || cmd == 'l') {
                    int x = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                    int y = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                    if (cmd == 'm' || cmd == 'l') { x += curX; y += curY; }
                    curX = x; curY = y;
                    outX[count[0]] = curX; outY[count[0]] = curY; count[0]++;
                } else if (cmd == 'V' || cmd == 'v') {
                    int y = fixedPoint.toFixed(Double.parseDouble(tokens[idx++]));
                    if (cmd == 'v') { y += curY; }
                    curY = y;
                    outX[count[0]] = curX; outY[count[0]] = curY; count[0]++;
                }
            }
        }
        return count[0];
    }
    
    private static boolean isCommand(String tok) {
        char c = tok.charAt(0);
        return "MmLlVvCcZz".indexOf(c) >= 0;
    }
}
