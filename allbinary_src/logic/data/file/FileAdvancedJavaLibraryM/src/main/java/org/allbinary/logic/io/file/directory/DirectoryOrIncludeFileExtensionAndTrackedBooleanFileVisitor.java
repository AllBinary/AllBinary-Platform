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

import org.allbinary.java.runtime.process.git.GitProcessHelper;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.CommonDataFileStrings;
import org.allbinary.logic.io.file.visitor.IncludeFileExtensionsBooleanFileVisitor;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public class DirectoryOrIncludeFileExtensionAndTrackedBooleanFileVisitor
    extends IncludeFileExtensionsBooleanFileVisitor {

    private final TrackedStrings trackedStrings = TrackedStrings.getInstance();
    private final GitProcessHelper gitProcessHelper = GitProcessHelper.getInstance();
    
    private final String includesString;

    public DirectoryOrIncludeFileExtensionAndTrackedBooleanFileVisitor(final BasicArrayList filterStringBasicArrayList) {
        super(filterStringBasicArrayList);

        this.includesString = null;
    }

    public DirectoryOrIncludeFileExtensionAndTrackedBooleanFileVisitor(final BasicArrayList filterStringBasicArrayList, final String includesString) {
        super(filterStringBasicArrayList);

        this.includesString = includesString;
    }

    @Override
    public Boolean visit(AbFile file) {
        if (file.isDirectory()) {
            return Boolean.TRUE;
        }

        if (this.includesString == null || file.getAbsolutePath().indexOf(this.includesString) >= 0) {
            return super.visit(file);
        } else {
            return Boolean.FALSE;
        }

    }

    @Override
    public Boolean visit(AbFile file, String fileNameString) {
        final String filePath = file.getPath();

        if (super.visit(file, fileNameString)) {
            //System.out.println("path: " + filePath);
            if (!(filePath.contains(this.trackedStrings.APPLICATION) || filePath.contains(this.trackedStrings.APP) || filePath.contains(this.trackedStrings.HTML_TEMP))) {
            if (!(filePath.contains(this.trackedStrings.TARGET_PATH_WINDOWS) || filePath.contains(this.trackedStrings.TARGET_PATH_UNIX))) {
                //System.out.println("Not target path");
                if (this.gitProcessHelper.isGitTracked(file)) {
                    //System.out.println("path: " + filePath);
                    //System.out.println("tracked");
                    return Boolean.TRUE;
                } else {
                    //System.out.println("path: " + filePath);
                    //System.out.println("not tracked");
                }
            } else {
                //System.out.println("target path");
            }
            } else {
                //System.out.println("app src path");
            }
        }

        return Boolean.FALSE;
    }

    public static void main(String[] args) {
        final CommonDataFileStrings commonFileStrings = CommonDataFileStrings.getInstance();
        final BasicArrayList list = new BasicArrayListD();
        list.add(commonFileStrings.JAVA);
        final String filePath =
            //"G:\\mnt\\bc\\mydev\\working\\j2me\\CommonJavaLibraryM\\src\\main\\java\\org\\allbinary\\logic\\communication\\http\\HttpData.java";
            "G:\\mnt\\bc\\mydev\\games\\ZeptoRacer\\platform\\j2se\\ZeptoRacerStaticPathsJ2SEM\\target\\dependency\\org\\allbinary\\logic\\system\\security\\licensing\\ZeptoRacerPCClientInformation.java";
        final boolean result = new DirectoryOrIncludeFileExtensionAndTrackedBooleanFileVisitor(list)
            .visit(AbFile.createAbFileFromRawPath(filePath));
        //.isGitTracked(AbFile.createAbFileFromRawPath(filePath));
        System.out.println(filePath + " is Tracked: " + result);
    }

}
