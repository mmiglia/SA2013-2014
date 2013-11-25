	package io;

	public class StringHolder
	implements java.io.Serializable
	{
		String s;

		/*
		public StringHolder()
		{
			s = null;
		}
		*/

		public StringHolder(String st)
		{
			s = st;
		}

		public String toString()
		{
			return s;
		}
	}
