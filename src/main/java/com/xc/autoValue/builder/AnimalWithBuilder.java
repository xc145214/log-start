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
package com.xc.autoValue.builder;

import com.google.auto.value.AutoValue;

/**
 * builder 模式
 *
 * @author xiachuan at 2016/5/16 13:59。
 */
@AutoValue
public abstract class AnimalWithBuilder {

    public abstract String name();

    public abstract int numberOfLegs();

    public static Builder builder() {
        return new AutoValue_AnimalWithBuilder.Builder();
    }

    @AutoValue.Builder
     abstract static class Builder {
        abstract Builder name(String value);

        abstract Builder numberOfLegs(int value);

        abstract AnimalWithBuilder build();
    }
}



