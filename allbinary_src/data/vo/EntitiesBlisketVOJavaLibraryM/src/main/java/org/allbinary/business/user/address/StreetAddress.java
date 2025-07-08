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
package org.allbinary.business.user.address;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.string.StringUtil;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import java.util.Set;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringValidationUtil;

public class StreetAddress
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String id;
   private String name;
   private String street;
   private String city;
   private String state;
   private String code;
   private String country;
   private boolean isDefault = false;
   
   public StreetAddress()
   {
   }

   public StreetAddress(Node node) throws Exception
   {
      Node nameNode = 
         DomSearchHelper.getNode(StreetAddressData.NAME, node.getChildNodes());
      this.name = DomNodeHelper.getTextNodeValue(nameNode);
      
      Node streetNode = 
         DomSearchHelper.getNode(StreetAddressData.STREET, node.getChildNodes());
      this.street = DomNodeHelper.getTextNodeValue(streetNode);

      Node cityNode = 
         DomSearchHelper.getNode(StreetAddressData.CITY, node.getChildNodes());
      this.city = DomNodeHelper.getTextNodeValue(cityNode);

      Node stateNode = 
         DomSearchHelper.getNode(StreetAddressData.STATE, node.getChildNodes());
      this.state = DomNodeHelper.getTextNodeValue(stateNode);

      Node codeNode = 
         DomSearchHelper.getNode(StreetAddressData.CODE, node.getChildNodes());
      this.code = DomNodeHelper.getTextNodeValue(codeNode);

      Node countryNode = 
         DomSearchHelper.getNode(StreetAddressData.COUNTRY, node.getChildNodes());
      this.country = DomNodeHelper.getTextNodeValue(countryNode);
      
      this.log();
   }

   public StreetAddress(StreetAddress streetAddress)
   {
      this.id = streetAddress.getId();
      this.name = streetAddress.getName();
      this.street = streetAddress.getStreet();
      this.city = streetAddress.getCity();
      this.state = streetAddress.getState();
      this.code = streetAddress.getCode();
      this.country = streetAddress.getCountry();
      this.isDefault = streetAddress.isDefault();
      this.log();      
   }

   public StreetAddress(HttpServletRequest request)
   {
      this.setId(request.getParameter(StreetAddressData.ID));
      this.setName(request.getParameter(StreetAddressData.NAME));
      this.setStreet(request.getParameter(StreetAddressData.STREET));
      this.setCity(request.getParameter(StreetAddressData.CITY));
      this.setState(request.getParameter(StreetAddressData.STATE));
      this.setCode(request.getParameter(StreetAddressData.CODE));
      this.setCountry(request.getParameter(StreetAddressData.COUNTRY));
      
      this.log();      
   }
   
   public StreetAddress(String name, String street, 
      String city, String state, String code, String country)
   {
      this.name = name;
      this.street = street;
      this.city = city;
      this.state = state;
      this.code = code;
      this.country = country;
      
      this.log();      
   }

   public StreetAddress(HashMap address)
   {
      this.id = (String) address.get(StreetAddressData.ID);
      this.name = (String) address.get(StreetAddressData.NAME);
      this.street = (String) address.get(StreetAddressData.STREET);
      this.city = (String) address.get(StreetAddressData.CITY);
      this.state = (String) address.get(StreetAddressData.STATE);
      this.code = (String) address.get(StreetAddressData.CODE);
      this.country = (String) address.get(StreetAddressData.COUNTRY);
      String def = (String) address.get(StreetAddressData.DEFAULT);
      if(def!=null && def.compareTo(StreetAddressData.DEFAULT)==0)
      {
         this.isDefault = true;
      }
      else
      {
         this.isDefault = false;
      }
      
      this.log();      
   }

   private void log()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
      {
         logUtil.put("Created Address: \n" + this.toHashMap(), this, "log");
      }
   }
   
   public void setId(String id)
   {
      this.id = id;
   }
   
   public void setName(String name)
   {
      this.name=name;
   }
   
   public void setStreet(String value)
   {
      this.street=value;
   }
   
   public void setCity(String value)
   {
      this.city=value;
   }
   
   public void setState(String value)
   {
      this.state=value;
   }
   
   public void setCode(String value)
   {
      this.code=value;
   }
   
   public void setCountry(String value)
   {
      this.country=value;
   }
   
   public String getName()
   {
      return this.name;
   }

   public String getStreet()
   {
      return this.street;
   }
   
   public String getCity()
   {
      return this.city;
   }
   
   public String getState()
   {
      return this.state;
   }
   
   public String getCode()
   {
      return this.code;
   }
   
   public String getCountry()
   {
      return this.country;
   }   

   public String getId()
   {
      return this.id;
   }      
   
   public boolean isDefault()
   {
      return isDefault;
   }

   public Boolean isEmpty()
   {
      /*
       *      if(StringValidationUtil.isEmpty(this.getName()) &&
         StringValidationUtil.isEmpty(this.getStreet()) &&
         StringValidationUtil.isEmpty(this.getCity()) &&
         StringValidationUtil.isEmpty(this.getState()) && 
         StringValidationUtil.isEmpty(this.getCode()) &&
         StringValidationUtil.isEmpty(this.getCountry()))
*/
       final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
       
      if(this.getName()==null && this.getName().compareTo(EMPTY_STRING)==0 &&
      this.getStreet()==null && this.getStreet().compareTo(EMPTY_STRING)==0 &&
      this.getCity()==null && this.getCity().compareTo(EMPTY_STRING)==0 &&
      this.getState()==null && this.getState().compareTo(EMPTY_STRING)==0 &&
      this.getCode()==null && this.getCode().compareTo(EMPTY_STRING)==0 &&
      this.getCountry()==null && this.getCountry().compareTo(EMPTY_STRING)==0)
      {
         return Boolean.TRUE;
      }
      return Boolean.FALSE;
   }
   
   public Boolean isValid()
   {
       final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
       
      if(this.getName()==null || this.getName().compareTo(EMPTY_STRING)==0 ||
      this.getStreet()==null || this.getStreet().compareTo(EMPTY_STRING)==0 ||
      this.getCity()==null || this.getCity().compareTo(EMPTY_STRING)==0 ||
      this.getState()==null || this.getState().compareTo(EMPTY_STRING)==0 ||
      this.getCode()==null || this.getCode().compareTo(EMPTY_STRING)==0 ||
      this.getCountry()==null || this.getCountry().compareTo(EMPTY_STRING)==0)
      {
         return Boolean.FALSE;
      }
      return Boolean.TRUE;
   }
   
   public String validationInfo()
   {
       StringBuilder stringBuffer = new StringBuilder();
       
       stringBuffer.append("Address Failed To Validate - All fields must contain data.<br></br>");
      
      StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
      
      if(!stringValidationUtil.isValidRequired(this.getName(), 
         StreetAddressData.MIN, StreetAddressData.MAX))
      {
          stringBuffer.append("Please enter a valid name");
          stringBuffer.append("<br></br>");
      }
      
      if(!stringValidationUtil.isValidRequired(this.getStreet(), 
         StreetAddressData.MIN, StreetAddressData.MAX))
      {
          stringBuffer.append("Please enter a valid street");
          stringBuffer.append("<br></br>");
      }
         
      if(!stringValidationUtil.isValidRequired(this.getCity(), 
         StreetAddressData.MIN, StreetAddressData.MAX))      
      {
          stringBuffer.append("Please enter a valid city");
          stringBuffer.append("<br></br>");
      }
         
      if(!stringValidationUtil.isValidRequired(this.getState(), 
         StreetAddressData.MIN, StreetAddressData.MAX))      
      {
          stringBuffer.append("Please enter a valid state");
          stringBuffer.append("<br></br>");
      }
         
      if(!stringValidationUtil.isValidRequired(this.getCode(), 
         StreetAddressData.MIN, StreetAddressData.MAX))      
      {
          stringBuffer.append("Please enter a valid zipcode");
          stringBuffer.append("<br></br>");
      }
         
      if(!stringValidationUtil.isValidRequired(this.getCountry(), 
         StreetAddressData.MIN, StreetAddressData.MAX))      
      {
          stringBuffer.append("Please enter a valid country");
          stringBuffer.append("<br></br>");
      }
      
      return stringBuffer.toString();
   }
  
   private Document toValidationInfoDoc()
   {
      return null;
   }
      
   private Node toValidationInfoNode(Document document)
   {
      try
      {
         Node node = document.createElement(StreetAddressData.FORM);
         node.appendChild(this.toXmlNode(document));
         return node;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "toValidationInfoNode", e);
         }
         //throw e;
         return null;
      }
   }   
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      
      hashMap.put(StreetAddressData.ID,this.id);
      hashMap.put(StreetAddressData.NAME,this.name);
      hashMap.put(StreetAddressData.STREET,this.street);
      hashMap.put(StreetAddressData.CITY,this.city);
      hashMap.put(StreetAddressData.STATE,this.state);
      hashMap.put(StreetAddressData.CODE,this.code);
      hashMap.put(StreetAddressData.COUNTRY,this.country);

      /*
      if(this.isDefault)
      {
         hashMap.put(StreetAddressData.DEFAULT,StreetAddressData.DEFAULT);
      }
      else hashMap.put(StreetAddressData.DEFAULT,EMPTY_STRING);
      */
      
      return hashMap;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      StringUtil stringUtil = StringUtil.getInstance();
       
      HashMap hashMap = this.toHashMap();
      Set keySet = hashMap.keySet();
      Object[] keyArray = keySet.toArray();
      int size = keyArray.length;
      
      Node node = document.createElement(StreetAddressData.ADDRESS);
      
      for (int i = 0; i < size; i++)
      {
         String name = (String) keyArray[i];
         String value = stringUtil.getInstance((String) hashMap.get(name));
         
         node.appendChild(ModDomHelper.createNameValueNodes(document, name, value));         
      }
            
      return node;
   }
      
}
