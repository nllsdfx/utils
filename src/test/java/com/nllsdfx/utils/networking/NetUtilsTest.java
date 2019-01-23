package com.nllsdfx.utils.networking;

import com.nllsdfx.utils.commons.StringUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class NetUtilsTest {

    @Test
    public void hexToIP() {

        assertNull(NetUtils.hexToIP(null));
        assertNull(NetUtils.hexToIP("hey"));
        assertNull(NetUtils.hexToIP("hey1"));
        assertNull(NetUtils.hexToIP("123"));
        assertNull(NetUtils.hexToIP(""));

        final String hex = "0A064156";

        String ip = NetUtils.hexToIP(hex);

        assertFalse(StringUtil.isBlank(ip));

        assertEquals("10.6.65.86", ip);

        final int hexInt = 0x0A064156;

        assertEquals("10.6.65.86", NetUtils.hexToIP(hexInt));

        final int dInt = 167791361;

        assertEquals("10.0.75.1", NetUtils.hexToIP(dInt));

    }
}