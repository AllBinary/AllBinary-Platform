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
package views.admin.orderhistory;

import java.util.Calendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.inventory.order.OrderHistory;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityFactory;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.globals.GLOBALS2;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.time.TimeUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import views.business.context.modules.storefront.HttpStoreComponentView;

public class DateRangeOrderHistoryView extends HttpStoreComponentView implements ValidationComponentInterface, DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   //TWB
   //private final String VIEWALLORDERS = "View All Orders";
   //private final String VIEWORDERSINLASTHOUR = "View Orders In Last Hour";
   //private final String VIEWORDERSINLASTDAY = "View Orders In Last Day";
   //private final String VIEWORDERSINLASTWEEK = "View Orders In Last Week";
   //private final String VIEWORDERSINLAST30DAYS = "View Orders In Last 30 Days";
   private final String VIEWALLORDERS = "View All";
   private final String VIEWORDERSINLASTHOUR = "Last Hour";
   private final String VIEWORDERSINLASTDAY = "Last Day";
   private final String VIEWORDERSINLASTWEEK = "Last Week";
   private final String VIEWORDERSINLAST30DAYS = "30 Days";
   
   //private TransformInfoInterface transformInfoInterface;
   
   //private WeblisketSession weblisketSession;
   
   //private HashMap propertiesHashMap;
   //private PageContext pageContext;
   
   private HttpServletRequest request;
   
   private String shipped;
   private String partiallyShipped;
   private String processing;
   private String preprocessing;
   private String cancelled;
   
   private String dateType;
   
   //long string
   private String toDate;
   private String fromDate;
   
   private String fromYear;
   private String fromMonth;
   private String fromDay;
   private String fromHour;
   
   private String toYear;
   private String toMonth;
   private String toDay;
   private String toHour;
   
   private String command;
   
   private final String ON = "on";
   
   private int MAXLEN = 15;
   
   public DateRangeOrderHistoryView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      this.request = (HttpServletRequest) this.getPageContext().getRequest();
      this.getFormData();
   }
   
   private void getFormData()
   {
      this.dateType = request.getParameter(OrderHistoryData.DATETYPE);
      this.preprocessing = request.getParameter(OrderHistoryData.PREPROCESSINGNAME);
      this.shipped = request.getParameter(OrderHistoryData.SHIPPEDNAME);
      this.partiallyShipped = request.getParameter(OrderHistoryData.PARTIALLYSHIPPEDNAME);
      this.processing = request.getParameter(OrderHistoryData.PROCESSINGNAME);
      this.cancelled = request.getParameter(OrderHistoryData.CANCELLEDNAME);
      
      this.fromYear = request.getParameter(OrderHistoryData.FROMYEAR);
      this.fromMonth = request.getParameter(OrderHistoryData.FROMMONTH);
      this.fromDay = request.getParameter(OrderHistoryData.FROMDAY);
      this.fromHour = request.getParameter(OrderHistoryData.FROMHOUR);
      
      this.toYear = request.getParameter(OrderHistoryData.TOYEAR);
      this.toMonth = request.getParameter(OrderHistoryData.TOMONTH);
      this.toDay = request.getParameter(OrderHistoryData.TODAY);
      this.toHour = request.getParameter(OrderHistoryData.TOHOUR);
      
      this.command = request.getParameter(GLOBALS2.ADMINCOMMAND);
   }
   
   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) this);
   }
   
   public String view() throws Exception
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "view()", e);
         }
         throw e;
      }
   }
   
   public Node toXmlNode(Document document)
   {
      try
      {
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
        	 StringMaker stringBuffer = new StringMaker();
        	 
        	 stringBuffer.append("Attempt to View orders in (fromDate=");
        	 stringBuffer.append(this.fromDate);
        	 stringBuffer.append(",toDate=");
        	 stringBuffer.append(this.toDate);
        	 stringBuffer.append(") and status");
        	 
        	 logUtil.put(stringBuffer.toString(), this, "view");
         }
         
         Node node = document.createElement(OrderData.ORDERS);
         
            /*
            if(preprocessing!=null && preprocessing.compareTo(OrderHistoryData.PREPROCESSING)==0)
            {
               Node preprocessingNode = document.createElement(OrderHistoryData.PREPROCESSING);
             
               Vector orderHistoryVector = OrderHistoryEntityFactory.getInstance().getOrders(OrderHistoryData.PREPROCESSING ,fromDate, toDate);
             
               iter = orderHistoryVector;
               while(iter.hasNext())
               {
                  OrderHistory orderHistory = (OrderHistory) iter.next();
                  preprocessingNode.appendChild(orderHistory.toXmlNode(document));
               }
               node.appendChild(preprocessingNode);
            }
             
            if(shipped!=null && shipped.compareTo(OrderHistoryData.SHIPPED)==0)
            {
               Node shippedNode = document.createElement(OrderHistoryData.SHIPPED);
             
               Vector orderHistoryVector = OrderHistoryEntityFactory.getInstance().getOrders(OrderHistoryData.SHIPPED ,fromDate, toDate);
             
               iter = orderHistoryVector;
               while(iter.hasNext())
               {
                  OrderHistory orderHistory = (OrderHistory) iter.next();
                  shippedNode.appendChild(orderHistory.toXmlNode(document));
               }
               node.appendChild(shippedNode);
            }
             
            if(partiallyShipped!=null && partiallyShipped.compareTo(OrderHistoryData.PARTIALLYSHIPPED)==0)
            {
               Node partiallyShippedNode = document.createElement(OrderHistoryData.PARTIALLYSHIPPED);
             
               Vector orderHistoryVector = OrderHistoryEntityFactory.getInstance().getOrders(OrderHistoryData.PARTIALLYSHIPPED ,fromDate, toDate);
             
               iter = orderHistoryVector;
               while(iter.hasNext())
               {
                  OrderHistory orderHistory = (OrderHistory) iter.next();
                  partiallyShippedNode.appendChild(orderHistory.toXmlNode(document));
               }
               node.appendChild(partiallyShippedNode);
            }
             
            if(processing!=null && processing.compareTo(OrderHistoryData.PROCESSING)==0)
            {
               Node processingNode = document.createElement(OrderHistoryData.PROCESSING);
             
               Vector orderHistoryVector = OrderHistoryEntityFactory.getInstance().getOrders(OrderHistoryData.PROCESSING ,fromDate, toDate);
             
               iter = orderHistoryVector();
               while(iter.hasNext())
               {
                  OrderHistory orderHistory = (OrderHistory) iter.next();
                  processingNode.appendChild(orderHistory.toXmlNode(document));
               }
               node.appendChild(processingNode);
            }
             
            if(cancelled!=null && cancelled.compareTo(OrderHistoryData.CANCELLED)==0)
            {
               Node cancelledNode = document.createElement(OrderHistoryData.CANCELLED);
             
               Vector orderHistoryVector = OrderHistoryEntityFactory.getInstance().getOrders(OrderHistoryData.CANCELLED ,fromDate, toDate);
             
               iter = orderHistoryVector;
               while(iter.hasNext())
               {
                  OrderHistory orderHistory = (OrderHistory) iter.next();
                  cancelledNode.appendChild(orderHistory.toXmlNode(document));
               }
               node.appendChild(cancelledNode);
            }
             */
         
            /*
            Vector orderHistoryVector = OrderHistoryEntityFactory.getInstance().getOrders(fromDate, toDate);
             
            iter = orderHistoryVector;
            while(iter.hasNext())
            {
               OrderHistory orderHistory = (OrderHistory) iter.next();
               node.appendChild(orderHistory.toXmlNode(document));
            }
             */
         
         if(preprocessing!=null && preprocessing.compareTo(ON)==0)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("Adding Preprocessing Orders", this, "toXmlNode");
            }
            
            Vector orderHistoryVector = OrderHistoryEntityFactory.getInstance().getOrders(OrderHistoryData.PREPROCESSING ,fromDate, toDate);
            
            final int size = orderHistoryVector.size();
            for(int index = 0; index < size; index++)
            {
               OrderHistory orderHistory = (OrderHistory) orderHistoryVector.get(index);
               node.appendChild(orderHistory.toXmlNode(document));
            }
         }
         
         if(shipped!=null && shipped.compareTo(ON)==0)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("Adding Shipped Orders",this, "toXmlNode");
            }

            Vector orderHistoryVector = OrderHistoryEntityFactory.getInstance().getOrders(OrderHistoryData.SHIPPED ,fromDate, toDate);
            
            final int size = orderHistoryVector.size();
            for(int index = 0; index < size; index++)
            {
               OrderHistory orderHistory = (OrderHistory) orderHistoryVector.get(index);
               node.appendChild(orderHistory.toXmlNode(document));
            }
         }
         
         if(partiallyShipped!=null && partiallyShipped.compareTo(ON)==0)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("Adding Partially Shipped Orders", this, "toDomNode");
            }
            
            Vector orderHistoryVector = OrderHistoryEntityFactory.getInstance().getOrders(OrderHistoryData.PARTIALLYSHIPPED ,fromDate, toDate);
            
            final int size = orderHistoryVector.size();
            for(int index = 0; index < size; index++)
            {
               OrderHistory orderHistory = (OrderHistory) orderHistoryVector.get(index);
               node.appendChild(orderHistory.toXmlNode(document));
            }
         }
         
         if(processing!=null && processing.compareTo(ON)==0)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("Adding Processing Orders", this, "toXmlNode");
            }
            
            Vector orderHistoryVector = OrderHistoryEntityFactory.getInstance().getOrders(OrderHistoryData.PROCESSING ,fromDate, toDate);
            
            final int size = orderHistoryVector.size();
            for(int index = 0; index < size; index++)
            {
               OrderHistory orderHistory = (OrderHistory) orderHistoryVector.get(index);
               node.appendChild(orderHistory.toXmlNode(document));
            }
         }
         
         if(cancelled!=null && cancelled.compareTo(ON)==0)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("Adding Cancelled Orders", this, "view");
            }
            
            Vector orderHistoryVector = OrderHistoryEntityFactory.getInstance().getOrders(OrderHistoryData.CANCELLED ,fromDate, toDate);
            
            final int size = orderHistoryVector.size();
            for(int index = 0; index < size; index++)
            {
               OrderHistory orderHistory = (OrderHistory) orderHistoryVector.get(index);
               node.appendChild(orderHistory.toXmlNode(document));
            }
         }
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         OrderHistoryData.PREPROCESSINGNAME,
         OrderHistoryData.PREPROCESSING));
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         OrderHistoryData.PROCESSINGNAME,
         OrderHistoryData.PROCESSING));
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         OrderHistoryData.CANCELLEDNAME,
         OrderHistoryData.CANCELLED));
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         OrderHistoryData.PARTIALLYSHIPPEDNAME,
         OrderHistoryData.PARTIALLYSHIPPED));
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         OrderHistoryData.SHIPPEDNAME,
         OrderHistoryData.SHIPPED));
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         GLOBALS2.VIEWNAME,
         GLOBALS2.VIEW));
         
         return node;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XSLLOGGINGERROR))
         {
            logUtil.put(this.commonStrings.FAILURE, this, "toXmlNode", e);
         }
         return null;
      }
      
   }
   
   public Boolean isValid()
   {
      try
      {
         
         Boolean isValid = Boolean.TRUE;
         
         if(dateType==null ||
         (dateType.compareTo(OrderHistoryData.TYPELONG)!=0 &&
         dateType.compareTo(OrderHistoryData.TYPECAESAR)!=0))
         {
            isValid = Boolean.FALSE;
         }
         
         if(dateType!=null)
         {
            
            if(dateType.compareTo(OrderHistoryData.TYPELONG)==0)
            {
               Calendar calendar = Calendar.getInstance();
               long currentTime = calendar.getTimeInMillis();
               
               //set date delta OrderHistoryData.DATEDELTA
               String rangeDate = StringUtil.getInstance().EMPTY_STRING;
               
               if(command.compareTo(VIEWORDERSINLASTHOUR)==0)
               {
                  long oneHour = (long) 60*60*1000;
                  rangeDate = new Long(oneHour).toString();
               }
               else
                  if(command.compareTo(VIEWORDERSINLASTDAY)==0)
                  {
                     long oneDay = (long) 24*60*60*1000;
                     rangeDate = new Long(oneDay).toString();
                  }
                  else
                     if(command.compareTo(VIEWORDERSINLASTWEEK)==0)
                     {
                        long oneWeek = (long) 7*24*60*60*1000;
                        rangeDate = new Long(oneWeek).toString();
                     }
                     else
                        if(command.compareTo(VIEWORDERSINLAST30DAYS)==0)
                        {
                           long thirtyDays = (long) 30*24*60*60*1000;
                           rangeDate = new Long(thirtyDays).toString();
                        }
                        else
                           if(command.compareTo(VIEWALLORDERS)==0)
                           {
                              rangeDate = new Long(currentTime).toString();
                           }
               
               long rangeDateLong = new Long(rangeDate).longValue();
               this.fromDate = new Long(currentTime-rangeDateLong).toString();
               String time = new String(new Long(currentTime).toString());
               this.toDate = time;
            }
            else
               if(dateType.compareTo(OrderHistoryData.TYPECAESAR)==0)
               {
                   Calendar calendar = Calendar.getInstance();

                   TimeUtil.getInstance().setCalendar(
                 		  calendar, fromYear, fromMonth, fromDay, fromHour);

                   this.fromDate = new Long(calendar.getTimeInMillis()).toString();
                  
                  TimeUtil.getInstance().setCalendar(
                		  calendar, toYear, toMonth, toDay, toHour);
                  
                  this.toDate = new Long(calendar.getTimeInMillis()).toString();
               }
            
            if(StringValidationUtil.getInstance().isEmpty(toDate) || toDate.length()>MAXLEN)
            {
               isValid = Boolean.FALSE;
            }
            
            if(StringValidationUtil.getInstance().isEmpty(fromDate) || fromDate.length()>MAXLEN)
            {
               isValid = Boolean.FALSE;
            }
            
            
         }
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
        	 StringMaker stringBuffer = new StringMaker();

        	 stringBuffer.append("Attempt to View orders in (fromDate=");
        	 stringBuffer.append(this.fromDate);
        	 stringBuffer.append(",toDate=");
        	 stringBuffer.append(this.toDate);
        	 stringBuffer.append(")");

        	 logUtil.put(stringBuffer.toString(), this, commonStrings.IS_VALID);
         }
         
         return isValid;
         
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("Exception in validation", this, commonStrings.IS_VALID, e);
         }
         return Boolean.FALSE;
      }
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
   public String validationInfo() throws Exception
   {
      try
      {
         
         StringMaker result = new StringMaker();
         
         if(dateType==null ||
         (dateType.compareTo(OrderHistoryData.TYPELONG)!=0 &&
         dateType.compareTo(OrderHistoryData.TYPECAESAR)!=0))
         {
            result.append("DATETYPE not recognized");
         }
         
         if(StringValidationUtil.getInstance().isEmpty(toDate) || toDate.length()>MAXLEN)
         {
            result.append("Invalid To Date");
         }
         
         if(StringValidationUtil.getInstance().isEmpty(fromDate) || fromDate.length()>MAXLEN)
         {
            result.append("Invalid From Date");
         }
         
         //"Date Range Invalid"
         return result.toString();
       
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to generate validation error info", this, "validationInfo()", e);
         }
         return "Error Getting Validation Info";
      }
   }
   
}
