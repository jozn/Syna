package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.Args;

@Immutable
public class ResponseServer implements HttpResponseInterceptor {
    private final String originServer;

    public ResponseServer() {
        this(null);
    }

    public ResponseServer(String str) {
        this.originServer = str;
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpResponse, "HTTP response");
        if (!httpResponse.containsHeader(HTTP.SERVER_HEADER) && this.originServer != null) {
            httpResponse.addHeader(HTTP.SERVER_HEADER, this.originServer);
        }
    }
}
