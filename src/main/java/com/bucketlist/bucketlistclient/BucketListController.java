package com.bucketlist.bucketlistclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BucketListController {

    @Autowired
    BucketRepository bucketRepository;

    @GetMapping(value = "/api/")
    public ResponseEntity index() {
        return ResponseEntity.ok(bucketRepository.findAll());
    }

    @GetMapping(value = "/api/bucket")
    public ResponseEntity getBucket(@RequestParam(value="id") Long id) {
        Optional<BucketList> foundBucketList = bucketRepository.findById(id);

        if(foundBucketList.isPresent()){
            return ResponseEntity.ok(foundBucketList.get());
        }else {
            return ResponseEntity.badRequest().body("No bucket with specified id " + id + " found");
        }
    }

    @PostMapping(value = "/api/")
    public ResponseEntity addToBucketList(@RequestParam(value="name") String name, @RequestParam(value="description") String desc) {
        return ResponseEntity.ok(bucketRepository.save(new BucketList(name, desc)));
    }

    @PutMapping(value = "/api/")
    public ResponseEntity updateBucketList(@RequestParam(value="name") String name, @RequestParam(value="id") Long id, @RequestParam(value="description") String desc) {
        Optional<BucketList> optionalBucketList = bucketRepository.findById(id);
        if(!optionalBucketList.isPresent()){
            return ResponseEntity.badRequest().body("No bucket with specified id " + id + " found");
        }

        BucketList foundBucketList = optionalBucketList.get();
        foundBucketList.setName(name);
        foundBucketList.setDescription(desc);

        return ResponseEntity.ok(bucketRepository.save(foundBucketList));
    }

    @DeleteMapping(value = "/api/")
    public ResponseEntity removeBucketList(@RequestParam(value="id") Long id) {
        bucketRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}