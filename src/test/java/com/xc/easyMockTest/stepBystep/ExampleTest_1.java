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
import org.junit.Before;
import org.junit.Test;

/**
 *  this is the first Mock Object.
 *
 *  @author xiachuan at 2017/3/14 10:48。
 */

public class ExampleTest_1 {

    //class to test
    private ClassTested classUnderTest;

    //class to mock
    private Collaborator mock;


    @Before
    public void setUp() throws Exception {
        classUnderTest = new ClassTested();
        classUnderTest.setListener(mock);
    }


    @Test
    public void testRemoveNonExistingDocument() throws Exception {
        // This call should not lead to any notification
        // of the Mock Object:
        classUnderTest.removeDocument("Does not exist");
    }
}

