/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.logic.io;

import java.io.IOException;
import java.io.Reader;

/**
 * A Reader that is always at the end. Reader.read (CharBuffer) reads with read (char[], int, int) so it is -1 as well.
 *
 * @author User
 */
public class NullReader extends Reader {

    public static final NullReader NULL_READER = new NullReader();

    @Override
    public int read() throws IOException {
        return -1;
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        return -1;
    }

    @Override
    public boolean ready() throws IOException {
        return false;
    }

    @Override
    public long skip(final long n) throws IOException {
        return 0L;
    }

    @Override
    public void close() {
    }
}
