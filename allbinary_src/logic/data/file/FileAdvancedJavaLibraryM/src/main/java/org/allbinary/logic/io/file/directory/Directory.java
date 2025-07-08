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
package org.allbinary.logic.io.file.directory;

import java.io.FileFilter;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileFactory;
import org.allbinary.logic.io.file.FileWrapperUtil;

import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.util.BasicArrayList;

public class Directory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final Directory instance = new Directory();

    /**
     * @return the instance
     */
    public static Directory getInstance()
    {
        return instance;
    }

    private Directory()
    {
    }

    public boolean create(AbPath directoryAbPath)
    {
        return this.create(directoryAbPath.toFileSystemString());
    }

    public void remove(AbPath existingDirectoryAbPath) throws Exception
    {
        this.remove(existingDirectoryAbPath.toFileSystemString());
    }

    //returns true if directories already exist or if creation was successful
    private boolean create(String directory)
    {
        try
        {
            final AbFile directoryFile = FileFactory.getInstance().getInstance(directory);
            if (!directoryFile.isDirectory())
            {
                //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FILE))
                //{
                PreLogUtil.put("Creating Directories: " + directory, this, "create");
                //}

                if (!directoryFile.mkdirs())
                {
                    return false;
                }
            }
            return true;
        } catch (Exception e)
        {
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FILEERROR))
            //{
            PreLogUtil.put("Error Creating Directories: " + directory, this, "create", e);
            //}
            return false;
        }
    }

    //see if directory contains files if not remove directory
    private void remove(String existingDirectory) throws Exception
    {
        final AbFile existingDirectoryFile = FileFactory.getInstance().getInstance(existingDirectory);
        if (existingDirectoryFile.isDirectory())
        {
            if (existingDirectoryFile.list().length > 0)
            {
                throw new Exception("Did not remove category: " + existingDirectory + " because files exist");
            } else if (existingDirectoryFile.delete())
            {
                //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FILE))
                //{
                PreLogUtil.put("Successfully Removed: " + existingDirectory,
                    "Directory", "remove");
                //}
            }
        } else
        {
            throw new Exception("Category Directory Is Missing:" + existingDirectory);
        }
    }

    public BasicArrayList search(FileFilter fileFilter, AbFile file)
    {
        return this.search(fileFilter, file, false);
    }

    //Find the files matching the FileFilter in the given directory

    public BasicArrayList search(FileFilter fileFilter, AbFile file, boolean isRecursiveSearch)
    {
        final BasicArrayList fileList = new BasicArrayList();

        BasicArrayList recursiveFileList;
        if (file.isDirectory())
        {
            //System.out.println("Is a Directory");
            final Object[] fileArray = file.listFiles(fileFilter);
            if(fileArray == null) {
                return fileList;
            }
            final AbFile[] files = FileWrapperUtil.wrapFiles(fileArray);

            if (files == null)
            {
                return fileList;
            }

            final int size = files.length;
            for (int index = 0; index < size; index++)
            {
                fileList.add(files[index]);
                if (isRecursiveSearch)
                {
                    recursiveFileList = this.search(
                        fileFilter,
                        files[index],
                        isRecursiveSearch);

                    fileList.addAll(recursiveFileList);
                }
            }
        }
        return fileList;
    }

    public BasicArrayList search(AbFile file)
    {
        return this.search(file, false);
    }

    //Return the files in the given directory
    public BasicArrayList search(final AbFile file, final boolean isRecursiveSearch)
    {
        final BasicArrayList fileList = new BasicArrayList();

        BasicArrayList recursiveFileList;
        if (file.isDirectory())
        {
            final Object[] fileArray = file.listFiles();
            if(fileArray == null) {
                return fileList;
            }
            final AbFile[] files = FileWrapperUtil.wrapFiles(fileArray);

            if (files == null)
            {
                return fileList;
            }

            for (int index = 0; index < files.length; index++)
            {
                fileList.add(files[index]);
                if (isRecursiveSearch)
                {
                    recursiveFileList = this.search(files[index], isRecursiveSearch);
                    fileList.addAll(recursiveFileList);
                }
            }
        }
        return fileList;
    }

    public BasicArrayList search(String searchValue, AbFile file)
    {
        return this.search(searchValue, file, false);
    }

    //Find the files matching the searchValue in the given directory
    public BasicArrayList search(final String searchValue, final AbFile file, final boolean isRecursiveSearch)
    {
        final BasicArrayList fileList = new BasicArrayList();

        BasicArrayList recursiveFileList;
        if (file.isDirectory())
        {
            final Object[] fileArray = file.listFiles();
            if(fileArray == null) {
                return fileList;
            }
            final AbFile[] files = FileWrapperUtil.wrapFiles(fileArray);

            if (files == null)
            {
                return fileList;
            }

            for (int index = 0; index < files.length; index++)
            {
                if (files[index].getPath().indexOf(searchValue) >= 0)
                {
                    fileList.add(files[index]);
                }

                if (isRecursiveSearch)
                {
                    recursiveFileList = this.search(searchValue, files[index], isRecursiveSearch);
                    fileList.addAll(recursiveFileList);
                }
            }
        }
        return fileList;
    }

    public BasicArrayList search(int level, AbFile file)
    {
        return this.search(level, file, false);
    }

    public BasicArrayList search(int level, AbFile file, boolean isRecursiveSearch)
    {
        final BasicArrayList fileList = new BasicArrayList();

        BasicArrayList recursiveFileList;
        if (file.isDirectory())
        {
            final Object[] fileArray = file.listFiles();
            if(fileArray == null) {
                return fileList;
            }
            final AbFile[] files = FileWrapperUtil.wrapFiles(fileArray);

            if (files == null)
            {
                return fileList;
            }

            for (int index = 0; index < files.length; index++)
            {
                fileList.add(files[index]);

                if (level <= 0)
                {
                    return fileList;
                }

                recursiveFileList = this.search(level - 1, files[index], isRecursiveSearch);
                fileList.addAll(recursiveFileList);
            }
        }
        return fileList;
    }
}
