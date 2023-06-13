package org.osgi.framework;

import java.util.Dictionary;

public interface BundleContext {
    
    public Bundle[] getBundles();
    
    public Bundle installBundle(String location) throws BundleException;

    public ServiceRegistration registerService(String clazz,
            Object service, Dictionary properties);
        
    public Object getService(ServiceReference reference);
    
    public ServiceReference[] getServiceReferences(String clazz,
            String filter) throws InvalidSyntaxException;

    public ServiceReference getServiceReference(String clazz);
}
