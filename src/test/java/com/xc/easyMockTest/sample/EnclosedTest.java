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
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  Test demonstrating the use of JUnit 4.12 new enclosed feature
 *
 *  @author xiachuan at 2017/3/13 17:02。
 */
@RunWith(Enclosed.class)
public class EnclosedTest {

    @RunWith(EasyMockRunner.class)
    public static abstract class Fixtures extends EasyMockSupport {

        @TestSubject
        ClassTested classUnderTest = new ClassTested();

        @Mock
        Collaborator mock;
    }

    public static class VoteRemoval extends Fixtures {

        @Before
        public void when() {
            // expect document addition
            mock.documentAdded("Document");
        }

        @Test
        public void voteForRemoval() {
            // expect to be asked to vote, and vote for it
            expect(mock.voteForRemoval("Document")).andReturn((byte) 42);
            // expect document removal
            mock.documentRemoved("Document");

            replayAll();
            classUnderTest.addDocument("Document", "content");
            assertTrue(classUnderTest.removeDocument("Document"));
            verifyAll();
        }

        @Test
        public void voteAgainstRemoval() {
            // expect to be asked to vote, and vote against it
            expect(mock.voteForRemoval("Document")).andReturn((byte) -42); //
            // document removal is *not* expected

            replayAll();
            classUnderTest.addDocument("Document", "content");
            assertFalse(classUnderTest.removeDocument("Document"));
            verifyAll();
        }
    }

    public static class VoteRemovals extends Fixtures {

        @Before
        public void when() {
            mock.documentAdded("Document 1");
            mock.documentAdded("Document 2");
        }

        @Test
        public void voteForRemovals() {
            expect(mock.voteForRemovals("Document 1", "Document 2")).andReturn((byte) 42);
            mock.documentRemoved("Document 1");
            mock.documentRemoved("Document 2");
            replayAll();
            classUnderTest.addDocument("Document 1", "content 1");
            classUnderTest.addDocument("Document 2", "content 2");
            assertTrue(classUnderTest.removeDocuments("Document 1", "Document 2"));
            verifyAll();
        }

        @Test
        public void voteAgainstRemovals() {
            expect(mock.voteForRemovals("Document 1", "Document 2")).andReturn((byte) -42);
            replayAll();
            classUnderTest.addDocument("Document 1", "content 1");
            classUnderTest.addDocument("Document 2", "content 2");
            assertFalse(classUnderTest.removeDocuments("Document 1", "Document 2"));
            verifyAll();
        }
    }
}


