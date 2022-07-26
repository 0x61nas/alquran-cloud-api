package com.anas.alqurancloudapi;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public interface Mapper extends Serializable {
     ObjectMapper mapper = new ObjectMapper();

}
