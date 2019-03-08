package com.star.dzcomment.util;

import com.star.dzcomment.bean.ProxyServerInfo;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil
{

	/**
	 * HttpClient连接工具类
	 *
	 * @param uri                      请求地址
	 * @param connectTimeout           连接超时时间
	 * @param socketTimeout            socket超时时间
	 * @param connectionRequestTimeout 连接请求超时时间
	 * @return String返回获取到数据
	 */
	public String HttpClientConnect(String uri, ProxyServerInfo proxyServerInfo, int connectTimeout, int socketTimeout, int connectionRequestTimeout,
			String cookie) throws Exception
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpClientContext context = HttpClientContext.create();
		HttpGet httpGet = new HttpGet(uri);
		HttpHost proxy = new HttpHost(proxyServerInfo.getIp(), proxyServerInfo.getPort(), "http");
		RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout)
				.setConnectionRequestTimeout(connectionRequestTimeout).build();
		httpGet.setConfig(requestConfig);
		//设置请求头消息
		httpGet.setHeader("Host", "www.dianping.com");
		httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
		httpGet.setHeader("Referer", uri);
		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
		httpGet.setHeader("Cookie", cookie);
		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet, context);

		//判断response对象不为空并且响应码为200
		String html = null;
		if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200 && httpResponse.getEntity() != null)
		{
			html = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
		}
		else
		{
			httpResponse.close();
			httpClient.close();
			throw new Exception("请求被拒绝，错误码=" + httpResponse.getStatusLine().getStatusCode());
		}
		httpResponse.close();
		httpClient.close();
		return html;
	}

	public String httpClient(String uri, ProxyServerInfo proxyServerInfo, int connectTimeout, int socketTimeout, int connectionRequestTimeout)
			throws Exception
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpClientContext context = HttpClientContext.create();
		HttpGet httpGet = new HttpGet(uri);
		HttpHost proxy = new HttpHost(proxyServerInfo.getIp(), proxyServerInfo.getPort(), "http");
		RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout)
				.setConnectionRequestTimeout(connectionRequestTimeout).build();
		httpGet.setConfig(requestConfig);
		//设置请求头消息
		httpGet.setHeader("Host", "www.dianping.com");
		httpGet.setHeader("Cache-Control", "max-age=0");
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
		httpGet.setHeader("Upgrade-Insecure-Requests", "1");
		httpGet.setHeader("Cookie",
				"navCtgScroll=0; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_cuid=1647dc6607ec8-0b9c87816f6a07-3a614f0b-240000-1647dc6607ec8; _lxsdk=1647dc6607ec8-0b9c87816f6a07-3a614f0b-240000-1647dc6607ec8; _hc.v=1e5c248d-cc8c-c13b-d26c-2c41a53c128e.1531118510; s_ViewType=10; aburl=1; cy=17; cye=xian; lgtoken=07d2e9127-6d84-41fd-bb8f-175e2b8553cf; dper=1ff324480cfcf01c13d9e7f390e306c43b1375a510804386de41cdb9722fe39e8f6be2c33166487cedcf47a3a02f8d596987b0adb9c1873f50a107f7dd90a23403db5e12ed32aa20196bb91c9d84dd5c9e6c3f2280d15381777bdf5f700c3fc0; ll=7fd06e815b796be3df069dec7836c3df; ua=dpuser_3725730569; ctu=71ddf3634acf17336dbac97719573c65d4084e0d27e18ad106b71a8aca379d34; _lxsdk_s=1647dc6607e-166-708-c57%7C%7C443");
		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet, context);

		//判断response对象不为空并且响应码为200
		String html = null;
		if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200 && httpResponse.getEntity() != null)
		{
			html = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
		}
		else
		{
			httpResponse.close();
			httpClient.close();
			throw new Exception("请求被拒绝，错误码=" + httpResponse.getStatusLine().getStatusCode());
		}
		httpResponse.close();
		httpClient.close();
		return html;
	}

}