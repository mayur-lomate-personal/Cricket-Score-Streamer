package com.mayur.ServerSentEvents.EventServer.controllers;

import com.mayur.ServerSentEvents.EventServer.models.LiveCricketScore;
import com.mayur.ServerSentEvents.EventServer.services.LiveCricketScoreHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class LiveCricketScoreController {

    @Autowired
    private LiveCricketScoreHandler processor;

    @PostMapping("/live-scores")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<LiveCricketScore> send(@RequestBody LiveCricketScore liveCricketScore) {
        log.info("Received '{}'", liveCricketScore);
        processor.publish(liveCricketScore);
        return Mono.just(liveCricketScore);
    }

    @GetMapping(path = "/live-scores", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Object>> consumer() {
        return Flux.create(sink -> processor.subscribe(sink::next)).map(
                liveScore -> ServerSentEvent.builder().data(liveScore).event("score").build());
    }

}
