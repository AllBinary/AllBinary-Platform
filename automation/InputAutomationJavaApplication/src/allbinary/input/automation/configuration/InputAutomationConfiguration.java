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
package allbinary.input.automation.configuration;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.w3c.dom.Document;

import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.basic.io.file.FileUtil;
import abcs.logic.basic.path.AbPath;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

//name="inputAutomationConfiguration", 
@XmlRootElement(namespace = "http://www.allbinary.com/InputAutomationConfiguration")
@XmlType(name="InputAutomationConfiguration")
public class InputAutomationConfiguration
{
    private final static String FILENAME = "InputAutomationConfig.xml";
    
    //@XmlElement(name="installed")
    private boolean installed;

    public InputAutomationConfiguration()
    throws Exception
    {
        /*
        LogUtil.put("Start", this, "Constructor");

        //AbPath abPath = new AbPath("", FILENAME);
        byte bytes[]= new byte[100000];
        FileInputStream idFile = new FileInputStream(FILENAME);
        int length = idFile.read(bytes);
        String data = new String(bytes);
        int endIndex = data.lastIndexOf('>');
        
        //String data = new CryptFileReader("xml","grx").get(abPath);
        Document document = DomDocumentHelper.create(data.substring(0, endIndex + 1));
        
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
        LogUtil.put("Adding: " + inputAutomationModuleConfiguration.getName(), this, "add");

        Node newNode = inputAutomationModuleConfiguration.toDomNode(
            this.getDocument());
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
        LogUtil.put("Removing: " + inputAutomationModuleInterface.getName(), this, "remove");

        Document document = this.getDocument();
        
        Node modulesNode = document.getElementsByTagName(
            InputAutomationModulesData.NAME).item(0);
        
        NodeList nameNodeList =
            document.getElementsByTagName(InputAutomationModuleData.NAME);
        
        LogUtil.put("Number Of Module(s) Specified: " + nameNodeList.getLength(), this,"remove");
        
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
                    
                    LogUtil.put("Name : " + className, this, "Contructor");
                    
                    InputAutomationModuleFactoryInterface gameAutomationRobotModuleInterface =
                        (InputAutomationModuleFactoryInterface) AbeFactory.getInstance(className);
                    
                    if(inputAutomationModuleInterface.getName().compareTo(
                        gameAutomationRobotModuleInterface.getName()) == 0)
                    {
                        
                        modulesNode.removeChild(node);
                    }
                }
                else
                {
                    LogUtil.put("Class Node Null", this,"Contructor");
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
        LogUtil.put(new Log("Installed: " + installed, this, "setIntalled"));
    }
    
    public void save()
    throws Exception
    {
        Document document = DomDocumentHelper.create();; 
        File file = new File(FILENAME);
        FileUtil.copy(new AbPath(file.getAbsolutePath()),
            new AbPath(file.getAbsolutePath() + ".bak.xml"));
        
        JAXBContext jaxbContext = 
            JAXBContext.newInstance(InputAutomationConfiguration.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(this, document);
        
        DomDocumentHelper.save(file, document);
    }
}
