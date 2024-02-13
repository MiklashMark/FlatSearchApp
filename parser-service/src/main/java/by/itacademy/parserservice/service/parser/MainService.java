package by.itacademy.parserservice.service.parser;

import by.itacademy.flatservice.repository.api.IPhotoRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableScheduling
public class MainService {
    private final RealtByParser realtByParser;

    private final IPhotoRepository photoRepo;
    private final SaveService saveService;

    public MainService(RealtByParser realtByParser, IPhotoRepository photoRepo, SaveService saveService) {
        this.realtByParser = realtByParser;
        this.photoRepo = photoRepo;
        this.saveService = saveService;
    }

    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    @Transactional
    public void realByParser() {
        System.out.println("start realt.by parsing");
        saveService.save();
        realtByParser.startFlatRentForDayParsing();
        realtByParser.startFlatRentForLongParsing();
        realtByParser.startFlatSalesParsing();
        photoRepo.removeDuplicatePhotos();
        System.out.println("end");
    }
}

