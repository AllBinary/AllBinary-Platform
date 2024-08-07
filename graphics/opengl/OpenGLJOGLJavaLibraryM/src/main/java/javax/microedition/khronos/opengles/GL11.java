package javax.microedition.khronos.opengles;

public interface GL11
    extends javax.microedition.khronos.opengles.GL10 {

    public static final int GL_ACTIVE_TEXTURE = 34016;
    public static final int GL_ADD_SIGNED = 34164;
    public static final int GL_ALPHA_SCALE = 3356;
    public static final int GL_ALPHA_TEST_FUNC = 3009;
    public static final int GL_ALPHA_TEST_REF = 3010;
    public static final int GL_ARRAY_BUFFER = 34962;
    public static final int GL_ARRAY_BUFFER_BINDING = 34964;
    public static final int GL_BLEND_DST = 3040;
    public static final int GL_BLEND_SRC = 3041;
    public static final int GL_BUFFER_ACCESS = 35003;
    public static final int GL_BUFFER_SIZE = 34660;
    public static final int GL_BUFFER_USAGE = 34661;
    public static final int GL_CLIENT_ACTIVE_TEXTURE = 34017;
    public static final int GL_CLIP_PLANE0 = 12288;
    public static final int GL_CLIP_PLANE1 = 12289;
    public static final int GL_CLIP_PLANE2 = 12290;
    public static final int GL_CLIP_PLANE3 = 12291;
    public static final int GL_CLIP_PLANE4 = 12292;
    public static final int GL_CLIP_PLANE5 = 12293;
    public static final int GL_COLOR_ARRAY_BUFFER_BINDING = 34968;
    public static final int GL_COLOR_ARRAY_POINTER = 32912;
    public static final int GL_COLOR_ARRAY_SIZE = 32897;
    public static final int GL_COLOR_ARRAY_STRIDE = 32899;
    public static final int GL_COLOR_ARRAY_TYPE = 32898;
    public static final int GL_COLOR_CLEAR_VALUE = 3106;
    public static final int GL_COLOR_WRITEMASK = 3107;
    public static final int GL_COMBINE = 34160;
    public static final int GL_COMBINE_ALPHA = 34162;
    public static final int GL_COMBINE_RGB = 34161;
    public static final int GL_CONSTANT = 34166;
    public static final int GL_COORD_REPLACE_OES = 34914;
    public static final int GL_CULL_FACE_MODE = 2885;
    public static final int GL_CURRENT_COLOR = 2816;
    public static final int GL_CURRENT_NORMAL = 2818;
    public static final int GL_CURRENT_TEXTURE_COORDS = 2819;
    public static final int GL_DEPTH_CLEAR_VALUE = 2931;
    public static final int GL_DEPTH_FUNC = 2932;
    public static final int GL_DEPTH_RANGE = 2928;
    public static final int GL_DEPTH_WRITEMASK = 2930;
    public static final int GL_DOT3_RGB = 34478;
    public static final int GL_DOT3_RGBA = 34479;
    public static final int GL_DYNAMIC_DRAW = 35048;
    public static final int GL_ELEMENT_ARRAY_BUFFER = 34963;
    public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 34965;
    public static final int GL_FRONT_FACE = 2886;
    public static final int GL_GENERATE_MIPMAP = 33169;
    public static final int GL_GENERATE_MIPMAP_HINT = 33170;
    public static final int GL_INTERPOLATE = 34165;
    public static final int GL_LINE_WIDTH = 2849;
    public static final int GL_LOGIC_OP_MODE = 3056;
    public static final int GL_MATRIX_MODE = 2976;
    public static final int GL_MAX_CLIP_PLANES = 3378;
    public static final int GL_MODELVIEW_MATRIX = 2982;
    public static final int GL_MODELVIEW_MATRIX_FLOAT_AS_INT_BITS_OES = 35213;
    public static final int GL_MODELVIEW_STACK_DEPTH = 2979;
    public static final int GL_NORMAL_ARRAY_BUFFER_BINDING = 34967;
    public static final int GL_NORMAL_ARRAY_POINTER = 32911;
    public static final int GL_NORMAL_ARRAY_STRIDE = 32895;
    public static final int GL_NORMAL_ARRAY_TYPE = 32894;
    public static final int GL_OPERAND0_ALPHA = 34200;
    public static final int GL_OPERAND0_RGB = 34192;
    public static final int GL_OPERAND1_ALPHA = 34201;
    public static final int GL_OPERAND1_RGB = 34193;
    public static final int GL_OPERAND2_ALPHA = 34202;
    public static final int GL_OPERAND2_RGB = 34194;
    public static final int GL_POINT_DISTANCE_ATTENUATION = 33065;
    public static final int GL_POINT_FADE_THRESHOLD_SIZE = 33064;
    public static final int GL_POINT_SIZE = 2833;
    public static final int GL_POINT_SIZE_ARRAY_BUFFER_BINDING_OES = 35743;
    public static final int GL_POINT_SIZE_ARRAY_OES = 35740;
    public static final int GL_POINT_SIZE_ARRAY_POINTER_OES = 35212;
    public static final int GL_POINT_SIZE_ARRAY_STRIDE_OES = 35211;
    public static final int GL_POINT_SIZE_ARRAY_TYPE_OES = 35210;
    public static final int GL_POINT_SIZE_MAX = 33063;
    public static final int GL_POINT_SIZE_MIN = 33062;
    public static final int GL_POINT_SPRITE_OES = 34913;
    public static final int GL_POLYGON_OFFSET_FACTOR = 32824;
    public static final int GL_POLYGON_OFFSET_UNITS = 10752;
    public static final int GL_PREVIOUS = 34168;
    public static final int GL_PRIMARY_COLOR = 34167;
    public static final int GL_PROJECTION_MATRIX = 2983;
    public static final int GL_PROJECTION_MATRIX_FLOAT_AS_INT_BITS_OES = 35214;
    public static final int GL_PROJECTION_STACK_DEPTH = 2980;
    public static final int GL_RGB_SCALE = 34163;
    public static final int GL_SAMPLE_BUFFERS = 32936;
    public static final int GL_SAMPLE_COVERAGE_INVERT = 32939;
    public static final int GL_SAMPLE_COVERAGE_VALUE = 32938;
    public static final int GL_SAMPLES = 32937;
    public static final int GL_SCISSOR_BOX = 3088;
    public static final int GL_SHADE_MODEL = 2900;
    public static final int GL_SRC0_ALPHA = 34184;
    public static final int GL_SRC0_RGB = 34176;
    public static final int GL_SRC1_ALPHA = 34185;
    public static final int GL_SRC1_RGB = 34177;
    public static final int GL_SRC2_ALPHA = 34186;
    public static final int GL_SRC2_RGB = 34178;
    public static final int GL_STATIC_DRAW = 35044;
    public static final int GL_STENCIL_CLEAR_VALUE = 2961;
    public static final int GL_STENCIL_FAIL = 2964;
    public static final int GL_STENCIL_FUNC = 2962;
    public static final int GL_STENCIL_PASS_DEPTH_FAIL = 2965;
    public static final int GL_STENCIL_PASS_DEPTH_PASS = 2966;
    public static final int GL_STENCIL_REF = 2967;
    public static final int GL_STENCIL_VALUE_MASK = 2963;
    public static final int GL_STENCIL_WRITEMASK = 2968;
    public static final int GL_SUBTRACT = 34023;
    public static final int GL_TEXTURE_BINDING_2D = 32873;
    public static final int GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING = 34970;
    public static final int GL_TEXTURE_COORD_ARRAY_POINTER = 32914;
    public static final int GL_TEXTURE_COORD_ARRAY_SIZE = 32904;
    public static final int GL_TEXTURE_COORD_ARRAY_STRIDE = 32906;
    public static final int GL_TEXTURE_COORD_ARRAY_TYPE = 32905;
    public static final int GL_TEXTURE_MATRIX = 2984;
    public static final int GL_TEXTURE_MATRIX_FLOAT_AS_INT_BITS_OES = 35215;
    public static final int GL_TEXTURE_STACK_DEPTH = 2981;
    public static final int GL_VERTEX_ARRAY_BUFFER_BINDING = 34966;
    public static final int GL_VERTEX_ARRAY_POINTER = 32910;
    public static final int GL_VERTEX_ARRAY_SIZE = 32890;
    public static final int GL_VERTEX_ARRAY_STRIDE = 32892;
    public static final int GL_VERTEX_ARRAY_TYPE = 32891;
    public static final int GL_VIEWPORT = 2978;
    public static final int GL_WRITE_ONLY = 35001;

    //void glGetPointerv(int pname, java.nio.Buffer[] params);

    void glBindBuffer(int target, int buffer);

    void glBufferData(int target, int size, java.nio.Buffer data, int usage);

    void glBufferSubData(int target, int offset, int size, java.nio.Buffer data);

    void glClipPlanef(int plane, float[] equation, int offset);

    void glClipPlanef(int plane, java.nio.FloatBuffer equation);

    //void glClipPlanex(int plane, int[] equation, int offset);

    //void glClipPlanex(int plane, java.nio.IntBuffer equation);

    void glColor4ub(byte red, byte green, byte blue, byte alpha);

    void glColorPointer(int size, int type, int stride, int offset);

    void glDeleteBuffers(int n, int[] buffers, int offset);

    void glDeleteBuffers(int n, java.nio.IntBuffer buffers);

    void glDrawElements(int mode, int count, int type, int offset);

    void glGenBuffers(int n, int[] buffers, int offset);

    void glGenBuffers(int n, java.nio.IntBuffer buffers);

    //void glGetBooleanv(int pname, boolean[] params, int offset);

    //void glGetBooleanv(int pname, java.nio.IntBuffer params);

    void glGetBufferParameteriv(int target, int pname, int[] params, int offset);

    void glGetBufferParameteriv(int target, int pname, java.nio.IntBuffer params);

    void glGetClipPlanef(int pname, float[] eqn, int offset);

    void glGetClipPlanef(int pname, java.nio.FloatBuffer eqn);

    //void glGetClipPlanex(int pname, int[] eqn, int offset);

    //void glGetClipPlanex(int pname, java.nio.IntBuffer eqn);

    //void glGetFixedv(int pname, int[] params, int offset);

    //void glGetFixedv(int pname, java.nio.IntBuffer params);

    void glGetFloatv(int pname, float[] params, int offset);

    void glGetFloatv(int pname, java.nio.FloatBuffer params);

    void glGetLightfv(int light, int pname, float[] params, int offset);

    void glGetLightfv(int light, int pname, java.nio.FloatBuffer params);

    //void glGetLightxv(int light, int pname, int[] params, int offset);

    //void glGetLightxv(int light, int pname, java.nio.IntBuffer params);

    void glGetMaterialfv(int face, int pname, float[] params, int offset);

    void glGetMaterialfv(int face, int pname, java.nio.FloatBuffer params);

    //void glGetMaterialxv(int face, int pname, int[] params, int offset);

    //void glGetMaterialxv(int face, int pname, java.nio.IntBuffer params);

    void glGetTexEnviv(int env, int pname, int[] params, int offset);

    void glGetTexEnviv(int env, int pname, java.nio.IntBuffer params);

    //void glGetTexEnvxv(int env, int pname, int[] params, int offset);

    //void glGetTexEnvxv(int env, int pname, java.nio.IntBuffer params);

    void glGetTexParameterfv(int target, int pname, float[] params, int offset);

    void glGetTexParameterfv(int target, int pname, java.nio.FloatBuffer params);

    void glGetTexParameteriv(int target, int pname, int[] params, int offset);

    void glGetTexParameteriv(int target, int pname, java.nio.IntBuffer params);

    //void glGetTexParameterxv(int target, int pname, int[] params, int offset);

    //void glGetTexParameterxv(int target, int pname, java.nio.IntBuffer params);

    boolean glIsBuffer(int buffer);

    boolean glIsEnabled(int cap);

    boolean glIsTexture(int texture);

    void glNormalPointer(int type, int stride, int offset);

    void glPointParameterf(int pname, float param);

    void glPointParameterfv(int pname, float[] params, int offset);

    void glPointParameterfv(int pname, java.nio.FloatBuffer params);

    //void glPointParameterx(int pname, int param);

    //void glPointParameterxv(int pname, int[] params, int offset);

    //void glPointParameterxv(int pname, java.nio.IntBuffer params);

    //void glPointSizePointerOES(int type, int stride, java.nio.Buffer pointer);

    void glTexCoordPointer(int size, int type, int stride, int offset);

    void glTexEnvi(int target, int pname, int param);

    void glTexEnviv(int target, int pname, int[] params, int offset);

    void glTexEnviv(int target, int pname, java.nio.IntBuffer params);

    void glTexParameterfv(int target, int pname, float[] params, int offset);

    void glTexParameterfv(int target, int pname, java.nio.FloatBuffer params);

    void glTexParameteri(int target, int pname, int param);

    void glTexParameteriv(int target, int pname, int[] params, int offset);

    void glTexParameteriv(int target, int pname, java.nio.IntBuffer params);

    //void glTexParameterxv(int target, int pname, int[] params, int offset);

    //void glTexParameterxv(int target, int pname, java.nio.IntBuffer params);

    void glVertexPointer(int size, int type, int stride, int offset);
}
