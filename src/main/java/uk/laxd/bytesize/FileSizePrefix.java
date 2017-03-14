package uk.laxd.bytesize;

import java.math.BigDecimal;

/**
 * Created by lawrence on 14/03/17.
 */
public enum FileSizePrefix {
    BYTE(new BigDecimal("1"), ""),
    KILOBYTE(new BigDecimal("1000"), "KB"),
    MEGABYTE(new BigDecimal("1000").multiply(KILOBYTE.unitBytes), "MB"),
    GIGABYTE(new BigDecimal("1000").multiply(MEGABYTE.unitBytes), "GB"),
    TERABYTE(new BigDecimal("1000").multiply(GIGABYTE.unitBytes), "TB"),
    PETABYTE(new BigDecimal("1000").multiply(TERABYTE.unitBytes), "PB"),
    EXABYTE(new BigDecimal("1000").multiply(PETABYTE.unitBytes), "EB"),
    ZETTABYTE(new BigDecimal("1000").multiply(EXABYTE.unitBytes), "ZB"),
    YOTTABYTE(new BigDecimal("1000").multiply(ZETTABYTE.unitBytes), "YB"),

    KIBIBYTE(new BigDecimal("1024"), "KiB"),
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

    public BigDecimal convert(BigDecimal sourceSize, FileSizePrefix sourcePrefix) {
        // Convert to bytes first
        BigDecimal bytes = sourcePrefix.unitBytes.multiply(sourceSize);
        
        // Then divide to get size
        return bytes.divide(this.unitBytes);
    }

    public BigDecimal getUnitBytes() {
        return unitBytes;
    }

    public String getText() {
        return text;
    }
}
