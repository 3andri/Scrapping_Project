package com.rumah.btn.logic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.j256.ormlite.stmt.Where;
import com.rumah.btn.OrmConnection;
import com.rumah.btn.model.BTN_rumah;

class grabingDetailTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws SQLException {
		grabingDetail gdO = new grabingDetail();
		grabingDetail spy=Mockito.spy(gdO);
		MockedStatic<grabingDetail> gds= mockStatic(grabingDetail.class);
		//OrmConnection orm = mock(OrmConnection.class);
		Where<BTN_rumah, String> wh = mock(Where.class);
		List<BTN_rumah> btn_rumahs = new ArrayList<BTN_rumah>();
		btn_rumahs.add(BTN_rumah.builder().btn_url("https://rumahmurahbtn.co.id/btn/detail/31464/pkb-210764").build());
		//when(orm.getRumahBtn()).thenReturn(btn_rumahs);
		when(wh.query()).thenReturn(btn_rumahs);
		
	}

}
