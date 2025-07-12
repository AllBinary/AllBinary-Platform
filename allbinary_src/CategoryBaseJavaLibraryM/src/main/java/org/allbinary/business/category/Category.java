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
package org.allbinary.business.category;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.category.hierarchy.CategoryHierarchy;
import org.allbinary.business.category.hierarchy.CategoryHierarchyInterface;
import org.allbinary.business.category.properties.CategoryPropertiesFactoryInterface;
import org.allbinary.business.category.properties.CategoryPropertiesInterface;
import org.allbinary.business.category.properties.root.RootCategoryPropertiesInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Category
    implements CategoryInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private CategoryHierarchyInterface categoryHierarchyInterface;
    private CategoryPropertiesInterface categoryPropertiesInterface;
    //Vector of child Categories
    private final Vector childCategoryVector = new Vector();
    private final Vector typeVector = new Vector();
    private final Integer PROPERTIES = new Integer(1);
    private final Integer CATEGORY = new Integer(0);

    //New Loner Category
    public Category(
        CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface) throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
        {
            logUtil.put(this.commonStrings.CONSTRUCTOR, this, "Category(CategoryPropertiesFactoryInterface)");
        }

        this.categoryPropertiesInterface =
            categoryPropertiesFactoryInterface.getInstance();

        this.categoryHierarchyInterface = (CategoryHierarchyInterface) new CategoryHierarchy(this, this);

        this.log();
    }

    public Category(
        CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface,
        int level) throws Exception
    {
        this.categoryPropertiesInterface =
            categoryPropertiesFactoryInterface.getInstance();

        this.categoryHierarchyInterface = (CategoryHierarchyInterface) new CategoryHierarchy(this, this, level);

        this.log();
    }

    //New Category With Parent Child Relationships
    public Category(
        CategoryInterface rootCategoryInterface,
        CategoryInterface parentCategoryInterface,
        CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface) throws Exception
    {
        this.categoryPropertiesInterface = (CategoryPropertiesInterface) categoryPropertiesFactoryInterface.getInstance();

        this.categoryHierarchyInterface = (CategoryHierarchyInterface) new CategoryHierarchy(
            rootCategoryInterface, parentCategoryInterface);

        this.log();
    }

    public Object getKey() throws Exception
    {
        return this.categoryPropertiesInterface.getKey();
    }

    private final static String NOT_ROOT = "This is not the root so it has no file path.";
    public AbPath getRootFilePath() throws Exception
    {
        AbPath rootAbPath = new AbPath(NOT_ROOT);

        CategoryInterface categoryInterface =
            this.categoryHierarchyInterface.getRoot();

        if (categoryInterface.getProperties().isRoot())
        {
            RootCategoryPropertiesInterface rootCategoryPropertiesInterface =
                (RootCategoryPropertiesInterface) categoryInterface.getProperties();
            rootAbPath = rootCategoryPropertiesInterface.getRootFilePath();
        }
        return rootAbPath;
    }

    public AbPath getFilePath() throws Exception
    {
        return new AbPath(this.getRootFilePath().toString()
            + this.getProperties().getPath(this.getHierarchy()).toString(),
            this.getProperties().getFileName());
    }

    public AbPath getWebAppPath() throws Exception
    {
        CategoryInterface categoryInterface =
            this.categoryHierarchyInterface.getRoot();
        return categoryInterface.getProperties().getWebAppPath();
    }

    public AbPath getPath() throws Exception
    {
        return this.categoryPropertiesInterface.getPath(
            this.categoryHierarchyInterface);
    }

    public synchronized boolean addChildProperty(CategoryPropertiesInterface categoryPropertiesInterface)
    {
        this.typeVector.add(this.PROPERTIES);
        return this.childCategoryVector.add(categoryPropertiesInterface);
    }

    public synchronized boolean addChild(CategoryInterface categoryInterface)
    {
        //remove duplicate if available
        this.removeDuplicateChild(categoryInterface);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
        {
            logUtil.put("adding", this, "addChild");
        }

        //update relationship
        CategoryHierarchyInterface childCategoryHierarchyInterface =
            categoryInterface.getHierarchy();
        childCategoryHierarchyInterface.setParent(this);
        childCategoryHierarchyInterface.setRoot(this.getHierarchy().getRoot());
        categoryInterface.setHierarchy(childCategoryHierarchyInterface);

        this.typeVector.add(this.CATEGORY);
        return this.childCategoryVector.add(categoryInterface);
    }

    public Vector getChildNodes()
    {
        return this.childCategoryVector;
    }

    //private static final String CATEGORYINTERFACE = "CategoryInterface";
    //private static final String CATEGORYPROPERTIESINTERFACE = "CategoryPropertiesInterface";

    public synchronized boolean removeChild(CategoryInterface categoryInterface)
    {
        final Vector removalVector = new Vector();

        boolean bool_return = false;

        final int size = this.childCategoryVector.size();
        for (int index = 0; index < size; index++)
        {
            if (this.CATEGORY == this.typeVector.get(index))
            {
                Object object = this.childCategoryVector.get(index);

                CategoryInterface currentCategoryInterface =
                    (CategoryInterface) object;

                if (currentCategoryInterface.getProperties().getValue().compareTo(
                    categoryInterface.getProperties().getValue()) == 0)
                {
                    removalVector.add(currentCategoryInterface);
                    bool_return = true;
                }
            }
        }

        this.removal(removalVector);

        return bool_return;
    }

    private final void removal(Vector removalVector)
    {
        final int removalSize = removalVector.size();
        for (int index = 0; index < removalSize; index++)
        {
            Object object = removalVector.get(index);

            int objectIndex =
                this.childCategoryVector.indexOf(object);

            this.typeVector.remove(objectIndex);
            this.childCategoryVector.remove(objectIndex);
        }
    }

    private synchronized boolean removeDuplicateChild(CategoryInterface categoryInterface)
    {
        final Vector removalVector = new Vector();

        boolean bool_return = false;

        final int size = this.childCategoryVector.size();
        for (int index = 0; index < size; index++)
        {
            Object object = this.childCategoryVector.get(index);

            if (this.PROPERTIES == this.typeVector.get(index))
            {
                CategoryPropertiesInterface categoryPropertiesInterface =
                    (CategoryPropertiesInterface) object;

                if (categoryInterface.getProperties().getValue().compareTo(
                    categoryPropertiesInterface.getValue()) == 0)
                {
                    removalVector.add(categoryPropertiesInterface);
                    bool_return = true;
                    break;
                }

            } else
            if (this.CATEGORY == this.typeVector.get(index))
            {
                CategoryInterface existingCategoryInterface =
                    (CategoryInterface) object;
                CategoryPropertiesInterface categoryPropertiesInterface =
                    existingCategoryInterface.getProperties();

                if (categoryInterface.getProperties().getValue().compareTo(
                    categoryPropertiesInterface.getValue()) == 0)
                {
                    removalVector.add(existingCategoryInterface);
                    bool_return = true;
                    break;
                }
            }
        }

        this.removal(removalVector);

        return bool_return;
    }

    public boolean isLeaf() throws Exception
    {
        if (this.childCategoryVector == null)
        {
            throw new Exception("Category Error");
        }

        if (this.childCategoryVector.size() == 0)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public CategoryPropertiesInterface getProperties()
    {
        return this.categoryPropertiesInterface;
    }

    public void setProperties(CategoryPropertiesInterface categoryPropertiesInterface)
    {
        this.categoryPropertiesInterface = categoryPropertiesInterface;
    }

    public CategoryHierarchyInterface getHierarchy()
    {
        return this.categoryHierarchyInterface;
    }

    public void setHierarchy(CategoryHierarchyInterface categoryHierarchyInterface)
    {
        this.categoryHierarchyInterface = categoryHierarchyInterface;
    }

    public synchronized Boolean isValid() throws Exception
    {
        if (!this.categoryPropertiesInterface.isValid().booleanValue())
        {
            return Boolean.FALSE;
        }

        final int size = this.childCategoryVector.size();
        for (int index = 0; index < size; index++)
        {
            Object object = this.childCategoryVector.get(index);

            if (this.PROPERTIES == this.typeVector.get(index))
            {
                CategoryPropertiesInterface categoryPropertiesInterface =
                    (CategoryPropertiesInterface) object;
                if (!categoryPropertiesInterface.isValid().booleanValue())
                {
                    return Boolean.FALSE;
                }
            } else
                if (this.CATEGORY == this.typeVector.get(index))
            {
                CategoryInterface categoryInterface = (CategoryInterface) object;
                if (!categoryInterface.isValid().booleanValue())
                {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }

    public Document toValidationInfoDoc() throws Exception
    {
        return null;
    }

    public Node toValidationInfoNode(Document document) throws Exception
    {
        return null;
    }

    public String validationInfo() throws Exception
    {
        return null;
    }

    public HashMap toHashMap() throws Exception
    {
        HashMap categoryHashMap = this.categoryPropertiesInterface.toHashMap();
        return categoryHashMap;
    }

    public Vector toVector() throws Exception
    {
        Vector categoryVector = this.categoryPropertiesInterface.toVector();
        categoryVector.add(this.childCategoryVector);
        return categoryVector;
    }

    public void log() throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
        {
            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append("Category Name: ");
            stringBuffer.append(this.categoryPropertiesInterface.getValue());
            stringBuffer.append("\nPath = ");
            stringBuffer.append(this.getPath().toString());
            stringBuffer.append("\nFile Path: ");
            stringBuffer.append(this.getFilePath().toString());

            logUtil.put(stringBuffer.toString(), this, "log()");
        }
    }
}
