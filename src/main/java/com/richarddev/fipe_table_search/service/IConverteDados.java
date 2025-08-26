package com.richarddev.fipe_table_search.service;

public interface IConverteDados {
    <t> t obterDados(String json, Class<t> classe);
}
