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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.string.CommonLabels;

/**
 *
 * @author User
 */
//ABDebugGL10
public class GL10 implements javax.microedition.khronos.opengles.GL10
{

    private final CommonLabels commonLabels = CommonLabels.getInstance();
    private final StringBuilder stringBuilder = new StringBuilder();

    protected final String RED = "red: ";
    protected final String GREEN = " green: ";
    protected final String BLUE = " blue: ";
    protected final String ALPHA = " alpha: ";
    protected final String PNAME = "pname: ";
    protected final String _PNAME = " pname: ";
    protected final String PARAM = " param: ";
    protected final String PARAMS = " params: ";
    protected final String LIGHT = "light: ";
    protected final String FACE = "face: ";
    protected final String TARGET = "target: ";

    private final javax.microedition.khronos.opengles.GL10 gl10;

    public GL10(javax.microedition.khronos.opengles.GL10 gl)
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, CommonStrings.getInstance().CONSTRUCTOR);
        this.gl10 = gl;
    }

    public void glActiveTexture(int texture)
    {
        PreLogUtil.put("texture: " + texture, this, "GL10.glActiveTexture");
        this.gl10.glActiveTexture(texture);
    }

    public void glAlphaFunc(int func, float ref)
    {
        PreLogUtil.put("func: " + func, this, "GL10.glAlphaFunc");
        this.gl10.glAlphaFunc(func, ref);
    }

    public void glAlphaFuncx(int func, int ref)
    {
        PreLogUtil.put("func: " + func, this, "GL10.glAlphaFuncx");
//        this.gl10.glAlphaFuncx(func, ref);
        throw new RuntimeException();
    }

    public void glBindTexture(int target, int texture)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" texture: ").append(texture).toString(), this, "GL10.glBindTexture");
        this.gl10.glBindTexture(target, texture);
    }

    public void glBlendFunc(int sfactor, int dfactor)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("sfactor: ").append(sfactor).append(" dfactor: ").append(dfactor).toString(), this, "GL10.glBlendFunc");
        this.gl10.glBlendFunc(sfactor, dfactor);
    }

    public void glClear(int mask)
    {
        PreLogUtil.put("mask: " + mask, this, "GL10.glClear");
        this.gl10.glClear(mask);
    }

    public void glClearColor(float red, float green, float blue, float alpha)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(RED).append(red).append(GREEN).append(green).append(BLUE).append(blue).append(ALPHA).append(alpha).toString(), this, "GL10.glClearColor");
        this.gl10.glClearColor(red, green, blue, alpha);
    }

    public void glClearColorx(int red, int green, int blue, int alpha)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(RED).append(red).append(GREEN).append(green).append(BLUE).append(blue).append(ALPHA).append(alpha).toString(), this, "GL10.glClearColorx");
//        this.gl10.glClearColorx(red, green, blue, alpha);
        throw new RuntimeException();
    }

    public void glClearDepthf(float depth)
    {
        PreLogUtil.put("depth: " + depth, this, "GL10.glClearDepthf");
        this.gl10.glClearDepthf(depth);
    }

    public void glClearDepthx(int depth)
    {
        PreLogUtil.put("depth: " + depth, this, "GL10.glClearDepthx");
//        this.gl10.glClearDepthx(depth);
        throw new RuntimeException();
    }

    public void glClearStencil(int s)
    {
        PreLogUtil.put("s: " + s, this, "GL10.glClearStencil");
        this.gl10.glClearStencil(s);
    }

    public void glClientActiveTexture(int texture)
    {
        PreLogUtil.put("texture: " + texture, this, "GL10.glClientActiveTexture");
        this.gl10.glClientActiveTexture(texture);
    }

    public void glColor4f(float red, float green, float blue, float alpha)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(RED).append(red).append(GREEN).append(green).append(BLUE).append(blue).append(ALPHA).append(alpha).toString(), this, "GL10.glColor4f");
        this.gl10.glColor4f(red, green, blue, alpha);
    }

    public void glColor4x(int red, int green, int blue, int alpha)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(RED).append(red).append(GREEN).append(green).append(BLUE).append(blue).append(ALPHA).append(alpha).toString(), this, "GL10.glColor4x");
//        this.gl10.glColor4x(red, green, blue, alpha);
        throw new RuntimeException();
    }

    public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(RED).append(red).append(GREEN).append(green).append(BLUE).append(blue).append(ALPHA).append(alpha).toString(), this, "GL10.glColorMask");
        this.gl10.glColorMask(red, green, blue, alpha);
    }

    public void glColorPointer(int size, int type, int stride, java.nio.Buffer pointer)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("size: ").append(size).append(" type: ").append(type).append(" stride: ").append(stride).append(" pointer: ").append(pointer).toString(), this, "GL10.glColorPointer");
        this.gl10.glColorPointer(size, type, stride, pointer);
    }

    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, java.nio.Buffer data)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("target: ").append(target).append(" level: ").append(level).append(" internalformat: ").append(internalformat).toString(), this, "GL10.glCompressedTexImage2D");
        this.gl10.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
    }

    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, java.nio.Buffer data)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("target: ").append(target).append(" level: ").append(level).append(" xoffset: ").append(xoffset).append(" yoffset: ").append(yoffset).toString(), this, "GL10.glCompressedTexSubImage2D");
        this.gl10.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
    }

    public void glCopyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height, int border)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("target: ").append(target).append(" level: ").append(level).toString(), this, "GL10.glCopyTexImage2D");
        this.gl10.glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
    }

    public void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("target: ").append(target).append(" level: ").append(level).append(" xoffset: ").append(xoffset).append(" yoffset: ").append(yoffset).toString(), this, "GL10.glCompressedTexSubImage2D");
        this.gl10.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
    }

    public void glCullFace(int mode)
    {
        PreLogUtil.put("mode: " + mode, this, "GL10.glCullFace");
        this.gl10.glCullFace(mode);
    }

    public void glDeleteTextures(int n, int[] textures, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("n: ").append(n).append(" textures: ").append(textures.length).toString(), this, "GL10.glDeleteTextures");
        this.gl10.glDeleteTextures(n, textures, offset);
    }

    public void glDeleteTextures(int n, java.nio.IntBuffer textures)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("n: ").append(n).append(" textures: ").append(textures).toString(), this, "GL10.glDeleteTextures");
        this.gl10.glDeleteTextures(n, textures);
    }

    public void glDepthFunc(int func)
    {
        PreLogUtil.put("func: " + func, this, "GL10.glDepthFunc");
        this.gl10.glDepthFunc(func);
    }

    public void glDepthMask(boolean flag)
    {
        PreLogUtil.put("flag: " + flag, this, "GL10.glDepthMask");
        this.gl10.glDepthMask(flag);
    }

    public void glDepthRangef(float zNear, float zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("zNear: ").append(zNear).append(" zFar: ").append(zFar).toString(), this, "GL10.glDepthRangef");
        this.gl10.glDepthRangef(zNear, zFar);
    }

    public void glDepthRangex(int zNear, int zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("zNear: ").append(zNear).append(" zFar: ").append(zFar).toString(), this, "GL10.glDepthRangex");
//        this.gl10.glDepthRangex(zNear, zFar);
        throw new RuntimeException();
    }

    public void glDisable(int cap)
    {
        PreLogUtil.put("cap: " + cap, this, "GL10.glDisable");
        this.gl10.glDisable(cap);
    }

    public void glDisableClientState(int array)
    {
        PreLogUtil.put("array: " + array, this, "GL10.glDisableClientState");
        this.gl10.glDisableClientState(array);
    }

    public void glDrawArrays(int mode, int first, int count)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("mode: ").append(mode).append(" first: ").append(first).append(" count: ").append(count).toString(), this, "GL10.glDrawArrays");
        this.gl10.glDrawArrays(mode, first, count);
    }

    public void glDrawElements(int mode, int count, int type, java.nio.Buffer indices)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("mode: ").append(mode).append(" count: ").append(count).append(" type: ").append(type).append(" indices: ").append(indices).toString(), this, "GL10.glDrawElements");
        this.gl10.glDrawElements(mode, count, type, indices);
    }

    public void glEnable(int cap)
    {
        PreLogUtil.put("cap: " + cap, this, "GL10.glEnable");
        this.gl10.glEnable(cap);
    }

    public void glEnableClientState(int array)
    {
        PreLogUtil.put("array: " + array, this, "GL10.glEnableClientState");
        this.gl10.glEnableClientState(array);
    }

    public void glFinish()
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, "GL10.glFinish");
        this.gl10.glFinish();
    }

    public void glFlush()
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, "GL10.glFlush");
        this.gl10.glFlush();
    }

    public void glFogf(int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glFogf");
        this.gl10.glFogf(pname, param);
    }

    public void glFogfv(int pname, float[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "GL10.glFogfv");
        this.gl10.glFogfv(pname, params, offset);
    }

    public void glFogfv(int pname, java.nio.FloatBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glFogfv");
        this.gl10.glFogfv(pname, params);
    }

    public void glFogx(int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glFogx");
//        this.gl10.glFogx(pname, param);
        throw new RuntimeException();
    }

    public void glFogxv(int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glFogxv");
//        this.gl10.glFogxv(pname, params, offset);
        throw new RuntimeException();
    }

    public void glFogxv(int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glFogxv");
//        this.gl10.glFogxv(pname, params);
        throw new RuntimeException();
    }

    public void glFrontFace(int mode)
    {
        PreLogUtil.put("mode: " + mode, this, "GL10.glFrontFace");
        this.gl10.glFrontFace(mode);
    }

    public void glFrustumf(float left, float right, float bottom, float top, float zNear, float zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("left: ").append(left).append(" right: ").append(right).append(" bottom: ").append(bottom).append(" top: ").append(top).toString(), this, "GL10.glFrustumf");
        this.gl10.glFrustumf(left, right, bottom, top, zNear, zFar);
    }

    public void glFrustumx(int left, int right, int bottom, int top, int zNear, int zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("left: ").append(left).append(" right: ").append(right).append(" bottom: ").append(bottom).append(" top: ").append(top).toString(), this, "GL10.glFrustumx");
//        this.gl10.glFrustumx(left, right, bottom, top, zNear, zFar);
        throw new RuntimeException();
    }

    public void glGenTextures(int n, int[] textures, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("n: ").append(n).append(" textures: ").append(textures.length).toString(), this, "GL10.glGenTextures");
        this.gl10.glGenTextures(n, textures, offset);
    }

    public void glGenTextures(int n, java.nio.IntBuffer textures)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("n: ").append(n).append(" textures: ").append(textures).toString(), this, "GL10.glGenTextures");
        this.gl10.glGenTextures(n, textures);
    }

    public int glGetError()
    {
        return this.gl10.glGetError();
    }

    public void glGetIntegerv(int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "GL10.glGetIntegerv");
        this.gl10.glGetIntegerv(pname, params, offset);
    }

    public void glGetIntegerv(int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glGetIntegerv");
        this.gl10.glGetIntegerv(pname, params);
    }

    public java.lang.String glGetString(int name)
    {
        return this.gl10.glGetString(name);
    }

    public void glHint(int target, int mode)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" mode: ").append(mode).toString(), this, "GL10.glHint");
        this.gl10.glHint(target, mode);
    }

    public void glLightModelf(int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glLightModelf");
        this.gl10.glLightModelf(pname, param);
    }

    public void glLightModelfv(int pname, float[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "GL10.glLightModelfv");
        this.gl10.glLightModelfv(pname, params, offset);
    }

    public void glLightModelfv(int pname, java.nio.FloatBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glLightModelfv");
        this.gl10.glLightModelfv(pname, params);
    }

    public void glLightModelx(int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glLightModelx");
//        this.gl10.glLightModelx(pname, param);
        throw new RuntimeException();
    }

    public void glLightModelxv(int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glLightModelxv");
//        this.gl10.glLightModelxv(pname, params, offset);
        throw new RuntimeException();
    }

    public void glLightModelxv(int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glLightModelxv");
//        this.gl10.glLightModelxv(pname, params);
        throw new RuntimeException();
    }

    public void glLightf(int light, int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glLightf");
        this.gl10.glLightf(light, pname, param);
    }

    public void glLightfv(int light, int pname, float[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glLightfv");
        this.gl10.glLightfv(light, pname, params, offset);
    }

    public void glLightfv(int light, int pname, java.nio.FloatBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glLightfv");
        this.gl10.glLightfv(light, pname, params);
    }

    public void glLightx(int light, int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glLightx");
//        this.gl10.glLightx(light, pname, param);
        throw new RuntimeException();
    }

    public void glLightxv(int light, int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glLightxv");
//        this.gl10.glLightxv(light, pname, params, offset);
        throw new RuntimeException();
    }

    public void glLightxv(int light, int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glLightxv");
//        this.gl10.glLightxv(light, pname, params);
        throw new RuntimeException();
    }

    public void glLineWidth(float width)
    {
        PreLogUtil.put(commonLabels.WIDTH_LABEL + width, this, "GL10.glLineWidth");
        this.gl10.glLineWidth(width);
    }

    public void glLineWidthx(int width)
    {
        PreLogUtil.put(commonLabels.WIDTH_LABEL + width, this, "GL10.glLineWidthx");
//        this.gl10.glLineWidthx(width);
        throw new RuntimeException();
    }

    public void glLoadIdentity()
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, "GL10.glLoadIdentity");
        this.gl10.glLoadIdentity();
    }

    public void glLoadMatrixf(float[] m, int offset)
    {
        PreLogUtil.put("m: " + m.length, this, "GL10.glLoadMatrixf");
        this.gl10.glLoadMatrixf(m, offset);
    }

    public void glLoadMatrixf(java.nio.FloatBuffer m)
    {
        PreLogUtil.put("m: " + m, this, "GL10.glLoadMatrixf");
        this.gl10.glLoadMatrixf(m);
    }

    public void glLoadMatrixx(int[] m, int offset)
    {
        PreLogUtil.put("m: " + m.length, this, "GL10.glLoadMatrixx");
//        this.gl10.glLoadMatrixx(m, offset);
        throw new RuntimeException();
    }

    public void glLoadMatrixx(java.nio.IntBuffer m)
    {
        PreLogUtil.put("m: " + m, this, "GL10.glLoadMatrixx");
//        this.gl10.glLoadMatrixx(m);
        throw new RuntimeException();
    }

    public void glLogicOp(int opcode)
    {
        PreLogUtil.put("opcode: " + opcode, this, "GL10.glLogicOp");
        this.gl10.glLogicOp(opcode);
    }

    public void glMaterialf(int face, int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glMaterialf");
        this.gl10.glMaterialf(face, pname, param);
    }

    public void glMaterialfv(int face, int pname, float[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glMaterialfv");
        this.gl10.glMaterialfv(face, pname, params, offset);
    }

    public void glMaterialfv(int face, int pname, java.nio.FloatBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glMaterialfv");
        this.gl10.glMaterialfv(face, pname, params);
    }

    public void glMaterialx(int face, int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glMaterialx");
//        this.gl10.glMaterialx(face, pname, param);
        throw new RuntimeException();
    }

    public void glMaterialxv(int face, int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "GL10.glMaterialv");
//        this.gl10.glMaterialxv(face, pname, params, offset);
        throw new RuntimeException();
    }

    public void glMaterialxv(int face, int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glMaterialxv");
//        this.gl10.glMaterialxv(face, pname, params);
        throw new RuntimeException();
    }

    public void glMatrixMode(int mode)
    {
        PreLogUtil.put("mode: " + mode, this, "GL10.glMatrixMode");
        this.gl10.glMatrixMode(mode);
    }

    public void glMultMatrixf(float[] m, int offset)
    {
        PreLogUtil.put("m: " + m, this, "GL10.glMultMatrixf");
        this.gl10.glMultMatrixf(m, offset);
    }

    public void glMultMatrixf(java.nio.FloatBuffer m)
    {
        PreLogUtil.put("m: " + m, this, "GL10.glMultMatrixf");
        this.gl10.glMultMatrixf(m);
    }

    public void glMultMatrixx(int[] m, int offset)
    {
        PreLogUtil.put("m: " + m, this, "GL10.glMultMatrixx");
//        this.gl10.glMultMatrixx(m, offset);
        throw new RuntimeException();
    }

    public void glMultMatrixx(java.nio.IntBuffer m)
    {
        PreLogUtil.put("m: " + m, this, "GL10.glMultMatrixx");
//        this.gl10.glMultMatrixx(m);
        throw new RuntimeException();
    }

    public void glMultiTexCoord4f(int target, float s, float t, float r, float q)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" s: ").append(s).append(" t: ").append(t).append(" r: ").append(r).append(" q: ").append(q).toString(), this, "GL10.glMultiTexCoord4f");
        this.gl10.glMultiTexCoord4f(target, s, t, r, q);
    }

    public void glMultiTexCoord4x(int target, int s, int t, int r, int q)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" s: ").append(s).append(" t: ").append(t).append(" r: ").append(r).append(" q: ").append(q).toString(), this, "GL10.glMultiTexCoord4f");
//        this.gl10.glMultiTexCoord4x(target, s, t, r, q);
        throw new RuntimeException();
    }

    public void glNormal3f(float nx, float ny, float nz)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(nx).append(" nx: ").append(nx).append(" ny: ").append(ny).append(" nz: ").append(nz).toString(), this, "GL10.glNormal3f");
        this.gl10.glNormal3f(nx, ny, nz);
    }

    public void glNormal3x(int nx, int ny, int nz)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(nx).append(" nx: ").append(nx).append(" ny: ").append(ny).append(" nz: ").append(nz).toString(), this, "GL10.glNormal3x");
//        this.gl10.glNormal3x(nx, ny, nz);
        throw new RuntimeException();
    }

    public void glNormalPointer(int type, int stride, java.nio.Buffer pointer)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("type: ").append(type).append(" stride: ").append(stride).toString(), this, "GL10.glNormalPointer");
        this.gl10.glNormalPointer(type, stride, pointer);
    }

    public void glOrthof(float left, float right, float bottom, float top, float zNear, float zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("left: ").append(left).append(" right: ").append(right).append(" bottom: ").append(bottom).append(" top: ").append(top).toString(), this, "GL10.glOrthof");
        this.gl10.glOrthof(left, right, bottom, top, zNear, zFar);
    }

    public void glOrthox(int left, int right, int bottom, int top, int zNear, int zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("left: ").append(left).append(" right: ").append(right).append(" bottom: ").append(bottom).append(" top: ").append(top).toString(), this, "GL10.glOrthox");
//        this.gl10.glOrthox(left, right, bottom, top, zNear, zFar);
        throw new RuntimeException();
    }

    public void glPixelStorei(int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glPixelStorei");
        this.gl10.glPixelStorei(pname, param);
    }

    public void glPointSize(float size)
    {
        PreLogUtil.put("size: " + size, this, "GL10.glPointSize");
        this.gl10.glPointSize(size);
    }

    public void glPointSizex(int size)
    {
        PreLogUtil.put("size: " + size, this, "GL10.glPointSizex");
//        this.gl10.glPointSizex(size);
        throw new RuntimeException();
    }

    public void glPolygonOffset(float factor, float units)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("factor: ").append(factor).append(" units: ").append(units).toString(), this, "GL10.glPolygonOffset");
        this.gl10.glPolygonOffset(factor, units);
    }

    public void glPolygonOffsetx(int factor, int units)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("factor: ").append(factor).append(" units: ").append(units).toString(), this, "GL10.glPolygonOffsetx");
//        this.gl10.glPolygonOffsetx(factor, units);
        throw new RuntimeException();
    }

    public void glPopMatrix()
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, "GL10.glPopMatrix");
        this.gl10.glPopMatrix();
    }

    public void glPushMatrix()
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, "GL10.glPushMatrix");
        this.gl10.glPushMatrix();
    }

    public void glReadPixels(int x, int y, int width, int height, int format, int type, java.nio.Buffer pixels)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(commonLabels.WIDTH_LABEL).append(width).append(commonLabels.HEIGHT_LABEL).append(height).toString(), this, "GL10.glReadPixels");
        this.gl10.glReadPixels(x, y, width, height, format, type, pixels);
    }

    public void glRotatef(float angle, float x, float y, float z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "GL10.glRotatef");
        this.gl10.glRotatef(angle, x, y, z);
    }

    public void glRotatex(int angle, int x, int y, int z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "GL10.glRotatex");
//        this.gl10.glRotatex(angle, x, y, z);
        throw new RuntimeException();
    }

    public void glSampleCoverage(float value, boolean invert)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("value: ").append(value).append(" invert: ").append(invert).toString(), this, "GL10.glSampleCoverage");
        this.gl10.glSampleCoverage(value, invert);
    }

    public void glSampleCoveragex(int value, boolean invert)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("value: ").append(value).append(" invert: ").append(invert).toString(), this, "GL10.glSampleCoverage");
//        this.gl10.glSampleCoveragex(value, invert);
        throw new RuntimeException();
    }

    public void glScalef(float x, float y, float z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "GL10.glScalef");
        this.gl10.glScalef(x, y, z);
    }

    public void glScalex(int x, int y, int z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "GL10.glScalex");
//        this.gl10.glScalex(x, y, z);
        throw new RuntimeException();
    }

    public void glScissor(int x, int y, int width, int height)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(commonLabels.WIDTH_LABEL).append(width).append(commonLabels.HEIGHT_LABEL).append(height).toString(), this, "GL10.glScissor");
        this.gl10.glScissor(x, y, width, height);
    }

    public void glShadeModel(int mode)
    {
        PreLogUtil.put("mode: " + mode, this, "GL10.glShadeModel");
        this.gl10.glShadeModel(mode);
    }

    public void glStencilFunc(int func, int ref, int mask)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("func: ").append(func).append(" ref: ").append(ref).append(" mask: ").append(mask).toString(), this, "GL10.glStencilFunc");
        this.gl10.glStencilFunc(func, ref, mask);
    }

    public void glStencilMask(int mask)
    {
        PreLogUtil.put("mask: " + mask, this, "GL10.glStencilMask");
        this.gl10.glStencilMask(mask);
    }

    public void glStencilOp(int fail, int zfail, int zpass)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("fail: ").append(fail).append(" zfail: ").append(zfail).append(" zpass: ").append(zpass).toString(), this, "GL10.glStencilOp");
        this.gl10.glStencilOp(fail, zfail, zpass);
    }

    public void glTexCoordPointer(int size, int type, int stride, java.nio.Buffer pointer)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("size: ").append(size).append(" type: ").append(type).append(" stride: ").append(stride).toString(), this, "GL10.glTexCoordPointer");
        this.gl10.glTexCoordPointer(size, type, stride, pointer);
    }

    public void glTexEnvf(int target, int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glTexEnvf");
        this.gl10.glTexEnvf(target, pname, param);
    }

    public void glTexEnvfv(int target, int pname, float[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glTexEnvf");
        this.gl10.glTexEnvfv(target, pname, params, offset);
    }

    public void glTexEnvfv(int target, int pname, java.nio.FloatBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glTexEnvf");
        this.gl10.glTexEnvfv(target, pname, params);
    }

    public void glTexEnvx(int target, int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glTexEnvx");
//        this.gl10.glTexEnvx(target, pname, param);
        throw new RuntimeException();
    }

    public void glTexEnvxv(int target, int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glTexEnvxv");
//        this.gl10.glTexEnvxv(target, pname, params, offset);
        throw new RuntimeException();
    }

    public void glTexEnvxv(int target, int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAMS).append(params).toString(), this, "GL10.glTexEnvxv");
//        this.gl10.glTexEnvxv(target, pname, params);
        throw new RuntimeException();
    }

    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, java.nio.Buffer pixels)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" level: ").append(level).append(" internalformat: ").append(internalformat).toString(), this, "GL10.glTexImage2D");
        this.gl10.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    public void glTexParameterf(int target, int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glTexParameterf");
        this.gl10.glTexParameterf(target, pname, param);
    }

    public void glTexParameterx(int target, int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAM).append(param).toString(), this, "GL10.glTexParameterx");
//        this.gl10.glTexParameterx(target, pname, param);
        throw new RuntimeException();
    }

    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, java.nio.Buffer pixels)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" level: ").append(level).append(" xoffset: ").append(xoffset).toString(), this, "GL10.glTexSubImage2D");
        this.gl10.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    public void glTranslatef(float x, float y, float z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "GL10.glTranslatef");
        this.gl10.glTranslatef(x, y, z);
    }

    public void glTranslatex(int x, int y, int z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "GL10.glTranslatex");
//        this.gl10.glTranslatex(x, y, z);
        throw new RuntimeException();
    }

    public void glVertexPointer(int size, int type, int stride, java.nio.Buffer pointer)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("size: ").append(size).append(" type: ").append(type).append(" stride: ").append(stride).toString(), this, "GL10.glVertexPointer");
        this.gl10.glVertexPointer(size, type, stride, pointer);
    }

    public void glViewport(int x, int y, int width, int height)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(commonLabels.WIDTH_LABEL).append(width).append(commonLabels.HEIGHT_LABEL).append(height).toString(), this, "GL10.glViewport");
        this.gl10.glViewport(x, y, width, height);
    }

    //These below are for the example only
    public void glBegin(int i)
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(commonLabels.W).append(width).append(commonLabels.H).append(height).toString(), this, "GL10.glViewport");
//        this.gl10.glBegin(i);
        throw new RuntimeException();
    }

    public void glEnd()
    {
        //stringBuilder.delete(0, stringBuilder.length());
        //PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(commonLabels.W).append(width).append(commonLabels.H).append(height).toString(), this, "GL10.glViewport");
//        this.gl10.glEnd();
        throw new RuntimeException();
    }
    
    
    public void glVertex3f(float f, float f2, float f3) {
//        this.gl10.glVertex3f(f, f2, f3);
        throw new RuntimeException();
    }
    
    public void glPolygonMode(int i, int i1) {
//        this.gl10.glPolygonMode(i, i1);
        throw new RuntimeException();
    }
    
}
