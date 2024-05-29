package com.jimmysalazar.springmetrics.controller;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salazar/metrics")
@Timed("salazar.timer") // a nivel de clase
public class TestMetrics {

    @Autowired
    private MeterRegistry registry;

    public static final Logger log = LoggerFactory.getLogger(TestMetrics.class);

    @GetMapping
    //@Timed("salazar.timer") // a nivel de método
    public ResponseEntity<String> get(){
        log.info("MeterRegistry used {}", registry.getClass().getName());
        //registry.counter("salazar.metrics").increment();

        // Sirve para medir un bloque de código en específico
        /**
        Timer timer = registry.timer("salazar.timer");
        timer.record(()->{
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){}
            log.info("Usless task");
        });
        **/
        return new ResponseEntity<String>("@salazarcodes", HttpStatus.OK);
    }

}
