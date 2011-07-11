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
   xmlns:jutil="/WEB-INF/jutil.tld"
   xmlns:admin="/WEB-INF/admin.tld"   
   xmlns:ecommerce="/WEB-INF/ecommerce.tld"   
   xmlns:generic="/WEB-INF/generic.tld"
   xmlns:payment="/WEB-INF/payment.tld"
   xmlns:transform="/WEB-INF/transform.tld"
   xmlns:transformInfoObjectConfig="/WEB-INF/transformInfoObjectConfig.tld" >

   <xsl:template name="menuPreview" >

<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

categoryDropDownMenuPreview<br/>
<jsp:directive.include file="categoryDropDownMenuPreview.jsp"/>
categoryListPreview<br/>
<jsp:directive.include file="categoryListPreview.jsp"/>
authenticationPreview<br/>
<jsp:directive.include file="authenticationPreview.jsp"/>
customerLinksStyleOnePreview<br/>
<jsp:directive.include file="customerLinksStyleOnePreview.jsp"/>
customerLinksStyleTwoPreview<br/>
<jsp:directive.include file="customerLinksStyleTwoPreview.jsp"/>
customerLinksStyleThreePreview<br/>
<jsp:directive.include file="customerLinksStyleThreePreview.jsp"/>
microRegistrationPreview<br/>
<jsp:directive.include file="microRegistrationPreview.jsp"/>
miniBasketPreview<br/>
<jsp:directive.include file="miniBasketPreview.jsp"/>
miniRegistrationPreview<br/>
<jsp:directive.include file="miniRegistrationPreview.jsp"/>
helpListPreview<br/>
<jsp:directive.include file="helpListPreview.jsp"/>
newsListPreview<br/>
<jsp:directive.include file="newsListPreview.jsp"/>
partnersListPreview<br/>
<jsp:directive.include file="partnersListPreview.jsp"/>
clearanceBestPreview<br/>
<jsp:directive.include file="clearanceBestPreview.jsp"/>
productSearchPreview<br/>
<jsp:directive.include file="productSearchPreview.jsp"/>
productsBestPreview<br/>
<jsp:directive.include file="productsBestPreview.jsp"/>
productsNewPreview<br/>
<jsp:directive.include file="productsNewPreview.jsp"/>
productsPopularPreview<br/>
<jsp:directive.include file="productsPopularPreview.jsp"/>
saleProductsPreview<br/>
<jsp:directive.include file="saleProductsPreview.jsp"/>
specialsPreview<br/>
<jsp:directive.include file="specialsPreview.jsp"/>
rebatesBestPreview<br/>
<jsp:directive.include file="rebatesBestPreview.jsp"/>

   </xsl:template>

</xsl:stylesheet> 
