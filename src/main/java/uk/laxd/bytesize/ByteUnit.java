package uk.laxd.bytesize;

import java.math.BigDecimal;

/**
 * Created by lawrence on 14/03/17.
 */
public enum ByteUnit {
    BYTE(new BigDecimal("1"), ""),

    KILOBYTE(new BigDecimal("1000").pow(1), "KB"),
    MEGABYTE(new BigDecimal("1000").pow(2), "MB"),
    GIGABYTE(new BigDecimal("1000").pow(3), "GB"),
    TERABYTE(new BigDecimal("1000").pow(4), "TB"),
    PETABYTE(new BigDecimal("1000").pow(5), "PB"),
    EXABYTE(new BigDecimal("1000").pow(6), "EB"),
    ZETTABYTE(new BigDecimal("1000").pow(7), "ZB"),
    YOTTABYTE(new BigDecimal("1000").pow(8), "YB"),

    KIBIBYTE(new BigDecimal("1024").pow(1), "KiB"),
    MEBIBYTE(new BigDecimal("1024").pow(2), "MiB"),
    GIBIBYTE(new BigDecimal("1024").pow(3), "GiB"),
    TEBIBYTE(new BigDecimal("1024").pow(4), "TiB"),
    PEBIBYTE(new BigDecimal("1024").pow(5), "PiB"),
    EXBIBYTE(new BigDecimal("1024").pow(6), "EiB"),
    ZEBIBYTE(new BigDecimal("1024").pow(7), "ZiB"),
    YOBIBYTE(new BigDecimal("1024").pow(8), "YiB");

    public static ByteUnit[] SI_UNITS = {
        KILOBYTE,
        MEGABYTE,
        GIGABYTE,
        TERABYTE,
        PETABYTE,
        EXABYTE,
        ZETTABYTE,
        YOTTABYTE
    };

    public static ByteUnit[] BINARY_UNITS = {
            KIBIBYTE,
            MEBIBYTE,
            GIBIBYTE,
            TEBIBYTE,
            PEBIBYTE,
            EXBIBYTE,
            ZEBIBYTE,
            YOBIBYTE
    };

    private BigDecimal unitBytes;
    private String text;

    ByteUnit(BigDecimal unitBytes, String text) {
        this.unitBytes = unitBytes;
        this.text = text;
    }

    /**
     * Converts the {@code sourceSize} from the given {@link ByteUnit}
     * to this {@link ByteUnit}'s size.
     * @param sourceSize Size of the source, in {@code sourcePrefix} units.
     * @param sourcePrefix Units of {@code sourceSize}.
     * @return The amount of this {@link ByteUnit}es that correspond to
     * the same file size as represented by {@code sourceSize} and {@code sourcePrefix}.
     */
    public BigDecimal convert(BigDecimal sourceSize, ByteUnit sourcePrefix) {
        // Convert to bytes first
        BigDecimal bytes = sourcePrefix.unitBytes.multiply(sourceSize);
        
        // Then divide to get size
        return bytes.divide(this.unitBytes);
    }

    /**
     * Convenience method that delegates to {@link #convert(BigDecimal, ByteUnit)} by
     * wrapping the {@code long} argument in a {@link BigDecimal}.
     *
     * @param sourceSize Size of the source, in {@link ByteUnit} units.
     * @param sourcePrefix Unit of the source size.
     * @return The long value of the required size, as returned by {@link BigDecimal#longValue()}
     */
    public long convert(long sourceSize, ByteUnit sourcePrefix) {
        return convert(new BigDecimal(String.valueOf(sourceSize)), sourcePrefix).longValue();
    }

    public BigDecimal getUnitBytes() {
        return unitBytes;
    }

    public String getText() {
        return text;
    }
}
