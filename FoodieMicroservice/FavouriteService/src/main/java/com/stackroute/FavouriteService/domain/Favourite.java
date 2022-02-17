package com.stackroute.FavouriteService.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
@Document
@Data
public class Favourite {
    @Id
    private String email;
    private List<Integer> restaurants;
    private List<Integer> cusines;
}
