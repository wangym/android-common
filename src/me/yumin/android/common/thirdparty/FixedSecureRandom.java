package me.yumin.android.common.thirdparty;

import java.security.SecureRandom;

public class FixedSecureRandom
extends SecureRandom
{
/**
 * 
 */
private static final long serialVersionUID = -144076469992183815L;
byte[]  seed = {
        (byte)0xaa, (byte)0xfd, (byte)0x12, (byte)0xf6, (byte)0x59,
        (byte)0xca, (byte)0xe6, (byte)0x34, (byte)0x89, (byte)0xb4,
        (byte)0x79, (byte)0xe5, (byte)0x07, (byte)0x6d, (byte)0xde,
        (byte)0xc2, (byte)0xf0, (byte)0x6c, (byte)0xb5, (byte)0x8f
};

public void nextBytes(
    byte[]  bytes)
{
    int offset = 0;

    while ((offset + seed.length) < bytes.length)
    {
        System.arraycopy(seed, 0, bytes, offset, seed.length);
        offset += seed.length;
    }

    System.arraycopy(seed, 0, bytes, offset, bytes.length - offset);
}
}