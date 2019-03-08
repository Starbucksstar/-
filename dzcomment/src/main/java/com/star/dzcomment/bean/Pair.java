package com.star.dzcomment.bean;

import java.io.Serializable;

public class Pair<T1,T2> implements Serializable
{

	private T1 left;
	private T2 right;

	public Pair(T1 left, T2 right)
	{
		this.left = left;
		this.right = right;
	}

	public T1 getLeft()
	{
		return left;
	}

	public void setLeft(T1 left)
	{
		this.left = left;
	}

	public T2 getRight()
	{
		return right;
	}

	public void setRight(T2 right)
	{
		this.right = right;
	}

	@Override
	public String toString()
	{
		return "Pair{" + "left=" + left + ", right=" + right + '}';
	}
}
