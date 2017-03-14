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

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

/**
 *  Example of how to perform partial mocking
 *
 *  @author xiachuan at 2017/3/13 16:40。
 */

public class PartialClassMockTest extends EasyMockSupport {

    public static class Rect{
        private int x;

        private int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getArea() {
            return getX() * getY();
        }
    }

    private Rect rect;

    @Before
    public void setUp() throws Exception {
        rect = partialMockBuilder(Rect.class).addMockedMethods("getX", "getY").createMock();
    }

    @After
    public void tearDown() throws Exception {
        rect = null;
    }

    @Test
    public void testGetArea() throws Exception {
        expect(rect.getX()).andReturn(4);
        expect(rect.getY()).andReturn(5);
        replayAll();
        assertEquals(20, rect.getArea());
        verifyAll();
    }
}