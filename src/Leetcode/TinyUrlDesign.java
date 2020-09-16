package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */
public class TinyUrlDesign {

    static Map<Integer, String> hashUrl = new HashMap<>();
    static String hostname = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public static String encode(String longUrl) {
        int urlHash = longUrl.hashCode();
        hashUrl.put(urlHash, longUrl);
        return hostname+urlHash;
    }

    // Decodes a shortened URL to its original URL.
    public static String decode(String shortUrl) {
        int urlKey = Integer.parseInt(shortUrl.replace(hostname,""));
        return hashUrl.get(urlKey);
    }

    public static void main(String[] args) {
        System.out.println(encode("https://leetcode.com/problems/design-tinyurl"));
        System.out.println(decode(encode("https://leetcode.com/problems/design-tinyurl")));
    }
}
