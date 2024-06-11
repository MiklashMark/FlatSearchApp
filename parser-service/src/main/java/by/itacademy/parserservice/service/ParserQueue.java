package by.itacademy.parserservice.service;

import by.itacademy.parserservice.repository.entity.Flat;
import by.itacademy.parserservice.service.api.IParserQueue;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@Log4j2
public class ParserQueue implements IParserQueue {

    private final BlockingQueue<Flat> blockingQueue = new LinkedBlockingQueue<>(MAX_CAPACITY);
    private final static int MAX_CAPACITY = 5;

    @Override
    public void add(Flat flat) {
        while (true) {
            try {
                blockingQueue.put(flat);
                break;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("ADD-THREAD WAS INTERRUPTED!");
            }
        }
    }

    @Override
    public Flat get() {
        while (true) {
            try {
                return blockingQueue.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("GET-THREAD WAS INTERRUPTED!");
            }
        }
    }

    @Override
    public BlockingQueue<Flat> getQueue() {
        return blockingQueue;
    }
}
