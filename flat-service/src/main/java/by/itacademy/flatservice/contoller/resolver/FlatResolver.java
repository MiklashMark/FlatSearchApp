package by.itacademy.flatservice.contoller.resolver;

import by.itacademy.flatservice.core.FlatFilter;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlatResolver implements HandlerMethodArgumentResolver {
    private final String PAGE_PARAM = "page";
    private final String SIZE_PARAM = "size";
    private final String PRICE_FROM_PARAM = "price_from";
    private final String PRICE_TO_PARAM = "price_to";
    private final String BEDROOMS_FROM_PARAM = "bedrooms_from";
    private final String BEDROOMS_TO_PARAM = "bedrooms_to";
    private final String AREA_FROM_PARAM = "area_from";
    private final String AREA_TO_PARAM = "area_to";
    private final String FLOORS_PARAM = "floors";
    private final String PHOTO_PARAM = "photo";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return FlatFilter.class.isAssignableFrom(parameter.getNestedParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        String pageRaw = webRequest.getParameter(PAGE_PARAM);
        int page = (pageRaw != null && !pageRaw.isBlank()) ? Integer.parseInt(pageRaw) : 0;

        String sizeRaw = webRequest.getParameter(SIZE_PARAM);
        int size = (sizeRaw != null && !sizeRaw.isBlank()) ? Integer.parseInt(sizeRaw) : 20;

        String priceFromRaw = webRequest.getParameter(PRICE_FROM_PARAM);
        int priceFrom = (priceFromRaw != null && !priceFromRaw.isBlank())
                ? Integer.parseInt(priceFromRaw) : 0;

        String priceToRaw = webRequest.getParameter(PRICE_TO_PARAM);
        int priceTo = (priceToRaw != null && !priceToRaw.isBlank())
                ? Integer.parseInt(priceToRaw) : Integer.MAX_VALUE;

        String bedroomsFromRaw = webRequest.getParameter(BEDROOMS_FROM_PARAM);
        int bedroomsFrom = (bedroomsFromRaw != null && !bedroomsFromRaw.isBlank())
                ? Integer.parseInt(bedroomsFromRaw) : 0;

        String bedroomsToRaw = webRequest.getParameter(BEDROOMS_TO_PARAM);
        int bedroomsTo = (bedroomsToRaw != null && !bedroomsToRaw.isBlank())
                ? Integer.parseInt(bedroomsToRaw) : Integer.MAX_VALUE;

        String areaFromRaw = webRequest.getParameter(AREA_FROM_PARAM);
        int areaFrom = (areaFromRaw != null && !areaFromRaw.isBlank()) ? Integer.parseInt(areaFromRaw) : 0;
        String areaToRaw = webRequest.getParameter(AREA_TO_PARAM);
        int areaTo = (areaToRaw != null && !areaToRaw.isBlank()) ? Integer.parseInt(areaToRaw) : Integer.MAX_VALUE;

        String floorsRaw = webRequest.getParameter(FLOORS_PARAM);
        List<Integer> floors = (floorsRaw != null && !floorsRaw.isBlank())
                ? Arrays.stream(floorsRaw.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList()) : Collections.emptyList();

        String photoRaw = webRequest.getParameter(PHOTO_PARAM);
        boolean photo = photoRaw != null && !photoRaw.isBlank() && Boolean.parseBoolean(photoRaw);

        return new FlatFilter(page, size, priceFrom, priceTo,
                bedroomsFrom, bedroomsTo, areaFrom, areaTo, floors, photo);

    }
}
