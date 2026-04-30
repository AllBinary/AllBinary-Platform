package org.osgi.framework;

public interface ServiceReference extends Comparable {
    
    Object getProperty(String key);

    String[] getPropertyKeys();
    
}
