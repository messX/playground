package urlshortner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UrlShortnerTest {

    @Test
    public void testShorten() {
        UrlShortner urlShortner = new UrlShortner();
        String regex = "[A-Za-z0-9]+";

        String shortUrl1 = urlShortner.shorten("http://example.com");

        assertTrue(shortUrl1.matches(regex));

    }
}
