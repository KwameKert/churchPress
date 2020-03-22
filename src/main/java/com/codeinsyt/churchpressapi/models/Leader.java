package com.codeinsyt.churchpressapi.models;

import javax.persistence.*;

@Entity
@Table(name = "app_leader")
public class Leader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

}
