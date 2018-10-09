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

package com.nllsdfx.utils.commons;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Class delivers tools to work with
 * system clipboard.
 */
public final class ClipboardUtil {

    private static final Clipboard CLIPBOARD;

    static {
        CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    private ClipboardUtil() {
    }

    /**
     * Sets the content of user's clipboard to the given data.
     *
     * @param data data to store in current user's clipboard.
     */
    public static void fillClipboard(final String data) {
        StringSelection selection = new StringSelection(data);
        CLIPBOARD.setContents(selection, selection);
    }
}
