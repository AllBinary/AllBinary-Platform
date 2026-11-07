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
import java.io.OutputStream;

/**
 * An OutputStream that ignores everything written to it.
 *
 * @author User
 */
public class NullOutputStream extends OutputStream {

    public static final NullOutputStream NULL_OUTPUT_STREAM = new NullOutputStream();

    @Override
    public void write(final int b) throws IOException {
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
    }

    @Override
    public void close() {
    }
}
