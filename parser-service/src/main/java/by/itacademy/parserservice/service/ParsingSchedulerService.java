package by.itacademy.parserservice.service;

import by.itacademy.exceptions.dto.flat.OfferType;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.InternalServerException;
import by.itacademy.parserservice.repository.api.IFlatRepository;
import by.itacademy.parserservice.repository.entity.Flat;
import by.itacademy.parserservice.service.api.IParserQueue;
import by.itacademy.parserservice.service.api.IParsingSchedulerService;
import by.itacademy.parserservice.service.api.IParsingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Service
@Log4j2
@Transactional(readOnly = true)
public class ParsingSchedulerService implements IParsingSchedulerService {
    private final IParsingService parsingServiceRealtBy;
    private final IFlatRepository flatRepository;
    private final IParserQueue parserQueue;
    private final static int threadsInPool = 4;

    public ParsingSchedulerService(IParsingService parsingServiceRealtBy,
                                   IFlatRepository flatRepository,
                                   IParserQueue parserQueue) {
        this.parsingServiceRealtBy = parsingServiceRealtBy;
        this.flatRepository = flatRepository;
        this.parserQueue = parserQueue;
    }

    @Override
    @Scheduled(fixedDelay = 86400)
    public void start() {
        CompletableFuture<Void> rentForLongFuture = CompletableFuture.runAsync(this::getRentForLongFlats);
        CompletableFuture<Void> saleFlatsFuture = CompletableFuture.runAsync(this::getSaleFlats);
        CompletableFuture<Void> rentForDayFlatsFuture = CompletableFuture.runAsync(this::getRentForDayFlats);

        CompletableFuture.allOf(rentForLongFuture, saleFlatsFuture, rentForDayFlatsFuture).join();
    }

    @Override
    public void getRentForLongFlats() {
        getFlat(OfferType.RENT);
    }

    @Override
    public void getSaleFlats() {
        getFlat(OfferType.SALE);
    }

    @Override
    public void getRentForDayFlats() {
        getFlat(OfferType.RENT_FOR_DAY);
    }

    @Override
    public void getFlat(OfferType offerType) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadsInPool);
        try {
            int pageCount = parsingServiceRealtBy.getPageCount(offerType);

            int pagesPerThread = pageCount / threadsInPool;
            int remainingPages = pageCount % threadsInPool;

            for (int i = 0; i < threadsInPool; i++) {
                int startPage = i * pagesPerThread + 1;
                int endPage = (i + 1) * pagesPerThread;

                if (i == threadsInPool - 1) {
                    endPage += remainingPages;
                }

                int finalEndPage = endPage;
                executorService.submit(() -> {
                    parsingServiceRealtBy.get(offerType, startPage, finalEndPage);
                });
            }
        } catch (Exception e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        } finally {
            shutdownExecutorsService(executorService);
        }
    }

    @Override
    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void save() {
        ExecutorService executorService = Executors.newFixedThreadPool(threadsInPool);
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Flat flat = parserQueue.get();

                if (flat == null) {
                    break;
                }

                try {
                    if (!flatRepository.existsByOriginalUrl(flat.getOriginalUrl())) {
                        flatRepository.save(flat);
                        log.info("SAVE " + flat.getOfferType() + " IN DB");
                    }
                } catch (DataAccessException e) {
                    throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
                }
            }
        } catch (Exception e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        } finally {
            shutdownExecutorsService(executorService);
        }
    }

    private void shutdownExecutorsService(ExecutorService executorService) {
        executorService.shutdown();
    }
}
