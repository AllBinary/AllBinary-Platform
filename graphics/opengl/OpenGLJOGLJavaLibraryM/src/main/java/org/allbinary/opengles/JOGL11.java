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

/**
 *
 * @author User
 */
//JOGLEToKhronosGL11
public class JOGL11 extends JOGL10 implements javax.microedition.khronos.opengles.GL11
{

    //private final StringBuilder stringBuilder = new StringBuilder();

//    private final String RED = "red: ";
//    private final String GREEN = " green: ";
//    private final String BLUE = " blue: ";
//    private final String ALPHA = " alpha: ";
//    private final String PNAME = "pname: ";
//    private final String _PNAME = " pname: ";
//    private final String PARAM = " param: ";
//    private final String PARAMS = " params: ";
//    private final String LIGHT = "light: ";
//    private final String FACE = "face: ";
//    private final String TARGET = "target: ";

    private final com.jogamp.opengl.GL2 gl11;

    public JOGL11(com.jogamp.opengl.GL gl)
    {
        super(gl);

        //PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, CommonStrings.getInstance().CONSTRUCTOR);
        this.gl11 = getJOGLGL();
    }

//    public void glGetPointerv(int pname, java.nio.Buffer[] params)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetPointerv");
//        this.gl11.glGetPointerv(pname, params);
//    }

    public void glBindBuffer(int target, int buffer)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" buffer: ").append(buffer).toString(), this, "GL11.glBindBuffer");
        this.gl11.glBindBuffer(target, buffer);
    }

    public void glBufferData(int target, int size, java.nio.Buffer data, int usage)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" size: ").append(size).append(" data: ").append(data).append(" usage: ").append(usage).toString(), this, "GL11.glBufferData");
        this.gl11.glBufferData(target, size, data, usage);
    }

    public void glBufferSubData(int target, int offset, int size, java.nio.Buffer data)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" size: ").append(size).append(" data: ").append(data).toString(), this, "GL11.glBufferSubData");
        this.gl11.glBufferSubData(target, offset, size, data);
    }

    public void glClipPlanef(int plane, float[] equation, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append("plane: ").append(plane).append(" equation: ").append(equation.length).toString(), this, "GL11.glClipPlanef");
        this.gl11.glClipPlanef(plane, equation, offset);
    }

    public void glClipPlanef(int plane, java.nio.FloatBuffer equation)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append("plane: ").append(plane).append(" equation: ").append(equation).toString(), this, "GL11.glClipPlanef");
        this.gl11.glClipPlanef(plane, equation);
    }

//    public void glClipPlanex(int plane, int[] equation, int offset)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append("plane: ").append(plane).append(" equation: ").append(equation).toString(), this, "GL11.glClipPlanex");
//        this.gl11.glClipPlanex(plane, equation, offset);
//    }

//    public void glClipPlanex(int plane, java.nio.IntBuffer equation)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append("plane: ").append(plane).append(" equation: ").append(equation).toString(), this, "GL11.glClipPlanex");
//        this.gl11.glClipPlanex(plane, equation);
//    }

    public void glColor4ub(byte red, byte green, byte blue, byte alpha)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(RED).append(red).append(GREEN).append(green).append(BLUE).append(blue).append(ALPHA).append(alpha).toString(), this, "GL11.glColor4ub");
        this.gl11.glColor4ub(red, green, blue, alpha);
    }

    public void glColorPointer(int size, int type, int stride, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(" size: ").append(size).append(" type: ").append(type).toString(), this, "GL11.glColorPointer");
        this.gl11.glColorPointer(size, type, stride, offset);
    }

    public void glDeleteBuffers(int n, int[] buffers, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(" n: ").append(n).append(" buffers: ").append(buffers.length).toString(), this, "GL11.glDeleteBuffers");
        this.gl11.glDeleteBuffers(n, buffers, offset);
    }

    public void glDeleteBuffers(int n, java.nio.IntBuffer buffers)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(" n: ").append(n).append(" buffers: ").append(buffers).toString(), this, "GL11.glDeleteBuffers");
        this.gl11.glDeleteBuffers(n, buffers);
    }

    public void glDrawElements(int mode, int count, int type, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(" mode: ").append(mode).append(" count: ").append(count).append(" type: ").append(type).toString(), this, "GL11.glDrawElements");
        this.gl11.glDrawElements(mode, count, type, offset);
    }

    public void glGenBuffers(int n, int[] buffers, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(" n: ").append(n).append(" buffers: ").append(buffers.length).toString(), this, "GL11.glGenBuffers");
        this.gl11.glGenBuffers(n, buffers, offset);
    }

    public void glGenBuffers(int n, java.nio.IntBuffer buffers)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(" n: ").append(n).append(" buffers: ").append(buffers).toString(), this, "GL11.glGenBuffers");
        this.gl11.glGenBuffers(n, buffers);
    }

//    public void glGetBooleanv(int pname, boolean[] params, int offset)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "GL11.glGetBooleanv");
//        this.gl11.glGetBooleanv(pname, params, offset);
//    }

//    public void glGetBooleanv(int pname, java.nio.IntBuffer params)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetBooleanv");
//        this.gl11.glGetBooleanv(pname, params);
//    }

    public void glGetBufferParameteriv(int target, int pname, int[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "GL11.glGetBufferParameteriv");
        this.gl11.glGetBufferParameteriv(target, pname, params, offset);
    }

    public void glGetBufferParameteriv(int target, int pname, java.nio.IntBuffer params)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetBufferParameteriv");
        this.gl11.glGetBufferParameteriv(target, pname, params);
    }

    public void glGetClipPlanef(int pname, float[] eqn, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(" eqn: ").append(eqn.length).toString(), this, "GL11.glGetClipPlanef");
        this.gl11.glGetClipPlanef(pname, eqn, offset);
    }

    public void glGetClipPlanef(int pname, java.nio.FloatBuffer eqn)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(" eqn: ").append(eqn).toString(), this, "GL11.glGetClipPlanef");
        this.gl11.glGetClipPlanef(pname, eqn);
    }

//    public void glGetClipPlanex(int pname, int[] eqn, int offset)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(" eqn: ").append(eqn).toString(), this, "GL11.glGetClipPlanex");
//        this.gl11.glGetClipPlanex(pname, eqn, offset);
//    }

//    public void glGetClipPlanex(int pname, java.nio.IntBuffer eqn)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(" eqn: ").append(eqn).toString(), this, "GL11.glGetClipPlanex");
//        this.gl11.glGetClipPlanex(pname, eqn);
//    }

//    public void glGetFixedv(int pname, int[] params, int offset)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetFixedv");
//        this.gl11.glGetFixedv(pname, params, offset);
//    }

//    public void glGetFixedv(int pname, java.nio.IntBuffer params)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetFixedv");
//        this.gl11.glGetFixedv(pname, params);
//    }

    public void glGetFloatv(int pname, float[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetFloatv");
        this.gl11.glGetFloatv(pname, params, offset);
    }

    public void glGetFloatv(int pname, java.nio.FloatBuffer params)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetFloatv");
        this.gl11.glGetFloatv(pname, params);
    }

    public void glGetLightfv(int light, int pname, float[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetLightfv");
        this.gl11.glGetLightfv(light, pname, params, offset);
    }

    public void glGetLightfv(int light, int pname, java.nio.FloatBuffer params)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetLightfv");
        this.gl11.glGetLightfv(light, pname, params);
    }

//    public void glGetLightxv(int light, int pname, int[] params, int offset)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetLightxv");
//        this.gl11.glGetLightxv(light, pname, params, offset);
//    }

//    public void glGetLightxv(int light, int pname, java.nio.IntBuffer params)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetLightxv");
//        this.gl11.glGetLightxv(light, pname, params);
//    }

    public void glGetMaterialfv(int face, int pname, float[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "GL11.glGetMaterialfv");
        this.gl11.glGetMaterialfv(face, pname, params, offset);
    }

    public void glGetMaterialfv(int face, int pname, java.nio.FloatBuffer params)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetMaterialfv");
        this.gl11.glGetMaterialfv(face, pname, params);
    }

//    public void glGetMaterialxv(int face, int pname, int[] params, int offset)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetMaterialxv");
//        this.gl11.glGetMaterialxv(face, pname, params, offset);
//    }

//    public void glGetMaterialxv(int face, int pname, java.nio.IntBuffer params)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetMaterialxv");
//        this.gl11.glGetMaterialxv(face, pname, params);
//    }

    public void glGetTexEnviv(int env, int pname, int[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append("env: ").append(env).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetTexEnviv");
        this.gl11.glGetTexEnviv(env, pname, params, offset);
    }

    public void glGetTexEnviv(int env, int pname, java.nio.IntBuffer params)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append("env: ").append(env).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetTexEnviv");
        this.gl11.glGetTexEnviv(env, pname, params);
    }

//    public void glGetTexEnvxv(int env, int pname, int[] params, int offset)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append("env: ").append(env).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetTexEnvxv");
//        this.gl11.glGetTexEnvxv(env, pname, params, offset);
//    }

//    public void glGetTexEnvxv(int env, int pname, java.nio.IntBuffer params)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append("env: ").append(env).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetTexEnvxv");
//        this.gl11.glGetTexEnvxv(env, pname, params);
//    }

    public void glGetTexParameterfv(int target, int pname, float[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "GL11.glGetTexParameterfv");
        this.gl11.glGetTexParameterfv(target, pname, params, offset);
    }

    public void glGetTexParameterfv(int target, int pname, java.nio.FloatBuffer params)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetTexParameterfv");
        this.gl11.glGetTexParameterfv(target, pname, params);
    }

    public void glGetTexParameteriv(int target, int pname, int[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetTexParameteriv");
        this.gl11.glGetTexParameteriv(target, pname, params, offset);
    }

    public void glGetTexParameteriv(int target, int pname, java.nio.IntBuffer params)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetTexParameteriv");
        this.gl11.glGetTexParameteriv(target, pname, params);
    }

//    public void glGetTexParameterxv(int target, int pname, int[] params, int offset)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetTexParameterxv");
//        this.gl11.glGetTexParameterxv(target, pname, params, offset);
//    }

//    public void glGetTexParameterxv(int target, int pname, java.nio.IntBuffer params)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glGetTexParameterxv");
//        this.gl11.glGetTexParameterxv(target, pname, params);
//    }

    public boolean glIsBuffer(int buffer)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append("buffer: ").append(buffer).toString(), this, "GL11.glIsBuffer");
        return this.gl11.glIsBuffer(buffer);
    }

    public boolean glIsEnabled(int cap)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append("cap: ").append(cap).toString(), this, "GL11.glIsEnabled");
        return this.gl11.glIsEnabled(cap);
    }

    public boolean glIsTexture(int texture)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append("texture: ").append(texture).toString(), this, "GL11.texture");
        return this.gl11.glIsTexture(texture);
    }

    public void glNormalPointer(int type, int stride, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append("type: ").append(type).append(" stride: ").append(stride).toString(), this, "GL11.glNormalPointer");
        this.gl11.glNormalPointer(type, stride, offset);
    }

    public void glPointParameterf(int pname, float param)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL11.glPointParameterf");
        this.gl11.glPointParameterf(pname, param);
    }

    public void glPointParameterfv(int pname, float[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glPointParameterfv");
        this.gl11.glPointParameterfv(pname, params, offset);
    }

    public void glPointParameterfv(int pname, java.nio.FloatBuffer params)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glPointParameterfv");
        this.gl11.glPointParameterfv(pname, params);
    }

//    public void glPointParameterx(int pname, int param)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL11.glPointParameterx");
//        this.gl11.glPointParameterx(pname, param);
//    }

//    public void glPointParameterxv(int pname, int[] params, int offset)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glPointParameterxv");
//        this.gl11.glPointParameterxv(pname, params, offset);
//    }

//    public void glPointParameterxv(int pname, java.nio.IntBuffer params)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glPointParameterxv");
//        this.gl11.glPointParameterxv(pname, params);
//    }

//    public void glPointSizePointerOES(int type, int stride, java.nio.Buffer pointer)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append("type: ").append(type).append(" stride: ").append(stride).toString(), this, "GL11.glPointSizePointerOES");
//        this.gl11.glPointSizePointerOES(type, stride, pointer);
//    }

    public void glTexCoordPointer(int size, int type, int stride, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append("size: ").append(size).append(" type: ").append(type).append(" stride: ").append(stride).toString(), this, "GL11.glTexCoordPointer");
        this.gl11.glTexCoordPointer(size, type, stride, offset);
    }

    public void glTexEnvi(int target, int pname, int param)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL11.glTexEnvi");
        this.gl11.glTexEnvi(target, pname, param);
    }

    public void glTexEnviv(int target, int pname, int[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "GL11.glTexEnvi");
        this.gl11.glTexEnviv(target, pname, params, offset);
    }

    public void glTexEnviv(int target, int pname, java.nio.IntBuffer params)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glTexEnvi");
        this.gl11.glTexEnviv(target, pname, params);
    }

    public void glTexParameterfv(int target, int pname, float[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glTexParameterfv");
        this.gl11.glTexParameterfv(target, pname, params, offset);
    }

    public void glTexParameterfv(int target, int pname, java.nio.FloatBuffer params)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glTexParameterfv");
        this.gl11.glTexParameterfv(target, pname, params);
    }

    public void glTexParameteri(int target, int pname, int param)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL11.glTexParameteri");
        this.gl11.glTexParameteri(target, pname, param);
    }

    public void glTexParameteriv(int target, int pname, int[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glTexParameteriv");
        this.gl11.glTexParameteriv(target, pname, params, offset);
    }

    public void glTexParameteriv(int target, int pname, java.nio.IntBuffer params)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glTexParameteriv");
        this.gl11.glTexParameteriv(target, pname, params);
    }

    public void glTexParameterxv(int target, int pname, int[] params, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glTexParameteriv");
        this.gl11.glTexParameteriv(target, pname, params, offset);
    }

//    public void glTexParameterxv(int target, int pname, java.nio.IntBuffer params)
//    {
//        //stringBuilder.delete(0, stringBuilder.length());
//        //PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL11.glTexParameterxv");
//        this.gl11.glTexParameterxv(target, pname, params);
//    }

    public void glVertexPointer(int size, int type, int stride, int offset)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append(" size: ").append(size).append(" type: ").append(type).toString(), this, "GL11.glVertexPointer");
        this.gl11.glVertexPointer(size, type, stride, offset);
    }

}
