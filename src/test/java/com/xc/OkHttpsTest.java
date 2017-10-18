/*************************************************************************
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *
 *                COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 *    ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *************************************************************************/
package com.xc;

import com.sun.net.ssl.SSLContext;
import junit.framework.TestCase;
import okhttp3.*;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Collections;

/**
 * XXXXXXXXXXXXXXXXXXXXX
 *
 * @author xiachuan at 2017/8/2 16:47。
 */

public class OkHttpsTest extends TestCase {
    ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
            .build();

    OkHttpClient client1 = new OkHttpClient.Builder()
            .connectionSpecs(Collections.singletonList(spec))
            .build();

    /**
     * Https quest
     * @throws Exception
     */
    public void testHttpsGet() throws Exception {

        OkHttpClient  client = new OkHttpClient.Builder()
                .certificatePinner(new CertificatePinner.Builder()
                        .add("publicobject.com", "sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=")
                        .build())
                .build();
        Request request = new Request.Builder()
                .url("https://publicobject.com/robots.txt")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        for (Certificate certificate : response.handshake().peerCertificates()) {
            System.out.println(CertificatePinner.pin(certificate));
        }
    }


}

