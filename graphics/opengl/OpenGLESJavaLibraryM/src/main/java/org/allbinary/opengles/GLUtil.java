/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
 */
package org.allbinary.opengles;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.allbinary.graphics.opengles.OpenGLCapabilities;

/**
 *
 * @author User
 */
public class GLUtil
{

    private static final GLUtil instance = new GLUtil();

    /**
     * @return the instance
     */
    public static GLUtil getInstance() {
        return instance;
    }

    private final int BYTES_PER_FLOAT = 4;
        
    public FloatBuffer makeFloatBuffer3(float $a, float $b, float $c) {
        final ByteBuffer b = ByteBuffer.allocateDirect(3 * BYTES_PER_FLOAT);
        b.order(ByteOrder.nativeOrder());
        final FloatBuffer buffer = b.asFloatBuffer();
        buffer.put($a);
        buffer.put($b);
        buffer.put($c);
        this.position(buffer, 0);
        return buffer;
    }

    public FloatBuffer makeFloatBuffer4(float $a, float $b, float $c, float $d) {
        final ByteBuffer b = ByteBuffer.allocateDirect(4 * BYTES_PER_FLOAT);
        b.order(ByteOrder.nativeOrder());
        final FloatBuffer buffer = b.asFloatBuffer();
        buffer.put($a);
        buffer.put($b);
        buffer.put($c);
        buffer.put($d);
        this.position(buffer, 0);
        return buffer;
    }

    public FloatBuffer makeFloatBuffer(final ByteBuffer byteBuffer) {
        final ByteBuffer b = ByteBuffer.allocateDirect(byteBuffer.limit() * BYTES_PER_FLOAT);
        b.order(ByteOrder.nativeOrder());
        final FloatBuffer buffer = b.asFloatBuffer();
        int unsigned;
        float v;
        while(byteBuffer.hasRemaining()) {
            unsigned = byteBuffer.get() & 0xFF;
            v = ((float) unsigned) / 255f;
            buffer.put(v);
//            if(v == 1.0f) {
//                buffer.put(v);
//            } else {
//                buffer.put(1.0f - v);
//            }
        }
        this.position(buffer, 0);
        return buffer;
    }    
    
    //Hack for Android Studio using internal JDK 11 or newer in builds.
    public Buffer position(final Buffer buffer, final int newPosition) {
        return buffer.position(newPosition);
    }

    //When Shaders are enabled this should be done on the GPU
    public void rotateUVs(final float[] uvs, final float angleDegrees, final float pivotU, final float pivotV) {
        final double angleRad = Math.toRadians(angleDegrees);
        final double cosA = Math.cos(angleRad);
        final double sinA = Math.sin(angleRad);

        for (int i = 0; i < uvs.length; i += 2) {
            float u = uvs[i];
            float v = uvs[i + 1];
            float du = u - pivotU;
            float dv = v - pivotV;
            float ru = (float) (du * cosA - dv * sinA) + pivotU;
            float rv = (float) (du * sinA + dv * cosA) + pivotV;
            uvs[i] = ru;
            uvs[i + 1] = rv;
        }
    }

}
