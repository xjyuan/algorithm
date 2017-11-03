package com.cy.sort;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1. Httpclient请求都可以使用此类
 * 2. 简单封装了get/post的方法，支持自定义编码，自定义header
 * 3. 使用实例可参考测试用例中的代码: HttpsClientServiceTest
 * <p>
 * Created by yuweijun on 2017-06-21.
 */
public class HttpsClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpsClientService.class);

    /**
     * 每次创建一个新的httpsclient，不共用使用连接池的httpsclient
     */
    private CloseableHttpClient getHttpsClient() {
        return HttpClients.custom()
                .setConnectionManager(new BasicHttpClientConnectionManager())
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(3000)
                        .setSocketTimeout(15000)
                        .build())
                .build();
    }

    private static final ThreadLocal<Map<String, String>> MAP_THREAD_LOCAL = new ThreadLocal<>();

    public HttpsClientService setHeaders(Map<String, String> headers) {
        MAP_THREAD_LOCAL.set(headers);
        return this;
    }

    public String get(String url) {
        return get(url, Charsets.UTF_8);
    }

    public String get(String url, Charset charset) {
        LOGGER.info("httpclient get url {}", url);
        try {
            HttpGet httpGet = new HttpGet(url);
            return execute(httpGet, charset);
        } catch (Exception e) {
            LOGGER.error("httpclient get request error for url : {}", url, e);
        }

        return null;
    }

    public String post(String url, Map<String, String> paramsMap) {
        return post(url, Charsets.UTF_8, paramsMap);
    }

    public String post(String url, Charset charset, Map<String, String> paramsMap) {
        LOGGER.info("httpclient post url {} with params : {}", url, paramsMap);

        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, charset));
            }

            return execute(method, charset);
        } catch (Exception e) {
            LOGGER.error("httpclient post error for url : {}", url, e);
        }

        return null;
    }

    /**
     * default post as utf-8 encoding
     */
    public String post(String url, String body) {
        LOGGER.info("httpclient post url {} with body {}", url, body);
        try {
            HttpPost method = new HttpPost(url);
            if (!Strings.isNullOrEmpty(body)) {
                method.setEntity(new StringEntity(body, Charsets.UTF_8));
            }
            return execute(method, Charsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("httpclient post error for url : {}", url, e);
        }

        return null;
    }
    
    /**
     * 支持普通参数与文件的混合提交
     * @author  zhouyucheng 
     * @param url		
     * @param charset
     * @param params	普通参数
     * @param files		文件参数
     * @return String
     */
	public String post(String url, Charset charset, Map<String, String> params, Map<String, File> files) {
		LOGGER.info("httpclient post url {} with params : {}, fileBodys : {}", url, params, files);
        try {
            HttpPost method = new HttpPost(url);
            
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            
            // 构建普通参数
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    StringBody value = new StringBody(param.getValue() , ContentType.create("text/plain", charset));
					builder.addPart(param.getKey(), value);
                }
            }
            
            // 文件参数
            if (files != null) {
                for (Map.Entry<String, File> param : files.entrySet()) {
					builder.addPart(param.getKey(), new FileBody(param.getValue()));
                }
            }
            
            method.setEntity(builder.build());
             
            return execute(method, Charsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("httpclient post error for url : {}", url, e);
        }

        return null;
    }

    private String execute(HttpUriRequest request, Charset charset) {
        CloseableHttpClient httpsClient = getHttpsClient();
        try {
            setHttpHeaders(request);
            CloseableHttpResponse response = httpsClient.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, charset);
                }
            } else {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                LOGGER.error("httpclient statusCode is {} and body : {}.", statusCode, body);
            }
        } catch (Exception e) {
            LOGGER.error("httpclient execute error", e);
        } finally {
            MAP_THREAD_LOCAL.remove();
            try {
                httpsClient.close();
            } catch (IOException e) {
                LOGGER.error("https client close error", e);
            }
        }

        return null;
    }

    private void setHttpHeaders(HttpUriRequest request) {
        Map<String, String> map = MAP_THREAD_LOCAL.get();
        if (map != null) {
            LOGGER.info("httpclient add request headers is {}", map);
            Set<String> keys = map.keySet();
            for (String key : keys) {
                String value = map.get(key);
                LOGGER.debug("header key and value is : {} = {}", key, value);
                request.setHeader(key, value);
            }
        }
    }

}
