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
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

/**
 *  Example of how to use {@code @Mock} and {@code @TestSubject} annotations with Junit Rule.
 *
 *  @author xiachuan at 2017/3/13 16:50。
 */

public class AnnotatedMockWithRuleTest extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    @Mock
    private Collaborator collaborator;

    @TestSubject
    private final ClassTested classUnderTest = new ClassTested();

    @Test
    public void addDocument() {
        collaborator.documentAdded("New Document");
        replayAll();
        classUnderTest.addDocument("New Document", "content");
        verifyAll();
    }
}
