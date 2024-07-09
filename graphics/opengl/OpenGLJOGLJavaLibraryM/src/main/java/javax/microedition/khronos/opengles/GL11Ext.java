package javax.microedition.khronos.opengles;

public interface GL11Ext
    extends javax.microedition.khronos.opengles.GL {

    public static final int GL_MATRIX_INDEX_ARRAY_BUFFER_BINDING_OES = 35742;
    public static final int GL_MATRIX_INDEX_ARRAY_OES = 34884;
    public static final int GL_MATRIX_INDEX_ARRAY_POINTER_OES = 34889;
    public static final int GL_MATRIX_INDEX_ARRAY_SIZE_OES = 34886;
    public static final int GL_MATRIX_INDEX_ARRAY_STRIDE_OES = 34888;
    public static final int GL_MATRIX_INDEX_ARRAY_TYPE_OES = 34887;
    public static final int GL_MATRIX_PALETTE_OES = 34880;
    public static final int GL_MAX_PALETTE_MATRICES_OES = 34882;
    public static final int GL_MAX_VERTEX_UNITS_OES = 34468;
    public static final int GL_TEXTURE_CROP_RECT_OES = 35741;
    public static final int GL_WEIGHT_ARRAY_BUFFER_BINDING_OES = 34974;
    public static final int GL_WEIGHT_ARRAY_OES = 34477;
    public static final int GL_WEIGHT_ARRAY_POINTER_OES = 34476;
    public static final int GL_WEIGHT_ARRAY_SIZE_OES = 34475;
    public static final int GL_WEIGHT_ARRAY_STRIDE_OES = 34474;
    public static final int GL_WEIGHT_ARRAY_TYPE_OES = 34473;
    
    void glTexParameterfv(int target, int pname, float[] param, int offset);

    void glCurrentPaletteMatrixOES(int matrixpaletteindex);

    void glDrawTexfOES(float x, float y, float z, float width, float height);

    void glDrawTexfvOES(float[] coords, int offset);

    void glDrawTexfvOES(java.nio.FloatBuffer coords);

    void glDrawTexiOES(int x, int y, int z, int width, int height);

    void glDrawTexivOES(int[] coords, int offset);

    void glDrawTexivOES(java.nio.IntBuffer coords);

    void glDrawTexsOES(short x, short y, short z, short width, short height);

    void glDrawTexsvOES(short[] coords, int offset);

    void glDrawTexsvOES(java.nio.ShortBuffer coords);

    void glDrawTexxOES(int x, int y, int z, int width, int height);

    void glDrawTexxvOES(int[] coords, int offset);

    void glDrawTexxvOES(java.nio.IntBuffer coords);

    void glEnable(int cap);

    void glEnableClientState(int array);

    void glLoadPaletteFromModelViewMatrixOES();

    void glMatrixIndexPointerOES(int size, int type, int stride, java.nio.Buffer pointer);

    void glMatrixIndexPointerOES(int size, int type, int stride, int offset);

    void glWeightPointerOES(int size, int type, int stride, java.nio.Buffer pointer);

    void glWeightPointerOES(int size, int type, int stride, int offset);
}
