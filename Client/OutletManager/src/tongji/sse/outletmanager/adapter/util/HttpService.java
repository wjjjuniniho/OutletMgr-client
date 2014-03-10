package tongji.sse.outletmanager.adapter.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;



public class HttpService {
	//private static final HttpService instance = new HttpService();
    private static final String CHARSET = "charset";
    private HttpClient client;

    private HttpService() {
        client = getHttpClient();
    }

    /**
     * get HTTP service instance
     * @return HTTP service instance
     */
    public static HttpService getInstance() {
        return new HttpService();
    }

    /**
     * execute HTTP request
     * @param request
     * @param context
     * @return HTTP response
     * @throws IOException
     */
    public HttpResponse execute(HttpRequestBase request, HttpContext context) throws IOException {
        return client.execute(request, context);
    }

    /**
     * parse HTTP response and return result
     * @param response
     * @return response result or null
     * @throws IOException
     */
    public String getResponseText(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            return null;
        }

        ByteArrayOutputStream responseTextHolder = new ByteArrayOutputStream();
        try {
            entity.writeTo(responseTextHolder);
            responseTextHolder.flush();
            return responseTextHolder.toString(getEncoding(entity));
        } finally {
            try {
                responseTextHolder.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getResponseText(HttpEntity entity) throws IOException {
        if (entity == null) {
            return null;
        }

        ByteArrayOutputStream responseTextHolder = new ByteArrayOutputStream();
        try {
            entity.writeTo(responseTextHolder);
            responseTextHolder.flush();
            return responseTextHolder.toString(getEncoding(entity));
        } finally {
            try {
                responseTextHolder.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Shut down service
     */
    public void shutdown() {
        if (client != null) {
            client.getConnectionManager().shutdown();
        }
    }

    private HttpClient getHttpClient() {
    	HttpClient httpClient = new DefaultHttpClient();

        return httpClient;
    }

    /**
     * set connection timeout
     * @param timeout
     * @return HttpService instance
     */
    public HttpService setConnectionTimeout(int timeout) {
        final HttpParams params = this.client.getParams();
        if (params != null) {
            params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
        }
        return this;
    }

    /**
     * set socket timeout
     * @param timeout
     * @return HttpService instance
     */
    public HttpService setSocketTimeout(int timeout) {
        final HttpParams params = this.client.getParams();
        if (params != null) {
            params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
        }
        return this;
    }

    private String getEncoding(HttpEntity entity) {
        if ((entity != null) && (entity.getContentType() != null)) {
            HeaderElement[] elements = entity.getContentType().getElements();
            if ((elements != null) && (elements.length > 0)) {
                if ((elements[0] != null) && (elements[0].getParameterByName(CHARSET) != null)) {
                    String encoding = elements[0].getParameterByName(CHARSET).getValue();
                    if ((encoding != null) && (encoding.length() > 0)) {
                        return encoding;
                    }
                }
            }
        }
        return HTTP.UTF_8;
    }
}
