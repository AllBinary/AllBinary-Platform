package org.osgi.framework;

import java.util.Dictionary;

public interface BundleContext {
    
    Bundle[] getBundles();
    
    Bundle installBundle(String location) throws BundleException;

    ServiceRegistration registerService(String clazz,
            Object service, Dictionary properties);
        
    Object getService(ServiceReference reference);
    
    ServiceReference[] getServiceReferences(String clazz,
            String filter) throws InvalidSyntaxException;

    ServiceReference getServiceReference(String clazz);
}
