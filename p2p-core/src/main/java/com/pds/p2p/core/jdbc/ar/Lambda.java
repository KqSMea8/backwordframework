package com.pds.p2p.core.jdbc.ar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.pds.p2p.core.jdbc.ar.ex.IllegalFieldNameException;

final class Lambda {
    private final Object o;
    private final Method fn;

    Lambda(Object o, Method fn) {
        this.o = o;
        this.fn = fn;
    }

    Object call(Record record, Object value) {
        try {
            return fn.invoke(o, record, value);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new IllegalFieldNameException(fn.getName(), e);
        }
    }
}
