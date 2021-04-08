package com.example.service;


import com.example.Config.ServiceConfig;
import com.example.dao.LoginLogDao;
import com.example.dao.UserDao;
import com.example.domain.LoginLog;
import com.example.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.inject.Inject;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Mock
    private LoginLogDao loginLogDao;
    @Test
    public void hasMatchUser() {
        Mockito.when(userDao.getMatchCount("admin", "123456")).thenReturn(1);
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertFalse(b2);
    }


    @Test
    public void findUserByUserName() {
        Mockito.when(userDao.findUserByUserName("admin")).thenReturn(new User(1, "admin"));
        User user = userService.findUserByUserName("admin");
        assertEquals(user.getUserName(), "admin");
    }

    @Test
    public void loginSuccess() {
        User user = new User(1, "admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());

        LoginLog loginLog = new LoginLog();
        loginLog.setLoginLogId(1);
        loginLog.setUserId(1);
        loginLog.setIp("192.168.12.7");
        loginLog.setLoginDate(new Date());
        Mockito.when(loginLogDao.insertLoginLog(loginLog)).thenReturn(loginLog);
        assertTrue(userService.saveLog(user));


    }
}

