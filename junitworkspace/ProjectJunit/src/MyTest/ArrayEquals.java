package MyTest;
import static org.junit.Assert.*;

import org.junit.Test;
public class ArrayEquals {
	@Test
	public void MyTest() {
		int[] a1= {1,2,3};
		int[] a2= {1,2,3};
		assertArrayEquals(a1,a2);
	}
}