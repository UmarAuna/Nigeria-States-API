package com.bucketlist.bucketlistclient.countrylist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class CountryListController {

    @Autowired
    CountryRepository countryRepository;
    @GetMapping(value = "/api/country/")
    public ResponseEntity index(){
        return ResponseEntity.ok(countryRepository.findAll());
    }

    @GetMapping(value = "/api/country/state/")
    public ResponseEntity getCountry(@RequestParam(value = "id") Long id){
        Optional<CountryList> foundCountryList = countryRepository.findById(id);

        if (foundCountryList.isPresent()){
            return ResponseEntity.ok(foundCountryList.get());
        }else{
            return ResponseEntity.badRequest().body("No country with specified id " + id + " found");
        }
    }

    @PostMapping(value = "/api/country/")
    public ResponseEntity addToCountryList(@Validated @RequestParam(value = "state", required = true) String state,
                                           @Validated @RequestParam(value = "capital", required = true) String capital){
        return ResponseEntity.ok(countryRepository.save(new CountryList(state,capital)));

    }

    @PutMapping(value = "/api/country/")
    public ResponseEntity updateCountryList(@RequestParam(value = "state") String state,
                                            @RequestParam(value = "id") Long id,
                                            @RequestParam(value = "capital") String capital){
        Optional<CountryList> optionalCountryList = countryRepository.findById(id);
        if (!optionalCountryList.isPresent()){
            return ResponseEntity.badRequest().body("No bucket with specified id " + id + " found");
        }

        CountryList foundCountryList = optionalCountryList.get();
        foundCountryList.setCapital(capital);
        foundCountryList.setState(state);

        return ResponseEntity.ok(countryRepository.save(foundCountryList));
    }

    @DeleteMapping(value = "/api/country/")
    public ResponseEntity removeCountry(@RequestParam(value = "id") Long id){
        countryRepository.deleteById(id);
        return ResponseEntity.badRequest().body("Deleted Successfully");
    }
}
