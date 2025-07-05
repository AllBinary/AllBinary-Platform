<%
/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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
%>

<%@ page buffer="32kb" %>

<%@ page import="java.util.*" %>

<%@ page import="abcs.globals.URLGLOBALS" %>
<%@ page import="abcs.globals.AppUrlGlobals" %>

<%@ page import="abcs.business.context.modules.storefronts.BasicStoreFrontInterface" %>
<%@ page import="abcs.business.context.modules.storefronts.BasicStoreFrontFactory" %>
<%@ page import="abcs.business.user.role.BasicUserRole" %>

<%@ page import="abcs.logic.basic.io.file.generators.OrderIdGenerator" %>
<%@ page import="abcs.logic.basic.io.file.generators.ProductIdGenerator" %>

<%@ page import="abcs.logic.basic.string.CommonStrings" %>
<%@ page import="abcs.logic.basic.string.StringValidationUtil" %>

<%@ page import="abcs.logic.system.loader.WebappClassLoaderInfo" %>

<%@ page import="org.allbinary.business.category.CategoryData" %>

<%@ page import="org.allbinary.business.context.modules.storefront.StoreFrontData" %>

<%@ page import="org.allbinary.business.entry.EntryData" %>

<%@ page import="org.allbinary.business.user.UserData" %>
<%@ page import="org.allbinary.business.user.role.UserRoleData" %>

<%@ page import="org.allbinary.business.user.address.BillingAddressData" %>
<%@ page import="org.allbinary.business.user.address.ShippingAddressData" %>
<%@ page import="org.allbinary.business.user.address.StreetAddressData" %>

<%@ page import="org.allbinary.business.user.commerce.inventory.item.BasicItemData" %>

<%@ page import="org.allbinary.business.user.commerce.inventory.order.OrderData" %>
<%@ page import="org.allbinary.business.user.commerce.inventory.order.OrderHistoryData" %>

<%@ page import="org.allbinary.business.user.commerce.money.payment.PaymentData" %>

<%@ page import="org.allbinary.business.user.commerce.shipping.ShippingMethodData" %>
<%@ page import="org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayData" %>

<%@ page import="org.allbinary.globals.GLOBALS" %>

<%@ page import="org.allbinary.logic.communication.http.request.session.WeblisketSessionData" %>

<%@ page import="org.allbinary.logic.control.search.SearchData" %>
<%@ page import="org.allbinary.logic.control.workflow.WorkFlowData" %>

<%@ page import="abcs.logic.visual.media.MediaData" %>

<%@ page import="org.allbinary.logic.visual.transform.info.CustomizerTransformInfoData" %>
<%@ page import="org.allbinary.logic.visual.transform.info.GeneratorTransformInfoData" %>
<%@ page import="org.allbinary.logic.visual.transform.info.RootTransformInfoData" %>
