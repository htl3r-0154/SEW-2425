import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HtmlStringToolsTest {

    @Test
    void testGetHtmlTagStatistic() throws IOException {
        Map<String, Double> s = HtmlStringTools.getHtmlTagStatistic("resources/test.html");
        assertNotNull(s);
        assertEquals(3, s.size());
        assertEquals(0.75, s.getOrDefault("a", 0.0) + s.getOrDefault("A", 0.0));
    }

    @Test
    void testGetAbsolutLinks() throws IOException {
        Set<String> links = HtmlStringTools.getAbsolutLinks("resources/test.html");
        assertNotNull(links);
        assertEquals(4, links.size());
    }

    @Test
    void testGetDomains() throws IOException {
        Set<String> domains = HtmlStringTools.getDomains("resources/test.html");
        assertNotNull(domains);
        assertEquals(2, domains.size());
    }
}
