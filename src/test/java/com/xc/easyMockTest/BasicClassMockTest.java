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
package com.xc.easyMockTest;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *  基本类模拟。
 *
 *  @author xiachuan at 2016/10/19 19:06。
 */

public class BasicClassMockTest extends EasyMockSupport {

    /**
     * Our nice class that is allowed to print
     */
    public static class Document {

        private final Printer printer;

        private String content;

        public Document(Printer printer) {
            this.printer = printer;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void print() {
            printer.print(content);
        }
    }

    /**
     * The 3rd party class to mock.
     */
    public static abstract class Printer {
        public abstract void print(String toPrint);
    }

    private Printer printer;

    private Document document;



    @Before
    public void setUp() {
        printer = mock(Printer.class);
        document = new Document(printer);
    }

    @After
    public void tearDown() {
        printer = null;
        document = null;
    }

    @Test
    public void testPrintContent() {
        printer.print("Hello world");
        replayAll();

        document.setContent("Hello world");
        document.print();

        verifyAll(); // make sure Printer.print was called
    }

    @Test
    public void testPrintEmptyContent() {
        printer.print("");
        replayAll();

        document.setContent("");
        document.print();

        verifyAll(); // make sure Printer.print was called
    }
}

