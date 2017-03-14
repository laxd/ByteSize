package uk.laxd.bytesize;

import java.math.BigDecimal;

/**
 * Created by lawrence on 14/03/17.
 */
public class ByteSize {

    public static String getFormattedFileSize(BigDecimal size, FileSizePrefix sizePrefix) {
        FileSizePrefix[] prefixes = FileSizePrefix.SI_UNITS;

        BigDecimal converted = FileSizePrefix.BYTE.convert(size, sizePrefix);
        FileSizePrefix convertedPrefix = FileSizePrefix.BYTE;

        for(FileSizePrefix prefix : prefixes) {

            converted = prefix.convert(size, FileSizePrefix.BYTE);
            convertedPrefix = prefix;

            if(converted.compareTo(new BigDecimal("1000")) > 0) {
                break;
            }
        }

        return converted.setScale(2, BigDecimal.ROUND_DOWN).toPlainString() + convertedPrefix.getText();
    }

    public static String getFormattedFileSize(BigDecimal bytes) {
        return getFormattedFileSize(bytes, FileSizePrefix.BYTE);
    }

}
