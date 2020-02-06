package com.senacor.tecco.ilms.katas.common.service;

import org.springframework.stereotype.Service;

@Service
public class ExceptionThrowingService {

    public <T extends RuntimeException> void throwException(T exception) {
        throw exception;
    }

}
