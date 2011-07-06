/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: January 5, 2006, 9:45 AM
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.business.backup;

import abcs.globals.URLGLOBALS;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.directory.Directory;
import abcs.logic.basic.io.file.zip.ZipFileUtil;
import abcs.logic.basic.path.AbPath;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.data.tables.context.module.storefronts.StoreFrontsEntity;
import allbinary.data.tables.context.module.storefronts.StoreFrontsEntityFactory;
import allbinary.globals.PATH_GLOBALS;
import java.util.Vector;

public class BlisketBackupFactory
{
    private static final BlisketBackupFactory instance = new BlisketBackupFactory();

    public static BlisketBackupFactory getInstance()
    {
        return instance;
    }

    private Vector getFileVector(String pathString)
        throws Exception
    {
        AbPath path = new AbPath(pathString);

        AbFile file = new AbFile(path);

        return Directory.getInstance().search(file, true);
    }

    public void backup()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Start", this, "backup()"));

            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(URLGLOBALS.getWebappPath());
            stringBuffer.append(PATH_GLOBALS.getInstance().BACKUP_PATH);

            String backupPath = stringBuffer.toString();

            AbPath path = new AbPath(backupPath);

            Vector fileVector = this.getFileVector(backupPath);

            this.backup(fileVector, path.toFileSystemString() + "backup.zip");

        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "backup()", e));
        }
    }

    public void backupViews()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Start", this, "backupViews()"));

            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(URLGLOBALS.getWebappPath());
            stringBuffer.append(PATH_GLOBALS.getInstance().BACKUP_PATH);

            String backupPath = stringBuffer.toString();

            AbPath path = new AbPath(backupPath);

            Vector fileVector = new Vector();

            //Backup other customizable generated files
            StoreFrontsEntity storeFrontsEntity =
                StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance();

            Vector storeFrontNamesVector = storeFrontsEntity.getStoreFrontNames();

            int size = storeFrontNamesVector.size();
            for (int index = 0; index < size; index++)
            {
                String nextStore = (String) storeFrontNamesVector.get(index);

                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(URLGLOBALS.getWebappPath());
                stringBuffer.append(PATH_GLOBALS.getInstance().VIEWS_PATH);
                stringBuffer.append(nextStore);

                String viewsPath = stringBuffer.toString();

                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append("Backup Store Views: ");
                stringBuffer.append(nextStore);
                stringBuffer.append(" from: ");
                stringBuffer.append(viewsPath);

                PreLogUtil.put(stringBuffer.toString(), this, "backupViews()");

                //Views
                fileVector.addAll(this.getFileVector(viewsPath));
            }

            this.backup(fileVector, path.toFileSystemString() + "backupViews.zip");

        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "backupViews()", e));
        }
    }

    public void backupResources()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Start", this, "backupResources()"));

            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(URLGLOBALS.getWebappPath());
            stringBuffer.append(PATH_GLOBALS.getInstance().BACKUP_PATH);

            String backupPath = stringBuffer.toString();

            AbPath path = new AbPath(backupPath);

            Vector fileVector = new Vector();

            //Backup other customizable generated files
            StoreFrontsEntity storeFrontsEntity =
                StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance();

            Vector storeFrontNamesVector = storeFrontsEntity.getStoreFrontNames();

            int size = storeFrontNamesVector.size();
            for (int index = 0; index < size; index++)
            {
                String nextStore = (String) storeFrontNamesVector.get(index);

                StoreFrontInterface storeFrontInterface =
                    storeFrontsEntity.getStoreFrontInterface(nextStore);

                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(URLGLOBALS.getWebappPath());
                stringBuffer.append(storeFrontInterface.getCurrentHostNamePath());
                stringBuffer.append(storeFrontInterface.getCategoryPath());

                String resourcesPath = stringBuffer.toString();

                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append("Backup Store Resrouces: ");
                stringBuffer.append(nextStore);
                stringBuffer.append(" from: ");
                stringBuffer.append(resourcesPath);

                PreLogUtil.put(stringBuffer.toString(), this, "backupResources()");

                //Views
                fileVector.addAll(this.getFileVector(resourcesPath));
            }

            this.backup(fileVector, path.toFileSystemString() + "backupResources.zip");

        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "backupResources()", e));
        }
    }

    public void backupJsps()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Start", this, "backupJsps()"));

            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(URLGLOBALS.getWebappPath());
            stringBuffer.append(PATH_GLOBALS.getInstance().BACKUP_PATH);

            String backupPath = stringBuffer.toString();

            AbPath path = new AbPath(backupPath);

            Vector fileVector = new Vector();

            //Backup other customizable generated files
            StoreFrontsEntity storeFrontsEntity =
                StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance();

            Vector storeFrontNamesVector = storeFrontsEntity.getStoreFrontNames();

            int size = storeFrontNamesVector.size();
            for (int index = 0; index < size; index++)
            {
                String nextStore = (String) storeFrontNamesVector.get(index);

                PreLogUtil.put("Backup Store Jsps: " + nextStore, this, "backupJsps()");

                //Store jsps - while they can be generated from the views I still back them up
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(URLGLOBALS.getWebappPath());
                stringBuffer.append(nextStore);

                fileVector.addAll(this.getFileVector(stringBuffer.toString()));
            }

            this.backup(fileVector, path.toFileSystemString() + "backupJsps.zip");

        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "backupJsps()", e));
        }
    }

    public void backup(Vector fileVector, String zipFile)
    {
        try
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append("ZipFile: ");
            stringBuffer.append(zipFile);
            stringBuffer.append(" Vector: ");
            stringBuffer.append(fileVector.size());

            //LogUtil.put(LogFactory.getInstance(
              //  "Creating Backup Zip File: " + stringBuffer.toString(), this, "backup()"));
            PreLogUtil.put("Creating Backup Zip File: " + stringBuffer.toString(), this, "backup()");

            ZipFileUtil.getInstance().create(zipFile, fileVector);

            LogUtil.put(LogFactory.getInstance(
                "Created Backup Zip File", this, "backup()"));

        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "backup()", e));
        }
    }
}
