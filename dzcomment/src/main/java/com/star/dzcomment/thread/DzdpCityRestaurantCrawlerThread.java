package com.star.dzcomment.thread;

import java.util.concurrent.Callable;

public class DzdpCityRestaurantCrawlerThread implements Callable<String>
{
	private int page;

	public DzdpCityRestaurantCrawlerThread(int page)
	{
		this.page = page;
	}

	@Override
	public String call() throws Exception
	{
		return null;
	}
}
