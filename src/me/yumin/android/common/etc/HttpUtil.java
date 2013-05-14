/**
 * 
 */
package me.yumin.android.common.etc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

/**
 * @author yumin
 * 
 */
public final class HttpUtil {

	/**
	 * 
	 */
	private static final String DEFAULT_CHARSET_NAME = "UTF-8";
	private static final int DEFAULT_CONNECT_TIMEOUT = 5000;
	private static final int DEFAULT_READ_TIMEOUT = 6000;

	/**
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 */
	public static String postParam(String url, Map<String, Object> paramMap) {

		String result = null;

		result = postParam(url, paramMap, DEFAULT_CHARSET_NAME, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT);

		return result;
	}

	/**
	 * 
	 * @param url
	 * @param paramMap
	 * @param charsetName
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 */
	public static String postParam(String url, Map<String, Object> paramMap, String charsetName, int connectTimeout, int readTimeout) {

		/* 定义变量 */
		String result = null;
		HttpURLConnection httpConnection = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;

		try {
			// 生成参数
			String queryString = paramMapToQueryString(paramMap, charsetName);
			// System.out.println(queryString);
			byte[] bytes = (null != queryString && 0 < queryString.length() ? queryString.getBytes() : null);
			// 建立连接
			httpConnection = getHttpURLConnection(url, charsetName, connectTimeout, readTimeout);
			httpConnection.connect();
			// 写入参数
			outputStream = new BufferedOutputStream(httpConnection.getOutputStream());
			if (null != bytes && 0 < bytes.length) { outputStream.write(bytes); }
			outputStream.flush();
			// 返回结果
			inputStream = httpConnection.getInputStream();
			if (null != httpConnection.getContentEncoding() && 0 == "gzip".compareTo(httpConnection.getContentEncoding())) {
				inputStream = new GZIPInputStream(inputStream);
			}
			inputStream = new BufferedInputStream(inputStream);
			result = inputStreamToString(inputStream, charsetName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/* 释放变量 */
			try {
				if (null != inputStream) {
					inputStream.close();
					inputStream = null;
				}
				if (null != outputStream) {
					outputStream.close();
					outputStream = null;
				}
				if (null != httpConnection) {
					httpConnection.disconnect();
					httpConnection = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 
	 * @param url
	 * @param charsetName
	 * @param connectTimeout
	 * @param readTimeout
	 * @return HttpURLConnection
	 * @throws IOException
	 */
	public static HttpURLConnection getHttpURLConnection(String url, String charsetName, int connectTimeout, int readTimeout) throws IOException {

		HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
		httpConnection.setConnectTimeout(connectTimeout);
		httpConnection.setDoInput(true);
		httpConnection.setDoOutput(true);
		httpConnection.setInstanceFollowRedirects(false);
		httpConnection.setReadTimeout(readTimeout);
		httpConnection.setRequestMethod("POST");
		httpConnection.setRequestProperty("Accept-Encoding", "gzip");
		httpConnection.setRequestProperty("Charset", charsetName);
		httpConnection.setRequestProperty("Connection", "close");
		httpConnection.setRequestProperty("User-Agent", "SDK");
		httpConnection.setUseCaches(false);

		return httpConnection;
	}

	// ====================
	// private methods
	// ====================

	/**
	 * 
	 * @param inputStream
	 * @param charsetName
	 * @return string
	 * @throws IOException
	 */
	private static String inputStreamToString(InputStream inputStream, String charsetName) throws IOException {

		String string = null;

		if (null != inputStream) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charsetName));
			string = bufferedReader.readLine();
		}

		return string;
	}

	/**
	 * 
	 * @param paramMap
	 * @param charsetName
	 * @return string,格式如&a=1&b=2&c=3
	 */
	private static String paramMapToQueryString(Map<String, Object> paramMap, String charsetName) {

		String queryString = null;

		if (null != paramMap && 0 < paramMap.size()) {
			try {
				Iterator<Entry<String, Object>> iterator = paramMap.entrySet().iterator();
				StringBuilder builder = new StringBuilder();
				while (iterator.hasNext()) {
					Entry<String, Object> entry = iterator.next();
					String key = String.valueOf(entry.getKey());
					String value = String.valueOf(entry.getValue());
					builder.append("&").append(key).append("=").append(URLEncoder.encode(value, charsetName));
				}
				queryString = builder.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return queryString;
	}
}
