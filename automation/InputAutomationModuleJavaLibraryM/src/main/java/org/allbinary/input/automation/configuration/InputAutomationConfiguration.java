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
package org.allbinary.input.automation.configuration;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.allbinary.data.tree.dom.document.DomDocumentFileHelper;

import org.w3c.dom.Document;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.input.automation.module.configuration.InputAutomationModuleConfiguration;
import org.allbinary.logic.io.file.FileUtil;
import org.allbinary.logic.io.file.FileWrapperUtil;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

//name="inputAutomationConfiguration", 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "http://www.allbinary.com/InputAutomationConfiguration")
@XmlType(name="InputAutomationConfiguration")
public class InputAutomationConfiguration
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final static String FILENAME = "InputAutomationConfig.xml";
    
    @XmlElement(name="INPUT_AUTOMATION_INSTALLATION")
    private boolean installed;

    @XmlElement(name="INPUT_AUTOMATION_MODULE")
    private List<InputAutomationModuleConfiguration> inputAutomationModuleConfigurationList;

    public InputAutomationConfiguration()
    throws Exception
    {
        /*
        logUtil.put(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);

        //AbPath abPath = new AbPath(stringUtil.EMPTY_STRING, FILENAME);
        byte bytes[]= new byte[100000];
        FileInputStream idFile = new FileInputStream(FILENAME);
        int length = idFile.read(bytes);
        String data = new String(bytes);
        int endIndex = data.lastIndexOf('>');
        
        //String data = new CryptFileReader("xml","grx").get(abPath);
        Document document = DomDocumentHelper.create(data.substring(0, endIndex + 1);
        
        NodeList nodeList = document.getElementsByTagName(InputAutomationData.NAME);
        
        if(nodeList.getLength() != 0)
        {
            Node node = nodeList.item(0);
            Node installationNode =
                DomSearchHelper.getNode(
                InputAutomationData.INSTALLATION,
                node.getChildNodes());
            
            String installedString =
                DomNodeHelper.getTextNodeValue(installationNode);
            this.setInstalled(Boolean.valueOf(installedString).booleanValue());
        }
        
        */
    }
    
    public static File getFile()
    {
        return new File(FILENAME);
    }
    
    /*
    //Get create dom node from new module on the existing document
    public void add(
        InputAutomationModuleConfiguration inputAutomationModuleConfiguration)
        throws Exception
    {
        logUtil.put("Adding: " + inputAutomationModuleConfiguration.getName(), this, "add");

        Node newNode = inputAutomationModuleConfiguration.toDomNode(
            this.getDocument();
        NodeList nodeList = this.getDocument().getElementsByTagName(
            InputAutomationModulesData.NAME);
        
        if(nodeList.getLength() != 0)
        {
            Node node = nodeList.item(0);
            node.appendChild(newNode);
        }
        else
        {
            throw new Exception("Unable to add new module");
        }
    }
    
    public void remove(InputAutomationModuleFactoryInterface inputAutomationModuleInterface)
        throws Exception
    {
        logUtil.put("Removing: " + inputAutomationModuleInterface.getName(), this, "remove");

        Document document = this.getDocument();
        
        Node modulesNode = document.getElementsByTagName(
            InputAutomationModulesData.NAME).item(0);
        
        NodeList nameNodeList =
            document.getElementsByTagName(InputAutomationModuleData.NAME);
        
        LogUtil.put(LogFactory.getInstance("Number Of Module(s) Specified: " + nameNodeList.getLength(), this,"remove");
        
        for(int index = 0; index < nameNodeList.getLength(); index++)
        {
            Node node = nameNodeList.item(index);
            NodeList nodeList = node.getChildNodes();
            
            if(nodeList!=null)
            {
                Node classNameNode =
                    DomSearchHelper.getNode(DynamicObjectData.NAME, nodeList);
                
                if(classNameNode!=null)
                {
                    String className =
                        DomNodeHelper.getTextNodeValue(classNameNode);
                    
                    LogUtil.put(LogFactory.getInstance("Name : " + className, this, "Contructor");
                    
                    InputAutomationModuleFactoryInterface gameAutomationRobotModuleInterface =
                        (InputAutomationModuleFactoryInterface) AbeFactory.getInstance().getInstance(className);
                    
                    if(inputAutomationModuleInterface.getName().compareTo(
                        gameAutomationRobotModuleInterface.getName()) == 0)
                    {
                        
                        modulesNode.removeChild(node);
                    }
                }
                else
                {
                    LogUtil.put(LogFactory.getInstance("Class Node Null", this,"Contructor");
                }
            }
            else
            {
                LogUtil.put(InputAutomationData.NAME + " Name Node Node Children", this,"Contructor");
            }
        }
        this.save();
    }
    */
    
    public boolean isInstalled()
    {
        return installed;
    }
    
    public void setInstalled(boolean installed)
    {
        this.installed = installed;
        logUtil.put("Installed: " + installed, this, "setIntalled");
    }
 
    /**
     * @return the inputAutomationModuleConfigurationList
     */
    public List<InputAutomationModuleConfiguration> getInputAutomationModuleConfigurationList() {
        return inputAutomationModuleConfigurationList;
    }

    public void save()
    throws Exception
    {
        Document document = DomDocumentHelper.create();; 
        File file = new File(FILENAME);
        FileUtil.getInstance().copy(new AbPath(file.getAbsolutePath()),
            new AbPath(file.getAbsolutePath() + ".bak.xml"));
        
        JAXBContext jaxbContext = 
            JAXBContext.newInstance(InputAutomationConfiguration.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(this, document);
        
        DomDocumentFileHelper.save(FileWrapperUtil.wrapFile(file), document);
    }
}
