package it.unige.dibris.sa;

public class IntHolder
{
	int value;
	
	public IntHolder(int val)
	{
		value = val;
	}

	public IntHolder()
	{
		this(0);
	}

	public static void main(String[] argv)
	{
		final IntHolder ih1 = new IntHolder(3);
		IntHolder ih2 = new IntHolder();

		System.out.println(ih1 + " " + ih2);
		//ih1 = new IntHolder(); non si puo' fare, e' final
		ih1.value = 156;
		System.out.println(ih1 + " " + ih2);
	}

	public String toString()
	{
		return String.valueOf(value);
	}
}