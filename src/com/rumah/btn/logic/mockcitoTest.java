/**
 * 
 */
package com.rumah.btn.logic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;

/**
 * @author Andri
 *
 */
class mockcitoTest {

	@Test
	void test() {
		mockcito data = new mockcito();
		mockcito mok = spy(mockcito.class);
		//when(mok.dana()).thenReturn("BBBBBBBBBBBBBBB");
		// mockcito data=new mockcito();
		// mok.getData();
		when(mok.dana()).thenReturn("sssssssstssssss");
		 mok.getData();

	}

}
