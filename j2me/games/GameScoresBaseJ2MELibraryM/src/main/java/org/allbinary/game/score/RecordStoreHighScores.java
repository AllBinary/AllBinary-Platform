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
import org.allbinary.game.configuration.persistance.NullRecordComparator;
import org.allbinary.game.configuration.persistance.NullRecordFilter;
import org.allbinary.game.configuration.persistance.NullRecordStore;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.persistance.PlatformRecordIdUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class RecordStoreHighScores extends HighScores
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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

        //logUtil.put(commonStrings.CONSTRUCTOR, this, commonStrings.CONSTRUCTOR);
        
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
        HighScores highScores = (HighScores) hashTable.get((Object) highScoreName);

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
    
    @Override
    public synchronized void addHighScore(final HighScore newHighScore) // throws Exception
    {
        RecordStore recordStore = NullRecordStore.NULL_RECORD_STORE;
        try
        {
            logUtil.put(new StringMaker().append("Adding HighScore: ").append(newHighScore.getScore()).toString(),this, commonStrings.ADD);

            // remove score replacing
            if (this.isTooManyHighScores())
            {
                logUtil.put("Removing Lowest Score", this, commonStrings.ADD);

                this.removeLowestHighScore();
            }

            recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

            final byte[] highScoreBytes = newHighScore.getAsBytes();

            final int recordId = recordStore.addRecord(highScoreBytes, 0, highScoreBytes.length);
            
            //logUtil.put(new StringMaker().append("New recordId: ").append(recordId).toString(),this, commonStrings.ADD);

            //logUtil.put("closeRecordStore",this, commonStrings.ADD);
            
            this.load();
            
            
        }
        catch (RecordStoreException e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.ADD, e);
            // throw e;
        }
        catch (IOException e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.ADD, e);
            // throw e;
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.ADD, e);
            // throw e;
        } finally {
            try {
                if (recordStore != null) {
                    PreLogUtil.put("Closing RecordStore", this, commonStrings.ADD);
                    recordStore.closeRecordStore();
                }
            } catch(RecordStoreException e) {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.ADD, e);
            }
        }

    }

    private void removeLowestHighScore() // throws Exception
    {
        RecordStore recordStore = NullRecordStore.NULL_RECORD_STORE;
        try
        {
            recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

            final RecordEnumeration recordEnum = recordStore.enumerateRecords(NullRecordFilter.NULL_RECORD_FILTER, NullRecordComparator.NULL_RECORD_COMPARATOR, true);

            final ScoreComparator scoreComparator = ((ScoreComparator) this.recordComparatorInterface);
            HighScore bestHighScore = new HighScore(-1, "none", GameInfo.NONE, scoreComparator.getBestScore());

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
                    final HighScore nextCurrentHighScore = new HighScore(id, name, GameInfo.NONE, nextScore);

                    if (this.recordComparatorInterface.compare(nextCurrentHighScore.getAsBytes(), bestHighScore.getAsBytes()) == RecordComparator.FOLLOWS) {
                        bestHighScore = nextCurrentHighScore;
                    }
                }
            }

            if (bestHighScore.getId() != -1)
            {
                logUtil.put(new StringMaker().append("Removing Lowest HighScore: ").append(bestHighScore.getScore()).toString(), this, commonStrings.LOAD);
                recordStore.deleteRecord(bestHighScore.getId());
            }

        }
        catch (RecordStoreException e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "removeLowestHighScore", e);
            // throw e;
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "removeLowestHighScore", e);
            // throw e;
        } finally {
            try {
                if (recordStore != null) {
                    PreLogUtil.put("Closing RecordStore", this, "removeLowestHighScore");
                    recordStore.closeRecordStore();
                }
            } catch(RecordStoreException e) {
                logUtil.put(commonStrings.EXCEPTION, this, "removeLowestHighScore", e);
            }
        }
    }

    // Key=score, Value=HighScore class
    // should be in order of score
    private void load() // throws RecordStoreException, IOException
    {
        RecordStore recordStore = NullRecordStore.NULL_RECORD_STORE;

        try
        {
            recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);
            //logUtil.put(recordStore.getName(), this, commonStrings.LOAD);

            this.setList(new BasicArrayList());

            final RecordEnumeration recordEnum = recordStore.enumerateRecords(NullRecordFilter.NULL_RECORD_FILTER, NullRecordComparator.NULL_RECORD_COMPARATOR, true);
            //logUtil.put("first hasNextElement: " + recordEnum.hasNextElement(), this, commonStrings.LOAD);

            byte[] recordAsBytes;
            ByteArrayInputStream byteArrayInputStream;
            DataInputStream inputStream;
            while (recordEnum.hasNextElement())
            {
                final int id = recordEnum.nextRecordId();
                //logUtil.put("id: " + id, this, commonStrings.LOAD);

                recordAsBytes = recordStore.getRecord(id);
                
                //logUtil.put("recordAsBytes: " + recordAsBytes, this, commonStrings.LOAD);
                
                if(recordAsBytes != null) {
                    byteArrayInputStream = new ByteArrayInputStream(recordAsBytes);
                    inputStream = new DataInputStream(byteArrayInputStream);
                
                    try {
                        final String name = inputStream.readUTF();
                        final long score = inputStream.readLong();
                        final HighScore newHighScore = new HighScore(id, name, GameInfo.NONE, score);

                        // Forced Sorting for bad RecordEnumeration Implementations
                        // Sadly this issue is common on many devices
                        final BasicArrayList list = this.getList();
                        int size = list.size();
                        int lastIndex = size;
                        for (int index = 0; index < size; index++) {
                            final HighScore highScore = (HighScore) list.objectArray[index];

                            // Found a spot then insert at that point
                            if (this.recordComparatorInterface.compare(newHighScore.getAsBytes(), highScore.getAsBytes()) == RecordComparator.PRECEDES) {
                                lastIndex = index;
                                break;
                            }
                        }

                        //logUtil.put(new StringMaker().append("Loading HighScore: ").append(newHighScore.getScore()).append(" for: ").append(this.getName()).toString(), this, commonStrings.LOAD);
                        list.add(lastIndex, newHighScore);

                        //logUtil.put(new StringMaker().append("Loaded HighScores Ordered: ").append(this.toString()).toString(), this, commonStrings.LOAD);
                    } catch (EOFException e) {
                        logUtil.put("EOF", this, commonStrings.LOAD, e);
                        throw e;
                    }
                }
            }

            // logUtil.put("Loaded HighScores Ordered: " +
            // this.toString(), this, commonStrings.LOAD);

        }
        catch (RecordStoreNotFoundException e)
        {
            logUtil.put("No High Scores", this, commonStrings.LOAD, e);
        }
        catch (RecordStoreException e)
        {
            logUtil.put(commonStrings.UNKNOWN, this, commonStrings.LOAD, e);
        }
        catch (IOException e)
        {
            logUtil.put(commonStrings.UNKNOWN, this, commonStrings.LOAD, e);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.UNKNOWN, this, commonStrings.LOAD, e);
        } finally {
            try {
                if (recordStore != null) {
                    PreLogUtil.put("Closing RecordStore", this, commonStrings.LOAD);
                    recordStore.closeRecordStore();
                }
            } catch(RecordStoreException e) {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.LOAD, e);
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
            logUtil.put(new StringMaker().append("HighScores RecordStore Max Reached: ").append(this.MAXHIGHSCORES).toString(), this, "isTooManyHighScores");
            return true;
        }
    }

    @Override
    public synchronized boolean isBestScore(HighScore newHighScore)
    throws Exception
    {
        try
        {
            // search scores and validate if a high score has been acheived

            if (!this.isTooManyHighScores())
            {
                logUtil.put("Slot Available for a High Score", this,"isBestScore");
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

                    if (recordComparatorInterface.compare(newHighScore.getAsBytes(), highScore.getAsBytes()) == RecordComparator.FOLLOWS)
                    // if(newHighScore.getScore() > highScore.getScore())
                    {
                        logUtil.put("Obtained a High Score", this,
                                "isBestScore");
                        return true;
                    }
                }
            }
            logUtil.put("Not a High Score", this, "isBestScore");
            return false;
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.ADD, e);
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