package br.com.mentaltech.utils;


import br.com.mentaltech.usecase.domain.MessageDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PageCollector<T> {

    private final Integer pageNumber;
    private final Integer pageSize;

    private PageCollector(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public static <T> PageCollector<T> of(Integer pageNumber, Integer pageSize) {
        return new PageCollector<>(pageNumber, pageSize);
    }

    public <T extends MessageDomain> Collector<T, ?, Page<T>> toPage() {
        return Collectors.collectingAndThen(Collectors.toList(), list -> {
            int start = pageNumber * pageSize;
            int end = Math.min(start + pageSize, list.size());
            return new PageImpl<>(list.subList(start, end), PageRequest.of(pageNumber, pageSize), list.size());
        });
    }
}