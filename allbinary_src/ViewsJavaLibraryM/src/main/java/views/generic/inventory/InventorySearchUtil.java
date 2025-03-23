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
import java.util.ListIterator;
import java.util.Vector;

import org.allbinary.util.BasicArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.string.regex.replace.Replace;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.InventoryData;
import org.allbinary.business.user.commerce.inventory.basket.BasketData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemView;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.control.search.SearchData;
import org.allbinary.logic.control.search.SearchParams;
import org.allbinary.logic.control.search.SearchRequest;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.visual.transform.StoreTransformer;
import org.allbinary.logic.visual.transform.data.TransformDocumentInterface;
import org.allbinary.logic.visual.transform.data.TransformStoreDocumentFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpSearch;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

/**
 *
 * @author user
 */
public class InventorySearchUtil {

    private static final  InventorySearchUtil instance = new InventorySearchUtil();

    /**
     * @return the instance
     */
    public static InventorySearchUtil getInstance()
    {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    //private static final String START_PAGE = " StartPage:";
    //private static final String END_PAGE = " EndPage:";
    //private static final String XML_COLON = " Xml: ";

    //private final String KEYWORD = "Keyword: ";
    //private final String PAGE_LENGTH = "\nPage Length: ";
    //private final String WITH = " with ";
    //private final String KEYWORD_ANALYSIS = "Keyword Analysis: Comparing ";
    //private final String PARTIAL_LIST_DOC_CURRENTPAGE = "Storing Partial Listing Doc: CurrentPage: ";
    //private final String STORING_CURRENT_PAGE = "Storing Doc: CurrentPage: ";

    public Vector getBasicItemIdColumn(
        SearchRequest searchRequest) throws Exception
    {
        InventoryEntity inventoryEntityInterface =
            InventoryEntityFactory.getInstance().getInventoryEntityInstance();

        StoreFrontInterface storeFrontInterface =
            searchRequest.getStoreFront();

        InventoryColumnUtil inventorySearchUtil =
            InventoryColumnUtil.getInstance();

        Vector column = inventorySearchUtil.getColumnWhereLike(
            inventoryEntityInterface,
            storeFrontInterface.getCategoryPath(),
            BasicItemData.ID);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
        {
            LogUtil.put(LogFactory.getInstance("Number Of Items Found: " + column.size(), this, "search"));
        }

        BasicArrayList subStoreVector = storeFrontInterface.getSubStores();

        int size = subStoreVector.size();
        for(int index = 0; index < size; index++)
        {
            String subStore = (String) subStoreVector.get(index);

            Vector substoreIdColumn = inventorySearchUtil.getColumnWhereLike(
                inventoryEntityInterface, subStore, BasicItemData.ID);

            column.addAll(substoreIdColumn);
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
        {
            LogUtil.put(LogFactory.getInstance("Number Of Items Found Including SubStores: "
                + column.size(), this, "search"));
        }

        return column;
    }

    private String getNoResults(
        TransformDocumentInterface viewDocumentInterface, Node inventoryNode)
        throws Exception
    {
        inventoryNode.appendChild(ModDomHelper.createNameValueNodes(
            viewDocumentInterface.getDoc(), SearchData.TOTAL_NUMBER_PAGES, "0"));

        inventoryNode.appendChild(ModDomHelper.createNameValueNodes(
            viewDocumentInterface.getDoc(), SearchData.TOTAL_NUMBER_ITEMS, "0"));

        String success = DomDocumentHelper.toString(viewDocumentInterface.getDoc());

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
        {
            LogUtil.put(LogFactory.getInstance(
                "No Results Xml: " + success, this, "search"));
        }

        return success;
    }

    public String[] search(final AbeClientInformationInterface abeClientInformation, SearchRequest searchRequest, Vector column)
    {
        try
        {
            InventoryEntity inventoryEntityInterface =
                InventoryEntityFactory.getInstance().getInventoryEntityInstance();

            SearchParams searchParams = searchRequest.getParams();
            int startPage = searchParams.getStartPageInt().intValue();
            int endPage = searchParams.getEndPageInt().intValue();
            int pageLength = searchParams.getLengthInt().intValue();
            int savedPagesInRange = 0;

            //this.searchRequest.getKeywords()
            HashMap columnValueHashMap = searchParams.get();

            String keyword = new Replace("-", CommonSeps.getInstance().SPACE).all(
                (String) columnValueHashMap.get(BasicItemData.KEYWORDS));

            if (StringValidationUtil.getInstance().isEmpty(keyword))
            {
                return null;
            }

            StringBuffer stringBuffer = new StringBuffer();

            /*
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
            {
            	stringBuffer.append(KEYWORD);
                stringBuffer.append(keyword);
                stringBuffer.append(START_PAGE);
                stringBuffer.append(startPage);
                stringBuffer.append(END_PAGE);
                stringBuffer.append(endPage);
                stringBuffer.append(PAGE_LENGTH);
                stringBuffer.append(pageLength);

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "search"));
            }
            */

            if (startPage >= 0)
            {
                if (pageLength == 0)
                {
                    pageLength = SearchData.DEFAULT_PAGE_LENGTH;
                }

                final int MAXPAGES = 100;
                String[] productListingPages = new String[MAXPAGES];
                Document[] documents = new Document[MAXPAGES];
                Node[] inventoryNodes = new Node[MAXPAGES];

                ListIterator iter = column.listIterator();

                keyword = keyword.toUpperCase();

                int lastPage = -1;
                int numberOfResultsOnCurrentPage = 0;
                int numberOfResults = 0;

                //then start new document
                TransformDocumentInterface viewDocumentInterface =
                    TransformStoreDocumentFactory.getInstance(
                    searchRequest);

                Node inventoryNode =
                    viewDocumentInterface.getDoc().createElement(
                    InventoryData.INVENTORY);

                viewDocumentInterface.getBaseNode().appendChild(inventoryNode);

                inventoryNode.appendChild(
                    ModDomHelper.createNameValueNodes(viewDocumentInterface.getDoc(),
                    SearchData.PAGE, new Integer(startPage).toString()));

                if (searchRequest.getFileBaseName() != null)
                {
                    inventoryNode.appendChild(
                        searchRequest.getFileBaseNameNode(
                        viewDocumentInterface.getDoc()));
                }

                /*
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                    org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("Initial Document Created", instance, "search"));
                }
                */

                int currentPage = -1;
                while (iter.hasNext())
                {
                    String product = new String((String) iter.next());

                    ItemInterface itemInterface =
                        inventoryEntityInterface.getItem(product);

                    String keywords = itemInterface.getKeywords();
                    keywords = keywords.toUpperCase();

                    /*
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                        org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
                    {
                        stringBuffer.delete(0, stringBuffer.length());

                        stringBuffer.append(KEYWORD_ANALYSIS);
                        stringBuffer.append(keyword);
                        stringBuffer.append(WITH);
                        stringBuffer.append(keywords);

                        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "search"));
                    }
                    */

                    if (keywords.indexOf(keyword) != -1)
                    {
                        //only create document item if item is on a requested page
                        if (currentPage + 1 >= startPage && currentPage + 1 <= endPage)
                        {
                            Node itemNode = new BasicItemView(itemInterface, new Vector()).toXmlNode(
                                viewDocumentInterface.getDoc());

                            itemNode.appendChild(ModDomHelper.createNameValueNodes(
                                viewDocumentInterface.getDoc(),
                                BasketData.ITEMTOTALINBASKET, "1"));

                            inventoryNode.appendChild(itemNode.cloneNode(true));
                        }

                        currentPage = ((numberOfResults + 1) / pageLength) - 1;
                        numberOfResultsOnCurrentPage++;

                        /*
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
                        {
                            stringBuffer.delete(0, stringBuffer.length());

                            stringBuffer.append("current: ");
                            stringBuffer.append(currentPage);
                            stringBuffer.append(" num: ");
                            stringBuffer.append(numberOfResults);
                            stringBuffer.append(" last: ");
                            stringBuffer.append(lastPage);

                            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "search"));
                        }
                        */

                        if (lastPage < currentPage)
                        {
                            if (currentPage >= startPage && currentPage <= endPage)
                            {
                                /*
                                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                                    org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
                                {
                                    stringBuffer.delete(0, stringBuffer.length());

                                    stringBuffer.append(STORING_CURRENT_PAGE);
                                    stringBuffer.append(currentPage);
                                    stringBuffer.append(START_PAGE);
                                    stringBuffer.append(startPage);
                                    stringBuffer.append(END_PAGE);
                                    stringBuffer.append(endPage);
                                    //stringBuffer.append(XML_COLON);
                                    //stringBuffer.append(DomDocumentHelper.toString(viewDocumentInterface.getDoc()));

                                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "search"));
                                }
                                */

                                inventoryNode.appendChild(
                                    ModDomHelper.createNameValueNodes(
                                    viewDocumentInterface.getDoc(),
                                    SearchData.TOTAL_NUMBER_ITEMS_ON_THIS_PAGE,
                                    new Integer(numberOfResultsOnCurrentPage).toString()));

                                //save current page result
                                documents[currentPage] = viewDocumentInterface.getDoc();
                                inventoryNodes[currentPage] = inventoryNode;

                                savedPagesInRange++;

                                //then start new document
                                viewDocumentInterface =
                                    TransformStoreDocumentFactory.getInstance(
                                    searchRequest);

                                inventoryNode =
                                    viewDocumentInterface.getDoc().createElement(
                                    InventoryData.INVENTORY);

                                viewDocumentInterface.getBaseNode().appendChild(
                                    inventoryNode);

                                inventoryNode.appendChild(
                                    ModDomHelper.createNameValueNodes(
                                    viewDocumentInterface.getDoc(),
                                    SearchData.PAGE,
                                    new Integer(currentPage + 1).toString()));

                                if (searchRequest.getFileBaseName() != null)
                                {
                                    inventoryNode.appendChild(
                                        searchRequest.getFileBaseNameNode(
                                        viewDocumentInterface.getDoc()));
                                }
                            }

                            lastPage = currentPage;
                            numberOfResultsOnCurrentPage = 0;
                        }

                        numberOfResults++;

                        if (lastPage >= MAXPAGES - 1)
                        {
                            break;
                        }
                    }
                }

                //Store last page if it exists
                if (numberOfResultsOnCurrentPage > 0
                    && (currentPage + 1 >= startPage && currentPage + 1 <= endPage))
                {
                    /*
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                        org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
                    {
                        stringBuffer.delete(0, stringBuffer.length());

                        stringBuffer.append(PARTIAL_LIST_DOC_CURRENTPAGE);
                        stringBuffer.append(currentPage);
                        stringBuffer.append(START_PAGE);
                        stringBuffer.append(startPage);
                        stringBuffer.append(END_PAGE);
                        stringBuffer.append(endPage);
                        //stringBuffer.append(XML_COLON);
                        //stringBuffer.append(DomDocumentHelper.toString(viewDocumentInterface.getDoc()));

                        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "search"));
                    }
                    */

                    //save current page result

                    inventoryNode.appendChild(
                        ModDomHelper.createNameValueNodes(viewDocumentInterface.getDoc(),
                        SearchData.TOTAL_NUMBER_ITEMS_ON_THIS_PAGE,
                        new Integer(numberOfResultsOnCurrentPage - 1).toString()));

                    documents[lastPage + 1] = viewDocumentInterface.getDoc();
                    inventoryNodes[lastPage + 1] = inventoryNode;

                    savedPagesInRange++;
                }

                if (numberOfResultsOnCurrentPage > 0)
                {
                    lastPage++;
                }

                //go back through the the existing pages in the requested page page
                //and add totals
                if (endPage > MAXPAGES - 1)
                {
                    endPage = MAXPAGES - 1;
                }

                for (int index = startPage; index <= endPage; index++)
                {
                    if (index <= lastPage)
                    {
                        Document tempDocument = documents[index];

                        /*
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
                        {
                            stringBuffer.delete(0, stringBuffer.length());

                            stringBuffer.append("Translating Doc: ");
                            stringBuffer.append(index);
                            stringBuffer.append(XML_COLON);
                            stringBuffer.append(DomDocumentHelper.toString(tempDocument));

                            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "search"));
                        }
                        */

                        inventoryNodes[index].appendChild(
                            ModDomHelper.createNameValueNodes(tempDocument,
                            SearchData.TOTAL_NUMBER_PAGES, new Integer(lastPage + 1).toString()));

                        inventoryNodes[index].appendChild(
                            ModDomHelper.createNameValueNodes(tempDocument,
                            SearchData.TOTAL_NUMBER_ITEMS, new Integer(numberOfResults).toString()));

                        //add Page Numbers
                        for (int pageIndex = 0; pageIndex <= lastPage; pageIndex++)
                        {
                            stringBuffer.delete(0, stringBuffer.length());
                            
                            //Add file name
                            stringBuffer.append(searchRequest.getFileBaseName());

                            if (stringBuffer.length() > 0)
                            {
                                if (pageIndex != 0)
                                {
                                    stringBuffer.append(Integer.toString(pageIndex));
                                }

                                inventoryNodes[index].appendChild(
                                    ModDomHelper.createNameValueNodes(tempDocument,
                                    SearchData.PAGE_INFO, Integer.toString(pageIndex),
                                    stringBuffer.toString()));
                            }
                            else
                            {
                                inventoryNodes[index].appendChild(
                                    ModDomHelper.createNameValueNodes(tempDocument,
                                    SearchData.PAGE_INFO, Integer.toString(pageIndex),
                                    SearchData.SEARCH));
                            }
                        }

                        String success = DomDocumentHelper.toString(tempDocument);

                        /*
                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
                        {
                            stringBuffer.delete(0, stringBuffer.length());

                            stringBuffer.append("Inventory Page: ");
                            stringBuffer.append(index);
                            stringBuffer.append(XML_COLON);
                            stringBuffer.append(success);

                            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "search"));
                        }
                        */

                        productListingPages[index] = new StoreTransformer(
                                abeClientInformation,
                            (TransformInfoInterface) new TransformInfoHttpSearch(
                            searchRequest)).translate(success);
                    }
                }

                //if no page was found in the range provided
                //return a page 0 with no items although with search totals
                //This should not occur during static page generation
                if (savedPagesInRange == 0)
                {
                    String result =
                        this.getNoResults(viewDocumentInterface, inventoryNode);

                    productListingPages[0] = new StoreTransformer(
                        abeClientInformation, new TransformInfoHttpSearch(searchRequest)).translate(result);
                }

                return productListingPages;

            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, instance, "search", e));
            }
            return null;
        }
    }

}
