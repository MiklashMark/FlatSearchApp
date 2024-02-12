package by.itacademy.flatservice.service.parser;

import by.itacademy.flatservice.repository.api.IFlatRepository;
import by.itacademy.flatservice.repository.entity.Flat;
import by.itacademy.flatservice.service.parser.api.ISaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.*;

@Service
@Slf4j
public class SaveService implements ISaveService {

    private final IFlatRepository flatRepo;
    private final BlockingQueue<Flat> allFlats = new LinkedBlockingQueue<>();

    public SaveService(IFlatRepository flatRepo) {
        this.flatRepo = flatRepo;
    }

    public void putIntoSaveQueue(Flat flatEntity) {
        try {
            allFlats.put(flatEntity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Async
    @Transactional
    @Override
    public void save() {
        while (true) {
            try {
                Flat flatEntity = allFlats.poll(10, TimeUnit.SECONDS);
                if (flatEntity == null) {
                    System.err.println("end of saving thread");
                    break;
                }
                Flat existingEntity = flatRepo.findById(flatEntity.getOriginalUrl()).orElse(null);

                if (existingEntity == null) {
                    flatRepo.save(flatEntity);
                    log.info("save");
                    continue;
                }

                if (!existingEntity.getPrice().equals(flatEntity.getPrice())) {
                    flatRepo.save(flatEntity);
                    log.info("update");
                }

            } catch (Throwable e) {
                System.err.println("2" + e.getMessage());
            }
        }
    }
}

