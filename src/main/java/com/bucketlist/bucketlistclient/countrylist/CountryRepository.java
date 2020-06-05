package com.bucketlist.bucketlistclient.countrylist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Dao
public interface CountryRepository extends JpaRepository<CountryList, Long> {

}
