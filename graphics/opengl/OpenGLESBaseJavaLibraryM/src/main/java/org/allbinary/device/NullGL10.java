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

    @Override
    public void glActiveTexture(final int texture) {
        return;
    }

    @Override
    public void glAlphaFunc(final int func, final float ref) {
        return;
    }

    @Override
    public void glBindTexture(final int target, final int texture) {
        return;
    }

    @Override
    public void glBlendFunc(final int sfactor, final int dfactor) {
        return;
    }

    @Override
    public void glClear(final int mask) {
        return;
    }

    @Override
    public void glClearColor(final float red, final float green, final float blue, final float alpha) {
        return;
    }

    @Override
    public void glClearDepthf(final float depth) {
        return;
    }

    @Override
    public void glClearStencil(final int s) {
        return;
    }

    @Override
    public void glClientActiveTexture(final int texture) {
        return;
    }

    @Override
    public void glColor4f(final float red, final float green, final float blue, final float alpha) {
        return;
    }

    @Override
    public void glColorMask(final boolean red, final boolean green, final boolean blue, final boolean alpha) {
        return;
    }

    @Override
    public void glColorPointer(final int size, final int type, final int stride, final Buffer pointer) {
        return;
    }

    @Override
    public void glCompressedTexImage2D(final int target, final int level, final int internalformat, final int width, final int height, final int border, final int imageSize, final Buffer data) {
        return;
    }

    @Override
    public void glCompressedTexSubImage2D(final int target, final int level, final int xoffset, final int yoffset, final int width, final int height, final int format, final int imageSize, final Buffer data) {
        return;
    }

    @Override
    public void glCopyTexImage2D(final int target, final int level, final int internalformat, final int x, final int y, final int width, final int height, final int border) {
        return;
    }

    @Override
    public void glCopyTexSubImage2D(final int target, final int level, final int xoffset, final int yoffset, final int x, final int y, final int width, final int height) {
        return;
    }

    @Override
    public void glCullFace(final int mode) {
        return;
    }

    @Override
    public void glDeleteTextures(final int n, final int[] textures, final int offset) {
        return;
    }

    @Override
    public void glDeleteTextures(final int n, final IntBuffer textures) {
        return;
    }

    @Override
    public void glDepthFunc(final int func) {
        return;
    }

    @Override
    public void glDepthMask(final boolean flag) {
        return;
    }

    @Override
    public void glDepthRangef(final float zNear, final float zFar) {
        return;
    }

    @Override
    public void glDisable(final int cap) {
        return;
    }

    @Override
    public void glDisableClientState(final int array) {
        return;
    }

    @Override
    public void glDrawArrays(final int mode, final int first, final int count) {
        return;
    }

    @Override
    public void glDrawElements(final int mode, final int count, final int type, final Buffer indices) {
        return;
    }

    @Override
    public void glEnable(final int cap) {
        return;
    }

    @Override
    public void glEnableClientState(final int array) {
        return;
    }

    @Override
    public void glFinish() {
        return;
    }

    @Override
    public void glFlush() {
        return;
    }

    @Override
    public void glFogf(final int pname, final float param) {
        return;
    }

    @Override
    public void glFogfv(final int pname, final float[] params, final int offset) {
        return;
    }

    @Override
    public void glFogfv(final int pname, final FloatBuffer params) {
        return;
    }

    @Override
    public void glFrontFace(final int mode) {
        return;
    }

    @Override
    public void glFrustumf(final float left, final float right, final float bottom, final float top, final float zNear, final float zFar) {
        return;
    }

    @Override
    public void glGenTextures(final int n, final int[] textures, final int offset) {
        return;
    }

    @Override
    public void glGenTextures(final int n, final IntBuffer textures) {
        return;
    }

    @Override
    public int glGetError() {
        return 0;
    }

    @Override
    public void glGetIntegerv(final int pname, final int[] params, final int offset) {
        return;
    }

    @Override
    public void glGetIntegerv(final int pname, final IntBuffer params) {
        return;
    }

    @Override
    public String glGetString(final int name) {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    @Override
    public void glHint(final int target, final int mode) {
        return;
    }

    @Override
    public void glLightModelf(final int pname, final float param) {
        return;
    }

    @Override
    public void glLightModelfv(final int pname, final float[] params, final int offset) {
        return;
    }

    @Override
    public void glLightModelfv(final int pname, final FloatBuffer params) {
        return;
    }

    @Override
    public void glLightf(final int light, final int pname, final float param) {
        return;
    }

    @Override
    public void glLightfv(final int light, final int pname, final float[] params, final int offset) {
        return;
    }

    @Override
    public void glLightfv(final int light, final int pname, final FloatBuffer params) {
        return;
    }

    @Override
    public void glLineWidth(final float width) {
        return;
    }

    @Override
    public void glLoadIdentity() {
        return;
    }

    @Override
    public void glLoadMatrixf(final float[] m, final int offset) {
        return;
    }

    @Override
    public void glLoadMatrixf(final FloatBuffer m) {
        return;
    }

    @Override
    public void glLogicOp(final int opcode) {
        return;
    }

    @Override
    public void glMaterialf(final int face, final int pname, final float param) {
        return;
    }

    @Override
    public void glMaterialfv(final int face, final int pname, final float[] params, final int offset) {
        return;
    }

    @Override
    public void glMaterialfv(final int face, final int pname, final FloatBuffer params) {
        return;
    }

    @Override
    public void glMatrixMode(final int mode) {
        return;
    }

    @Override
    public void glMultMatrixf(final float[] m, final int offset) {
        return;
    }

    @Override
    public void glMultMatrixf(final FloatBuffer m) {
        return;
    }

    @Override
    public void glMultiTexCoord4f(final int target, final float s, final float t, final float r, final float q) {
        return;
    }

    @Override
    public void glNormal3f(final float nx, final float ny, final float nz) {
        return;
    }

    @Override
    public void glNormalPointer(final int type, final int stride, final Buffer pointer) {
        return;
    }

    @Override
    public void glOrthof(final float left, final float right, final float bottom, final float top, final float zNear, final float zFar) {
        return;
    }

    @Override
    public void glPixelStorei(final int pname, final int param) {
        return;
    }

    @Override
    public void glPointSize(final float size) {
        return;
    }

    @Override
    public void glPolygonOffset(final float factor, final float units) {
        return;
    }

    @Override
    public void glPopMatrix() {
        return;
    }

    @Override
    public void glPushMatrix() {
        return;
    }

    @Override
    public void glReadPixels(final int x, final int y, final int width, final int height, final int format, final int type, final Buffer pixels) {
        return;
    }

    @Override
    public void glRotatef(final float angle, final float x, final float y, final float z) {
        return;
    }

    @Override
    public void glRotatex(final int angle, final int x, final int y, final int z) {
        return;
    }

    @Override
    public void glSampleCoverage(final float value, final boolean invert) {
        return;
    }

    @Override
    public void glScalef(final float x, final float y, final float z) {
        return;
    }

    @Override
    public void glScalex(final int x, final int y, final int z) {
        return;
    }

    @Override
    public void glScissor(final int x, final int y, final int width, final int height) {
        return;
    }

    @Override
    public void glShadeModel(final int mode) {
        return;
    }

    @Override
    public void glStencilFunc(final int func, final int ref, final int mask) {
        return;
    }

    @Override
    public void glStencilMask(final int mask) {
        return;
    }

    @Override
    public void glStencilOp(final int fail, final int zfail, final int zpass) {
        return;
    }

    @Override
    public void glTexCoordPointer(final int size, final int type, final int stride, final Buffer pointer) {
        return;
    }

    @Override
    public void glTexEnvf(final int target, final int pname, final float param) {
        return;
    }

    @Override
    public void glTexEnvfv(final int target, final int pname, final float[] params, final int offset) {
        return;
    }

    @Override
    public void glTexEnvfv(final int target, final int pname, final FloatBuffer params) {
        return;
    }

    @Override
    public void glTexImage2D(final int target, final int level, final int internalformat, final int width, final int height, final int border, final int format, final int type, final Buffer pixels) {
        return;
    }

    @Override
    public void glTexParameterf(final int target, final int pname, final float param) {
        return;
    }

    @Override
    public void glTexParameteri(final int target, final int pname, final int param) {
        return;
    }

    @Override
    public void glTexSubImage2D(final int target, final int level, final int xoffset, final int yoffset, final int width, final int height, final int format, final int type, final Buffer pixels) {
        return;
    }

    @Override
    public void glTranslatef(final float x, final float y, final float z) {
        return;
    }

    @Override
    public void glVertexPointer(final int size, final int type, final int stride, final Buffer pointer) {
        return;
    }

    @Override
    public void glViewport(final int x, final int y, final int width, final int height) {
        return;
    }

    @Override
    public void glBegin(final int i) {
        return;
    }

    @Override
    public void glEnd() {
        return;
    }

    @Override
    public void glVertex3f(final float f, final float f2, final float f3) {
        return;
    }

    @Override
    public void glPolygonMode(final int i, final int i1) {
        return;
    }
}
