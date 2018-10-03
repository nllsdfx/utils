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
