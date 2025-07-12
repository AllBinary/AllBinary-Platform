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

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:jsp="http://java.sun.com/JSP/Page"
>

    <xsl:template name="menu" >

<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

categoryDropDownMenu<br/>
<jsp:directive.include file="categoryDropDownMenu.jsp"/>
categoryList<br/>
<jsp:directive.include file="categoryList.jsp"/>
authentication<br/>
<jsp:directive.include file="authentication.jsp"/>
customerLinksStyleOne<br/>
<jsp:directive.include file="customerLinksStyleOne.jsp"/>
customerLinksStyleTwo<br/>
<jsp:directive.include file="customerLinksStyleTwo.jsp"/>
customerLinksStyleThree<br/>
<jsp:directive.include file="customerLinksStyleThree.jsp"/>
microRegistration<br/>
<jsp:directive.include file="microRegistration.jsp"/>
miniBasket<br/>
<jsp:directive.include file="miniBasket.jsp"/>
miniRegistration<br/>
<jsp:directive.include file="miniRegistration.jsp"/>
helpList<br/>
<jsp:directive.include file="helpList.jsp"/>
newsList<br/>
<jsp:directive.include file="newsList.jsp"/>
partnersList<br/>
<jsp:directive.include file="partnersList.jsp"/>
clearanceBest<br/>
<jsp:directive.include file="clearanceBest.jsp"/>
productSearch<br/>
<jsp:directive.include file="productSearch.jsp"/>
productsBest<br/>
<jsp:directive.include file="productsBest.jsp"/>
productsNew<br/>
<jsp:directive.include file="productsNew.jsp"/>
productsPopular<br/>
<jsp:directive.include file="productsPopular.jsp"/>
saleProducts<br/>
<jsp:directive.include file="saleProducts.jsp"/>
specials<br/>
<jsp:directive.include file="specials.jsp"/>
rebatesBest<br/>
<jsp:directive.include file="rebatesBest.jsp"/>

    </xsl:template>

</xsl:stylesheet> 
