package uriencoder;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

public class SpringUtilTest {

    @Test
    public void minorChecks() {
        String base = "http://example.com/query";

        Map<String, String> param = new TreeMap<>();
        param.put("msg", "hello world");
        param.put("id", "&<>");

        String query = param.entrySet().stream()
                .map((e) -> e.getKey() + "=" + SpringUtil.encodeUriComponent(e.getValue(), StandardCharsets.UTF_8, SpringUtil.Type.QUERY_PARAM))
                .collect(Collectors.joining("&"));

        String url = base + "?" + query;

        Assert.assertEquals("http://example.com/query?id=%26%3C%3E&msg=hello%20world", url);
    }

}
