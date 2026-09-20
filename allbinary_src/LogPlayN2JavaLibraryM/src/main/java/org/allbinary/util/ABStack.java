/*
 * AllBinary Open License Version 1
 * Copyright (c) 2026 AllBinary
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
package org.allbinary.util;

import java.util.Stack;
import jsinterop.annotations.JsType;

/**
 *
 * @author User
 */
@JsType
public class ABStack<E> extends Stack<E> {

    @Override
    public E push(E item) {
        return super.push(item);
    }

    @Override
    public synchronized E pop() {
        return super.pop();
    }

    @Override
    public synchronized E peek() {
        return super.peek();
    }

    @Override
    public boolean empty() {
        return super.empty();
    }

    @Override
    public synchronized int search(Object obj) {
        return super.search(obj);
    }

}
