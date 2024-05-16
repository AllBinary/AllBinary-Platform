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
package org.allbinary.game.score.remote;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.SoftwareInformation;
import org.allbinary.game.GameInfo;
import org.allbinary.game.score.HighScore;
import org.allbinary.game.score.HighScores;
import org.allbinary.game.score.RemoteErrorHighScoresSingletonFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class RemoteHighScores extends HighScores {

    private static final Hashtable hashTable = new Hashtable();
    private final AbeClientInformationInterface abeClientInformation;
    private final SoftwareInformation softwareInformation;
    
    private Boolean ascending;

    public final String ASCENDING = "ASCENDING";

    private RemoteHighScores(
        final AbeClientInformationInterface abeClientInformation, 
        final SoftwareInformation softwareInformation, final GameInfo gameInfo,
        final String heading, final String columnTwoHeading, final Boolean ascending, 
        final boolean preload)
        throws Exception {
        super(gameInfo.toString(), heading, columnTwoHeading);

        this.abeClientInformation = abeClientInformation;
        this.softwareInformation = softwareInformation;

        this.setAscending(ascending);

        if(preload)  {
            RemoteHighScoresProcessorFactory.getInstance().process(this, this.abeClientInformation, gameInfo);
        }
    }

    public static synchronized HighScores getInstance(
        final AbeClientInformationInterface abeClientInformation, 
        final SoftwareInformation softwareInformation, final GameInfo gameInfo,
        final String heading, final String columnTwoHeading, final Boolean isAscending) {
        return RemoteHighScores.getInstance(abeClientInformation, softwareInformation, gameInfo, heading, columnTwoHeading, isAscending, true);
    }
    
    public static synchronized HighScores getInstance(
        final AbeClientInformationInterface abeClientInformation, 
        final SoftwareInformation softwareInformation, final GameInfo gameInfo,
        final String heading, final String columnTwoHeading, final Boolean isAscending, final boolean preload) {
        try {
            HighScores highScores = (HighScores) hashTable.get(gameInfo);

            if (highScores == null) {
                highScores = new RemoteHighScores(
                    abeClientInformation, softwareInformation, gameInfo,
                    heading, columnTwoHeading, isAscending, preload);

                hashTable.put(gameInfo, highScores);
            }

            return highScores;
        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, RemoteErrorHighScoresSingletonFactory.getInstance(), CommonStrings.getInstance().GET_INSTANCE, e));
            return RemoteErrorHighScoresSingletonFactory.getInstance();
        }
    }

    //@Override
    public void addHighScore(final HighScore newHighScore) {
        RemoteHighScoresSubmissionProcessorFactory.getInstance().process(this, this.abeClientInformation, newHighScore);
    }

    //This is called when the data comes back in the response
    public void update(final Hashtable hashtable) {
        this.getList().clear();
        final Vector vector = (Vector) hashtable.get(RemoteHighScoresData.getInstance().HIGH_SCORES);
        if (vector != null) {
            for (int index = 0; index < vector.size(); index++) {
                final Vector highScoreVector = (Vector) vector.elementAt(index);
                final String displayName = (String) highScoreVector.elementAt(0);
                final String score = (String) highScoreVector.elementAt(1);

                final long longScore = Long.parseLong(score);
                //Long.valueOf(score).longValue()

                final HighScore highScore = new HighScore(-1, displayName, null, longScore);

                this.getList().add(highScore);
            }
        } else {
            
            final Enumeration enumeration = hashtable.elements();
            Object nextElement;
            while(enumeration.hasMoreElements()) {
                nextElement = enumeration.nextElement();
                LogUtil.put(LogFactory.getInstance("NextElement: " + nextElement, this, CommonStrings.getInstance().PROCESS));
            }

        }
    }

    private void setAscending(final Boolean ascending) {
        this.ascending = ascending;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public SoftwareInformation getSoftwareInformation() {
        return softwareInformation;
    }
}
