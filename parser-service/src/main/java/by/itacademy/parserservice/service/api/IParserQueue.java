package by.itacademy.parserservice.service.api;

import by.itacademy.parserservice.repository.entity.Flat;

import java.util.concurrent.BlockingQueue;

public interface IParserQueue {
    void add(Flat flat);
    Flat get();
    BlockingQueue<Flat> getQueue();
}
