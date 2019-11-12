package com.packt.quarkus.chapter4;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeCustomerEndpointIT extends CustomerEndpointTest {

    // Execute the same tests but in native mode.
}