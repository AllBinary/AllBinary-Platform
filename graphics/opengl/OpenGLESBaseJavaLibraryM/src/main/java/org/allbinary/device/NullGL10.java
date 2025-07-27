package org.allbinary.device;

import javax.microedition.khronos.opengles.GL10;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.allbinary.logic.string.StringUtil;

/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
/**
 *
 * @author User
 */
public class NullGL10 implements GL10 {

    public static final NullGL10 NULL_GL10 = new NullGL10();

    public void glActiveTexture(final int texture) {
        return;
    }

    public void glAlphaFunc(final int func, final float ref) {
        return;
    }

    public void glBindTexture(final int target, final int texture) {
        return;
    }

    public void glBlendFunc(final int sfactor, final int dfactor) {
        return;
    }

    public void glClear(final int mask) {
        return;
    }

    public void glClearColor(final float red, final float green, final float blue, final float alpha) {
        return;
    }

    public void glClearDepthf(final float depth) {
        return;
    }

    public void glClearStencil(final int s) {
        return;
    }

    public void glClientActiveTexture(final int texture) {
        return;
    }

    public void glColor4f(final float red, final float green, final float blue, final float alpha) {
        return;
    }

    public void glColorMask(final boolean red, final boolean green, final boolean blue, final boolean alpha) {
        return;
    }

    public void glColorPointer(final int size, final int type, final int stride, final Buffer pointer) {
        return;
    }

    public void glCompressedTexImage2D(final int target, final int level, final int internalformat, final int width, final int height, final int border, final int imageSize, final Buffer data) {
        return;
    }

    public void glCompressedTexSubImage2D(final int target, final int level, final int xoffset, final int yoffset, final int width, final int height, final int format, final int imageSize, final Buffer data) {
        return;
    }

    public void glCopyTexImage2D(final int target, final int level, final int internalformat, final int x, final int y, final int width, final int height, final int border) {
        return;
    }

    public void glCopyTexSubImage2D(final int target, final int level, final int xoffset, final int yoffset, final int x, final int y, final int width, final int height) {
        return;
    }

    public void glCullFace(final int mode) {
        return;
    }

    public void glDeleteTextures(final int n, final int[] textures, final int offset) {
        return;
    }

    public void glDeleteTextures(final int n, final IntBuffer textures) {
        return;
    }

    public void glDepthFunc(final int func) {
        return;
    }

    public void glDepthMask(final boolean flag) {
        return;
    }

    public void glDepthRangef(final float zNear, final float zFar) {
        return;
    }

    public void glDisable(final int cap) {
        return;
    }

    public void glDisableClientState(final int array) {
        return;
    }

    public void glDrawArrays(final int mode, final int first, final int count) {
        return;
    }

    public void glDrawElements(final int mode, final int count, final int type, final Buffer indices) {
        return;
    }

    public void glEnable(final int cap) {
        return;
    }

    public void glEnableClientState(final int array) {
        return;
    }

    public void glFinish() {
        return;
    }

    public void glFlush() {
        return;
    }

    public void glFogf(final int pname, final float param) {
        return;
    }

    public void glFogfv(final int pname, final float[] params, final int offset) {
        return;
    }

    public void glFogfv(final int pname, final FloatBuffer params) {
        return;
    }

    public void glFrontFace(final int mode) {
        return;
    }

    public void glFrustumf(final float left, final float right, final float bottom, final float top, final float zNear, final float zFar) {
        return;
    }

    public void glGenTextures(final int n, final int[] textures, final int offset) {
        return;
    }

    public void glGenTextures(final int n, final IntBuffer textures) {
        return;
    }

    public int glGetError() {
        return 0;
    }

    public void glGetIntegerv(final int pname, final int[] params, final int offset) {
        return;
    }

    public void glGetIntegerv(final int pname, final IntBuffer params) {
        return;
    }

    public String glGetString(final int name) {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    public void glHint(final int target, final int mode) {
        return;
    }

    public void glLightModelf(final int pname, final float param) {
        return;
    }

    public void glLightModelfv(final int pname, final float[] params, final int offset) {
        return;
    }

    public void glLightModelfv(final int pname, final FloatBuffer params) {
        return;
    }

    public void glLightf(final int light, final int pname, final float param) {
        return;
    }

    public void glLightfv(final int light, final int pname, final float[] params, final int offset) {
        return;
    }

    public void glLightfv(final int light, final int pname, final FloatBuffer params) {
        return;
    }

    public void glLineWidth(final float width) {
        return;
    }

    public void glLoadIdentity() {
        return;
    }

    public void glLoadMatrixf(final float[] m, final int offset) {
        return;
    }

    public void glLoadMatrixf(final FloatBuffer m) {
        return;
    }

    public void glLogicOp(final int opcode) {
        return;
    }

    public void glMaterialf(final int face, final int pname, final float param) {
        return;
    }

    public void glMaterialfv(final int face, final int pname, final float[] params, final int offset) {
        return;
    }

    public void glMaterialfv(final int face, final int pname, final FloatBuffer params) {
        return;
    }

    public void glMatrixMode(final int mode) {
        return;
    }

    public void glMultMatrixf(final float[] m, final int offset) {
        return;
    }

    public void glMultMatrixf(final FloatBuffer m) {
        return;
    }

    public void glMultiTexCoord4f(final int target, final float s, final float t, final float r, final float q) {
        return;
    }

    public void glNormal3f(final float nx, final float ny, final float nz) {
        return;
    }

    public void glNormalPointer(final int type, final int stride, final Buffer pointer) {
        return;
    }

    public void glOrthof(final float left, final float right, final float bottom, final float top, final float zNear, final float zFar) {
        return;
    }

    public void glPixelStorei(final int pname, final int param) {
        return;
    }

    public void glPointSize(final float size) {
        return;
    }

    public void glPolygonOffset(final float factor, final float units) {
        return;
    }

    public void glPopMatrix() {
        return;
    }

    public void glPushMatrix() {
        return;
    }

    public void glReadPixels(final int x, final int y, final int width, final int height, final int format, final int type, final Buffer pixels) {
        return;
    }

    public void glRotatef(final float angle, final float x, final float y, final float z) {
        return;
    }

    public void glRotatex(final int angle, final int x, final int y, final int z) {
        return;
    }

    public void glSampleCoverage(final float value, final boolean invert) {
        return;
    }

    public void glScalef(final float x, final float y, final float z) {
        return;
    }

    public void glScalex(final int x, final int y, final int z) {
        return;
    }

    public void glScissor(final int x, final int y, final int width, final int height) {
        return;
    }

    public void glShadeModel(final int mode) {
        return;
    }

    public void glStencilFunc(final int func, final int ref, final int mask) {
        return;
    }

    public void glStencilMask(final int mask) {
        return;
    }

    public void glStencilOp(final int fail, final int zfail, final int zpass) {
        return;
    }

    public void glTexCoordPointer(final int size, final int type, final int stride, final Buffer pointer) {
        return;
    }

    public void glTexEnvf(final int target, final int pname, final float param) {
        return;
    }

    public void glTexEnvfv(final int target, final int pname, final float[] params, final int offset) {
        return;
    }

    public void glTexEnvfv(final int target, final int pname, final FloatBuffer params) {
        return;
    }

    public void glTexImage2D(final int target, final int level, final int internalformat, final int width, final int height, final int border, final int format, final int type, final Buffer pixels) {
        return;
    }

    public void glTexParameterf(final int target, final int pname, final float param) {
        return;
    }

    public void glTexParameteri(final int target, final int pname, final int param) {
        return;
    }

    public void glTexSubImage2D(final int target, final int level, final int xoffset, final int yoffset, final int width, final int height, final int format, final int type, final Buffer pixels) {
        return;
    }

    public void glTranslatef(final float x, final float y, final float z) {
        return;
    }

    public void glVertexPointer(final int size, final int type, final int stride, final Buffer pointer) {
        return;
    }

    public void glViewport(final int x, final int y, final int width, final int height) {
        return;
    }

    public void glBegin(final int i) {
        return;
    }

    public void glEnd() {
        return;
    }

    public void glVertex3f(final float f, final float f2, final float f3) {
        return;
    }

    public void glPolygonMode(final int i, final int i1) {
        return;
    }
}
