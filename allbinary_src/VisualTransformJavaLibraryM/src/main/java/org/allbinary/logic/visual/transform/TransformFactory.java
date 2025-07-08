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
package org.allbinary.logic.visual.transform;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.transform.info.TransformInfoEntity;
import org.allbinary.data.tables.transform.info.TransformInfoEntityBuilder;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoData;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoObjectFactory;
import org.allbinary.string.CommonStrings;

public class TransformFactory {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final TransformFactory instance = new TransformFactory();

    public static TransformFactory getInstance() {
        return instance;
    }

    private TransformFactory() {
    }


    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    //create child instance from objectConfig data stored in 
    //another views info and do not use request info
    public TransformInterface getInstance(
        final AbeClientInformationInterface abeClientInformation, final String viewName, final TransformInfoInterface ownerTransformInfoInterface)
        throws Exception {
        try {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                logUtil.put("Creating Transform: " + viewName,this, commonStrings.GET_INSTANCE);
            }

            TransformInfoHttpInterface ownerTransformInfoHttpInterface =
                (TransformInfoHttpInterface) ownerTransformInfoInterface;

            TransformInfoEntity transformInfoEntity =
                TransformInfoEntityBuilder.getInstance();

            TransformInfoInterface transformInfoInterface =
                transformInfoEntity.get(
                    viewName, ownerTransformInfoHttpInterface.getPropertiesHashMap(),
                    ownerTransformInfoHttpInterface.getPageContext());

            if (transformInfoInterface == null) {
                throw new Exception("No Such View In DB: " + viewName);
            }

            transformInfoInterface.setChild();

            final Object object = TransformInfoObjectFactory.getInstance().getInstance(abeClientInformation, transformInfoInterface);

            /*
         if(!(object instanceof TransformInterface))
         {
            throw new Exception("View Object Not Instance Of TransformInterface but is: " + ClassUtil.viewAll(object,"\n"));
         }
         else
             */
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                logUtil.put("Created Transform: " + viewName, this,
                    commonStrings.GET_INSTANCE);
            }

            return (TransformInterface) object;
        } catch (Exception e) {
            //String error = "Failed To Get Instance: " + viewName + "->TransformFactory";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR)) {
                logUtil.put(commonStrings.EXCEPTION, this,
                    commonStrings.GET_INSTANCE, e);
            }
            throw e;
        }
    }

    //create a root/parent instance from db and/or request
    public TransformInterface getInstance(
        final AbeClientInformationInterface abeClientInformation,
        final HashMap propertiesHashMap, final PageContext pageContext)
        throws Exception {
        try {
            final TransformInfoData transformInfoData = TransformInfoData.getInstance();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                logUtil.put("Creating Transform: " + propertiesHashMap.get(transformInfoData.NAME),this, commonStrings.GET_INSTANCE);
            }

            final TransformInfoEntity transformInfoEntity =
                TransformInfoEntityBuilder.getInstance();

            TransformInfoInterface transformInfoInterface =
                transformInfoEntity.get(
                    (String) propertiesHashMap.get(transformInfoData.NAME),
                    propertiesHashMap, pageContext);

            if (transformInfoInterface != null) {
                transformInfoInterface.override(propertiesHashMap);
            } else {
                transformInfoInterface = (TransformInfoInterface) TransformInfoHttpFactory.getInstance(propertiesHashMap, pageContext);
            }

            final Object object = TransformInfoObjectFactory.getInstance().getInstance(abeClientInformation, transformInfoInterface);

            /*
         if(!(object instanceof TransformInterface)) 
         {
            throw new Exception("View Object Not Instance Of TransformInterface but is: " + 
               ClassUtil.viewAll(object,"\n"));
         }
         else
             */
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                logUtil.put("Created Transform: "
                    + propertiesHashMap.get(transformInfoData.NAME), this, commonStrings.GET_INSTANCE);
            }
            return (TransformInterface) object;
        } catch (Exception e) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR)) {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Failed To Get Instance: ");
                stringBuffer.append((String) propertiesHashMap.get(TransformInfoData.getInstance().NAME));
                stringBuffer.append("->TransformFactory");

                logUtil.put(stringBuffer.toString(), this, commonStrings.GET_INSTANCE, e);
            }
            throw e;
        }
    }

    //create a root instance from a root TransformInfoInterface
    public TransformInterface getInstance(
        final AbeClientInformationInterface abeClientInformation,
        final TransformInfoInterface transformInfoInterface)
        throws Exception {
        try {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                logUtil.put("Creating Transform: " + transformInfoInterface.getName(),this, "getInstance(TransformInfoInterface)");
            }

            final Object object = TransformInfoObjectFactory.getInstance().getInstance(
                abeClientInformation, transformInfoInterface);

            /*
         if(!(object instanceof TransformInterface)) 
         {
            throw new Exception("View Object Not Instance Of TransformInterface but is: " + ClassUtil.viewAll(object,"\n"));
         }
         else
             */
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY)) {
                logUtil.put("Created Transform: " + transformInfoInterface.getName(),this, "getInstance(TransformInfoInterface)");
            }
            return (TransformInterface) object;
        } catch (Exception e) {
            //String error = "Failed To Get Instance: " + transformInfoInterface.getName();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR)) {
                logUtil.put(commonStrings.EXCEPTION, this, "getInstance(TransformInfoInterface)", e);
            }
            throw e;
        }
    }
}
