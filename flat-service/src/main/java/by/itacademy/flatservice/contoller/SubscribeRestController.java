package by.itacademy.flatservice.contoller;

import by.itacademy.flatservice.core.SubscribeDTO;
import by.itacademy.flatservice.core.SubscribeFilterDTO;
import by.itacademy.flatservice.service.api.ISubscribeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/subscribe")
public class SubscribeRestController {
    private final ISubscribeService subscribeService;

    public SubscribeRestController(ISubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody SubscribeFilterDTO subscribeFilterDTO) {
        subscribeService.add(subscribeFilterDTO);
        return ResponseEntity.status(201).body("Subscription added successfully.");
    }

    @GetMapping
    public ResponseEntity<SubscribeDTO> get() {
        return ResponseEntity.ok(subscribeService.get());
    }
}
