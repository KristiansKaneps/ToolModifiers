package k4neps.toolmodifiers.utils;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Utils
{
	private Utils() {}

	public static <T> boolean check(T value, Predicate<T> check)
	{
		return check.test(value);
	}

	public static <T> T fixValue(T value, Predicate<T> isValid, Function<T, T> fix)
	{
		if(isValid.test(value))
			return fix.apply(value);
		return value;
	}

	public static void intRange(int minIncl, int maxIncl, Consumer<Integer> iterate)
	{
		for(int x = minIncl; x <= maxIncl; x++)
			iterate.accept(x);
	}

	public static void intRange2D(int minXIncl, int maxXIncl, int minYIncl, int maxYIncl, BiConsumer<Integer, Integer> iterate)
	{
		for(int x = minXIncl; x <= maxXIncl; x++)
			for(int y = minYIncl; y <= maxYIncl; y++)
				iterate.accept(x, y);
	}

	public static class Int
	{
		protected int integer;

		public Int(int integer)
		{
			this.integer = integer;
		}

		public int get()
		{
			return integer;
		}

		public int increment()
		{
			return ++integer;
		}

		public int decrement()
		{
			return --integer;
		}

		public int incrementAfter()
		{
			return integer++;
		}

		public int decrementAfter()
		{
			return integer--;
		}
	}
}
