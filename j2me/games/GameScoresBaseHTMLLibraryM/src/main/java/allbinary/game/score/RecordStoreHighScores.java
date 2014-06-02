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
package allbinary.game.score;

import abcs.logic.basic.string.StringMaker;
import abcs.logic.basic.Unknown;
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
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

    private final String RECORD_ID = "_HighScores";
    private final int MAXHIGHSCORES = 100;

    private RecordComparator recordComparatorInterface;

    private RecordStoreHighScores(
            String name, String heading, String columnTwoHeading, 
            RecordComparator recordComparatorInterface) // throws
    // Exception
    {
        super(name, heading, columnTwoHeading);

        this.recordComparatorInterface = recordComparatorInterface;
        this.load();
    }

    public static synchronized HighScores getInstance(
            String highScoreName, String heading, String columnTwoHeading,
            RecordComparator recordComparatorInterface)
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

    public synchronized void add(HighScore newHighScore) // throws Exception
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Adding HighScore: " + newHighScore.getScore(),
                    this, CommonStrings.getInstance().ADD));

            // remove score replacing
            if (this.isTooManyHighScores())
            {
                LogUtil.put(LogFactory.getInstance("Removing Lowest Score", this, CommonStrings.getInstance().ADD));

                this.removeLowestHighScore();
            }

            RecordStore recordStore = RecordStore.openRecordStore(this.getName() + RECORD_ID, true);

            byte[] highScoreBytes = newHighScore.getBytes();

            recordStore.addRecord(highScoreBytes, 0, highScoreBytes.length);

            recordStore.closeRecordStore();

            this.load();
        }
        catch (RecordStoreException e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().ADD, e));
            // throw e;
        }
        catch (IOException e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().ADD, e));
            // throw e;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().ADD, e));
            // throw e;
        }
    }

    private void removeLowestHighScore() // throws Exception
    {
        try
        {
            RecordStore recordStore = RecordStore.openRecordStore(this
                    .getName()
                    + RECORD_ID, true);

            RecordEnumeration recordEnum = recordStore.enumerateRecords(null,
                    null, true);
            // recordStore.enumerateRecords(null, (RecordComparator) this,
            // true);

            HighScore bestHighScore = new HighScore(-1, "none", null, 0);

            while (recordEnum.hasNextElement())
            {
                int id = recordEnum.nextRecordId();

                //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                  //      recordStore.getRecord(id));
                //DataInputStream inputStream = new DataInputStream(
                  //      byteArrayInputStream);

                //String name = inputStream.readUTF();
                //long nextScore = inputStream.readLong();
                //HighScore nextCurrentHighScore = new HighScore(id, name, null,
                  //      nextScore);

                //if (this.recordComparatorInterface.compare(nextCurrentHighScore.getBytes(), bestHighScore.getBytes()) == RecordComparator.PRECEDES)
                //{
                  //  bestHighScore = nextCurrentHighScore;
                //}
            }

            if (bestHighScore.getId() != -1)
            {
                recordStore.deleteRecord(bestHighScore.getId());
            }

            recordStore.closeRecordStore();
            
            if(false)
            {
                //TWB - Allow HTML version to build
                throw new IOException();
            }            
        }
        catch (RecordStoreException e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().ADD, e));
            // throw e;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().ADD, e));
            // throw e;
        }
    }

    // Key=score, Value=HighScore class
    // should be in order of score
    private void load() // throws RecordStoreException, IOException
    {
        try
        {
            RecordStore recordStore = RecordStore.openRecordStore(
                    this.getName() + RECORD_ID, true);

            this.setList(new BasicArrayList());

            RecordEnumeration recordEnum = recordStore.enumerateRecords(null,
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
                    BasicArrayList list = this.getList();
                    int size = list.size();
                    int lastIndex = size;
                    for (int index = 0; index < size; index++)
                    {
                        HighScore highScore = (HighScore) list.objectArray[index];

                        // Found a spot then insert at that point
                        //if (this.recordComparatorInterface.compare(newHighScore.getBytes(), highScore.getBytes()) == RecordComparator.PRECEDES)
                        //{
                          //  lastIndex = index;
                            //break;
                        //}
                    }

                    //LogUtil.put(LogFactory.getInstance("Loading HighScore: " + newHighScore.getScore() + " for: " + this.getName(), this, "load"));

                    //list.add(lastIndex, newHighScore);

                    //LogUtil.put(LogFactory.getInstance("Loaded HighScores Ordered: " + this.toString(), this, "load"));
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

        }
        catch (RecordStoreNotFoundException e)
        {
            LogUtil.put(LogFactory.getInstance("No High Scores", this, "load", e));
        }
        catch (RecordStoreException e)
        {
            LogUtil.put(LogFactory.getInstance(Unknown.NAME, this, "load", e));
        }
        catch (IOException e)
        {
            LogUtil.put(LogFactory.getInstance(Unknown.NAME, this, "load", e));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(Unknown.NAME, this, "load", e));
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
            LogUtil.put(LogFactory.getInstance("HighScores RecordStore Max Reached: "
                    + this.MAXHIGHSCORES, this, "isTooManyHighScores"));
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
                LogUtil.put(LogFactory.getInstance("Slot Available for a High Score", this,
                        "isBestScore"));
                return true;
            }
            else
            {

                // Enumeration enumeration = this.highScoresHashTable.keys();
                BasicArrayList list = this.getList();
                int size = list.size();
                for (int index = 0; index < size; index++)
                {
                    HighScore highScore = (HighScore) list.objectArray[index];

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
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().ADD, e));
            throw e;
        }
    }

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        BasicArrayList list = this.getList();
        int size = list.size();
        for (int index = 0; index < size; index++)
        {
            HighScore highScore = (HighScore) list.objectArray[index];
            stringBuffer.append(highScore.getScoreString());
            stringBuffer.append(CommonSeps.getInstance().COMMA_SEP);
        }

        return stringBuffer.toString();
    }
}