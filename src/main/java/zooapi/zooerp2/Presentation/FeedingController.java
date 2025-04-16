package zooapi.zooerp2.Presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zooapi.zooerp2.Domain.DTO.Requests.FeedingRequest;
import zooapi.zooerp2.Domain.Entities.Animal;
import zooapi.zooerp2.Domain.Entities.Feeding;
import zooapi.zooerp2.Domain.Entities.FeedingSchedule;
import zooapi.zooerp2.Domain.Interfaces.Application.FeedingServiceI;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/feeding")
@RequiredArgsConstructor
@Tag(name = "Приемы пищи", description = "Управление расписанием приемов пищи")
public class FeedingController {
    private final FeedingServiceI feedingService;

    @GetMapping("/")
    @Operation(summary = "Получить расписание приемов пищи")
    public ResponseEntity<ArrayList<Feeding>> getFeedingSchedule(@Valid @RequestBody FeedingRequest request,
                                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        var ret = feedingService.getFeedingSchedule(request.getStartDate(), request.getEndDate());

        return ResponseEntity.status(HttpStatus.OK).body(ret);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Отметить следующее кормление в расписании как выполненное",
            description = "")
    public ResponseEntity<Animal> performFeeding(
            @PathVariable UUID id) {
        feedingService.feed(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
