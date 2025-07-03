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
package org.allbinary.data.tree.category;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.category.CategoryData;
import org.allbinary.business.category.CategoryFactoryInterface;
import org.allbinary.business.category.CategoryInterface;
import org.allbinary.business.category.properties.CategoryProperties;
import org.allbinary.business.category.properties.CategoryPropertiesInterface;
import org.allbinary.logic.control.crypt.file.CryptFileReader;

public class CategoryLoader 
    extends CategoryModifierTree 
    implements CategoryLoaderInterface
{
    public CategoryLoader(CategoryFactoryInterface categoryFactoryInterface)
    {
        super(categoryFactoryInterface);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
        {
            LogUtil.put(LogFactory.getInstance("Constructing", this, "CategoryLoader(CategoryFactoryInterface categoryFactoryInterface)"));
        }
    }

    //Creates new root category if possible
    public synchronized Document getDoc(CategoryInterface categoryInterface)
    {
        try
        {
            if (categoryInterface.getProperties().getPath(
                categoryInterface.getHierarchy()) != null)
            {
                //LogUtil.put(LogFactory.getInstance("Level: " + categoryInterface.getHierarchy().getLevel(), this, "getDoc"));
                if (categoryInterface.getHierarchy().getLevel() == 1)
                {
                    AbFile rootAbFile = new AbFile(categoryInterface.getRootFilePath());

                    if (!rootAbFile.isDirectory())
                    {
                        rootAbFile.mkdirs();

                        if (!rootAbFile.isDirectory())
                        {
                            throw new Exception("Could Not Create Directory");
                        }
                        this.save(categoryInterface);
                    }
                }

                AbPath fileAbPath = categoryInterface.getFilePath();

                CryptFileReader cryptFileReader = new CryptFileReader(
                    CategoryData.getInstance().UNCRYPTED_EXTENSION,
                    CategoryData.getInstance().ENCRYPTED_EXTENSION);

                Document document = DomDocumentHelper.create(
                    cryptFileReader.get(fileAbPath));

                return document;

            } else
            {
                throw new Exception("Null Category");
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                try
                {
                    categoryInterface.log();
                } catch (Exception e2)
                {
                    LogUtil.put(LogFactory.getInstance("Could Not Log Category", this, "getDoc", e));
                }
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "getDoc", e));
            }
            return null;
        }
    }

    /*
    public synchronized CategoryInterface get(CategoryInterface categoryInterface)
    {
    try
    {
    Document document = this.getDoc(categoryInterface);

    return this.get(document);
    }
    catch(Exception e)
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
    {
    LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, commonStrings.GET, e);
    }
    return null;
    }
    }
     */
    public synchronized CategoryInterface get(CategoryInterface categoryInterface)
    {
        try
        {
            Document document = this.getDoc(categoryInterface);

            Node categoryNode = DomSearchHelper.getNode(
                CategoryData.getInstance().NAME, document.getChildNodes());

            return this.addProperties(categoryInterface, categoryNode.getChildNodes());
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, commonStrings.GET, e));
            }
            return null;
        }
    }

    //Retrieve up to the specified level
   /*
    public synchronized CategoryInterface getUpToLevel(
    CategoryInterface categoryInterface, int level)
    {
    try
    {
    if(level < 0) throw new Exception("Level Must Be A Positive Number");
    
    Document document = this.getDoc(categoryInterface);
    
    return this.get(document);
    }
    catch(Exception e)
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
    {
    LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "getUpToLevel", e);
    }
    return null;
    }
    }
    
    //This loads everything as a result could use up a bunch of memory
    public synchronized CategoryInterface getAll(
    CategoryInterface categoryInterface)
    {
    try
    {
    Document document = this.getDoc(categoryInterface);
    
    //CategoryInterface categoryInterface =
    // (CategoryInterface) categoryFactoryInterface.getInstance(
    //  categoryPath + FileData.SEPARATOR + categoryName);
    
    return this.get(document);
    }
    catch(Exception e)
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
    {
    LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "getAll", e);
    }
    return null;
    }
    }
     */
    public synchronized CategoryInterface get(Document document)
    {
        try
        {
            Node categoryNode = DomSearchHelper.getNode(
                CategoryData.getInstance().NAME, document.getChildNodes());

            CategoryInterface loadedCategoryInterface =
                this.categoryFactoryInterface.getRootInstanceFromNode(categoryNode);

            return this.addProperties(loadedCategoryInterface, categoryNode.getChildNodes());
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, commonStrings.GET, e));
            }
            return null;
        }
    }

    /*
    public synchronized CategoryInterface get(Node categoryNode)
    {
    try
    {
    CategoryInterface loadedCategoryInterface =
    this.categoryFactoryInterface.getRootInstance(categoryNode);

    return this.addProperties(loadedCategoryInterface, categoryNode.getChildNodes());
    }
    catch(Exception e)
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
    {
    LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, commonStrings.GET, e);
    }
    return null;
    }
    }
     */
    public synchronized CategoryInterface getAll(CategoryInterface categoryInterface)
    {
        try
        {
            Document document = this.getDoc(categoryInterface);

            return this.getAll(document);
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, commonStrings.GET, e));
            }
            return null;
        }
    }

    private synchronized CategoryInterface getAll(Document document)
    {
        try
        {
            Node categoryNode = DomSearchHelper.getNode(
                CategoryData.getInstance().NAME, document.getChildNodes());

            CategoryInterface loadedCategoryInterface =
                this.categoryFactoryInterface.getRootInstanceFromNode(categoryNode);

            //return this.getAll(loadedCategoryInterface, categoryNode.getChildNodes());
            return this.addProperties(loadedCategoryInterface, categoryNode.getChildNodes());
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, commonStrings.GET, e));
            }
            return null;
        }
    }

    private synchronized CategoryInterface getAll(
        CategoryInterface parentCategoryInterface, NodeList categoryNodeList)
    {
        try
        {
            if (categoryNodeList != null)
            {
                for (int index = 0; index < categoryNodeList.getLength(); index++)
                {
                    Node categoryNode = categoryNodeList.item(index);

                    if (categoryNode != null
                        && categoryNode.getNodeName().compareTo(CategoryData.getInstance().NAME) == 0)
                    {
                        CategoryInterface categoryInterface =
                            (CategoryInterface) this.categoryFactoryInterface.getInstance(
                            parentCategoryInterface.getHierarchy().getRoot(),
                            parentCategoryInterface,
                            categoryNode);

                        parentCategoryInterface.addChild(categoryInterface);
                    }
                }
            }
            return parentCategoryInterface;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, commonStrings.GET, e));
            }
            return null;
        }
    }

    //This could be in the category constructor
    private synchronized CategoryInterface addProperties(
        CategoryInterface loadedCategoryInterface, NodeList categoryNodeList)
    {
        try
        {
            if (categoryNodeList != null)
            {
                for (int index = 0; index < categoryNodeList.getLength(); index++)
                {
                    Node categoryNode = categoryNodeList.item(index);

                    if (categoryNode != null
                        && categoryNode.getNodeName().compareTo(CategoryData.getInstance().NAME) == 0)
                    {
                        CategoryPropertiesInterface categoryPropertiesInterface =
                            (CategoryPropertiesInterface) new CategoryProperties(categoryNode);

                        loadedCategoryInterface.addChildProperty(
                            categoryPropertiesInterface);
                    }
                }
            }
            return loadedCategoryInterface;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, commonStrings.GET, e));
            }
            return null;
        }
    }
}
