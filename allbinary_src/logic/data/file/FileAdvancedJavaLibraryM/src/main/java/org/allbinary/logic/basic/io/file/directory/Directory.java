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
package org.allbinary.logic.basic.io.file.directory;

import org.allbinary.logic.basic.io.file.AbFile;
import org.allbinary.logic.basic.io.file.FileFactory;
import org.allbinary.logic.basic.io.file.FileWrapperUtil;
import java.io.FileFilter;
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.logic.basic.path.AbPath;
import org.allbinary.logic.communication.log.PreLogUtil;

public class Directory
{
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

    public static synchronized boolean create(AbPath directoryAbPath)
    {
        return Directory.create(directoryAbPath.toFileSystemString());
    }

    public static synchronized void remove(AbPath existingDirectoryAbPath) throws Exception
    {
        Directory.remove(existingDirectoryAbPath.toFileSystemString());
    }

    //returns true if directories already exist or if creation was successful
    private static synchronized boolean create(String directory)
    {
        try
        {
            AbFile directoryFile = FileFactory.getInstance().getInstance(directory);
            if (!directoryFile.isDirectory())
            {
                //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FILE))
                //{
                PreLogUtil.put("Creating Directories: " + directory, "Directory", "create");
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
            PreLogUtil.put("Error Creating Directories: " + directory, "Directory", "create", e);
            //}
            return false;
        }
    }

    //see if directory contains files if not remove directory
    private static synchronized void remove(String existingDirectory) throws Exception
    {
        AbFile existingDirectoryFile = FileFactory.getInstance().getInstance(existingDirectory);
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

    public Vector search(FileFilter fileFilter, AbFile file)
    {
        return this.search(fileFilter, file, false);
    }

    //Find the files matching the FileFilter in the given directory

    public Vector search(FileFilter fileFilter, AbFile file, boolean isRecursiveSearch)
    {
        Vector fileVector = new Vector();

        if (file.isDirectory())
        {
            //System.out.println("Is a Directory");
            final Object[] fileArray = file.listFiles(fileFilter);
            if(fileArray == null) {
                return fileVector;
            }
            final AbFile[] files = FileWrapperUtil.wrapFiles(fileArray);

            if (files == null)
            {
                return fileVector;
            }

            for (int index = 0; index < files.length; index++)
            {
                fileVector.add(files[index]);
                if (isRecursiveSearch)
                {
                    Vector vector = this.search(
                        fileFilter,
                        files[index],
                        isRecursiveSearch);

                    Iterator iterator = vector.iterator();
                    while (iterator.hasNext())
                    {
                        fileVector.add(iterator.next());
                    }
                }
            }
        }
        return fileVector;
    }

    public Vector search(AbFile file)
    {
        return this.search(file, false);
    }

    //Return the files in the given directory
    public Vector search(AbFile file, boolean isRecursiveSearch)
    {
        Vector fileVector = new Vector();

        if (file.isDirectory())
        {
            final Object[] fileArray = file.listFiles();
            if(fileArray == null) {
                return fileVector;
            }
            AbFile[] files = FileWrapperUtil.wrapFiles(fileArray);

            if (files == null)
            {
                return fileVector;
            }

            for (int index = 0; index < files.length; index++)
            {
                fileVector.add(files[index]);
                if (isRecursiveSearch)
                {
                    Vector vector =
                        this.search(files[index], isRecursiveSearch);
                    Iterator iterator = vector.iterator();
                    while (iterator.hasNext())
                    {
                        fileVector.add(iterator.next());
                    }
                }
            }
        }
        return fileVector;
    }

    public Vector search(String searchValue, AbFile file)
    {
        return this.search(searchValue, file, false);
    }

    //Find the files matching the searchValue in the given directory
    public Vector search(final String searchValue, final AbFile file, final boolean isRecursiveSearch)
    {
        final Vector fileVector = new Vector();

        if (file.isDirectory())
        {
            final Object[] fileArray = file.listFiles();
            if(fileArray == null) {
                return fileVector;
            }
            final AbFile[] files = FileWrapperUtil.wrapFiles(fileArray);

            if (files == null)
            {
                return fileVector;
            }

            for (int index = 0; index < files.length; index++)
            {
                if (files[index].getPath().indexOf(searchValue) >= 0)
                {
                    fileVector.add(files[index]);
                }

                if (isRecursiveSearch)
                {
                    Vector vector = this.search(
                        searchValue, files[index], isRecursiveSearch);

                    Iterator iterator = vector.iterator();
                    while (iterator.hasNext())
                    {
                        fileVector.add(iterator.next());
                    }
                }
            }
        }
        return fileVector;
    }

    public Vector search(int level, AbFile file)
    {
        return this.search(level, file, false);
    }

    public Vector search(int level, AbFile file, boolean isRecursiveSearch)
    {
        Vector fileVector = new Vector();

        if (file.isDirectory())
        {
            final Object[] fileArray = file.listFiles();
            if(fileArray == null) {
                return fileVector;
            }
            final AbFile[] files = FileWrapperUtil.wrapFiles(fileArray);

            if (files == null)
            {
                return fileVector;
            }

            for (int index = 0; index < files.length; index++)
            {
                fileVector.add(files[index]);

                if (level <= 0)
                {
                    return fileVector;
                }

                Vector vector = this.search(level - 1, files[index], isRecursiveSearch);

                Iterator iterator = vector.iterator();
                while (iterator.hasNext())
                {
                    fileVector.add(iterator.next());
                }
            }
        }
        return fileVector;
    }
}
