package urlshortner;

import java.math.BigInteger;
import java.util.List;

public class UrlShortner {
    private BigInteger CURRENT_COUNT = BigInteger.ZERO;
    private final List<String> BASE62_INDEXES = List.of(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    );

    private String generateBase62(BigInteger number) {
        String  base62 = "";
        while (number.compareTo(BigInteger.ZERO) > 0) {
            BigInteger remainder = number.mod(BigInteger.valueOf(62));
            base62 = BASE62_INDEXES.get(remainder.intValue()) + base62;
            number = number.divide(BigInteger.valueOf(62));
        }
        return base62;
    }

    private void incrementCount() {
        CURRENT_COUNT = CURRENT_COUNT.add(BigInteger.ONE);
    }

    public String shorten(String url) {
        incrementCount();
        return generateBase62(CURRENT_COUNT);
    }
}
