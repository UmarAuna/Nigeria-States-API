package com.bucketlist.bucketlistclient.bucketlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

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
    public ResponseEntity addToBucketList(@Validated @RequestParam(value="name", required = true) String name,
                                          @Validated  @RequestParam(value="description", required = true) String desc) {

        return ResponseEntity.ok(bucketRepository.save(new BucketList(name, desc)));
    }


    @PutMapping(value = "/api/")
    public ResponseEntity updateBucketList( @RequestParam(value="name") String name, @RequestParam(value="id") Long id,  @RequestParam(value="description") String desc) {
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
        return ResponseEntity.badRequest().body("Deleted Successfully");
    }
}