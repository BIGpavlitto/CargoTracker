package com.example.cargologger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Controller
public class StopwatchController {
    private long startTime = 0;
    private long elapsedTime = 0;
    private boolean running = false;

    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis() - elapsedTime;
            running = true;
        }
    }

    public void stop() {
        if (running) {
            elapsedTime = System.currentTimeMillis() - startTime;
            running = false;
        }
    }

    public void reset() {
        elapsedTime = 0;
        if (running) {
            startTime = System.currentTimeMillis();
        }
    }

    @GetMapping("/api/time")
    @ResponseBody
    public String getTime() {
        long currentTime = running ? System.currentTimeMillis() - startTime + elapsedTime : elapsedTime;
        long HH = TimeUnit.MILLISECONDS.toHours(currentTime);
        long MM = TimeUnit.MILLISECONDS.toMinutes(currentTime) % 60;
        long SS = TimeUnit.MILLISECONDS.toSeconds(currentTime) % 60;

        return String.format("%02d:%02d:%02d", HH, MM, SS);
    }
}
