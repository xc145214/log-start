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
package com.xc.easyMockTest.stepBystep;

import com.xc.easyMock.sample.ClassTested;
import com.xc.easyMock.sample.Collaborator;
import static org.easymock.EasyMock.*;
import org.junit.Before;
import org.junit.Test;

/**
 *  to get a mock Object;
 *  1. create a Mock Object for the interface we would like to simulate.
 *  2. record the expected behavior
 *  3. switch the mock object to repay state.
 *
 *  @author xiachuan at 2017/3/14 10:56。
 */

public class ExampleTest_2 {
    private ClassTested classUnderTest;
    private Collaborator mock;

    @Before
    public void setUp() throws Exception {
        mock = mock(Collaborator.class);

        classUnderTest = new ClassTested();
        classUnderTest.setListener(mock);
    }

    @Test
    public void testRemoveNonExistingDocument() throws Exception {

// 2 (we do not expect anything)
        replay(mock); // 3
        classUnderTest.removeDocument("Does not exist");
    }
}

