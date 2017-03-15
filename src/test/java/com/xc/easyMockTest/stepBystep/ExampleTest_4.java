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
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

import static org.easymock.EasyMock.replay;

/**
 * junit runner
 *
 * @author xiachuan at 2017/3/14 11:06。
 */

public class ExampleTest_4 {

    @Rule
    public EasyMockRule mockRule = new EasyMockRule(this);

    @TestSubject
    private ClassTested classUnderTest = new ClassTested();

    @Mock
    private Collaborator mock;


    @Test
    public void testRemoveNonExistingDocument() throws Exception {
        // This call should not lead to any notification
        // of the Mock Object:
        replay(mock);
        classUnderTest.removeDocument("Does not exist");
    }
}

