package org.osgi.framework;

public interface Bundle {
    
    String getSymbolicName();

    void start() throws BundleException;

    void start(int options) throws BundleException;
}
