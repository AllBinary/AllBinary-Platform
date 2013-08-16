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
package views.generic.inventory;


import java.util.HashMap;
import java.util.Vector;

import views.admin.inventory.listings.InventoryViewSearchInterface;
import abcs.logic.basic.io.InputOutputTypeData;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.basic.string.regex.replace.Replace;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.user.commerce.inventory.item.BasicItemData;
import allbinary.data.tables.staticpages.StaticPagesEntity;
import allbinary.logic.control.search.SearchParams;
import allbinary.logic.control.search.SearchRequest;

/*
 *This class updates the static page db table and generates/updates the html
 *associated with each keyword filename pair listed in the static page db table
 *when a client request a search this class returns a static page
 */
public class InventorySearch implements InventoryViewSearchInterface
{

    private final SearchRequest searchRequest;

    public InventorySearch(SearchRequest searchRequest)
    {
        super();
        this.searchRequest = searchRequest;
    }

    /*This function searches the static pages db for a match*/
    public String searchSingleStaticPage() throws Exception
    {
        try
        {
            StoreFrontInterface storeFront = this.searchRequest.getStoreFront();

            SearchParams searchParams = this.searchRequest.getParams();
            HashMap columnValueHashMap = searchParams.get();

            String file = new StaticPagesEntity().getFile(
                storeFront.getName(), new Replace("-", CommonSeps.getInstance().SPACE).all(
                (String) columnValueHashMap.get(BasicItemData.KEYWORDS)));

            if (StringValidationUtil.getInstance().isEmpty(file))
            {
                return null;
            }
            else
            {
            	StringBuffer stringBuffer = new StringBuffer();
            	
            	stringBuffer.append(storeFront.getCurrentHostName());
                stringBuffer.append(storeFront.getCurrentHostNamePath());
                stringBuffer.append(storeFront.getStaticPath());
                stringBuffer.append(file);
                stringBuffer.append(this.searchRequest.getParams().getEndPage());
                stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
                stringBuffer.append(InputOutputTypeData.getInstance().DEFAULT);

                String filePath = new Replace(CommonSeps.getInstance().SPACE, "%20").all(stringBuffer.toString());

                return filePath;
            }
        }
        catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.PRODUCTSEARCHLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Failed", this, "searchSingleStaticPage", e));
            }
            throw new Exception("Failed retrieve Single Product Page Static");
        }
    }

    public String searchSingleDynamicPage() throws Exception
    {
        try
        {
            String str[] = search();
            return str[this.searchRequest.getParams().getStartPageInt().intValue()];
        }
        catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.PRODUCTSEARCHLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Command Failed", this, "searchSingleDynamicPage", e));
            }
            throw new Exception("Failed retrieve Single Product Page Dynamically");
        }
    }

    //Node contentTypeNode = document.createElement(this.searchRequest.getContentType());
    public String[] search() throws Exception
    {
        InventorySearchUtil inventorySearchUtil =
            InventorySearchUtil.getInstance();

        Vector vector = inventorySearchUtil.getBasicItemIdColumn(searchRequest);

        return inventorySearchUtil.search(searchRequest, vector);
    }

    public String getProduct(String product) throws Exception
    {
        return InventorySearchProductUtil.getInstance().getProduct(searchRequest, product);
    }

    /*
    public String remove(String keywords)
    {
    //remove static file and db entry if no match left
    return StringUtil.getInstance();
    }
    
    public String update(String keywords)
    {
    //generate a save path
    //generate static pages from the listgenerator
    //if keywords not in static pages db add to static pages db
    //otherwise update db
    return StringUtil.getInstance();
    }
     */
    /*
    public String upgradeAll(String storeName)
    {
    }
     */
}
