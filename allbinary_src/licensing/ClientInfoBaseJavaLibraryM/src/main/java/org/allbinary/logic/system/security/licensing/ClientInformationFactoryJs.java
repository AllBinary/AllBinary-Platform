package org.allbinary.logic.system.security.licensing;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public interface ClientInformationFactoryJs {
    ClientInformation getInstance();
}