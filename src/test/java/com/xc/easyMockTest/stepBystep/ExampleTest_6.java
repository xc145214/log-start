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
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

/**
 *  EasyMockSupport
 *
 *  @author xiachuan at 2017/3/14 11:14。
 */

public class ExampleTest_6 extends EasyMockSupport {

    private Collaborator firstCollaborator;
    private Collaborator secondCollaborator;
    private ClassTested classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new ClassTested();
    }

    @Test
    public void addDocument() {
        // creation phase
        firstCollaborator = mock(Collaborator.class);
        secondCollaborator = mock(Collaborator.class);
        classUnderTest.setListener(firstCollaborator);
        classUnderTest.setListener(secondCollaborator);

        // recording phase
        firstCollaborator.documentAdded("New Document");
        secondCollaborator.documentAdded("New Document");
        replayAll(); // replay all mocks at once

        // test
        classUnderTest.addDocument("New Document", new byte[0].toString());
        verifyAll(); // verify all mocks at once
    }

}

