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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import java.io.IOException;
import java.util.Hashtable;
import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;
import org.allbinary.util.BasicArrayList;

public class RecordStoreHighScores extends HighScores
{
    private static final Hashtable hashTable = new Hashtable();
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private final String RECORD_ID = "_HighScores";
    private final int MAXHIGHSCORES = 100;

    private final RecordComparator recordComparatorInterface;

    private RecordStoreHighScores(
            final String name, final String heading, final String columnTwoHeading, 
            final RecordComparator recordComparatorInterface) // throws
    // Exception
    {
        super(name, heading, columnTwoHeading);

        this.recordComparatorInterface = recordComparatorInterface;
        this.load();
    }

    public static synchronized HighScores getInstance(
            final String highScoreName, final String heading, final String columnTwoHeading,
            final RecordComparator recordComparatorInterface)
    {
        HighScores highScores = (HighScores) hashTable.get(highScoreName);

        if (highScores == null)
        {
            highScores = new RecordStoreHighScores(
                    highScoreName, heading, columnTwoHeading, recordComparatorInterface);
            hashTable.put(highScores.getName(), highScores);
        }

        return highScores;
    }

    public synchronized void add(final HighScore newHighScore) // throws Exception
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("Adding HighScore: ").append(newHighScore.getScore()).toString(),
                    this, commonStrings.ADD));

            // remove score replacing
            if (this.isTooManyHighScores())
            {
                LogUtil.put(LogFactory.getInstance("Removing Lowest Score", this, commonStrings.ADD));

                this.removeLowestHighScore();
            }

            final RecordStore recordStore = RecordStore.openRecordStore(new StringMaker().append(this.getName()).append(RECORD_ID).toString(), true);

            final byte[] highScoreBytes = newHighScore.getBytes();

            recordStore.addRecord(highScoreBytes, 0, highScoreBytes.length);

            recordStore.closeRecordStore();

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
        }
    }

    private void removeLowestHighScore() // throws Exception
    {
        try
        {
            final RecordStore recordStore = RecordStore.openRecordStore(new StringMaker().append(this.getName()).append(RECORD_ID).toString(), true);

            final RecordEnumeration recordEnum = recordStore.enumerateRecords(null,
                    null, true);
            // recordStore.enumerateRecords(null, (RecordComparator) this,
            // true);

            final HighScore worstHighScore = new HighScore(-1, "none", null, ((ScoreComparator) this.recordComparatorInterface).getBestScore());

            while (recordEnum.hasNextElement())
            {
                final int id = recordEnum.nextRecordId();

                //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                  //      recordStore.getRecord(id));
                //DataInputStream inputStream = new DataInputStream(
                  //      byteArrayInputStream);

                //final String name = inputStream.readUTF();
                //final long nextScore = inputStream.readLong();
                //final HighScore nextCurrentHighScore = new HighScore(id, name, null,
                  //      nextScore);

                //if (this.recordComparatorInterface.compare(nextCurrentHighScore.getBytes(), bestHighScore.getBytes()) == RecordComparator.FOLLOWS)
                //{
                  //  bestHighScore = nextCurrentHighScore;
                //}
            }

            if (worstHighScore.getId() != -1)
            {
                recordStore.deleteRecord(worstHighScore.getId());
            }

            recordStore.closeRecordStore();            
        }
        catch (RecordStoreException e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.ADD, e));
            // throw e;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.ADD, e));
            // throw e;
        }
    }

    // Key=score, Value=HighScore class
    // should be in order of score
    private void load() // throws RecordStoreException, IOException
    {
        try
        {
            final RecordStore recordStore = RecordStore.openRecordStore(new StringMaker().append(this.getName()).append(RECORD_ID).toString(), true);

            this.setList(new BasicArrayList());

            final RecordEnumeration recordEnum = recordStore.enumerateRecords(null,
                    null, true);
            // recordStore.enumerateRecords(null, (RecordComparator) this,
            // true);

            while (recordEnum.hasNextElement())
            {
                int id = recordEnum.nextRecordId();
                //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                  //      recordStore.getRecord(id));
                //DataInputStream inputStream = new DataInputStream(
                  //      byteArrayInputStream);
                try
                {
                    //String name = inputStream.readUTF();
                    //long score = inputStream.readLong();
                    //HighScore newHighScore = new HighScore(id, name, null, score);

                    // Forced Sorting for bad RecordEnumeration Implementations
                    // Sadly this issue is common on many devices
                    final BasicArrayList list = this.getList();
                    final int size = list.size();
                    int lastIndex = size;
                    for (int index = 0; index < size; index++)
                    {
                        final HighScore highScore = (HighScore) list.objectArray[index];

                        // Found a spot then insert at that point
                        //if (this.recordComparatorInterface.compare(newHighScore.getBytes(), highScore.getBytes()) == RecordComparator.PRECEDES)
                        //{
                          //  lastIndex = index;
                            //break;
                        //}
                    }

                    //LogUtil.put(LogFactory.getInstance(new StringBuilder().append("Loading HighScore: ").append(newHighScore.getScore()).append(" for: ").append(this.getName()).toString(), this, "load"));

                    //list.add(lastIndex, newHighScore);

                    //LogUtil.put(LogFactory.getInstance(new StringBuilder().append("Loaded HighScores Ordered: ").append(this.toString()).toString(), this, "load"));
                }
                catch (Exception e)
                {
                    LogUtil.put(LogFactory.getInstance("EOF", this, "load", e));
                    throw e;
                }
            }

            recordStore.closeRecordStore();

            // LogUtil.put(LogFactory.getInstance("Loaded HighScores Ordered: " +
            // this.toString(), this, "load"));

            if(false)
            {
                //TWB - Allow HTML version to build
                throw new IOException();
            }            
            
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

    public synchronized boolean isBestScore(final HighScore newHighScore)
    throws Exception
    {
        try
        {
            // search scores and validate if a high score has been acheived

            if (!this.isTooManyHighScores())
            {
                LogUtil.put(LogFactory.getInstance("Slot Available for a High Score", this,
                        "isBestScore"));
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