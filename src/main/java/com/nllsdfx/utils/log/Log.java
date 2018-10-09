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

package com.nllsdfx.utils.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Log {

    private Logger logger;

    private Log(Class<?> c) {
        logger = Logger.getLogger(c.getName());
    }

    public void error(Throwable t) {
        logger.log(Level.SEVERE, t.getLocalizedMessage());
    }

    public void error(String msg) {
        logger.log(Level.SEVERE, msg);
    }

    public void debug(Throwable t) {
        logger.log(Level.FINE, t.getLocalizedMessage());
    }

    public void debug(String msg) {
        logger.log(Level.FINE, msg);
    }

    public static Log getLogger(Class<?> c) {
        return new Log(c);
    }

}
