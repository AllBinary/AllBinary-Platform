package org.osgi.framework;

public interface ServiceReference extends Comparable {
    
    public Object getProperty(String key);

    public String[] getPropertyKeys();
    
}
