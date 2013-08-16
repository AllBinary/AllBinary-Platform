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
package views.admin.inventory.listings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.allbinary.util.BasicArrayList;

import views.generic.inventory.InventoryColumnUtil;
import views.generic.inventory.InventorySearchUtil;
import abcs.globals.URLGLOBALS;
import abcs.logic.basic.io.AbDataOutputStream;
import abcs.logic.basic.io.DataOutputStreamFactory;
import abcs.logic.basic.io.InputOutputTypeData;
import abcs.logic.basic.io.StreamUtil;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.directory.Directory;
import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.SpecialCharacterUtil;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.basic.string.regex.replace.Replace;
import abcs.logic.basic.string.tokens.UniqueTokens;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.user.commerce.inventory.item.BasicItemData;
import allbinary.data.tables.context.module.storefronts.StoreFrontsEntity;
import allbinary.data.tables.staticpages.StaticPagesEntity;
import allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import allbinary.logic.control.search.SearchParams;
import allbinary.logic.control.search.SearchRequest;

public class ProductListing implements ProductListingInterface
{
    private final StoreFrontsEntity storeFronts;
    private final StaticPagesEntity staticPages;
    private final InventoryEntity inventory;
    private final SearchRequest searchRequest;
    private final String INVENTORY = ".InventoryView";

    public ProductListing(SearchRequest searchRequest)
    {
        this.searchRequest = searchRequest;

        this.storeFronts = new StoreFrontsEntity();
        this.staticPages = new StaticPagesEntity();
        this.inventory = new InventoryEntity();
    }

    private final HashSet getHashSet(StoreFrontInterface storeFront)
        throws Exception
    {
        InventoryColumnUtil inventoryColumnUtil =
            InventoryColumnUtil.getInstance();

        Vector keywords = inventoryColumnUtil.getColumnWhereLike(
            this.inventory, storeFront.getCategoryPath(), BasicItemData.KEYWORDS);

        BasicArrayList subStoreVector = storeFront.getSubStores();

        int size = subStoreVector.size();
        for(int index = 0; index < size; index++)
        {
            String subStore = (String) subStoreVector.get(index);
            //AbSqlData.ANYSINGLECHARACTERMATCH
            Vector substoreKeywords = inventoryColumnUtil.getColumnWhereLike(
                this.inventory,
                AbPathData.getInstance().SEPARATOR + subStore,
                BasicItemData.CATEGORY);

            keywords.addAll(substoreKeywords);
        }

        //if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.S))
        //{
        //  LogUtil.put(LogFactory.getInstance("Keyword/Categories: " + keywords, this, "generateAll()"));
        //}

        UniqueTokens uniqueTokens = new UniqueTokens();
        HashSet keywordHashSet = uniqueTokens.getWhithoutDashesAndSkipNumberOnlyTokens(keywords);
        return keywordHashSet;
    }

    private void savePage(String file, String data)
        throws Exception
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.STATICPAGEGENERATIONLOGGING))
        {
            //Creates a new File
            LogUtil.put(LogFactory.getInstance("Creating File: " + file, this, "generateAll()"));
        }

        AbFile newFile = new AbFile(file);
        if (newFile.exists())
        {
            newFile.delete();
        }
        newFile.createNewFile();

        if (newFile.exists())
        {
            AbDataOutputStream idOutData =
                DataOutputStreamFactory.getInstance().getInstance(newFile);
            idOutData.writeBytes(data);

            idOutData.flush();

            StreamUtil.getInstance().close(idOutData);

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.STATICPAGEGENERATIONLOGGING))
        {
            LogUtil.put(LogFactory.getInstance("Wrote Total Bytes: " + newFile.length(), this, "generateAll()"));
        }

        } else
        {
            throw new Exception("Could Not Create: " + file);
        }
    }

    private void create(String keywordData,
        HashMap keywordFilenameHashMap, Vector vector, AbPath staticPath)
        throws Exception
    {
        StringBuffer stringBuffer = new StringBuffer();

        HashMap hashMap = SpecialCharacterUtil.getHashMap();
        hashMap.put(CommonSeps.getInstance().SPACE, StringUtil.getInstance().EMPTY_STRING);
        String pageName = new Replace(hashMap).all(keywordData);

        //add keywords to search params
        SearchParams searchParams = this.searchRequest.getParams();
        searchParams.add(BasicItemData.KEYWORDS, keywordData);
        searchParams.setStartPage("0");
        this.searchRequest.setParams(searchParams);

        this.searchRequest.setFileBaseName(pageName);

        //generate Static pages from the custom generate list
        //InventoryViewSearchInterface inventoryViewSearchInterface =
        //  InventoryViewFactory.getInstance(this.searchRequest);

        InventorySearchUtil inventorySearchUtil =
            InventorySearchUtil.getInstance();

        //creates a listing from the created search data ^
        String[] productListingPages =
            inventorySearchUtil.search(searchRequest, vector);

        for (int index = 0; index < productListingPages.length; index++)
        {
            if (productListingPages[index] == null)
            {
                break;
            }

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.STATICPAGEGENERATIONLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Saving Listing: " + index, this, "generateAll()"));
            }

            String indexStr = StringUtil.getInstance().EMPTY_STRING;
            if (index > 0)
            {
                indexStr = new Integer(index).toString();
            }

            keywordFilenameHashMap.put(keywordData, pageName + indexStr);

            stringBuffer.delete(0, stringBuffer.length());

            stringBuffer.append(staticPath);
            stringBuffer.append(pageName);
            stringBuffer.append(indexStr);
            stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
            stringBuffer.append(InputOutputTypeData.getInstance().DEFAULT);

            String file = stringBuffer.toString();

            //Create Directories
            if (!Directory.create(staticPath))
            {
                throw new Exception("Could Not Create Directory: " + staticPath);
            }

            this.savePage(file, productListingPages[index]);
        }
    }

    private void addStaticPageInfoToDatabase(
        StoreFrontInterface storeFront, HashMap keywordFilenameHashMap)
    {
        //add to Static info to staticpages db
        Set keywordHashSetForHashMap = keywordFilenameHashMap.keySet();
        Iterator keywordHashSetIter = keywordHashSetForHashMap.iterator();
        while (keywordHashSetIter.hasNext())
        {
            Vector insertVector = new Vector();
            String keywordData = (String) keywordHashSetIter.next();
            String fileName = (String) keywordFilenameHashMap.get(keywordData);

            insertVector.add(storeFront.getName());
            insertVector.add(keywordData);
            insertVector.add(fileName);
            this.staticPages.insert(insertVector);
        }
    }

    public String generateAll() throws Exception
    {
        try
        {
            final StoreFrontInterface storeFront = this.searchRequest.getStoreFront();

            final StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(URLGLOBALS.getWebappPath());
            stringBuffer.append(storeFront.getName());
            stringBuffer.append(AbPathData.getInstance().SEPARATOR);
            stringBuffer.append(storeFront.getStaticPath());

            //URLGLOBALS.getMainPath() + storeFront.getCurrentHomeHostName()
            //+ storeFront.getTestHtmlPath() + storeFront.getStaticPath()

            final AbPath staticPath = new AbPath(stringBuffer.toString());

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.STATICPAGEGENERATIONLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Store Static Pages Path: " + staticPath, this, "generateAll()"));
            }

            //compile a list of possible keywords
            final HashSet keywordHashSet = this.getHashSet(storeFront);

            //create filenames associated with keywords and
            //create a new hash map containing all off the keywords and
            //their associated Static page filenames
            final Iterator iter = keywordHashSet.iterator();

            HashMap keywordFilenameHashMap = new HashMap();

            if (!iter.hasNext())
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append("Products For ");
                stringBuffer.append(storeFront.getName());
                stringBuffer.append(" Store Not Found");

                return stringBuffer.toString();
            }

            InventorySearchUtil inventorySearchUtil =
                InventorySearchUtil.getInstance();

            Vector vector = inventorySearchUtil.getBasicItemIdColumn(searchRequest);

            while (iter.hasNext())
            {
                String keywordData = (String) iter.next();

                if (keywordData.length() > 1)
                {
                    this.create(keywordData, keywordFilenameHashMap, vector, staticPath);
                }
            }

            this.addStaticPageInfoToDatabase(
                storeFront, keywordFilenameHashMap);

            stringBuffer.delete(0, stringBuffer.length());

            stringBuffer.append("Static Files Generated Successfully For ");
            stringBuffer.append(storeFront.getName());
            stringBuffer.append(" it used packages ");
            stringBuffer.append(storeFront.getPackageLocation());
            stringBuffer.append(INVENTORY);

            return stringBuffer.toString();

        } catch (Exception e)
        {
            throw e;
        }
    }

    public String generateAll(String storeName) throws Exception
    {
        try
        {
            StringBuffer stringBuffer = new StringBuffer();

            if (storeName != null)
            {
                if (storeName.compareTo(allbinary.globals.GLOBALS.GENERATEALLSTORES) != 0)
                {
                    this.searchRequest.setStoreFront(this.storeFronts.getStoreFrontInterface(storeName));

                    //String temp = this.generateAll();
                    //if(temp!=null)
                    // result = temp + "<br>";

                    stringBuffer.append(this.generateAll());
                    stringBuffer.append("<br />");

                } else
                {

                    Vector storeFrontVector = this.storeFronts.getStoreFrontNames();
                    Iterator iter = storeFrontVector.iterator();

                    while (iter.hasNext())
                    {
                        storeName = (String) iter.next();

                        this.searchRequest.setStoreFront(this.storeFronts.getStoreFrontInterface(storeName));

                        //String temp = ;
                        //if(temp!=null)
                        // result += temp + "<br>";
                        stringBuffer.append(this.generateAll());
                        stringBuffer.append("<br />");
                    }
                }
            } else
            {
                return "Generation Failed No Store Specified<br/>";
            }

            stringBuffer.append("All Static Pages Generated<br/>");

            return stringBuffer.toString();

        } catch (Exception e)
        {
            throw e;
        }
    }
}
