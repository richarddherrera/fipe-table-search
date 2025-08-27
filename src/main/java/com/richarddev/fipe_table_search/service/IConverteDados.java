package com.richarddev.fipe_table_search.service;

import java.util.List;

public interface IConverteDados {
    <t> t obterDados(String json, Class<t> classe);

    <t> List<t> obterLista(String json, Class<t> classe);
}
