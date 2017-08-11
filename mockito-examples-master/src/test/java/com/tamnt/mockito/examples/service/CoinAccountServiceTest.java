package com.tamnt.mockito.examples.service;

import com.tamnt.mockito.examples.service.dao.CoinAccountDAO;
import com.tamnt.mockito.examples.service.entity.CoinAccount;
import com.tamnt.mockito.examples.service.service.impl.CoinAccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CoinAccountServiceTest {

    @InjectMocks
    private CoinAccountServiceImpl coinAccountService;

    @Mock
    private CoinAccountDAO coinAccountDAO;

    @Captor
    private ArgumentCaptor<CoinAccount> coinAccountArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    
    /**
     * Testing Void Methods với Mockito
     * 
     * Có 2 cách
     * Sử dụng verify
     * */
    
    /** verify
     * *
     * Giải thích: veify của mockito là để kiểm tra xem method của dependency có được gọi hay ko
     * Cụ thể hay đây khi test createCoinAccount của service 
     * thì mình cần verify xem có đúng là khi gọi method này của service thì createCoinAccount của DAO được gọi 1 lần hay ko?
     *  và tất nhiên là method updateCoinAccount của DAO không bao giờ được gọi rồi :)
     */
    
    @Test
    public void testCreateCoinAccountByVerify() {

        CoinAccount coinAccount = new CoinAccount();
        coinAccountService.createCoinAccount(coinAccount);

        //verify that the createCoinAccount method has been invoked
        verify(coinAccountDAO).createCoinAccount(any(CoinAccount.class));
        //the above is similar to : verify(coinAccountDAO, times(1)).createCoinAccount(any(CoinAccount.class));
        verify(coinAccountDAO, times(1)).createCoinAccount(any(CoinAccount.class));

        //verify that the updateCoinAccount method has never been  invoked
        verify(coinAccountDAO, never()).updateCoinAccount(any(CoinAccount.class));
    }

    /** dùng ArgumentCaptor để test void method
     *
     * Như ví dụ bên dưới thì chúng ta captor coinAccount để xem sau khi gọi createCoinAccount của service 
     * thì object này thay đổi như thế nào. 
     * Cụ thể là ở đây khi ta ta gọi createCoinAccount ở service thì createdDate được set value
     * 
     * Vậy khi captor đc object sau khi gọi service 
     * 		ta chỉ cần verify xem các thuộc tính của object này có thay đổi đúng như ta mong muốn không hay thôi.
     */
    @Test
    public void testCreateCoinAccountByArgumentCaptor() {

        // Requirement: we want to register a new CoinAccount. Every new CoinAccount
        // should be set createdDate before saving in the database.
        coinAccountService.createCoinAccount(new CoinAccount());

        // captures the argument which was passed in to save method.
        verify(coinAccountDAO).createCoinAccount(coinAccountArgumentCaptor.capture());

        // make sure createdDate is assigned by the createCoinAccount method before saving.
        assertThat(coinAccountArgumentCaptor.getValue().getCreatedDate(), is(notNullValue()));

    }

}
