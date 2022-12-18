package com.example.inditx.application.rest.controller;

import com.example.inditx.domain.Constants;
import com.example.inditx.domain.model.FareModel;
import com.example.inditx.domain.service.FareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class FareController {
    private static final Logger log = LoggerFactory.getLogger(FareController.class);

    @Autowired
    private final FareService fareService;

    public FareController(FareService fareService) {
        this.fareService = fareService;
    }

    @Operation(summary = "Get a fare according to the business rules")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Find the right fare",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = FareModel.class)) }),
        @ApiResponse(responseCode = "404", description = "No fares were found",
            content = @Content) })
    @PostMapping(
        value = {"/fare"},
        produces = {"application/json"},
        consumes = {"application/json"})
    public ResponseEntity<FareModel> getFare(@RequestBody FareModel fareModel) {
        log.debug("Entering with fareModel {}", fareModel);
        Optional<FareModel> filteredFareModelOpt = fareService.getFareByProductAndBrand(fareModel);
        if (filteredFareModelOpt.isEmpty()) {
            log.debug(Constants.MESSAGE_FARE_NOT_FOUND);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.MESSAGE_FARE_NOT_FOUND);
        }
        log.debug("Leaving");
        return new ResponseEntity<>(filteredFareModelOpt.get(), HttpStatus.OK);
    }

}
