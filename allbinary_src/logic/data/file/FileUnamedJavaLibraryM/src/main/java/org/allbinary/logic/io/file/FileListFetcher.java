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

import org.allbinary.logic.io.file.directory.DirectoryOrIncludeFileExtensionBooleanFileVisitor;
import org.allbinary.logic.io.file.directory.SubDirectory;
import org.allbinary.logic.io.file.filter.VisitorFileFilter;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class FileListFetcher
{
    private static final FileListFetcher instance = new FileListFetcher();

    /**
     * @return the instance
     */
    public static FileListFetcher getInstance()
    {
        return instance;
    }

    private final SubDirectory subDirectory = SubDirectory.getInstance();
    
    public BasicArrayList getFiles(String path)
    {
        try
        {
            

            final BasicArrayList files = subDirectory.search(new AbFile(path));
            return files;

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: " + e + "\nMsg: " + e.getMessage());
        }
        return null;
    }
    
    public BasicArrayList getFiles(final String path, final String includeExtension)
    {
        try
        {
            final BasicArrayList includeExtensionBasicArrayList = new BasicArrayList();
            includeExtensionBasicArrayList.add(includeExtension);
            final VisitorFileFilter visitorFileFilter = new VisitorFileFilter(
                    new DirectoryOrIncludeFileExtensionBooleanFileVisitor(
                            includeExtensionBasicArrayList));

            final BasicArrayList files = subDirectory.search(visitorFileFilter, new AbFile(path));
            return files;

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: " + e + "\nMsg: " + e.getMessage());
        }
        return null;
    }

    public BasicArrayList getFiles(final String path, final String pathIncludes, final String includeExtension)
    {
        try
        {
            final BasicArrayList includeExtensionBasicArrayList = new BasicArrayList();
            includeExtensionBasicArrayList.add(includeExtension);
            final VisitorFileFilter visitorFileFilter = new VisitorFileFilter(
                    new DirectoryOrIncludeFileExtensionBooleanFileVisitor(
                            includeExtensionBasicArrayList, pathIncludes));

            final BasicArrayList files = subDirectory.search(visitorFileFilter, new AbFile(path));
            return files;

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: " + e + "\nMsg: " + e.getMessage());
        }
        return null;
    }
}
