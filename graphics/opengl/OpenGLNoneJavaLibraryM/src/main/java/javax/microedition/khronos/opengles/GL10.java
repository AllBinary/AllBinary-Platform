package javax.microedition.khronos.opengles;

public interface GL10 extends GL {

    void glActiveTexture(int texture);

    void glAlphaFunc(int func, float ref);

    //void glAlphaFuncx(int func, int ref);

    void glBindTexture(int target, int texture);

    void glBlendFunc(int sfactor, int dfactor);

    void glClear(int mask);

    void glClearColor(float red, float green, float blue, float alpha);

    //void glClearColorx(int red, int green, int blue, int alpha);

    void glClearDepthf(float depth);

    //void glClearDepthx(int depth);

    void glClearStencil(int s);

    void glClientActiveTexture(int texture);

    void glColor4f(float red, float green, float blue, float alpha);

    //void glColor4x(int red, int green, int blue, int alpha);

    void glColorMask(boolean red, boolean green, boolean blue, boolean alpha);

    void glColorPointer(int size, int type, int stride, java.nio.Buffer pointer);

    void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, java.nio.Buffer data);

    void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, java.nio.Buffer data);

    void glCopyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height, int border);

    void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height);

    void glCullFace(int mode);

    void glDeleteTextures(int n, int[] textures, int offset);

    void glDeleteTextures(int n, java.nio.IntBuffer textures);

    void glDepthFunc(int func);

    void glDepthMask(boolean flag);

    void glDepthRangef(float zNear, float zFar);

    //void glDepthRangex(int zNear, int zFar);

    void glDisable(int cap);

    void glDisableClientState(int array);

    void glDrawArrays(int mode, int first, int count);

    void glDrawElements(int mode, int count, int type, java.nio.Buffer indices);

    void glEnable(int cap);

    void glEnableClientState(int array);

    void glFinish();

    void glFlush();

    void glFogf(int pname, float param);

    void glFogfv(int pname, float[] params, int offset);

    void glFogfv(int pname, java.nio.FloatBuffer params);

    //void glFogx(int pname, int param);

    //void glFogxv(int pname, int[] params, int offset);

    //void glFogxv(int pname, java.nio.IntBuffer params);

    void glFrontFace(int mode);

    void glFrustumf(float left, float right, float bottom, float top, float zNear, float zFar);

    //void glFrustumx(int left, int right, int bottom, int top, int zNear, int zFar);

    void glGenTextures(int n, int[] textures, int offset);

    void glGenTextures(int n, java.nio.IntBuffer textures);

    int glGetError();

    void glGetIntegerv(int pname, int[] params, int offset);

    void glGetIntegerv(int pname, java.nio.IntBuffer params);

    java.lang.String glGetString(int name);

    void glHint(int target, int mode);

    void glLightModelf(int pname, float param);

    void glLightModelfv(int pname, float[] params, int offset);

    void glLightModelfv(int pname, java.nio.FloatBuffer params);

    //void glLightModelx(int pname, int param);

    //void glLightModelxv(int pname, int[] params, int offset);

    //void glLightModelxv(int pname, java.nio.IntBuffer params);

    void glLightf(int light, int pname, float param);

    void glLightfv(int light, int pname, float[] params, int offset);

    void glLightfv(int light, int pname, java.nio.FloatBuffer params);

    //void glLightx(int light, int pname, int param);

    //void glLightxv(int light, int pname, int[] params, int offset);

    //void glLightxv(int light, int pname, java.nio.IntBuffer params);

    void glLineWidth(float width);

    //void glLineWidthx(int width);

    void glLoadIdentity();

    void glLoadMatrixf(float[] m, int offset);

    void glLoadMatrixf(java.nio.FloatBuffer m);

    //void glLoadMatrixx(int[] m, int offset);

    //void glLoadMatrixx(java.nio.IntBuffer m);

    void glLogicOp(int opcode);

    void glMaterialf(int face, int pname, float param);

    void glMaterialfv(int face, int pname, float[] params, int offset);

    void glMaterialfv(int face, int pname, java.nio.FloatBuffer params);

    //void glMaterialx(int face, int pname, int param);

    //void glMaterialxv(int face, int pname, int[] params, int offset);

    //void glMaterialxv(int face, int pname, java.nio.IntBuffer params);

    void glMatrixMode(int mode);

    void glMultMatrixf(float[] m, int offset);

    void glMultMatrixf(java.nio.FloatBuffer m);

    //void glMultMatrixx(int[] m, int offset);

    //void glMultMatrixx(java.nio.IntBuffer m);

    void glMultiTexCoord4f(int target, float s, float t, float r, float q);

    //void glMultiTexCoord4x(int target, int s, int t, int r, int q);

    void glNormal3f(float nx, float ny, float nz);

    //void glNormal3x(int nx, int ny, int nz);

    void glNormalPointer(int type, int stride, java.nio.Buffer pointer);

    void glOrthof(float left, float right, float bottom, float top, float zNear, float zFar);

    //void glOrthox(int left, int right, int bottom, int top, int zNear, int zFar);

    void glPixelStorei(int pname, int param);

    void glPointSize(float size);

    //void glPointSizex(int size);

    void glPolygonOffset(float factor, float units);

    //void glPolygonOffsetx(int factor, int units);

    void glPopMatrix();

    void glPushMatrix();

    void glReadPixels(int x, int y, int width, int height, int format, int type, java.nio.Buffer pixels);

    void glRotatef(float angle, float x, float y, float z);

    void glRotatex(int angle, int x, int y, int z);

    void glSampleCoverage(float value, boolean invert);

    //void glSampleCoveragex(int value, boolean invert);

    void glScalef(float x, float y, float z);

    void glScalex(int x, int y, int z);

    void glScissor(int x, int y, int width, int height);

    void glShadeModel(int mode);

    void glStencilFunc(int func, int ref, int mask);

    void glStencilMask(int mask);

    void glStencilOp(int fail, int zfail, int zpass);

    void glTexCoordPointer(int size, int type, int stride, java.nio.Buffer pointer);

    void glTexEnvf(int target, int pname, float param);

    void glTexEnvfv(int target, int pname, float[] params, int offset);

    void glTexEnvfv(int target, int pname, java.nio.FloatBuffer params);

    //void glTexEnvx(int target, int pname, int param);

    //void glTexEnvxv(int target, int pname, int[] params, int offset);

    //void glTexEnvxv(int target, int pname, java.nio.IntBuffer params);

    void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, java.nio.Buffer pixels);

    void glTexParameterf(int target, int pname, float param);

    //void glTexParameterx(int target, int pname, int param);
    
    void glTexParameteri(int target, int pname, int param);

    void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, java.nio.Buffer pixels);

    void glTranslatef(float x, float y, float z);

    //void glTranslatex(int x, int y, int z);

    void glVertexPointer(int size, int type, int stride, java.nio.Buffer pointer);

    void glViewport(int x, int y, int width, int height);

    //These below are for the example only
    public void glBegin(int i);

    public void glEnd();
        
    public void glVertex3f(float f, float f2, float f3);
    
    public void glPolygonMode(int i, int i1);
    
}
