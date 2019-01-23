package com.nllsdfx.utils.networking;

import com.nllsdfx.utils.commons.StringUtil;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Utils for networking
 */
public final class NetUtils {

    private NetUtils() {
    }

    /**
     * Converts given hex string to host address (e.g. 127.0.0.1)
     *
     * @param hex hex string
     * @return host address or {@code null}, if can't convert.
     */
    public static String hexToIP(final String hex) {

        if (StringUtil.isBlank(hex)) {
            return null;
        }

        try {
            return InetAddress.getByAddress(DatatypeConverter.parseHexBinary(hex)).getHostAddress();
        } catch (UnknownHostException | IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Converts given hex int to ip address (e.g. 127.0.0.1)
     *
     * @param hex hex representation of ip
     * @return host address or {@code null}, if can't convert.
     */
    public static String hexToIP(final int hex) {
        BigInteger i = new BigInteger(String.valueOf(hex), 10);
        return hexToIP(String.format("0%s", i.toString(16)));
    }
}
