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
import com.xc.easyMock.lesson.UserService;
import junit.framework.TestCase;
import org.easymock.EasyMock;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

/**
 *  base test.
 *
 *  @author xiachuan at 2017/3/14 16:52。
 */

public class UserServiceTest_1 extends TestCase {

    public void testGetById() throws Exception {
        UserDao dao = EasyMock.createMock(UserDao.class);

        User user = new User();
        user.setId("001");
        user.setName("lily");

        expect(dao.getById("001")).andDelegateTo(user);
        replay(dao);

    }
}

