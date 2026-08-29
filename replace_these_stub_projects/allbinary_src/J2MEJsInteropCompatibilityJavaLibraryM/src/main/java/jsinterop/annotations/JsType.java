package jsinterop.annotations;

//J2SE to J2ME compatibility
public @interface JsType {

    String name() default "<auto>";

    String namespace() default "<auto>";

    boolean isNative() default false;

}
