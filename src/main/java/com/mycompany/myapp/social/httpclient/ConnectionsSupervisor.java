package com.mycompany.myapp.social.httpclient;

import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsSupervisor extends Thread {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionsSupervisor.class);
    private static final int FULL_CONNECTION_TIMEOUT_S = 60;
    private static final int WAIT_BEFORE_KILL_REQUEST_S = 10;
    private static final int CONNECTIONS_SUPERVISOR_WAIT_MS = 1000;
    private final Map<HttpUriRequest, Long> streams = new ConcurrentHashMap();

    ConnectionsSupervisor() {
        this.setDaemon(true);
        this.setName("Connections supervisor");
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var3) {
                ;
            }

            long time = Instant.now().getEpochSecond();
            this.streams.entrySet().stream().filter((entry) -> {
                return time > ((Long)entry.getValue()).longValue();
            }).forEach((entry) -> {
                HttpUriRequest request = (HttpUriRequest)entry.getKey();
                if (this.streams.containsKey(request)) {
                    LOG.error(String.format("HttpUriRequest killed after timeout (%d sec.) exceeded: %s", Integer.valueOf(60), request));
                    request.abort();
                    this.removeRequest(request);
                }

            });
        }
    }

    void addRequest(HttpUriRequest request) {
        this.streams.put(request, Instant.now().getEpochSecond() + 60L + 10L);
    }

    void removeRequest(HttpUriRequest request) {
        this.streams.remove(request);
    }
}
