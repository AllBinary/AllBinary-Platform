/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.logic.visual.transform.info.objectConfig;

import org.allbinary.data.tree.dom.document.mapping.DomDocumentMappingInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import java.util.Vector;

public interface TransformInfoObjectConfigInterface
   extends DomDocumentMappingInterface
{
   //All of the following methods should eventually be removed when subclassing is setup for objectConfigs
   
   //TransformInfoObjectConfigGenerator getGeneratorNode() throws Exception;
   
   //void update(TransformInfoInterface transformInfoInterface, String newMapping) throws Exception;
   
   ///void delete(TransformInfoInterface transformInfoInterface, String newMapping) throws Exception;
   
   //void insert(TransformInfoInterface transformInfoInterface, String newMapping) throws Exception;
      
   //Looks to see if view is in this TransformInfoObjectConfig
   //Usually used for updating a sites templates
   boolean containsView(TransformInfoInterface transformInfoInterface);
   
   String getName() throws Exception;

   Vector getTransformsGroup(String group) throws Exception;
   //set a single parent compoenent that is the template
   //void set(TransformInfoInterface transformInfoInterface) throws Exception;
   //void set(String aParentViewName) throws Exception;
   //String get() throws Exception;
   //InputStream createInputStream() throws Exception;

   Vector getTransforms() throws Exception;
   Vector getParentTransforms() throws Exception;
   Vector getGroupTransforms() throws Exception;
   
   String getOutputTypeName() throws Exception;
   void setOutputTypeName(String value);

   String getInputOutputTypeName() throws Exception;
   String getInputOutputTypeFile() throws Exception;
   String getImportUriPath() throws Exception;

   //void setImportUriPath(String importUriPath);
   
   String toString();
}
