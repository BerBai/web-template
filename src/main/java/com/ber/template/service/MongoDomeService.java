package com.ber.template.service;

import com.ber.template.domain.MongoDome;

import java.util.List;

public interface MongoDomeService {
    List<MongoDome> getAll();

    Boolean addOrUpdate(MongoDome mongoDome);

    Boolean delete(MongoDome mongoDome);

    MongoDome getByName(String name);
}
