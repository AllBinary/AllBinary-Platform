<?xml version="1.0" encoding="UTF-8" ?>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:jsp="http://java.sun.com/JSP/Page"
>
   <xsl:output method="xml" indent="yes" />

    <xsl:template name="jspDirectivePageImports" >

<jsp:directive.page import="java.util.*" />

<jsp:directive.page import="org.allbinary.business.context.modules.storefronts.BasicStoreFrontInterface" />
<jsp:directive.page import="org.allbinary.business.context.modules.storefronts.BasicStoreFrontFactory" />
<jsp:directive.page import="org.allbinary.business.user.role.BasicUserRole" />

<jsp:directive.page import="org.allbinary.globals.URLGLOBALS" />

<jsp:directive.page import="org.allbinary.logic.basic.io.LineReader" />
<jsp:directive.page import="org.allbinary.logic.basic.string.StringUtil"/>
<jsp:directive.page import="org.allbinary.logic.basic.string.StringValidationUtil"/>

<jsp:directive.page import="org.allbinary.logic.communication.http.request.session.BasicWeblisketSession" />

<jsp:directive.page import="org.allbinary.logic.system.loader.WebappClassLoaderInfo"/>

<jsp:directive.page import="org.allbinary.business.context.modules.storefront.StoreFrontData" />

<jsp:directive.page import="org.allbinary.business.entry.EntryData" />

<jsp:directive.page import="org.allbinary.business.forward.ForwardData" />

<jsp:directive.page import="org.allbinary.business.quoterequest.QuoteRequestData" />

<jsp:directive.page import="org.allbinary.business.user.UserData" />

<jsp:directive.page import="org.allbinary.business.user.address.BillingAddressData" />
<jsp:directive.page import="org.allbinary.business.user.address.ShippingAddressData" />

<jsp:directive.page import="org.allbinary.business.user.commerce.inventory.basket.BasketData" />

<jsp:directive.page import="org.allbinary.business.user.commerce.inventory.item.BasicItemData" />

<jsp:directive.page import="org.allbinary.business.user.commerce.inventory.order.OrderHistoryData" />
<jsp:directive.page import="org.allbinary.business.user.commerce.inventory.order.OrderData" />

<jsp:directive.page import="org.allbinary.business.user.commerce.money.payment.PaymentData" />
<jsp:directive.page import="org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayData" />

<jsp:directive.page import="org.allbinary.business.user.role.UserRoleData" />

<jsp:directive.page import="org.allbinary.globals.GLOBALS" />

<jsp:directive.page import="org.allbinary.logic.communication.http.request.session.WeblisketSessionData" />

<jsp:directive.page import="org.allbinary.logic.control.search.SearchData" />

    </xsl:template>

</xsl:stylesheet> 
