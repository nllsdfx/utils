/*
 *     Copyright (C) 2018  Nllsdfx Utils
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.nllsdfx.utils.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * IOUtils is designed to simplify some
 * routine task like closing streams.
 */
public final class IOUtil {

    private static Logger log = Logger.getLogger(IOUtil.class.getName());

    private IOUtil() {
    }

    /**
     * Closes closeable. Checks
     * if it's nullable and tries to close
     * it after. Logs exception if could't close
     * the closable.
     *
     * @param closeable a stream or closable that
     *                  must be closed.
     */
    public static void closeIO(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ex) {
                log.log(Level.SEVERE, ex.getLocalizedMessage());
            }
        }
    }

    /**
     * Flushes and close object.
     *
     * @param object  object that implements both
     *                {@link Closeable} and
     *                {@link Flushable} object.
     * @param <T></T> closeable & flushable object.
     */
    public static <T extends Closeable & Flushable> void closeWithFlush(final T object) {
        if (object != null) {
            try {
                object.flush();
            } catch (IOException ex) {
                log.log(Level.SEVERE, ex.getLocalizedMessage());
            } finally {
                try {
                    object.close();
                } catch (IOException ex) {
                    log.log(Level.SEVERE, ex.getLocalizedMessage());
                }
            }
        }
    }
}
