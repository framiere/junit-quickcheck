/*
 The MIT License

 Copyright (c) 2010-2013 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.pholser.junit.quickcheck.internal;

import java.lang.annotation.Annotation;

import com.pholser.junit.quickcheck.generator.ValuesOf;
import org.junit.Test;

import static org.junit.Assert.*;

public class SampleSizerTest {
    enum Ternary {
        YES, NO, MAYBE
    }

    @Test public void primitiveBooleanWithValuesOfTrumpsSampleSize() {
        ParameterContext parameter = new ParameterContext(boolean.class);
        addValuesOf(parameter);

        SampleSizer sizer = new SampleSizer(5, parameter);

        assertEquals(2, sizer.sampleSize());
    }

    @Test public void wrapperBooleanWithValuesOfTrumpsSampleSize() {
        ParameterContext parameter = new ParameterContext(Boolean.class);
        addValuesOf(parameter);

        SampleSizer sizer = new SampleSizer(6, parameter);

        assertEquals(2, sizer.sampleSize());
    }

    @Test public void enumWithValuesOfTrumpsSampleSize() {
        ParameterContext parameter = new ParameterContext(Ternary.class);
        addValuesOf(parameter);

        SampleSizer sizer = new SampleSizer(7, parameter);

        assertEquals(3, sizer.sampleSize());
    }

    @Test public void otherClassesUseConfiguredSampleSize() {
        ParameterContext parameter = new ParameterContext(String.class);
        addValuesOf(parameter);

        SampleSizer sizer = new SampleSizer(8, parameter);

        assertEquals(8, sizer.sampleSize());
    }

    private void addValuesOf(ParameterContext parameter) {
        parameter.addConfiguration(ValuesOf.class, new ValuesOf() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return ValuesOf.class;
            }
        });
    }
}
