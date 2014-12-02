package router.util;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * Responsible for converting HTML to plain text for email body
 *
 * Created by Shaochen Huang on 11/26/14.
 */
public class EmailBodyConverterUtil {

    /**
     * Convert HTML to plain text with Jsoup helper
     * @param html
     * @return
     */
    public static String convert(String html){
        if(StringUtils.isEmpty(html))
            return html;
        Document doc = Jsoup.parse(html);
        return doc.body().text();
    }
}
