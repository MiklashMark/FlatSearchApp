package by.itacademy.parserservice.service;

import by.itacademy.exceptions.dto.flat.OfferType;
import by.itacademy.exceptions.dto.flat.enums.HtmlTagRealtBy;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.RealtByParsingException;
import by.itacademy.parserservice.config.ParsingUrlConfig;
import by.itacademy.parserservice.repository.api.IFlatRepository;
import by.itacademy.parserservice.repository.entity.Flat;
import by.itacademy.parserservice.repository.entity.Photo;
import by.itacademy.parserservice.service.api.IParserQueue;
import by.itacademy.parserservice.service.api.IParsingService;
import by.itacademy.parserservice.service.utils.ParsingUtilsRealtBy;
import by.itacademy.parserservice.service.utils.UserAgentGenerator;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ParsingServiceRealtBy implements IParsingService {
    private final IFlatRepository flatRepository;
    private final ParsingUrlConfig parsingUrlConfig;
    private final ParsingUtilsRealtBy parsingUtilsRealtBy;
    private final UserAgentGenerator userAgentGenerator;
    private final IParserQueue parserQueue;
    private final static int flatsPerPage = 20;

    public ParsingServiceRealtBy(IFlatRepository flatRepository, ParsingUrlConfig parsingUrlConfig,
                                 ParsingUtilsRealtBy parsingUtilsRealtBy,
                                 UserAgentGenerator userAgentGenerator, IParserQueue parserQueue) {
        this.flatRepository = flatRepository;
        this.parsingUrlConfig = parsingUrlConfig;
        this.parsingUtilsRealtBy = parsingUtilsRealtBy;
        this.userAgentGenerator = userAgentGenerator;
        this.parserQueue = parserQueue;
    }


    private Flat setInformation(Document document,
                                String originalUrl,
                                OfferType offerType) {
        Flat flat = new Flat();
        flat.setCreateDate(LocalDateTime.now());
        flat.setUpdateDate(LocalDateTime.now());
        flat.setOriginalUrl(originalUrl);
        flat.setOfferType(offerType);

        Elements elements = document.select(HtmlTagRealtBy.RELATIVE_LI.getTagName());
        elements.forEach(e -> {
            Element spanElement = e.selectFirst(HtmlTagRealtBy.SPAN.getTagName());
            Element valueElement = e.selectFirst(HtmlTagRealtBy.P.getTagName());

            if (spanElement != null && valueElement != null) {
                String spanText = spanElement.text().trim();
                String valueText = valueElement.text().trim();
                switch (spanText) {
                    case "Количество комнат":
                        flat.setBedrooms(Integer.parseInt(valueText));
                        break;
                    case "Площадь общая":
                        flat.setArea(parsingUtilsRealtBy.formatArea(valueText));
                        break;
                    case "Этаж / этажность":
                        flat.setFloor(parsingUtilsRealtBy.formatFloor(valueText));
                        break;
                }
            }
        });
        Element priceElement = document.selectFirst(HtmlTagRealtBy.H2.getTagName());
        if (priceElement != null) {
            String price = priceElement.text();
            flat.setPrice(parsingUtilsRealtBy.formatPrice(price));
        }
        flat.setDescription(getDescription(document));
        List<String> photoUrls = getPhotoUrls(document);
        List<Photo> photos = new ArrayList<>();

        for (String u : photoUrls) {
            Photo photo = new Photo(u);
            photos.add(photo);
        }

        flat.setPhotos(photos);
        return flat;
    }

    private String getDescription(Document document) {
        Elements descriptionElement = document.select(HtmlTagRealtBy.DESCRIPTION_DIV.getTagName());
        String description = descriptionElement.text();
        if (description.isEmpty()) {
            Element noteElement = document.selectFirst(HtmlTagRealtBy.NOTE_DIV.getTagName());
            if (noteElement != null) {
                description = noteElement.text();
            }
        }
        return description;
    }

    private List<String> getPhotoUrls(Document document) {
        return document.select(HtmlTagRealtBy.IMG.getTagName()).stream()
                .map(img -> img.attr(HtmlTagRealtBy.SRC.getTagName()))
                .filter(src -> src.endsWith(".jpg"))
                .collect(Collectors.toList());
    }

    public int getFlatsCount(OfferType offerType) {
        return getPageCount(offerType) * flatsPerPage;
    }

    public int getPageCount(OfferType offerType) {
        try {
            Document document = Jsoup.connect(offerType.getValue())
                    .userAgent(userAgentGenerator.getRandomUserAgent())
                    .get();
            Elements pageLinks = document.select("a.cursor-pointer");

            int maxPage = 1;
            for (Element link : pageLinks) {
                String pageNumberStr = link.text();
                if (!pageNumberStr.isEmpty() && pageNumberStr.matches("\\d+")) {
                    int pageNumber = Integer.parseInt(pageNumberStr);
                    if (pageNumber > maxPage) {
                        maxPage = pageNumber;
                    }
                }
            }
            return maxPage;
        } catch (IOException e) {
            throw new RealtByParsingException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }


    @Override
    public void get(OfferType offerType, int startPage, int endPage) {
        for (int i = startPage; i <= endPage; i++) {
            try {
                Document document = Jsoup.connect(offerType.getValue() + "?page=" + i)
                        .userAgent(userAgentGenerator.getRandomUserAgent())
                        .get();
                Elements divsWithDataIndex = document.select("div[data-index]");
                divsWithDataIndex.stream().flatMap(div -> div.select("a.z-1").stream())
                        .forEach(link -> {
                            try {
                                Document flatDocument = Jsoup.connect(parsingUrlConfig.getBasic() + link.attr("href")).get();
                                Flat flat = setInformation(flatDocument, link.attr("href"), offerType);
                                parserQueue.add(flat);
                                log.info(offerType.getValue() + "FLAT WAS ADDED IN QUEUE");
                            } catch (IOException e) {
                                throw new RealtByParsingException(ErrorMessages.SERVER_ERROR.getMessage());
                            }
                        });
            } catch (IOException e) {
                throw new RealtByParsingException(ErrorMessages.SERVER_ERROR.getMessage());
            }
        }
    }

}


