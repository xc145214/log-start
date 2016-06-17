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
package com.xc;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * guava test
 *
 * @author xiachuan at 2016/4/30 11:29。
 */

public class GuavaTest extends TestCase {

    Logger LOG = LoggerFactory.getLogger(GuavaTest.class);


    public void testOptional() throws Exception {
        Optional<Integer> possible = Optional.of(5);
        LOG.info(" optional:{}", possible.isPresent());
        LOG.info("get:{}", possible.get());
    }

    public void testOptionalNull() throws Exception {
        Optional<Integer> possible = Optional.fromNullable(null);
        LOG.info(" optional:{}", possible.isPresent());
    }

    public void testCheck() throws Exception {
        LOG.info("check null {}", Preconditions.checkNotNull(new Integer(1)));
        LOG.info("check null {}", Preconditions.checkNotNull(null));
    }

    public void testObject() throws Exception {
        LOG.info("object equal:{}",Objects.equal("a","a"));
        LOG.info("object equal:{}",Objects.equal(null,"a"));
        LOG.info("object equal:{}",Objects.equal("a",null));
        LOG.info("object equal:{}",Objects.equal(null,null));
    }


}

