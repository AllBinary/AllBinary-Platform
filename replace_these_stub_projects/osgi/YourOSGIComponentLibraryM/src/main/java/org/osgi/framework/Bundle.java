package org.osgi.framework;

public interface Bundle {
    
    public String getSymbolicName();

    public void start() throws BundleException;

    public void start(int options) throws BundleException;
}
