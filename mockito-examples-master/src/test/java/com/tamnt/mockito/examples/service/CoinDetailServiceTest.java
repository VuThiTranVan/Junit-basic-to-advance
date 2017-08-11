package com.tamnt.mockito.examples.service;

import com.tamnt.mockito.examples.service.dao.CoinDetailDAO;
import com.tamnt.mockito.examples.service.entity.CoinDetail;
import com.tamnt.mockito.examples.service.service.impl.CoinDetailServiceImpl;
import org.hibernate.StaleObjectStateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doAnswer;


/**
 * Inject mock với Mockito
 * 
 * @Mock sẽ tạo ra mock implementation cho dependency CoinAccountDAO
 * @InjectMocks là dành đối tượng đc test, ở đây chính là CoinAccountService
 * 
 *              Đừng bỏ qua MockitoAnnotations.initMocks(this) ở setUp()
 */

@RunWith(MockitoJUnitRunner.class)
public class CoinDetailServiceTest {

	@InjectMocks
	private CoinDetailServiceImpl coinDetailService;

//	@Mock
	private CoinDetailDAO coinDetailDAO;

	@Before
	public void setUp() throws Exception {
		coinDetailDAO = Mockito.mock(CoinDetailDAO.class);

		MockitoAnnotations.initMocks(this);
	}

	/**
	 * When - dùng để giả lập một lời gọi hàm nào đó được sử dụng bên trong
	 * method đang được kiểm thử. - thường đi kèm với .thenReturn(),
	 * .thenAnswer(), .thenThrow() để chỉ định kết quả trả lại.
	 * 
	 * method Mockito.anyString(), Mockito.anyInt(), Mockito.any,… - thường được
	 * dùng khi mock các method có tham số, mà bạn không xác định được giá trị
	 * của các tham số đó.
	 * 
	 * Mockito.when(method_C()).thenAnswer( new Answer<String>(){ public String
	 * answer(InvocationOnMock invocation){ String str = “demoNewAnswer”; return
	 * str; } }); Nếu kiểu trả về thuộc kiểu khác kiểu String, chẳng hạn
	 * Integer, - chúng ta cần thay kiểu String bằng Integer ở 2 chỗ: khai báo
	 * Answer<String> - và public String answer() thành Answer<Integer> và
	 * public Integer answer().
	 */

	/**
	 * thenReturn(T value) Sets a return value to be returned when the method is
	 * called. You should use thenReturn or doReturn when you know the return
	 * value at the time you mock a method call. This defined value is returned
	 * when you invoke the mocked method.
	 */
	/**
	 * Ví dụ: Mocking Methods với Mockito
	 * 
	 * @Ignore nếu muốn bỏ qua @Test đó
	 */
	@Ignore
	@Test
	public void testAddCoinDetail_returnsNewCoinDetail() {

		when(coinDetailDAO.create(any(CoinDetail.class))).thenReturn(new CoinDetail());

		CoinDetail customer = new CoinDetail();

		assertThat(coinDetailService.create(customer), is(notNullValue()));

	}

	@Ignore
	@Test
	public void testAddCoinDetail_returnsNewCoinDetailWithId() {

		when(coinDetailDAO.create(any(CoinDetail.class))).thenAnswer(new Answer<CoinDetail>() {

			@Override
			public CoinDetail answer(InvocationOnMock invocationOnMock) throws Throwable {

				Object[] arguments = invocationOnMock.getArguments();
				if (arguments != null && arguments.length > 0 && arguments[0] != null) {
					CoinDetail coinDetail = (CoinDetail) arguments[0];
					coinDetail.setCoinDetailId(1);
					return coinDetail;
				}

				return null;
			}
		});

		CoinDetail coinDetail = new CoinDetail();

		coinDetailService.create(coinDetail);

		Assert.assertEquals(coinDetail.getCoinDetailId(), 1);
	}

	@Ignore
	@Test(expected = RuntimeException.class)
	public void testAddCoinDetail_throwsException() {
		when(coinDetailDAO.create(any(CoinDetail.class))).thenThrow(RuntimeException.class);
		CoinDetail coinDetail = new CoinDetail();
		coinDetailService.create(coinDetail);
	}

	
	/**
	 * Mocking Void Methods với Mockito
	 * 
	 * Dùng doAnswer nếu chúng ta muốn mock method của dependency
	 * 			Ví dụ , nếu chúng ta muốn test method updateCoinDetail ở tầng service 
	 * 			nhưng ở tầng DAO method updateCoinDetail lại là void method
	 * */
	@Ignore
	@Test
	public void testUpdate() {

		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				Object[] arguments = invocation.getArguments();
				if (arguments != null && arguments.length > 0 && arguments[0] != null) {
					CoinDetail coinDetail = (CoinDetail) arguments[0];
					coinDetail.setCoinBalance(BigDecimal.TEN);
				}

				return null;
			}
		}).when(coinDetailDAO).updateCoinDetail(any(CoinDetail.class));

		// calling the method under test
		CoinDetail coinDetail = coinDetailService.updateCoinDetail(new CoinDetail(), BigDecimal.TEN);

		assertThat(coinDetail, is(notNullValue()));
		assertEquals(coinDetail.getCoinBalance(), BigDecimal.TEN);

	}

	/**
	 * Mocking Void Methods với Mockito
	 * 
	 * Nếu muốn mock method của tầng DAO throw Exception
	 */
	@Test(expected = StaleObjectStateException.class)
	public void testUpdate_throwsException() {

		doThrow(StaleObjectStateException.class).when(coinDetailDAO).updateCoinDetail(any(CoinDetail.class));
		// calling the method under test
		CoinDetail coinDetail = coinDetailService.updateCoinDetail(new CoinDetail(), BigDecimal.ONE);

	}
}
