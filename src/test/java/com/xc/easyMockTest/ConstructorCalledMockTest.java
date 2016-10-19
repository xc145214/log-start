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

import java.math.BigDecimal;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

/**
 *  Example of how to partial mock with actually calling a constructor.
 *
 *  @author xiachuan at 2016/10/19 16:14。
 */

public class ConstructorCalledMockTest   extends EasyMockSupport {

    public static abstract class TaxCalculator{

        private final BigDecimal[] values;

        public TaxCalculator(BigDecimal... values){
        this.values = values;
        }

        protected  abstract BigDecimal rate();

        public BigDecimal tax(){
            BigDecimal result = BigDecimal.ZERO;

            for (BigDecimal d:values){
                result = result.add(d);
            }

            return result.multiply(rate());
        }
    }

    private TaxCalculator tc;

    @Before
    public void setUp() throws Exception {

        /**
         * 1.创建Mock对象。
         */
        tc = partialMockBuilder(TaxCalculator.class)
                .withConstructor(BigDecimal[].class)
                .withArgs((Object) new BigDecimal[]{new BigDecimal("5"), new BigDecimal("15")}) // varargs are special since they are in fact arrays
                .createMock(); // no need to mock any methods, abstract ones are mocked by default
    }

    @After
    public void tearDown() throws Exception {
        /**
         * 4.执行验证。
         */
        verifyAll();
    }

    @Test
    public void testTax() throws Exception {

        /**
         * 2.记录Mock对象期望的行为。
         */
        expect(tc.rate()).andStubReturn(new BigDecimal("0.20"));

        /**
         * 3.创建对象，执行方法。
         */
        replayAll();

        assertEquals(new BigDecimal("4.00"), tc.tax());

    }

    @Test
    public void testTax_ZeroRate() {

        expect(tc.rate()).andStubReturn(BigDecimal.ZERO);
        replayAll();

        assertEquals(BigDecimal.ZERO, tc.tax());
    }
}

