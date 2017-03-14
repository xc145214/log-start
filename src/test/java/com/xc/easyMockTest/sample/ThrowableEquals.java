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

import org.easymock.IArgumentMatcher;

import static org.easymock.EasyMock.reportMatcher;

/**
 *  XXXXXXXXXXXXXXXXXXXXX
 *
 *  @author xiachuan at 2017/3/14 10:43。
 */

public class ThrowableEquals implements IArgumentMatcher {

    private final Throwable expected;

    public ThrowableEquals(Throwable expected) {
        this.expected = expected;
    }

    public boolean matches(Object actual) {
        if (!(actual instanceof Throwable)) {
            return false;
        }
        String actualMessage = ((Throwable) actual).getMessage();
        return expected.getClass().equals(actual.getClass()) && expected.getMessage().equals(actualMessage);
    }


    public void appendTo(StringBuffer buffer) {
        buffer.append("<");
        buffer.append(expected.getClass().getName());
        buffer.append(" with message \"");
        buffer.append(expected.getMessage());
        buffer.append("\">");
    }

    public static <T extends Throwable> T eqException(T in) {
        reportMatcher(new ThrowableEquals(in));
        return in;
    }
}

