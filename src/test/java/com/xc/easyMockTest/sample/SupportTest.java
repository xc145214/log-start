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
package com.xc.easyMockTest.sample;

import com.xc.easyMock.sample.ClassTested;
import com.xc.easyMock.sample.Collaborator;
import org.easymock.EasyMockSupport;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;

/**
 *
 *
 *  @author xiachuan at 2016/10/19 18:47。
 */

public class SupportTest extends EasyMockSupport {

    private Collaborator collaborator;

    private ClassTested classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new ClassTested();
    }

    @After
    public void tearDown() throws Exception {
        verifyAll();
    }

    @Test
    public void testAddDocument() throws Exception {
        collaborator = mock(Collaborator.class);

        classUnderTest.setListener(collaborator);
        collaborator.documentAdded("New Document");
        replayAll();
        classUnderTest.addDocument("New Document", "content");
    }

    @Test
    public void voteForRemovals() {

        IMocksControl ctrl = createControl();
        collaborator = ctrl.createMock(Collaborator.class);
        classUnderTest.setListener(collaborator);

        collaborator.documentAdded("Document 1");

        expect(collaborator.voteForRemovals("Document 1")).andReturn((byte) 20);

        collaborator.documentRemoved("Document 1");

        replayAll();

        classUnderTest.addDocument("Document 1", "content");
        assertTrue(classUnderTest.removeDocuments("Document 1"));

        verifyAll();
    }
}

