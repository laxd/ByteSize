package uk.laxd.bytesize;

import java.math.BigDecimal;

/**
 * Created by lawrence on 14/03/17.
 */
public class ByteSize {

    public String getFormattedFileSize(BigDecimal size, FileSizePrefix sizePrefix) {
        FileSizePrefix[] prefixes = FileSizePrefix.SI_UNITS;

        BigDecimal converted = FileSizePrefix.BYTE.convert(size, sizePrefix);
        FileSizePrefix convertedPrefix = FileSizePrefix.BYTE;

        for(FileSizePrefix prefix : prefixes) {

            converted = prefix.convert(size, FileSizePrefix.BYTE);
            convertedPrefix = prefix;

            if(isCorrectSize(converted)) {
                break;
            }
        }

        return converted.setScale(2, BigDecimal.ROUND_DOWN).toPlainString() + convertedPrefix.getText();
    }

    public String getFormattedFileSize(BigDecimal bytes) {
        return getFormattedFileSize(bytes, FileSizePrefix.BYTE);
    }

    private boolean isCorrectSize(BigDecimal size) {
        return size.compareTo(new BigDecimal("1000")) < 0;
    }

}
