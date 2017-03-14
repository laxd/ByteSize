package uk.laxd.bytesize;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by lawrence on 14/03/17.
 */
public class FileSizePrefixTest {

    @Test
    public void testConversionIsCorrect() throws Exception {
        BigDecimal result = FileSizePrefix.BYTE.convert(new BigDecimal("1"), FileSizePrefix.MEGABYTE);

        assertEquals(new BigDecimal("1000000"), result);
    }

    @Test
    public void testDecimalConversion() throws Exception {
        BigDecimal result = FileSizePrefix.KILOBYTE.convert(new BigDecimal("500"), FileSizePrefix.BYTE);

        assertEquals(new BigDecimal("0.5"), result);
    }

    @Test
    public void testSiToNonSiConversion() throws Exception {
        BigDecimal result = FileSizePrefix.MEBIBYTE.convert(new BigDecimal("1"), FileSizePrefix.MEGABYTE);

        result = result.setScale(2, BigDecimal.ROUND_DOWN);

        assertEquals(new BigDecimal("0.95"), result);

        TimeUnit.DAYS.convert(1, TimeUnit.HOURS);
    }


}