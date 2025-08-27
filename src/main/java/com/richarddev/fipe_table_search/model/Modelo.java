package com.richarddev.fipe_table_search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
    public record Modelo(List<Dados> modelos) {
}
