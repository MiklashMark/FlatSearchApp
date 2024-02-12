package by.itacademy.flatservice.contoller;

import by.itacademy.exceptions.dto.flat.FlatPageDTO;
import by.itacademy.flatservice.core.FlatFilter;
import by.itacademy.flatservice.service.api.IFlatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flats")
public class FlatRestController {
    private final IFlatService service;

    public FlatRestController(IFlatService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<FlatPageDTO> get( FlatFilter filter)  {
        return new ResponseEntity<>(service.getPage(filter), HttpStatus.OK);
    }
}
