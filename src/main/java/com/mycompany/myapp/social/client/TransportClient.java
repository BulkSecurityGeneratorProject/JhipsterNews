package com.mycompany.myapp.social.client;

import java.io.File;
import java.io.IOException;

public interface TransportClient {
    ClientResponse get(String var1) throws IOException;

    ClientResponse post(String var1, String var2) throws IOException;

    ClientResponse post(String var1, String var2, File var3) throws IOException;

    ClientResponse post(String var1, String var2, String var3) throws IOException;

    ClientResponse get(String var1, String var2) throws IOException;

    ClientResponse post(String var1) throws IOException;

    ClientResponse delete(String var1) throws IOException;

    ClientResponse delete(String var1, String var2) throws IOException;

    ClientResponse delete(String var1, String var2, String var3) throws IOException;
}
