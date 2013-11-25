package threads;

public class Timer
{
	public static void main(String[] argv)
	{
		long nominale = 1000;
		long correzione = 0;
		long prev, now;
		prev = System.currentTimeMillis();
		System.out.println(prev);
		while(true)
		{
			try
			{
				Thread.sleep(nominale + correzione);
				now = System.currentTimeMillis();

				long aspettativa = prev+nominale;
				long attesaEffettiva = now-prev;
				correzione = prev+nominale-now;
				System.out.println(now);
				System.out.println("Ho dormito " + attesaEffettiva + " millisecondi");
				prev += nominale;
			}
			catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
	}
}
