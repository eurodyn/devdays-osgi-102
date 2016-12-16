package com.devdays102.api;

import com.devdays102.api.dto.NameDTO;

import java.util.List;

public interface GreeterService {
    String greet(String name);
    List<NameDTO> getGreeters();
}
