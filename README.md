# uriencoder: Properly encode your URL

Based on https://github.com/spring-projects/spring-framework/commit/d1aee0e8691c41753621332ff69b17be3f7c8ba2 from Oct 6th.

spring-web has many good utils but is too big and drags in many other dependencies...

To encode your URL, and particularly your query parameters, you just need a small method from UriComponentsBuilder.
Extracted now in SpringUtil.

How to use:

```java
        String base = "http://example.com/query";

        Map<String, String> param = new TreeMap<>();
        param.put("msg", "hello world");
        param.put("id", "&<>");

        String query = param.entrySet().stream()
                .map((e) -> e.getKey() + "=" + SpringUtil.encodeUriComponent(e.getValue(), StandardCharsets.UTF_8, SpringUtil.Type.QUERY_PARAM))
                .collect(Collectors.joining("&"));

        String url = base + "?" + query;

        Assert.assertEquals("http://example.com/query?id=%26%3C%3E&msg=hello%20world", url);
```