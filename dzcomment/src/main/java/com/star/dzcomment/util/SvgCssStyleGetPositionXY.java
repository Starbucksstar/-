package com.star.dzcomment.util;

import com.star.dzcomment.bean.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;

public class SvgCssStyleGetPositionXY
{

	private static HashMap<String, Pair<String, String>> hashMap = new HashMap<>();
	private static SvgCssStyleGetPositionXY svgCssStyleGetPositionXY = new SvgCssStyleGetPositionXY();

	private static  Logger logger = LoggerFactory.getLogger(SvgCssStyleGetPositionXY.class);

	public SvgCssStyleGetPositionXY()
	{
		init();
	}

	/**
	 * .vqiuvt{background:-28.0px -2719.0px;}.vqicwl{background:-14.0px -2997.0px;}
	 * 格式化解析上面css文件内容
	 */
	public void init()
	{
		File file = null;
		try
		{
			file = ResourceUtils.getFile("classpath:svgcss.txt");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			StringBuilder stringBuilder = new StringBuilder();
			String str;
			while ((str = bufferedReader.readLine()) != null)
			{
				stringBuilder.append(str);
			}

			String svgCss = stringBuilder.toString();
			String[] strings = svgCss.split(";}.");

			for (String string : strings)
			{
				String id = string.split("{")[0].replaceAll(".", "").trim();
				String position = string.replaceAll("background:", "");
				String positionX = position.split(" ")[0];
				String positionY = position.split(" ")[1];
				hashMap.put(id, new Pair<>(positionX, positionY));
			}

			if (hashMap.size() > 0)
			{
				logger.info("初始化Svg Css样式坐标移动数据成功！");
			}
			else
			{
				logger.info("初始化Svg Css样式坐标移动数据失败！");
			}
		}
		catch (FileNotFoundException e)
		{
			logger.error("SvgCss文件不存在");
		}
		catch (IOException e)
		{
			logger.error("读取SvgCss文件异常，异常信息={}", e.getMessage(), e);
		}

	}

	/**
	 * 获取中文和数字的Position X、Y
	 * @param clas
	 * @return
	 */
	public static Pair<String, String> getCssBcPositionXY(String clas)
	{
		return hashMap.get(clas);
	}

}
