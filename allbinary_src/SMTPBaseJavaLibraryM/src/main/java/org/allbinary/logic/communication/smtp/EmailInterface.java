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
package org.allbinary.logic.communication.smtp;

import javax.mail.internet.MimeMessage;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.document.mapping.DomDocumentMappingInterface;

public interface EmailInterface
   extends DomNodeInterface, DomDocumentMappingInterface
{
    public String getDebugInfo();
    public MimeMessage getMimeMessage();
    public String log() throws Exception;
}
