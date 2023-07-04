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
package org.allbinary.logic.basic.io.file;

import java.util.Vector;
import org.allbinary.logic.basic.io.file.AbFile;
import org.allbinary.logic.basic.io.file.directory.DirectoryOrIncludeFileExtensionBooleanFileVisitor;
import org.allbinary.logic.basic.io.file.directory.SubDirectory;
import org.allbinary.logic.basic.io.file.filter.VisitorFileFilter;

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

    public Vector getFiles(String path)
    {
        try
        {
            final SubDirectory subDirectory = new SubDirectory();

            final Vector files = subDirectory.search(new AbFile(path));
            return files;

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: " + e + "\nMsg: " + e.getMessage());
        }
        return null;
    }
    
    public Vector getFiles(final String path, final String includeExtension)
    {
        try
        {
            final SubDirectory subDirectory = new SubDirectory();

            final Vector includeExtensionVector = new Vector();
            includeExtensionVector.add(includeExtension);
            final VisitorFileFilter visitorFileFilter = new VisitorFileFilter(
                    new DirectoryOrIncludeFileExtensionBooleanFileVisitor(
                            includeExtensionVector));

            final Vector files = subDirectory.search(visitorFileFilter, new AbFile(path));
            return files;

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: " + e + "\nMsg: " + e.getMessage());
        }
        return null;
    }

    public Vector getFiles(final String path, final String pathIncludes, final String includeExtension)
    {
        try
        {
            final SubDirectory subDirectory = new SubDirectory();

            final Vector includeExtensionVector = new Vector();
            includeExtensionVector.add(includeExtension);
            final VisitorFileFilter visitorFileFilter = new VisitorFileFilter(
                    new DirectoryOrIncludeFileExtensionBooleanFileVisitor(
                            includeExtensionVector, pathIncludes));

            final Vector files = subDirectory.search(visitorFileFilter, new AbFile(path));
            return files;

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: " + e + "\nMsg: " + e.getMessage());
        }
        return null;
    }
}
