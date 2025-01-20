package MyTest;
import static org.junit.Assert.*;

import org.junit.Test;
public class RectangleTest {
	@Test
	public void PerimTest() {
		int a=4;int b=4;
		int actual=16;
		assertEquals(actual,Rectangle.getPerim(a,b));
	}
}