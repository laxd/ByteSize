package uk.laxd.bytesize;

import java.math.BigDecimal;

/**
 * Created by lawrence on 14/03/17.
 */
public enum FileSizePrefix {
    BYTE(new BigDecimal("1"), ""),

    KILOBYTE(new BigDecimal("1000").multiply(BYTE.unitBytes), "KB"),
    MEGABYTE(new BigDecimal("1000").multiply(KILOBYTE.unitBytes), "MB"),
    GIGABYTE(new BigDecimal("1000").multiply(MEGABYTE.unitBytes), "GB"),
    TERABYTE(new BigDecimal("1000").multiply(GIGABYTE.unitBytes), "TB"),
    PETABYTE(new BigDecimal("1000").multiply(TERABYTE.unitBytes), "PB"),
    EXABYTE(new BigDecimal("1000").multiply(PETABYTE.unitBytes), "EB"),
    ZETTABYTE(new BigDecimal("1000").multiply(EXABYTE.unitBytes), "ZB"),
    YOTTABYTE(new BigDecimal("1000").multiply(ZETTABYTE.unitBytes), "YB"),

    KIBIBYTE(new BigDecimal("1024").multiply(BYTE.unitBytes), "KiB"),
    MEBIBYTE(new BigDecimal("1024").multiply(KIBIBYTE.unitBytes), "MiB"),
    GIBIBYTE(new BigDecimal("1024").multiply(MEBIBYTE.unitBytes), "GiB"),
    TEBIBYTE(new BigDecimal("1024").multiply(GIBIBYTE.unitBytes), "TiB"),
    PEBIBYTE(new BigDecimal("1024").multiply(TEBIBYTE.unitBytes), "PiB"),
    EXBIBYTE(new BigDecimal("1024").multiply(PEBIBYTE.unitBytes), "EiB"),
    ZEBIBYTE(new BigDecimal("1024").multiply(EXBIBYTE.unitBytes), "ZiB"),
    YOBIBYTE(new BigDecimal("1024").multiply(ZEBIBYTE.unitBytes), "YiB");

    public static FileSizePrefix[] SI_UNITS = {
        KILOBYTE,
        MEGABYTE,
        GIGABYTE,
        TERABYTE,
        PETABYTE,
        EXABYTE,
        ZETTABYTE,
        YOTTABYTE
    };

    public static FileSizePrefix[] NON_SI_UNITS = {
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

    FileSizePrefix(BigDecimal unitBytes, String text) {
        this.unitBytes = unitBytes;
        this.text = text;
    }

    /**
     * Converts the {@code sourceSize} from the given {@link FileSizePrefix}
     * to this {@link FileSizePrefix}'s size.
     * @param sourceSize Size of the source, in {@code sourcePrefix} units.
     * @param sourcePrefix Units of {@code sourceSize}.
     * @return The amount of this {@link FileSizePrefix}es that correspond to
     * the same file size as represented by {@code sourceSize} and {@code sourcePrefix}.
     */
    public BigDecimal convert(BigDecimal sourceSize, FileSizePrefix sourcePrefix) {
        // Convert to bytes first
        BigDecimal bytes = sourcePrefix.unitBytes.multiply(sourceSize);
        
        // Then divide to get size
        return bytes.divide(this.unitBytes);
    }

    /**
     * Convenience method that delegates to {@link #convert(BigDecimal, FileSizePrefix)} by
     * wrapping the {@code long} argument in a {@link BigDecimal}.
     *
     * @param sourceSize Size of the source, in {@link FileSizePrefix} units.
     * @param sourcePrefix Unit of the source size.
     * @return The long value of the required size, as returned by {@link BigDecimal#longValue()}
     */
    public long convert(long sourceSize, FileSizePrefix sourcePrefix) {
        return convert(new BigDecimal(String.valueOf(sourceSize)), sourcePrefix).longValue();
    }

    public BigDecimal getUnitBytes() {
        return unitBytes;
    }

    public String getText() {
        return text;
    }
}
