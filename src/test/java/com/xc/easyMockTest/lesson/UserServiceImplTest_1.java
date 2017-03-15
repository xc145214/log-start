/**
 * **********************************************************************
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * <p/>
 * COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 * ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * ***********************************************************************
 */
package com.xc.easyMockTest.lesson;

import com.xc.easyMock.lesson.User;
import com.xc.easyMock.lesson.UserDao;
import com.xc.easyMock.lesson.UserServiceImpl;
import junit.framework.TestCase;
import org.easymock.EasyMock;

/**
 *  用户测试。
 *
 *  @author xiachuan at 2017/3/14 17:05。
 */

public class UserServiceImplTest_1 extends TestCase {

    public void testQuery() throws Exception {

        /**
         * Record: 创建Mock对象，并期望Mock对象被调用，同时给出我们希望的这个方法的结果。
         */
        User expectedUser = new User();
        expectedUser.setId("1001");
        expectedUser.setAge(30);
        expectedUser.setName("user-1001");


        UserDao userDao  = EasyMock.createMock(UserDao.class);
        EasyMock.expect(userDao.getById("1001")).andReturn(expectedUser);

/**
 * 我们关注的主要测试对象将被创建，之前在record阶段创建的相关依赖被关联到主要测试对象，然后执行被测试的方法，以模拟真实运行环境下主要测试对象的行为。
 */
        EasyMock.replay(userDao);

        UserServiceImpl  service = new UserServiceImpl();
        service.setUserDao(userDao);
        User user = service.query("1001");

        /**
         * 验证测试的结果和交互行为。
         */
        assertNotNull(user);
        assertEquals("1001", user.getId());
        assertEquals(30L, user.getAge().longValue());
        assertEquals("user-1001", user.getName());

        EasyMock.verify(userDao);

    }
}

