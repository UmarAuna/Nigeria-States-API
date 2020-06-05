package com.bucketlist.bucketlistclient.countrylist;

import javax.persistence.*;

@Entity
@Table(name = "country_list")
public class CountryList {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "state", length = 60)
    private String state;

    @Column(name = "capital", length = 60)
    private  String capital;

    public CountryList() {
    }

    public CountryList(long id, String state, String capital) {
        this.id = id;
        this.state = state;
        this.capital = capital;
    }

    public CountryList(String state, String capital) {
        this.state = state;
        this.capital = capital;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "CountryList{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", capital='" + capital + '\'' +
                '}';
    }
}
