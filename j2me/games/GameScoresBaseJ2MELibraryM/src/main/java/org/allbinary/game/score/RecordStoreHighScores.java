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
package org.allbinary.game.score;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Hashtable;

import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;

import org.allbinary.game.GameInfo;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.persistance.PlatformRecordIdUtil;
import org.allbinary.util.BasicArrayList;

public class RecordStoreHighScores extends HighScores
{
    private static final Hashtable hashTable = new Hashtable();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final PlatformRecordIdUtil platformRecordIdUtil = PlatformRecordIdUtil.getInstance();
    
    //_HighScores
    private final String RECORD_ID = "_HS";
    private final int MAXHIGHSCORES = 100;

    private final GameInfo gameInfo;
    private final AbeClientInformationInterface abeClientInformation;
    
    private final RecordComparator recordComparatorInterface;

    private RecordStoreHighScores(final AbeClientInformationInterface abeClientInformation, final GameInfo gameInfo, 
        final String name, final String heading, final String columnTwoHeading, 
        final RecordComparator recordComparatorInterface) // throws
    // Exception
    {
        super(name, heading, columnTwoHeading);

        //LogUtil.put(LogFactory.getInstance(commonStrings.CONSTRUCTOR, this, commonStrings.CONSTRUCTOR));
        
        this.abeClientInformation = abeClientInformation;
        this.gameInfo = gameInfo;
        this.recordComparatorInterface = recordComparatorInterface;
        
        this.load();
    }

    public static synchronized HighScores getInstance(
        final AbeClientInformationInterface abeClientInformation, final GameInfo gameInfo, 
        final String highScoreName, final String heading, final String columnTwoHeading,
        final RecordComparator recordComparatorInterface)
    {
        HighScores highScores = (HighScores) hashTable.get(highScoreName);

        if (highScores == null)
        {
            highScores = new RecordStoreHighScores(abeClientInformation, gameInfo, 
                highScoreName, heading, columnTwoHeading, recordComparatorInterface);
            hashTable.put(highScores.getName(), highScores);
        }

        return highScores;
    }

    private String getRecordId(final AbeClientInformationInterface abeClientInformation) {
        return platformRecordIdUtil.getRecordId(abeClientInformation, new StringMaker().append(CommonSeps.getInstance().UNDERSCORE).append(this.getName()).append(RECORD_ID).toString());
    }
    
    //@Override
    public synchronized void addHighScore(final HighScore newHighScore) // throws Exception
    {
        RecordStore recordStore = null;
        try
        {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("Adding HighScore: ").append(newHighScore.getScore()).toString(),this, commonStrings.ADD));

            // remove score replacing
            if (this.isTooManyHighScores())
            {
                LogUtil.put(LogFactory.getInstance("Removing Lowest Score", this, commonStrings.ADD));

                this.removeLowestHighScore();
            }

            recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

            final byte[] highScoreBytes = newHighScore.getBytes();

            final int recordId = recordStore.addRecord(highScoreBytes, 0, highScoreBytes.length);
            
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("New recordId: ").append(recordId).toString(),this, commonStrings.ADD));

            //LogUtil.put(LogFactory.getInstance("closeRecordStore",this, commonStrings.ADD));
            
            this.load();
            
            
        }
        catch (RecordStoreException e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.ADD, e));
            // throw e;
        }
        catch (IOException e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.ADD, e));
            // throw e;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.ADD, e));
            // throw e;
        } finally {
            try {
                if (recordStore != null) {
                    PreLogUtil.put("Closing RecordStore", this, commonStrings.ADD);
                    recordStore.closeRecordStore();
                }
            } catch(RecordStoreException e) {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.ADD, e));
            }
        }

    }

    private void removeLowestHighScore() // throws Exception
    {
        RecordStore recordStore = null;
        try
        {
            recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

            final RecordEnumeration recordEnum = recordStore.enumerateRecords(null,null, true);
            // recordStore.enumerateRecords(null, (RecordComparator) this, true);

            HighScore bestHighScore = new HighScore(-1, "none", null, ((ScoreComparator) this.recordComparatorInterface).getBestScore());

            byte[] recordAsBytes;
            ByteArrayInputStream byteArrayInputStream;
            DataInputStream inputStream;
            while (recordEnum.hasNextElement())
            {
                final int id = recordEnum.nextRecordId();

                recordAsBytes = recordStore.getRecord(id);
                if(recordAsBytes != null) {
                    byteArrayInputStream = new ByteArrayInputStream(recordAsBytes);
                    inputStream = new DataInputStream(byteArrayInputStream);

                    final String name = inputStream.readUTF();
                    final long nextScore = inputStream.readLong();
                    final HighScore nextCurrentHighScore = new HighScore(id, name, null, nextScore);

                    if (this.recordComparatorInterface.compare(nextCurrentHighScore.getBytes(), bestHighScore.getBytes()) == RecordComparator.FOLLOWS) {
                        bestHighScore = nextCurrentHighScore;
                    }
                }
            }

            if (bestHighScore.getId() != -1)
            {
                LogUtil.put(LogFactory.getInstance(new StringMaker().append("Removing Lowest HighScore: ").append(bestHighScore.getScore()).toString(), this, "load"));
                recordStore.deleteRecord(bestHighScore.getId());
            }

        }
        catch (RecordStoreException e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "removeLowestHighScore", e));
            // throw e;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "removeLowestHighScore", e));
            // throw e;
        } finally {
            try {
                if (recordStore != null) {
                    PreLogUtil.put("Closing RecordStore", this, "removeLowestHighScore");
                    recordStore.closeRecordStore();
                }
            } catch(RecordStoreException e) {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "removeLowestHighScore", e));
            }
        }
    }

    // Key=score, Value=HighScore class
    // should be in order of score
    private void load() // throws RecordStoreException, IOException
    {
        RecordStore recordStore = null;

        try
        {
            recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);
            //LogUtil.put(LogFactory.getInstance(recordStore.getName(), this, "load"));

            this.setList(new BasicArrayList());

            final RecordEnumeration recordEnum = recordStore.enumerateRecords(null,null, true);
            // recordStore.enumerateRecords(null, (RecordComparator) this, true);
            //LogUtil.put(LogFactory.getInstance("first hasNextElement: " + recordEnum.hasNextElement(), this, "load"));

            byte[] recordAsBytes;
            ByteArrayInputStream byteArrayInputStream;
            DataInputStream inputStream;
            while (recordEnum.hasNextElement())
            {
                final int id = recordEnum.nextRecordId();
                //LogUtil.put(LogFactory.getInstance("id: " + id, this, "load"));

                recordAsBytes = recordStore.getRecord(id);
                
                //LogUtil.put(LogFactory.getInstance("recordAsBytes: " + recordAsBytes, this, "load"));
                
                if(recordAsBytes != null) {
                    byteArrayInputStream = new ByteArrayInputStream(recordAsBytes);
                    inputStream = new DataInputStream(byteArrayInputStream);
                
                    try {
                        final String name = inputStream.readUTF();
                        final long score = inputStream.readLong();
                        final HighScore newHighScore = new HighScore(id, name, null, score);

                        // Forced Sorting for bad RecordEnumeration Implementations
                        // Sadly this issue is common on many devices
                        final BasicArrayList list = this.getList();
                        int size = list.size();
                        int lastIndex = size;
                        for (int index = 0; index < size; index++) {
                            final HighScore highScore = (HighScore) list.objectArray[index];

                            // Found a spot then insert at that point
                            if (this.recordComparatorInterface.compare(newHighScore.getBytes(), highScore.getBytes()) == RecordComparator.PRECEDES) {
                                lastIndex = index;
                                break;
                            }
                        }

                        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Loading HighScore: ").append(newHighScore.getScore()).append(" for: ").append(this.getName()).toString(), this, "load"));
                        list.add(lastIndex, newHighScore);

                        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Loaded HighScores Ordered: ").append(this.toString()).toString(), this, "load"));
                    } catch (EOFException e) {
                        LogUtil.put(LogFactory.getInstance("EOF", this, "load", e));
                        throw e;
                    }
                }
            }

            // LogUtil.put(LogFactory.getInstance("Loaded HighScores Ordered: " +
            // this.toString(), this, "load"));

        }
        catch (RecordStoreNotFoundException e)
        {
            LogUtil.put(LogFactory.getInstance("No High Scores", this, "load", e));
        }
        catch (RecordStoreException e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.UNKNOWN, this, "load", e));
        }
        catch (IOException e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.UNKNOWN, this, "load", e));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.UNKNOWN, this, "load", e));
        } finally {
            try {
                if (recordStore != null) {
                    PreLogUtil.put("Closing RecordStore", this, "load");
                    recordStore.closeRecordStore();
                }
            } catch(RecordStoreException e) {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "load", e));
            }
        }

    }

    private boolean isTooManyHighScores()
    {
        if (this.getList() != null
                && this.getList().size() < MAXHIGHSCORES)
        // if(this.highScoresHashTable != null &&
        // this.highScoresHashTable.size() < MAXHIGHSCORES)
        {
            return false;
        }
        else
        {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("HighScores RecordStore Max Reached: ").append(this.MAXHIGHSCORES).toString(), this, "isTooManyHighScores"));
            return true;
        }
    }

    public synchronized boolean isBestScore(HighScore newHighScore)
    throws Exception
    {
        try
        {
            // search scores and validate if a high score has been acheived

            if (!this.isTooManyHighScores())
            {
                LogUtil.put(LogFactory.getInstance("Slot Available for a High Score", this,"isBestScore"));
                return true;
            }
            else
            {

                // Enumeration enumeration = this.highScoresHashTable.keys();
                final BasicArrayList list = this.getList();
                final int size = list.size();
                for (int index = 0; index < size; index++)
                {
                    final HighScore highScore = (HighScore) list.objectArray[index];

                    if (recordComparatorInterface.compare(newHighScore.getBytes(), highScore.getBytes()) == RecordComparator.FOLLOWS)
                    // if(newHighScore.getScore() > highScore.getScore())
                    {
                        LogUtil.put(LogFactory.getInstance("Obtained a High Score", this,
                                "isBestScore"));
                        return true;
                    }
                }
            }
            LogUtil.put(LogFactory.getInstance("Not a High Score", this, "isBestScore"));
            return false;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.ADD, e));
            throw e;
        }
    }

    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(super.toString());
        
        final BasicArrayList list = this.getList();
        final int size = list.size();
        for (int index = 0; index < size; index++)
        {
            final HighScore highScore = (HighScore) list.objectArray[index];
            stringBuffer.append(highScore.getScoreString());
            stringBuffer.append(CommonSeps.getInstance().COMMA_SEP);
        }

        return stringBuffer.toString();
    }
}