package uk.laxd.bytesize;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by lawrence on 14/03/17.
 */
public class ByteUnitTest {

    @Test
    public void testConversionIsCorrect() throws Exception {
        BigDecimal result = ByteUnit.BYTE.convert(new BigDecimal("1"), ByteUnit.MEGABYTE);

        assertEquals(new BigDecimal("1000000"), result);
    }

    @Test
    public void testDecimalConversion() throws Exception {
        BigDecimal result = ByteUnit.KILOBYTE.convert(new BigDecimal("500"), ByteUnit.BYTE);

        assertEquals(new BigDecimal("0.5"), result);
    }

    @Test
    public void testSiToNonSiConversion() throws Exception {
        BigDecimal result = ByteUnit.MEBIBYTE.convert(new BigDecimal("1"), ByteUnit.MEGABYTE);

        result = result.setScale(2, BigDecimal.ROUND_DOWN);

        assertEquals(new BigDecimal("0.95"), result);

        TimeUnit.DAYS.convert(1, TimeUnit.HOURS);
    }


}