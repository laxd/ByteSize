package uk.laxd.bytesize;

import java.math.BigDecimal;

/**
 * <p>Provides utility methods to convert a file size to a human-readable {@link String} format.</p>
 *
 * <p>All methods that take a {@code size} without a corresponding {@link FileSizePrefix} are
 * assumed to be represented in Bytes.</p>
 *
 * @author Lawrence Andrews
 */
public class ByteSize {

    private static final int DEFAULT_SCALE = 2;

    /**
     * <p>Formats the given filesize (as represented by {@code size} and {@code sizePrefix})
     * in a sensible way such as to attempt to be most readable.</p>
     *
     * <p>Formatting is done by repeatedly converting from the source {@link FileSizePrefix}
     * to increasingly larger ones until a filesize is found that can be represented using only
     * that {@link FileSizePrefix}, and moving up to the next one would result in less
     * than a single unit of size.</p>
     *
     * <p>I.e. given this method call: {@code getFormattedFileSize(1432, MB, 2)}, the result will be 1.43GB</p>
     *
     * @param size Size of the file size to format, in {@code sizePrefix} units.
     * @param sizePrefix Unit of the file size.
     * @param scale Scale to use when formatting, as specified by {@link BigDecimal#setScale(int)}
     * @return The formatted value, represented as multiples of the largest unit such that the next largest unit
     * would have to be represented solely as a fraction.
     */
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

    /**
     * <p>Formats the given filesize (as represented by {@code size} and {@code sizePrefix})
     * in a sensible way such as to attempt to be most readable.</p>
     *
     * <p>Formatting is done by repeatedly converting from the source {@link FileSizePrefix}
     * to increasingly larger ones until a filesize is found that can be represented using only
     * that {@link FileSizePrefix}, and moving up to the next one would result in less
     * than a single unit of size.</p>
     *
     * @param size Size of the file size to format, in {@code sizePrefix} units.
     * @param sizePrefix Unit of the file size.
     * @return The formatted value, represented as multiples of the largest unit such that the next largest unit
     * would have to be represented solely as a fraction.
     * @see #getFormattedFileSize(BigDecimal, FileSizePrefix, int)
     */
    public String getFormattedFileSize(BigDecimal size, FileSizePrefix sizePrefix) {
        return getFormattedFileSize(size, sizePrefix, DEFAULT_SCALE);
    }

    /**
     * <p>Formats the given filesize (as represented by {@code size}, assumed to be represented
     * in bytes) in a sensible way such as to attempt to be most readable.</p>
     *
     * <p>Formatting is done by repeatedly converting from bytes
     * to increasingly larger units until a filesize is found that can be represented using only
     * that {@link FileSizePrefix}, and moving up to the next one would result in less
     * than a single unit of size.</p>
     *
     * @param size Size of the file size to format, in bytes.
     * @param scale Scale to use when formatting, as specified by {@link BigDecimal#setScale(int)}
     * @return The formatted value, represented as multiples of the largest unit such that the next largest unit
     * would have to be represented solely as a fraction.
     * @see #getFormattedFileSize(BigDecimal, FileSizePrefix, int)
     */
    public String getFormattedFileSize(BigDecimal size, int scale) {
        return getFormattedFileSize(size, FileSizePrefix.BYTE, scale);
    }

    /**
     * <p>Formats the given filesize (as represented by {@code size}, assumed to be represented
     * in bytes) in a sensible way such as to attempt to be most readable. Results are returned
     * truncated to 2dp.</p>
     *
     * <p>Formatting is done by repeatedly converting from bytes
     * to increasingly larger units until a filesize is found that can be represented using only
     * that {@link FileSizePrefix}, and moving up to the next one would result in less
     * than a single unit of size.</p>
     *
     * @param size Size of the file size to format, in bytes.
     * @return The formatted value, represented as multiples of the largest unit such that the next largest unit
     * would have to be represented solely as a fraction.
     * @see #getFormattedFileSize(BigDecimal, FileSizePrefix, int)
     */
    public String getFormattedFileSize(BigDecimal size) {
        return getFormattedFileSize(size, FileSizePrefix.BYTE, DEFAULT_SCALE);
    }

    private boolean isCorrectSize(BigDecimal size) {
        return size.compareTo(new BigDecimal("1000")) < 0;
    }
}
