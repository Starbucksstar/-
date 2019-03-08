package com.star.dzcomment.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 大众点评文字替换工具类
 */
public class DzdpWordsReplaceUtil
{
	private static String WORDSSVGURL = "http://s3plus.meituan.net/v1/mss_0a06a471f9514fc79c981b5466f56b91/svgtextcss/737a81f62d09e9dc36ba15506aa8f999.svg";
	private static Logger logger = LoggerFactory.getLogger(DzdpWordsReplaceUtil.class);
	private static LinkedHashMap<Integer, Integer> numData = new LinkedHashMap<>();
	private static LinkedHashMap<Integer, String> wordsData = new LinkedHashMap<>();
	//大众点评加密数字数组，解析直接就是下标
	private static String[] str = {"ciykim","1","ciyof7","ciyj72","ciyx72","ciyb3n","ciyari","ciy3ii","ciyb3n","ciy0vx"};



	public static String getWordsSvgFile() throws Exception
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(WORDSSVGURL);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == 200 && response.getEntity() != null)
		{
			String data = EntityUtils.toString(response.getEntity(), "utf-8");
			return data;
		}
		else
		{
			logger.error("获取大众点评文字替换svg文件异常，异常信息={}", response.getAllHeaders().toString());
		}

		return null;
	}

	/**
	 * 获取下标和汉字集合
	 * @param data
	 */
	public static void parseWordsSet(String data)
	{
		if (StringUtils.isBlank(data))
		{
			throw new IllegalArgumentException("破解字符串不能为空！");
		}
		data = data.replaceAll("svg", "html");

		//获取svg文件中文下标
		Document document = Jsoup.parse(data);
		Elements elements = document.select("defs").first().select("path");
		for (Element element : elements)
		{
			int id = Integer.parseInt(element.attr("id"));
			int d = Integer.parseInt(element.attr("d").split(" ")[1]);
			numData.put(d, id);
		}

		//获取破解中文集合
		Elements elements1 = document.select("text").first().select("textPath");
		for (Element element : elements1)
		{
			int i = Integer.parseInt(element.attr("xlink:href").split("#")[1]);
			String words = element.text().trim();
			wordsData.put(i, words);
		}
	}

	/**
	 * 获取替换的中文结果
	 * @param bcPostitionX
	 * @param bcPositionY
	 * @return
	 */
	public static String getWord(String bcPostitionX, String bcPositionY)
	{
		float xx = Float.parseFloat(bcPostitionX.replaceAll("px","").replaceAll("-", "").trim());
		float yy = Float.parseFloat(bcPositionY.replaceAll("px","").replaceAll("-", "").trim());

		int x = (int) xx;
		int y = (int) yy;

		/**
		 * 计算汉字位置算法：
		 * 横坐标x/width(14)=文字每行列数=col
		 * 纵坐标在下标集合中不大于value对应的key=文字行数=row
		 * 通过文字每行(行，列)到汉字集合中去匹配对应的汉字
		 */

		int col = x / 14;
		int row = 0;

		Set<Integer> set = numData.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext())
		{
			Integer value = (Integer) iterator.next();
			if (y < value)
			{
				row = numData.get(value);
				break;
			}
		}

		System.out.println(row+":"+col);

		//获取文字
		String str = wordsData.get(row);
		char[] strs = str.toCharArray();
		return String.valueOf(strs[col]);
	}

	/**
	 * 获取大众点评破解数字
	 * @param clas
	 * @return
	 */
	public static int getNum(String clas){
		int i=0;
		for(String s:str){
			if(s.equals(clas)){
				return i;
			}
			i++;
		}
		return 0;
	}

	public static void main(String[] args) throws Exception
	{
		String svg = getWordsSvgFile();
		System.out.println(svg);
		parseWordsSet(svg);
		System.out.println(getWord("-120.0px","-431.0px"));
	}
}
