package uk.laxd.bytesize;

import java.math.BigDecimal;

/**
 * Created by lawrence on 14/03/17.
 */
public class ByteSize {

    private static final int DEFAULT_SCALE = 2;

    public String getFormattedFileSize(BigDecimal size, FileSizePrefix sizePrefix, int scale) {
        FileSizePrefix[] prefixes = FileSizePrefix.SI_UNITS;

        BigDecimal converted = FileSizePrefix.BYTE.convert(size, sizePrefix);
        FileSizePrefix convertedPrefix = FileSizePrefix.BYTE;

        for(FileSizePrefix prefix : prefixes) {

            if(isCorrectSize(converted)) {
                // Stop if we have found the correct
                // size prefix.
                break;
            }

            // Convert size to next prefix.
            converted = prefix.convert(size, sizePrefix);
            convertedPrefix = prefix;
        }

        return converted.setScale(scale, BigDecimal.ROUND_DOWN).toPlainString() + convertedPrefix.getText();
    }

    public String getFormattedFileSize(BigDecimal bytes, FileSizePrefix prefix) {
        return getFormattedFileSize(bytes, prefix, DEFAULT_SCALE);
    }

    public String getFormattedFileSize(BigDecimal bytes, int scale) {
        return getFormattedFileSize(bytes, FileSizePrefix.BYTE, scale);
    }

    public String getFormattedFileSize(BigDecimal bytes) {
        return getFormattedFileSize(bytes, FileSizePrefix.BYTE, DEFAULT_SCALE);
    }

    private boolean isCorrectSize(BigDecimal size) {
        return size.compareTo(new BigDecimal("1000")) < 0;
    }
}
