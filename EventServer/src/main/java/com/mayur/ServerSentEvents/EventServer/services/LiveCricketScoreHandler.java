package com.mayur.ServerSentEvents.EventServer.services;

import com.mayur.ServerSentEvents.EventServer.models.LiveCricketScore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@Slf4j
@Service
public class LiveCricketScoreHandler {

    private final List<Consumer<LiveCricketScore>> listeners = new CopyOnWriteArrayList<>();

    public void subscribe(Consumer<LiveCricketScore> listener) {
        listeners.add(listener);
        log.info("New one added, total consumer: {}", listeners.size());
    }

    public void publish(LiveCricketScore liveCricketScore) {
        log.info("Processing live score: {}", liveCricketScore);
        listeners.forEach(listener -> listener.accept(liveCricketScore));
    }
}
