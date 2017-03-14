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
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Example of how to use {@code @Mock} and {@code @TestSubject} annotations with JUnit Runner.
 *
 * @author xiachuan at 2017/3/13 16:54。
 */
@RunWith(EasyMockRunner.class)
public class AnnotatedMockWithRunnerTest extends EasyMockSupport {

    @TestSubject
    private final ClassTested classUnderTested = new ClassTested();

    @Mock
    private Collaborator collaborator;

    @Test
    public void testAddDocument()  {
        collaborator.documentAdded("New Document");
        replayAll();
        classUnderTested.addDocument("New Document", "content");
        verifyAll();
    }
}


