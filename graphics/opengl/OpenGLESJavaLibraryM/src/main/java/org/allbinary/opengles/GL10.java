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

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.communication.log.PreLogUtil;

/**
 *
 * @author User
 */
public class GL10 implements javax.microedition.khronos.opengles.GL10
{

    private final StringBuilder stringBuilder = new StringBuilder();

    private final String RED = "red: ";
    private final String GREEN = " green: ";
    private final String BLUE = " blue: ";
    private final String ALPHA = " alpha: ";
    private final String PNAME = "pname: ";
    private final String _PNAME = " pname: ";
    private final String PARAM = " param: ";
    private final String PARAMS = " params: ";
    private final String LIGHT = "light: ";
    private final String FACE = "face: ";
    private final String TARGET = "target: ";

    private final javax.microedition.khronos.opengles.GL10 gl;

    public GL10(javax.microedition.khronos.opengles.GL10 gl)
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, CommonStrings.getInstance().CONSTRUCTOR);
        this.gl = gl;
    }

    public void glActiveTexture(int texture)
    {
        PreLogUtil.put("texture: " + texture, this, "glActiveTexture");
        this.gl.glActiveTexture(texture);
    }

    public void glAlphaFunc(int func, float ref)
    {
        PreLogUtil.put("func: " + func, this, "glAlphaFunc");
        this.gl.glAlphaFunc(func, ref);
    }

    public void glAlphaFuncx(int func, int ref)
    {
        PreLogUtil.put("func: " + func, this, "glAlphaFuncx");
        this.gl.glAlphaFuncx(func, ref);
    }

    public void glBindTexture(int target, int texture)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" texture: ").append(texture).toString(), this, "glBindTexture");
        this.gl.glBindTexture(target, texture);
    }

    public void glBlendFunc(int sfactor, int dfactor)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("sfactor: ").append(sfactor).append(" dfactor: ").append(dfactor).toString(), this, "glBlendFunc");
        this.gl.glBlendFunc(sfactor, dfactor);
    }

    public void glClear(int mask)
    {
        PreLogUtil.put("mask: " + mask, this, "glClear");
        this.gl.glClear(mask);
    }

    public void glClearColor(float red, float green, float blue, float alpha)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(RED).append(red).append(GREEN).append(green).append(BLUE).append(blue).append(ALPHA).append(alpha).toString(), this, "glClearColor");
        this.gl.glClearColor(red, green, blue, alpha);
    }

    public void glClearColorx(int red, int green, int blue, int alpha)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(RED).append(red).append(GREEN).append(green).append(BLUE).append(blue).append(ALPHA).append(alpha).toString(), this, "glClearColorx");
        this.gl.glClearColorx(red, green, blue, alpha);
    }

    public void glClearDepthf(float depth)
    {
        PreLogUtil.put("depth: " + depth, this, "glClearDepthf");
        this.gl.glClearDepthf(depth);
    }

    public void glClearDepthx(int depth)
    {
        PreLogUtil.put("depth: " + depth, this, "glClearDepthx");
        this.gl.glClearDepthx(depth);
    }

    public void glClearStencil(int s)
    {
        PreLogUtil.put("s: " + s, this, "glClearStencil");
        this.gl.glClearStencil(s);
    }

    public void glClientActiveTexture(int texture)
    {
        PreLogUtil.put("texture: " + texture, this, "glClientActiveTexture");
        this.gl.glClientActiveTexture(texture);
    }

    public void glColor4f(float red, float green, float blue, float alpha)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(RED).append(red).append(GREEN).append(green).append(BLUE).append(blue).append(ALPHA).append(alpha).toString(), this, "glColor4f");
        this.gl.glColor4f(red, green, blue, alpha);
    }

    public void glColor4x(int red, int green, int blue, int alpha)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(RED).append(red).append(GREEN).append(green).append(BLUE).append(blue).append(ALPHA).append(alpha).toString(), this, "glColor4x");
        this.gl.glColor4x(red, green, blue, alpha);
    }

    public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(RED).append(red).append(GREEN).append(green).append(BLUE).append(blue).append(ALPHA).append(alpha).toString(), this, "glColorMask");
        this.gl.glColorMask(red, green, blue, alpha);
    }

    public void glColorPointer(int size, int type, int stride, java.nio.Buffer pointer)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("size: ").append(size).append(" type: ").append(type).append(" stride: ").append(stride).append(" pointer: ").append(pointer).toString(), this, "glColorPointer");
        this.gl.glColorPointer(size, type, stride, pointer);
    }

    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, java.nio.Buffer data)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("target: ").append(target).append(" level: ").append(level).append(" internalformat: ").append(internalformat).toString(), this, "glCompressedTexImage2D");
        this.gl.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
    }

    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, java.nio.Buffer data)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("target: ").append(target).append(" level: ").append(level).append(" xoffset: ").append(xoffset).append(" yoffset: ").append(yoffset).toString(), this, "glCompressedTexSubImage2D");
        this.gl.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
    }

    public void glCopyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height, int border)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("target: ").append(target).append(" level: ").append(level).toString(), this, "glCopyTexImage2D");
        this.gl.glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
    }

    public void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("target: ").append(target).append(" level: ").append(level).append(" xoffset: ").append(xoffset).append(" yoffset: ").append(yoffset).toString(), this, "glCompressedTexSubImage2D");
        this.gl.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
    }

    public void glCullFace(int mode)
    {
        PreLogUtil.put("mode: " + mode, this, "glCullFace");
        this.gl.glCullFace(mode);
    }

    public void glDeleteTextures(int n, int[] textures, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("n: ").append(n).append(" textures: ").append(textures.length).toString(), this, "glDeleteTextures");
        this.gl.glDeleteTextures(n, textures, offset);
    }

    public void glDeleteTextures(int n, java.nio.IntBuffer textures)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("n: ").append(n).append(" textures: ").append(textures).toString(), this, "glDeleteTextures");
        this.gl.glDeleteTextures(n, textures);
    }

    public void glDepthFunc(int func)
    {
        PreLogUtil.put("func: " + func, this, "glDepthFunc");
        this.gl.glDepthFunc(func);
    }

    public void glDepthMask(boolean flag)
    {
        PreLogUtil.put("flag: " + flag, this, "glDepthMask");
        this.gl.glDepthMask(flag);
    }

    public void glDepthRangef(float zNear, float zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("zNear: ").append(zNear).append(" zFar: ").append(zFar).toString(), this, "glDepthRangef");
        this.gl.glDepthRangef(zNear, zFar);
    }

    public void glDepthRangex(int zNear, int zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("zNear: ").append(zNear).append(" zFar: ").append(zFar).toString(), this, "glDepthRangex");
        this.gl.glDepthRangex(zNear, zFar);
    }

    public void glDisable(int cap)
    {
        PreLogUtil.put("cap: " + cap, this, "glDisable");
        this.gl.glDisable(cap);
    }

    public void glDisableClientState(int array)
    {
        PreLogUtil.put("array: " + array, this, "glDisableClientState");
        this.gl.glDisableClientState(array);
    }

    public void glDrawArrays(int mode, int first, int count)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("mode: ").append(mode).append(" first: ").append(first).append(" count: ").append(count).toString(), this, "glDrawArrays");
        this.gl.glDrawArrays(mode, first, count);
    }

    public void glDrawElements(int mode, int count, int type, java.nio.Buffer indices)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("mode: ").append(mode).append(" count: ").append(count).append(" type: ").append(type).append(" indices: ").append(indices).toString(), this, "glDrawElements");
        this.gl.glDrawElements(mode, count, type, indices);
    }

    public void glEnable(int cap)
    {
        PreLogUtil.put("cap: " + cap, this, "glEnable");
        this.gl.glEnable(cap);
    }

    public void glEnableClientState(int array)
    {
        PreLogUtil.put("array: " + array, this, "glEnableClientState");
        this.gl.glEnableClientState(array);
    }

    public void glFinish()
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, "glFinish");
        this.gl.glFinish();
    }

    public void glFlush()
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, "glFlush");
        this.gl.glFlush();
    }

    public void glFogf(int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "glFogf");
        this.gl.glFogf(pname, param);
    }

    public void glFogfv(int pname, float[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "glFogfv");
        this.gl.glFogfv(pname, params, offset);
    }

    public void glFogfv(int pname, java.nio.FloatBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glFogfv");
        this.gl.glFogfv(pname, params);
    }

    public void glFogx(int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "glFogx");
        this.gl.glFogx(pname, param);
    }

    public void glFogxv(int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glFogxv");
        this.gl.glFogxv(pname, params, offset);
    }

    public void glFogxv(int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glFogxv");
        this.gl.glFogxv(pname, params);
    }

    public void glFrontFace(int mode)
    {
        PreLogUtil.put("mode: " + mode, this, "glFrontFace");
        this.gl.glFrontFace(mode);
    }

    public void glFrustumf(float left, float right, float bottom, float top, float zNear, float zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("left: ").append(left).append(" right: ").append(right).append(" bottom: ").append(bottom).append(" top: ").append(top).toString(), this, "glFrustumf");
        this.gl.glFrustumf(left, right, bottom, top, zNear, zFar);
    }

    public void glFrustumx(int left, int right, int bottom, int top, int zNear, int zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("left: ").append(left).append(" right: ").append(right).append(" bottom: ").append(bottom).append(" top: ").append(top).toString(), this, "glFrustumx");
        this.gl.glFrustumx(left, right, bottom, top, zNear, zFar);
    }

    public void glGenTextures(int n, int[] textures, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("n: ").append(n).append(" textures: ").append(textures.length).toString(), this, "glGenTextures");
        this.gl.glGenTextures(n, textures, offset);
    }

    public void glGenTextures(int n, java.nio.IntBuffer textures)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("n: ").append(n).append(" textures: ").append(textures).toString(), this, "glGenTextures");
        this.gl.glGenTextures(n, textures);
    }

    public int glGetError()
    {
        return this.gl.glGetError();
    }

    public void glGetIntegerv(int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "glGetIntegerv");
        this.gl.glGetIntegerv(pname, params, offset);
    }

    public void glGetIntegerv(int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glGetIntegerv");
        this.gl.glGetIntegerv(pname, params);
    }

    public java.lang.String glGetString(int name)
    {
        return this.gl.glGetString(name);
    }

    public void glHint(int target, int mode)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" mode: ").append(mode).toString(), this, "glHint");
        this.gl.glHint(target, mode);
    }

    public void glLightModelf(int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "glLightModelf");
        this.gl.glLightModelf(pname, param);
    }

    public void glLightModelfv(int pname, float[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "glLightModelfv");
        this.gl.glLightModelfv(pname, params, offset);
    }

    public void glLightModelfv(int pname, java.nio.FloatBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glLightModelfv");
        this.gl.glLightModelfv(pname, params);
    }

    public void glLightModelx(int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "glLightModelx");
        this.gl.glLightModelx(pname, param);
    }

    public void glLightModelxv(int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glLightModelxv");
        this.gl.glLightModelxv(pname, params, offset);
    }

    public void glLightModelxv(int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glLightModelxv");
        this.gl.glLightModelxv(pname, params);
    }

    public void glLightf(int light, int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "glLightf");
        this.gl.glLightf(light, pname, param);
    }

    public void glLightfv(int light, int pname, float[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glLightfv");
        this.gl.glLightfv(light, pname, params, offset);
    }

    public void glLightfv(int light, int pname, java.nio.FloatBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glLightfv");
        this.gl.glLightfv(light, pname, params);
    }

    public void glLightx(int light, int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "glLightx");
        this.gl.glLightx(light, pname, param);
    }

    public void glLightxv(int light, int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glLightxv");
        this.gl.glLightxv(light, pname, params, offset);
    }

    public void glLightxv(int light, int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(LIGHT).append(light).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glLightxv");
        this.gl.glLightxv(light, pname, params);
    }

    public void glLineWidth(float width)
    {
        PreLogUtil.put("width: " + width, this, "glLineWidth");
        this.gl.glLineWidth(width);
    }

    public void glLineWidthx(int width)
    {
        PreLogUtil.put("width: " + width, this, "glLineWidthx");
        this.gl.glLineWidthx(width);
    }

    public void glLoadIdentity()
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, "glLoadIdentity");
        this.gl.glLoadIdentity();
    }

    public void glLoadMatrixf(float[] m, int offset)
    {
        PreLogUtil.put("m: " + m.length, this, "glLoadMatrixf");
        this.gl.glLoadMatrixf(m, offset);
    }

    public void glLoadMatrixf(java.nio.FloatBuffer m)
    {
        PreLogUtil.put("m: " + m, this, "glLoadMatrixf");
        this.gl.glLoadMatrixf(m);
    }

    public void glLoadMatrixx(int[] m, int offset)
    {
        PreLogUtil.put("m: " + m.length, this, "glLoadMatrixx");
        this.gl.glLoadMatrixx(m, offset);
    }

    public void glLoadMatrixx(java.nio.IntBuffer m)
    {
        PreLogUtil.put("m: " + m, this, "glLoadMatrixx");
        this.gl.glLoadMatrixx(m);
    }

    public void glLogicOp(int opcode)
    {
        PreLogUtil.put("opcode: " + opcode, this, "glLogicOp");
        this.gl.glLogicOp(opcode);
    }

    public void glMaterialf(int face, int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "glMaterialf");
        this.gl.glMaterialf(face, pname, param);
    }

    public void glMaterialfv(int face, int pname, float[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glMaterialfv");
        this.gl.glMaterialfv(face, pname, params, offset);
    }

    public void glMaterialfv(int face, int pname, java.nio.FloatBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glMaterialfv");
        this.gl.glMaterialfv(face, pname, params);
    }

    public void glMaterialx(int face, int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "glMaterialx");
        this.gl.glMaterialx(face, pname, param);
    }

    public void glMaterialxv(int face, int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params.length).toString(), this, "glMaterialv");
        this.gl.glMaterialxv(face, pname, params, offset);
    }

    public void glMaterialxv(int face, int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(FACE).append(face).append(PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glMaterialxv");
        this.gl.glMaterialxv(face, pname, params);
    }

    public void glMatrixMode(int mode)
    {
        PreLogUtil.put("mode: " + mode, this, "glMatrixMode");
        this.gl.glMatrixMode(mode);
    }

    public void glMultMatrixf(float[] m, int offset)
    {
        PreLogUtil.put("m: " + m, this, "glMultMatrixf");
        this.gl.glMultMatrixf(m, offset);
    }

    public void glMultMatrixf(java.nio.FloatBuffer m)
    {
        PreLogUtil.put("m: " + m, this, "glMultMatrixf");
        this.gl.glMultMatrixf(m);
    }

    public void glMultMatrixx(int[] m, int offset)
    {
        PreLogUtil.put("m: " + m, this, "glMultMatrixx");
        this.gl.glMultMatrixx(m, offset);
    }

    public void glMultMatrixx(java.nio.IntBuffer m)
    {
        PreLogUtil.put("m: " + m, this, "glMultMatrixx");
        this.gl.glMultMatrixx(m);
    }

    public void glMultiTexCoord4f(int target, float s, float t, float r, float q)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" s: ").append(s).append(" t: ").append(t).append(" r: ").append(r).append(" q: ").append(q).toString(), this, "glMultiTexCoord4f");
        this.gl.glMultiTexCoord4f(target, s, t, r, q);
    }

    public void glMultiTexCoord4x(int target, int s, int t, int r, int q)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" s: ").append(s).append(" t: ").append(t).append(" r: ").append(r).append(" q: ").append(q).toString(), this, "glMultiTexCoord4f");
        this.gl.glMultiTexCoord4x(target, s, t, r, q);
    }

    public void glNormal3f(float nx, float ny, float nz)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(nx).append(" nx: ").append(nx).append(" ny: ").append(ny).append(" nz: ").append(nz).toString(), this, "glNormal3f");
        this.gl.glNormal3f(nx, ny, nz);
    }

    public void glNormal3x(int nx, int ny, int nz)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(nx).append(" nx: ").append(nx).append(" ny: ").append(ny).append(" nz: ").append(nz).toString(), this, "glNormal3x");
        this.gl.glNormal3x(nx, ny, nz);
    }

    public void glNormalPointer(int type, int stride, java.nio.Buffer pointer)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("type: ").append(type).append(" stride: ").append(stride).toString(), this, "glNormalPointer");
        this.gl.glNormalPointer(type, stride, pointer);
    }

    public void glOrthof(float left, float right, float bottom, float top, float zNear, float zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("left: ").append(left).append(" right: ").append(right).append(" bottom: ").append(bottom).append(" top: ").append(top).toString(), this, "glOrthof");
        this.gl.glOrthof(left, right, bottom, top, zNear, zFar);
    }

    public void glOrthox(int left, int right, int bottom, int top, int zNear, int zFar)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("left: ").append(left).append(" right: ").append(right).append(" bottom: ").append(bottom).append(" top: ").append(top).toString(), this, "glOrthox");
        this.gl.glOrthox(left, right, bottom, top, zNear, zFar);
    }

    public void glPixelStorei(int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(PNAME).append(pname).append(PARAM).append(param).toString(), this, "glPixelStorei");
        this.gl.glPixelStorei(pname, param);
    }

    public void glPointSize(float size)
    {
        PreLogUtil.put("size: " + size, this, "glPointSize");
        this.gl.glPointSize(size);
    }

    public void glPointSizex(int size)
    {
        PreLogUtil.put("size: " + size, this, "glPointSizex");
        this.gl.glPointSizex(size);
    }

    public void glPolygonOffset(float factor, float units)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("factor: ").append(factor).append(" units: ").append(units).toString(), this, "glPolygonOffset");
        this.gl.glPolygonOffset(factor, units);
    }

    public void glPolygonOffsetx(int factor, int units)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("factor: ").append(factor).append(" units: ").append(units).toString(), this, "glPolygonOffsetx");
        this.gl.glPolygonOffsetx(factor, units);
    }

    public void glPopMatrix()
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, "glPopMatrix");
        this.gl.glPopMatrix();
    }

    public void glPushMatrix()
    {
        PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, "glPushMatrix");
        this.gl.glPushMatrix();
    }

    public void glReadPixels(int x, int y, int width, int height, int format, int type, java.nio.Buffer pixels)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" width: ").append(width).append(" height: ").append(height).toString(), this, "glReadPixels");
        this.gl.glReadPixels(x, y, width, height, format, type, pixels);
    }

    public void glRotatef(float angle, float x, float y, float z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "glRotatef");
        this.gl.glRotatef(angle, x, y, z);
    }

    public void glRotatex(int angle, int x, int y, int z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "glRotatex");
        this.gl.glRotatex(angle, x, y, z);
    }

    public void glSampleCoverage(float value, boolean invert)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("value: ").append(value).append(" invert: ").append(invert).toString(), this, "glSampleCoverage");
        this.gl.glSampleCoverage(value, invert);
    }

    public void glSampleCoveragex(int value, boolean invert)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("value: ").append(value).append(" invert: ").append(invert).toString(), this, "glSampleCoverage");
        this.gl.glSampleCoveragex(value, invert);
    }

    public void glScalef(float x, float y, float z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "glScalef");
        this.gl.glScalef(x, y, z);
    }

    public void glScalex(int x, int y, int z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "glScalex");
        this.gl.glScalex(x, y, z);
    }

    public void glScissor(int x, int y, int width, int height)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" width: ").append(width).append(" height: ").append(height).toString(), this, "glScissor");
        this.gl.glScissor(x, y, width, height);
    }

    public void glShadeModel(int mode)
    {
        PreLogUtil.put("mode: " + mode, this, "glShadeModel");
        this.gl.glShadeModel(mode);
    }

    public void glStencilFunc(int func, int ref, int mask)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("func: ").append(func).append(" ref: ").append(ref).append(" mask: ").append(mask).toString(), this, "glStencilFunc");
        this.gl.glStencilFunc(func, ref, mask);
    }

    public void glStencilMask(int mask)
    {
        PreLogUtil.put("mask: " + mask, this, "glStencilMask");
        this.gl.glStencilMask(mask);
    }

    public void glStencilOp(int fail, int zfail, int zpass)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("fail: ").append(fail).append(" zfail: ").append(zfail).append(" zpass: ").append(zpass).toString(), this, "glStencilOp");
        this.gl.glStencilOp(fail, zfail, zpass);
    }

    public void glTexCoordPointer(int size, int type, int stride, java.nio.Buffer pointer)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("size: ").append(size).append(" type: ").append(type).append(" stride: ").append(stride).toString(), this, "glTexCoordPointer");
        this.gl.glTexCoordPointer(size, type, stride, pointer);
    }

    public void glTexEnvf(int target, int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAM).append(param).toString(), this, "glTexEnvf");
        this.gl.glTexEnvf(target, pname, param);
    }

    public void glTexEnvfv(int target, int pname, float[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glTexEnvf");
        this.gl.glTexEnvfv(target, pname, params, offset);
    }

    public void glTexEnvfv(int target, int pname, java.nio.FloatBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glTexEnvf");
        this.gl.glTexEnvfv(target, pname, params);
    }

    public void glTexEnvx(int target, int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAM).append(param).toString(), this, "glTexEnvx");
        this.gl.glTexEnvx(target, pname, param);
    }

    public void glTexEnvxv(int target, int pname, int[] params, int offset)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glTexEnvxv");
        this.gl.glTexEnvxv(target, pname, params, offset);
    }

    public void glTexEnvxv(int target, int pname, java.nio.IntBuffer params)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAMS).append(params).toString(), this, "glTexEnvxv");
        this.gl.glTexEnvxv(target, pname, params);
    }

    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, java.nio.Buffer pixels)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" level: ").append(level).append(" internalformat: ").append(internalformat).toString(), this, "glTexImage2D");
        this.gl.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    public void glTexParameterf(int target, int pname, float param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAM).append(param).toString(), this, "glTexParameterf");
        this.gl.glTexParameterf(target, pname, param);
    }

    public void glTexParameterx(int target, int pname, int param)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(_PNAME).append(pname).append(PARAM).append(param).toString(), this, "glTexParameterx");
        this.gl.glTexParameterx(target, pname, param);
    }

    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, java.nio.Buffer pixels)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append(TARGET).append(target).append(" level: ").append(level).append(" xoffset: ").append(xoffset).toString(), this, "glTexSubImage2D");
        this.gl.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    public void glTranslatef(float x, float y, float z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "glTranslatef");
        this.gl.glTranslatef(x, y, z);
    }

    public void glTranslatex(int x, int y, int z)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "glTranslatex");
        this.gl.glTranslatex(x, y, z);
    }

    public void glVertexPointer(int size, int type, int stride, java.nio.Buffer pointer)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("size: ").append(size).append(" type: ").append(type).append(" stride: ").append(stride).toString(), this, "glVertexPointer");
        this.gl.glVertexPointer(size, type, stride, pointer);
    }

    public void glViewport(int x, int y, int width, int height)
    {
        stringBuilder.delete(0, stringBuilder.length());
        PreLogUtil.put(stringBuilder.append("x: ").append(x).append(" y: ").append(y).append(" width: ").append(width).append(" height: ").append(height).toString(), this, "glViewport");
        this.gl.glViewport(x, y, width, height);
    }

}
