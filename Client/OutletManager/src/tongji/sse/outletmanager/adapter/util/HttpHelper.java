package tongji.sse.outletmanager.adapter.util;

import java.util.List;

public class HttpHelper {
    /**
     * Accept Header
     */
    public static final String HEADER_ACCEPT = "Accept";

    /**
     * Cache-Control Header
     */
    public static final String CACHE_CONTROL_NAME = "Cache-Control";
    
    /**
     * Cache-Control max-age time
     */
    public static final String MAX_AGE_PATTERN = "max-age";

    /**
     * JSON type
     */
    public static final String JSON_TYPE = "application/json";

    /**
     * Content type
     */
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    
    /**
     * Content
     */
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    
    public static final String HTTP_PROTOCOL = "http://";
    
    public static String baseUrl = "http://192.168.1.103:8080";
    
   
    
    
    public static String buildURL(String hostURL, String query) {
        // build URL
        StringBuilder buffer = new StringBuilder();

        if ((hostURL != null) && (hostURL.length() > 0)) {
            buffer.append(hostURL);
        }

        if ((query != null) && (query.length() > 0)) {
            if (!query.startsWith("?")) {
                buffer.append('?');
            }
            buffer.append(query);
        }

        return buffer.toString();
    }
    
    public static void setBaseURL(String serverIp, String serverPort) {
    	baseUrl = HTTP_PROTOCOL + serverIp + ":" + serverPort;
    }
    
    
    public static String getBaseURL() {
    	return baseUrl;
    }
    
    
    
}
