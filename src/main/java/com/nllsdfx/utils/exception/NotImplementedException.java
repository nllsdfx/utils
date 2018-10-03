package com.nllsdfx.utils.exception;

/**
 * As it's not recommended to use classes from
 * sun.* packages the class was developed instead.
 */
public final class NotImplementedException extends RuntimeException {

    private NotImplementedException() {
    }

    private static class Holder {
        static final NotImplementedException EXCEPTION = new NotImplementedException();
    }

    public static NotImplementedException exception() {
        return Holder.EXCEPTION;
    }

}
