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
import java.io.Writer;

/**
 * A Writer that ignores everything written to it. Writer.append writes so it is ignored as well.
 *
 * @author User
 */
public class NullWriter extends Writer {

    public static final NullWriter NULL_WRITER = new NullWriter();

    @Override
    public void write(final int c) throws IOException {
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
    }

    @Override
    public void write(final String str) throws IOException {
    }

    @Override
    public void write(final String str, final int off, final int len) throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }
}
