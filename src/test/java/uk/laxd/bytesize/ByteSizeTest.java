package uk.laxd.bytesize;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by lawrence on 14/03/17.
 */
public class ByteSizeTest {

    private ByteSize byteSize = new ByteSize();

    @Test
    public void testStringConversionWithNoConversionNeeded() throws Exception {
        String fileSize = byteSize.getFormattedFileSize(new BigDecimal("500"), 0);

        assertEquals("500", fileSize);
    }

    @Test
    public void testStringConversionFromBytesToKilobytes() throws Exception {
        String fileSize = byteSize.getFormattedFileSize(new BigDecimal("1234"));

        assertEquals("1.23KB", fileSize);
    }

    @Test
    public void testStringConversionFromLargerPrefixToSmallerPrefix() throws Exception {
        String fileSize = byteSize.getFormattedFileSize(new BigDecimal("0.001"), FileSizePrefix.MEGABYTE, 1);

        assertEquals("1.0KB", fileSize);
    }
}