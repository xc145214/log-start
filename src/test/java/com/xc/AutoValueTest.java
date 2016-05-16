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

import com.xc.autoValue.builder.AnimalWithBuilder;
import com.xc.autoValue.sample.Animal;
import junit.framework.TestCase;


/**
 * 不可变类测试。
 *
 * @author xiachuan at 2016/5/16 10:24。
 */

public class AutoValueTest extends TestCase {


    public void testAnimal() throws Exception {
        Animal dog = Animal.create("dog", 4);
//        assertEquals("dog", dog.name());
//        assertEquals(4, dog.numberOfLegs());

        // You probably don't need to write assertions like these; just illustrating.
        assertTrue(Animal.create("dog", 4).equals(dog));
        assertFalse(Animal.create("cat", 4).equals(dog));
        assertFalse(Animal.create("dog", 2).equals(dog));

        assertEquals("Animal{name=dog, numberOfLegs=4}", dog.toString());

    }

    public void testBuilder() throws Exception {
        AnimalWithBuilder dog = AnimalWithBuilder
                .builder()
                .name("dog")
                .numberOfLegs(4)
                .build();
        assertEquals("dog", dog.name());
        assertEquals(4, dog.numberOfLegs());

        // You probably don't need to write assertions like these; just illustrating.
        assertTrue(AnimalWithBuilder.builder().name("dog").numberOfLegs(4).build().equals(dog));
        assertFalse(AnimalWithBuilder.builder().name("cat").numberOfLegs(4).build().equals(dog));
        assertFalse(AnimalWithBuilder.builder().name("dog").numberOfLegs(2).build().equals(dog));

        assertEquals("Animal{name=dog, numberOfLegs=4}", dog.toString());

    }
}

