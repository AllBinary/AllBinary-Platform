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
package admin.taghelpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.business.category.CategoryComponent;
import org.allbinary.business.category.CategoryData;
import org.allbinary.business.category.CategoryInterface;
import org.allbinary.business.category.store.StoreCategoryFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.data.tree.category.CategoryLoaderFactory;
import org.allbinary.data.tree.category.CategoryLoaderInterface;
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoBasic;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class CategoryRequestHelper extends ModifyTable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   /*
    <request>
       <command></command>
       <PARENT>
          <CATEGORY_NAME></CATEGORY_NAME>
       </PARENT>
       <CATEGORY_NAME></CATEGORY_NAME>
       //any number of categories for insert or deletion
    <request>
    */

     
   private PageContext pageContext;
   private HashMap hashMap;

   private HttpServletRequest request;
   
   //private StoreFrontInterface storeFrontInterface;
   
   private CategoryLoaderInterface categoryLoaderInterface;
   
   private CategoryInterface categoryInterface;
   private CategoryInterface childCategoryInterface;
   
   private TransformInfoInterface transformInfoInterface;

   public CategoryRequestHelper(HashMap hashMap, PageContext pageContext)
   {
      try
      {
         this.request = (HttpServletRequest) pageContext.getRequest();
         this.pageContext = pageContext;
         this.hashMap = hashMap;

         this.getXmlData();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"CategoryRequestHelper()",e);
         }
      }
   }

   private final static String categoryRequest = new StringMaker().append("<").append(CategoryData.getInstance().REQUEST).append(">").toString();
   
   public void getXmlData()
   {
      try
      {
         Map map = this.request.getParameterMap();
         
         final CategoryData categoryData = CategoryData.getInstance();
         
         final Set keys = map.keySet();
         
         final Object[] keyArray = keys.toArray();
         final int size = keyArray.length;
         if(size > 0)
         {
            String xmlRequest = (String) keyArray[0];

            int index = 1;
            while(index < size && !xmlRequest.startsWith(categoryRequest))
            {
               xmlRequest = (String) keyArray[index];
               if(xmlRequest.startsWith(categoryRequest)) break;
            }
            
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
            {
               logUtil.put(xmlRequest, this, "getXmlData()");
            }

            Document document = DomDocumentHelper.create(xmlRequest);

            Node requestNode = DomSearchHelper.getNode(
               categoryData.REQUEST, document.getChildNodes());

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
            {
               logUtil.put(DomDocumentHelper.toString(document), this, "getXmlData()");
            }

            this.setCategoryLoader(requestNode);

            Node parentCategoryNode = DomSearchHelper.getNode(
               categoryData.PARENT, requestNode.getChildNodes());

            Node categoryNode = DomSearchHelper.getNode(
               categoryData.NAME, parentCategoryNode.getChildNodes());

            //Create search Category from request
            this.categoryInterface = 
               (CategoryInterface) new StoreCategoryFactory(this.transformInfoInterface).getRootInstanceFromNode(categoryNode);
            
            //load file and add make a new root from the loaded file using the 
            //category factory embeded in the categoryLoader
            this.categoryInterface = this.categoryLoaderInterface.get(this.categoryInterface);
            
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
            {
               this.categoryInterface.log();
               logUtil.put("Loaded Parent Category", this, "getXmlData()");
            }

            Node childCategoryNode = DomSearchHelper.getNodeNoThrow(
               categoryData.NAME, requestNode.getChildNodes());

            if(childCategoryNode!=null)
            {
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
               {
                  logUtil.put("Loading Child Category", this, "getXmlData()");
               }
               
               this.childCategoryInterface = (CategoryInterface) 
                  new StoreCategoryFactory(this.transformInfoInterface).getInstance(
                     childCategoryNode);

               //this.categoryLoaderInterface.get(childCategoryNode);

               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
               {
                  this.childCategoryInterface.log();
                  logUtil.put("Loaded Child Category", this, "getXmlData()");
               }
            }
            else
            {
               this.childCategoryInterface = this.categoryInterface;
            }
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"getXmlData()",e);
         }
      }
   }

   private void setCategoryLoader(Node requestNode) throws Exception
   {
      Node storeNameNode = 
         DomSearchHelper.getNode(
         StoreFrontData.getInstance().NAME, requestNode.getChildNodes());

      if(storeNameNode!=null)
      {
         String storeName = DomNodeHelper.getTextNodeValue(storeNameNode);

         if(storeName!=null)
         {
            StoreFrontInterface storeFrontInterface = 
               StoreFrontFactory.getInstance(storeName);

            this.transformInfoInterface = (TransformInfoInterface)
                new TransformInfoBasic(storeFrontInterface, hashMap, pageContext);

            if(this.transformInfoInterface == null)
            {
            	throw new Exception("TransformInfo null");
            }

            StoreCategoryFactory storeCategoryFactory = 
               new StoreCategoryFactory(this.transformInfoInterface);
                        
            this.categoryLoaderInterface = 
               CategoryLoaderFactory.getInstance(storeCategoryFactory);
         }
         else
         {
            throw new Exception("Store Name Error: " + storeName);
         }
      }
   }

   /*
   public void getFormData()
   {
      Map map = request.getParameterMap();
    
      Vector addVector = new Vector();
      Vector removeVector = new Vector();
    
      Set keys = map.keySet();
    
      while(keyIter.hasNext())
      {
         String key = (String) keyIter.next();
         String[] values = (String[]) map.get(key);
    
         HashMap hashMap = new HashMap();
         hashMap.put(stringUtil.EMPTY_STRING;,new String(values[0]));
         if(key.startsWith(
         addVector.add(new Category(hashMap));
         //logUtil.put("key: " + key + " Value: " + values[0],this,"getFormData()");
      }
    
      this.addCategories = new Categories(addHashMap);
      this.removeCategories = new Categories(removeHashMap);
   }
    */
   
   public String insert()
   {
      try
      {
         //Vector values = new Vector();
                  
         String success = "Successfully Added the following to the Category table";

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put("inserting",this,"insert()");
         }
         
         this.categoryLoaderInterface.insert(
            this.categoryInterface, this.childCategoryInterface);

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"insert()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add item to Category";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"insert()",e);
         }
         return error;
      }
   }
   
   public String delete()
   {
      try
      {
         String success = "Successfully Removed " + CategoryData.getInstance().NAME + "=" + 
            this.childCategoryInterface.getPath();

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put("Deleting",this,"delete()");
         }
         
         this.categoryLoaderInterface.delete(
            this.categoryInterface, this.childCategoryInterface);

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"delete()");
         }

         return success;
      }
      catch(Exception e)
      {
         try
         {
            String error = "Failed to remove category: " + this.childCategoryInterface.getPath();
         
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
               logUtil.put(commonStrings.EXCEPTION, this, "delete()", e);
            }
            return error;
         }
         catch(Exception e2)
         {
            String error = "Failed to remove category and show the path of the failed category";
         
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
               logUtil.put(commonStrings.EXCEPTION, this, "delete()", e2);
            }
            return error;
         }
      }
   }
   
   public String viewCategory()
   {
      try
      {
         Document document = 
            new CategoryComponent(this.childCategoryInterface).toXmlDoc();
         
         String xmlString = DomDocumentHelper.toString(document);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(xmlString,this,"viewCategory()");
         }
         return xmlString;
      }
      catch(Exception e)
      {
         String error = "Failed to get Category";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"viewCategory()",e);
         }
         return error;
      }
   }
   
   public String viewCategories()
   {
      try
      {
         String success = StringUtil.getInstance().EMPTY_STRING;
         //= CategoryEntityFactory.getInstance().getTable(id);
         
         //Categories categories;
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"viewCategories()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to view Categories table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"viewCategories()",e);
         }
         return error;
      }
   }
   
   public String update()
   {
      try
      {
         //HashMap values = new HashMap();
         
         //CategoryEntityFactory.getInstance().update(values);
         
         String success = "New Item Successfully added";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"update()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add Item";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"update()",e);
         }
         return error;
      }
   }   
}
