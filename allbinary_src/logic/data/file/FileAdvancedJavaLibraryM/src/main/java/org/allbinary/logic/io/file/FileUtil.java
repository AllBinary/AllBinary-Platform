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
package org.allbinary.logic.io.file;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.io.AbDataOutputStream;
import org.allbinary.logic.io.AbFileInputStream;
import org.allbinary.logic.io.AbFileLocalInputStream;
import org.allbinary.logic.io.AbFileSystem;
import org.allbinary.logic.io.DataOutputStreamFactory;
import org.allbinary.logic.io.FileStreamFactory;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.directory.Directory;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;

//data/init/views/TestStore/template/type/genericTemplateObjectConfig.xml
public class FileUtil
{
    private static final FileUtil instance = new FileUtil();

    /**
     * @return the instance
     */
    public static FileUtil getInstance()
    {
        return instance;
    }

    private final Directory directory = Directory.getInstance();
    
    private final LogConfigTypeFactory logConfigTypeFactory = LogConfigTypeFactory.getInstance();
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final StreamUtil streamUtil = StreamUtil.getInstance();
    
    private FileUtil()
    {
    }

    //overwrite files
    /*
    private static void copyFile(File from, File to) throws Exception
    {
    try
    {
    FileInputStream fileInputStream = new FileInputStream(from);
    DataInputStream dataInputStream =
    new DataInputStream(fileInputStream);

    FileOutputStream fileOutputStream = new FileOutputStream(to);
    DataOutputStream dataOutputStream =
    new DataOutputStream(fileOutputStream);

    outputStream = (ByteArrayOutputStream)
    StreamUtil.get(inputStream, outputStream);
    /*
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
    {
    LogUtil.put(LogFactory.getInstance(
    "Copied File from: " + from.toString() + " to: " + to.toString(),
    instance,"copyFile");
    }
     **/
    /*
    }
    catch(Exception e)
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILEERROR))
    {
    LogUtil.put(LogFactory.getInstance("Error Copying File from: " + from.toString() + " to: " +
    to.toString(),instance,"copyFile", e);
    }
    throw e;
    }
    }
     */
    public void write(InputStream inputStream, AbFile file)
        throws Exception
    {
        AbDataOutputStream dataOutputStream =
            DataOutputStreamFactory.getInstance().getInstance(file);

        this.write(inputStream, dataOutputStream);
    }

    public void write(InputStream inputStream, AbDataOutputStream dataOutputStream)
        throws Exception
    {
        try
        {
            dataOutputStream = (AbDataOutputStream)
                StreamUtil.getInstance().get(inputStream, dataOutputStream, new byte[16384]);

            dataOutputStream.flush();
        } finally
        {
            StreamUtil.getInstance().close(dataOutputStream);
        }
    }

    public void write(InputStream inputStream, AbDataOutputStream dataOutputStream, byte[] buffer)
        throws Exception
    {
        try
        {
            dataOutputStream = (AbDataOutputStream)
                StreamUtil.getInstance().get(inputStream, dataOutputStream, buffer);

            dataOutputStream.flush();
        } finally
        {
            StreamUtil.getInstance().close(dataOutputStream);
        }
    }

    private void copy(AbFileInputStream dataInputStream, AbDataOutputStream dataOutputStream)
        throws Exception
    {
        try
        {
            dataOutputStream = (AbDataOutputStream) StreamUtil.getInstance().get(dataInputStream, dataOutputStream, new byte[16384]);

            dataOutputStream.flush();
        } finally
        {
            StreamUtil.getInstance().close(dataOutputStream);
            StreamUtil.getInstance().close(dataInputStream);
        }
    }

    /*
     * TWB - Does not work with GAE io
    private static void copy(AbFileInputStream fileInputStream, AbFileOutputStream fileOutputStream)
    throws Exception
    {
    try
    {
    FileChannel inputFileChannel = fileInputStream.getChannel();
    FileChannel outputFileChannel = fileOutputStream.getChannel();

    inputFileChannel.transferTo(0, inputFileChannel.size(), outputFileChannel);

    //FileUtil.copy(
    //new DataInputStream(fileInputStream),
    //new DataOutputStream(fileOutputStream));
    } finally
    {
    StreamUtil.getInstance().close(fileOutputStream);
    StreamUtil.getInstance().close(fileInputStream);
    }
    }
     */
    /*
    private final boolean isInCloud(AbFile file)
    throws Exception
    {
    AbPath rootPath = new AbPath(URLGLOBALS.getWebappPath());

    //if (file.getPath().indexOf(rootPath) != -1)
    if (file.getPath().contains(rootPath.getPath()))
    {
    return false;
    } else
    {
    return true;
    }
    }
     */

    private AbPath fixPath(AbFile file, AbPath path, AbPath realPath, String cloud)
        throws Exception
    {
        int beginIndex = file.getPath().indexOf(cloud) + cloud.length();
        //int beginIndex = path.getPath().length();

        int endIndex = file.getPath().indexOf(file.getName());

        String fixedPathString =
            realPath.toFileSystemString()
            + file.getPath().substring(beginIndex, endIndex);

        AbPath fixedPath = new AbPath(fixedPathString);

        Directory.create(fixedPath);

        final String string = fixedPath.toFileSystemString();
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
        {
            LogUtil.put(LogFactory.getInstance(string, getInstance(), "fixPath"));
        }

        AbPath outPath = new AbPath(string, file.getName());

        return outPath;
    }

    public void copyToCloud(AbFile file, AbPath path, AbPath realPath, String cloud)
        throws Exception
    {
        this.copyToCloud(file, path, realPath, cloud, false, false);
    }

    public void copyToCloud(AbFile file, AbPath path, AbPath realPath, String cloud,
        boolean overwriteNewer, boolean overwriteAll)
        throws Exception
    {
        try
        {
            AbPath outPath = fixPath(file, path, realPath, cloud);

            AbFile outFile = new AbFile(outPath);

            //if (!isInCloud(outFile))
            //{

            if (!this.copyPrepare(file, outFile, overwriteNewer, overwriteAll))
            {
                return;
            } else
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("Out File: ");
                    stringBuffer.append(outFile.getPath());
                    stringBuffer.append(" In File: ");
                    stringBuffer.append(file.getPath());
                    stringBuffer.append(" to cloud.");

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copyToCloud"));
                }
            }

            AbDataOutputStream dataOutputStream =
                DataOutputStreamFactory.getInstance().getInstance(outFile);

            AbFileInputStream fileInputStream =
                new AbFileLocalInputStream(file);

            //AbDataInputStream dataInputStream =
            //  new AbDataInputStream(fileInputStream);

            this.copy(fileInputStream, dataOutputStream);

            /*
            } else
            {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
            {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Out File: ");
            stringBuffer.append(outFile.getPath());
            stringBuffer.append(" already in cloud.");

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "copyToCloud"));
            }
            }
             *
             */

        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILEERROR))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Error Copying File File: ");
                stringBuffer.append(file.toString());
                stringBuffer.append(" to cloud.");

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copyToCloud", e));
            }
            throw e;
        }
    }

    public void copyToCloud(AbFile file, AbFile outFile)
        throws Exception
    {
        this.copyToCloud(file, outFile, false, false);
    }

    public void copyToCloud(AbFile file, AbFile outFile, boolean overwriteNewer, boolean overwriteAll)
        throws Exception
    {
        try
        {
            StringBuffer stringBuffer = new StringBuffer();

            //if (!isInCloud(outFile))
            //{

            if (!this.copyPrepare(file, outFile, overwriteNewer, overwriteAll))
            {
                return;
            } else
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append("Out File: ");
                    stringBuffer.append(outFile.getPath());
                    stringBuffer.append(" In File: ");
                    stringBuffer.append(file.getPath());
                    stringBuffer.append(" to cloud.");

                    LogUtil.put(LogFactory.getInstance(
                        stringBuffer.toString(), getInstance(), "copyToCloud"));
                }
            }

            AbDataOutputStream dataOutputStream =
                DataOutputStreamFactory.getInstance().getInstance(outFile);

            AbFileInputStream fileInputStream =
                new AbFileLocalInputStream(file);

            //AbDataInputStream dataInputStream =
            //  new AbDataInputStream(fileInputStream);

            this.copy(fileInputStream, dataOutputStream);

            /*
            } else
            {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
            {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Out File: ");
            stringBuffer.append(outFile.getPath());
            stringBuffer.append(" already in cloud.");

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "copyToCloud"));
            }
            }
             *
             */

        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILEERROR))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Error Copying File File: ");
                stringBuffer.append(file.toString());
                stringBuffer.append(" to cloud.");

                LogUtil.put(LogFactory.getInstance(
                    stringBuffer.toString(), getInstance(), "copyToCloud", e));
            }
            throw e;
        }
    }

    private boolean copyPrepare(
        AbFile fromFile, AbFile toFile,
        boolean overwriteNewer, boolean overwriteAll)
        throws Exception
    {
        if (toFile.exists())
        {
            StringBuffer stringBuffer = new StringBuffer();
            //Overwrite existing
            if (overwriteAll)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append("Overwriting File: ");
                    stringBuffer.append(toFile.getPath());

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copyFile"));
                }

                toFile.delete();
                toFile.createNewFile();
            } else //If new and in overwrite mode
            if (toFile.lastModified() < fromFile.lastModified() && overwriteNewer)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append("Newer by: ");
                    stringBuffer.append(fromFile.lastModified() - toFile.lastModified());
                    stringBuffer.append("ms ");
                    stringBuffer.append("Copying File: ");
                    stringBuffer.append(toFile.getPath());

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copyFile"));
                }
                toFile.delete();
                toFile.createNewFile();
            } else
            //Not copying over file
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append("File Already Exists");

                    if (overwriteNewer)
                    {
                        stringBuffer.append(" And Is Not Older");
                    } else
                    {
                        stringBuffer.append(" And Not A In Overwrite Mode");
                    }

                    stringBuffer.append(": ");
                    stringBuffer.append(toFile.getPath());

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copyFile"));
                }
                return false;
            }

        } else
        {
            toFile.createNewFile();
        }
        return true;
    }

    private void copyFile(AbFile fromFile, AbFile toFile) throws Exception
    {
        this.copyFile(fromFile, toFile, false, false);
    }

    private void copyFile(AbFile fromFile, AbFile toFile, boolean overwriteNewer, boolean overwriteAll) throws Exception
    {
        try
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Copying ");
                stringBuffer.append(fromFile.length());
                stringBuffer.append(" bytes from File: ");
                stringBuffer.append(fromFile.getPath());
                stringBuffer.append(" to: ");
                stringBuffer.append(toFile.getPath());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copyFile"));
            }

            if (!this.copyPrepare(fromFile, toFile, overwriteNewer, overwriteAll))
            {
                return;
            }

            AbDataOutputStream dataOutputStream =
                DataOutputStreamFactory.getInstance().getInstance(toFile);

            AbFileInputStream fileInputStream =
                new AbFileInputStream(fromFile);

            //AbDataInputStream dataInputStream =
            //  new AbDataInputStream(fileInputStream);

            this.copy(fileInputStream, dataOutputStream);

        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILEERROR))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Error Copying File fromFile: ");
                stringBuffer.append(fromFile.toString());
                stringBuffer.append(" to: ");
                stringBuffer.append(toFile.toString());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copyFile", e));
            }
            throw e;
        }
    }

    private static String getNewDirectory(AbFile fromFile)
    {
        String newDirectory = fromFile.getPath();

        String separatorChar = java.io.File.separator;

        if (AbFileSystem.getInstance().isType("com.vobject.appengine.java.io"))
        {
            separatorChar = AbPathData.getInstance().SEPARATOR;
        }

        int lastIndex = newDirectory.lastIndexOf(separatorChar);

        if (lastIndex >= newDirectory.length())
        {
            newDirectory = newDirectory.substring(0, newDirectory.length() - 1);
            lastIndex = newDirectory.lastIndexOf(separatorChar);
        }

        return newDirectory.substring(lastIndex, newDirectory.length());
    }

    public void copyDirectoryPortion(
        AbPath fromDirectoryAbPath, AbPath toDirectoryAbPath, boolean overwriteNewer, boolean overwriteAll, int current, int total)
        throws Exception
    {
        AbFile file = new AbFile(fromDirectoryAbPath);

        if (!file.isDirectory())
        {
            throw new Exception("Not a directory: " + file.getPath());
        }

        BasicArrayList fileList = directory.search(file, true);

        int size = fileList.size();

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("Searched: ");
        stringBuffer.append(file.getPath());
        stringBuffer.append(" Total: ");
        stringBuffer.append(size);

        //Add one to round up so files will not be missed
        int portion = size / total + 1;

        int start = portion * current;

        int end = start + portion;

        if (end > size)
        {
            end = size;
        }

        stringBuffer.append(" Section: ");
        stringBuffer.append(start);
        stringBuffer.append(" - ");
        stringBuffer.append(end);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
        {
            LogUtil.put(LogFactory.getInstance(
                stringBuffer.toString(), getInstance(), "copySomeFilesToDirectory()"));
        }

        //PreLogUtil.put(stringBuffer.toString(), instance, "copySomeFilesToDirectory()");

        for (int index = start; index < end; index++)
        {
            AbFile nextFile = (AbFile) fileList.get(index);

            if (nextFile.isDirectory())
            {
                //Directory.create(nextFile.getPath());
            } else
            {
                String path = new AbPath(nextFile.getPath()).getPath();

                //Remove Webapp path if not in from path
                //String temp = fromDirectoryAbPath.getPath();
                int beginIndex = fromDirectoryAbPath.getPath().length();
                //get webapp path may have schema that may not be in paths
                if (path.indexOf(URLGLOBALS.getWebappPath()) < 0)
                {
                    beginIndex -= new AbPath(URLGLOBALS.getWebappPath()).getPath().length();
                }

                //String newPath = toDirectoryAbPath.getPath() + path.substring(beginIndex);
                String newPath = toDirectoryAbPath.toFileSystemString() + path.substring(beginIndex);

                //stringBuffer.delete(0, stringBuffer.length());
                //stringBuffer.append("Path: ");
                //stringBuffer.append(path);
                //stringBuffer.append(" beginindex: ");
                //stringBuffer.append(beginIndex);
                //stringBuffer.append(" new path: ");
                //stringBuffer.append(newPath);

                //LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "copyDirectoryPortion"));

                AbFile toFile = new AbFile(new AbPath(newPath));

                this.copyFile(nextFile, toFile, overwriteNewer, overwriteAll);
            }
        }
    }

    //recursively copy files and create directories
    private void copyDirectory(AbFile fromFile, AbFile to) throws Exception
    {
        try
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Copying Directory from: ");
                stringBuffer.append(fromFile.getPath());
                stringBuffer.append(" to: ");
                stringBuffer.append(to.getPath());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copyDirectory"));
            }

            //create new directory            
            String newDirectory = getNewDirectory(fromFile);

            AbPath newDirectoryAbPath =
                new AbPath(to.getPath() + AbPathData.getInstance().SEPARATOR + newDirectory);

            if (!Directory.create(newDirectoryAbPath))
            {
                throw new Exception("Failed to create directory: " + newDirectoryAbPath);
            }

            //copy all files and subdirectories
            AbFile[] fileArray = FileWrapperUtil.wrapFiles(fromFile.listFiles());

            int size = fileArray.length;

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Copying ");
                stringBuffer.append(size);
                stringBuffer.append(" files from: ");
                stringBuffer.append(fromFile.getPath());
                stringBuffer.append(" to: ");
                stringBuffer.append(to.getPath());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copyDirectory"));
            }

            for (int index = 0; index < size; index++)
            {
                AbFile file = fileArray[index];
                if (file.isFile())
                {
                    this.copyFile(file, new AbFile(newDirectoryAbPath.toString(), file.getName()));
                } else if (file.isDirectory())
                {
                    this.copyDirectory(file, new AbFile(newDirectoryAbPath));
                } else
                {
                    throw new Exception("File Copy Error");
                }
            }
            /*
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
            {
            LogUtil.put(LogFactory.getInstance(
            "Copied Directory fromFile: " + fromFile.toString() + " to: " + to.toString(),
            instance,"copyDirectory");
            }
             */
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILEERROR))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Error Copying Directory fromFile: ");
                stringBuffer.append(fromFile.toString());
                stringBuffer.append(" to: ");
                stringBuffer.append(to.toString());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copyDirectory", e));
            }
            throw e;
        }
    }

    //This should use the same login that copy or cp uses on the command line
    //meaning the following:
    //if the to/destination is a file then the fromFile will be renamed to the to/destination and fromFile must be a file
    //if the to/destination is a directory and the fromFile is a file then the file is copied to a file with the same name
    //if the to/destination is a directory and the fromFile is a directory then copy the directory including subdirectories
    //Note: in the last case if the fromFile ends with a separator then the subdirectories are copied into the to/destination directory
    //otherwise the fromFile directory will be copied and all of its contents
    //Example: fromFile = /home/dir to = /home/dest/ then the result will be /home/dest/dir
    //Example: fromFile = /home/dir/ to = /home/dest/ then the result will be /home/dest/(all subdirectories of /home/dir/)
    public synchronized boolean copy(AbPath fromAbPath, AbPath to) throws Exception
    {
        try
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Copying AbPaths from: ");
                stringBuffer.append(fromAbPath);
                stringBuffer.append(" to: ");
                stringBuffer.append(to);

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copy"));
            }

            if (fromAbPath == null)
            {
                throw new Exception("Cannot Copy From A Null Location");
            }
            if (to == null)
            {
                throw new Exception("Cannot Copy To A Null Location");
            }

            AbFile fromLocationFile = new AbFile(fromAbPath);
            AbFile toLocationFile = new AbFile(to);

            //copy single file
            if (fromLocationFile.isFile())
            {
                if (toLocationFile.isDirectory())
                {
                    //keep the same file
                    AbFile file = new AbFile(
                        toLocationFile, fromLocationFile.getName());

                    this.copyFile(fromLocationFile, file);

                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
                    {
                        StringBuffer stringBuffer = new StringBuffer();

                        stringBuffer.append("Copied file=");
                        stringBuffer.append(fromLocationFile.getName());
                        stringBuffer.append(" from: ");
                        stringBuffer.append(fromLocationFile.getPath());
                        stringBuffer.append(" to: ");
                        stringBuffer.append(toLocationFile.getPath());

                        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copy"));
                    }
                } else
                {
                    //rename the file
                    this.copyFile(fromLocationFile, toLocationFile);

                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
                    {
                        StringBuffer stringBuffer = new StringBuffer();

                        stringBuffer.append("Copied file with new name ");
                        stringBuffer.append(" from: ");
                        stringBuffer.append(fromLocationFile.getPath());
                        stringBuffer.append(" to: ");
                        stringBuffer.append(toLocationFile.getPath());

                        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copy"));
                    }
                }
            } else //copy directories
            if (fromLocationFile.isDirectory() && toLocationFile.isDirectory())
            {
                //check last character
                if (fromAbPath.toFileSystemString().charAt(fromAbPath.toFileSystemString().length() - 1) == java.io.File.separatorChar)
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
                    {
                        LogUtil.put(LogFactory.getInstance("Copying subdirectories", getInstance(), "copy"));
                    }

                    AbFile[] fileArray = FileWrapperUtil.wrapFiles(fromLocationFile.listFiles());
                    for (int index = 0; index < fileArray.length; index++)
                    {
                        AbFile file = fileArray[index];
                        if (file.isFile())
                        {
                            AbFile aFile = new AbFile(
                                toLocationFile, file.getName());

                            this.copyFile(file, aFile);
                        } else if (file.isDirectory())
                        {
                            copyDirectory(file, toLocationFile);
                        } else
                        {
                            throw new Exception("File Copy Error");
                        }
                    }

                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
                    {
                        LogUtil.put(LogFactory.getInstance("Copied subdirectories", getInstance(), "copy"));
                    }
                } else
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
                    {
                        LogUtil.put(LogFactory.getInstance("Copyinhg directory", getInstance(), "copy"));
                    }

                    copyDirectory(fromLocationFile, toLocationFile);

                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILE))
                    {
                        LogUtil.put(LogFactory.getInstance("Copied directory", getInstance(), "copy"));
                    }
                }
            }
            return true;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.FILEERROR))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Error Copying fromAbPath: ");
                stringBuffer.append(fromAbPath);
                stringBuffer.append(" to: ");
                stringBuffer.append(to);

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "copy", e));
            }
            throw e;
        }
    }
    //TWB -Could add
    //FileUtil.encCopy(rootCategoryAbFile, categoryAbPath,
    // CategoryData.ENCRYPTED_EXTENSION);
    
    public String readAsString(final String fileName)
    {
        final byte[] bytes = new byte[1000000];
        return this.readAsString(fileName, bytes);
    }

    public String readAsString(final String fileName, final byte[] bytes)
    {
        FileInputStream idFile = null;
        try
        {
            idFile = new FileInputStream(fileName);
            final int size = idFile.read(bytes);
            if(size > 0) {
                return new String(bytes, 0, size);
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(logConfigTypeFactory.IDLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "SmallInsert", e));
            }
        } finally {
            streamUtil.close(idFile);
        }
        return StringUtil.getInstance().EMPTY_STRING;
    }
    
   public boolean shouldSkip(final AbFile file, final String[] skipFiles) {
       final int size = skipFiles.length;
       String skipFile;
       for(int index = 0; index < size; index++) {
           skipFile = skipFiles[index];
           if (file.getPath().compareTo(skipFile) == 0) {
               return true;
           }
       }
       return false;
   }               
    
    private final String WRITE_LABEL = "Write file: ";
    private final String DATA_LABEL = " data: ";
    private final String WRITE_METHOD = "write";
    
    public void write(String filePath, String string) throws Exception
    {
        AbDataOutputStream dataOutputStream = null;
        try
        {
            //LogUtil.put(LogFactory.getInstance("Write Configuration: " + this.toString(), this, "write"));
            PreLogUtil.put(new StringBuilder().append(this.WRITE_LABEL).append(filePath).append(DATA_LABEL).append(string).toString(), this, this.WRITE_METHOD);

            final FileStreamFactory fileInputStreamFactory = FileStreamFactory.getInstance();

            final OutputStream fileOutputStream = fileInputStreamFactory
                    .getFileOutputStreamInstance(
                            StringUtil.getInstance().EMPTY_STRING, filePath);

            dataOutputStream = new AbDataOutputStream(fileOutputStream);

            dataOutputStream.write(string.getBytes(), 0, string.length());

            dataOutputStream.flush();
        }
        finally
        {
            StreamUtil.getInstance().close(dataOutputStream);
        }        
    }

    public List<String> loadFileAsList(final AbFile file, final int max, final byte[] byteArray1) {
        return FileUtil2.getInstance().loadFileAsList(file, max, byteArray1);
    }
    
}
