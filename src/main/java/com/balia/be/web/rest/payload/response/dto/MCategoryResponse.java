package com.balia.be.web.rest.payload.response.dto;

import com.balia.be.domain.MCategories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MCategoryResponse {
    private Long id;
    private String name;
    private Integer status;
    private List<MCategoryResponse> child;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MCategoryResponse> getChild() {
        return child;
    }

    public void setChild(List<MCategoryResponse> child) {
        this.child = child;
    }

    public MCategoryResponse(Long id, String name, Integer status, List<MCategoryResponse> child) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.child = child;
    }

    public static MCategoryResponse fromEntity(MCategories category) {
        List<MCategoryResponse> children = category.getChildren() != null
                ? category.getChildren().stream()
                .map(MCategoryResponse::fromEntity)
                .collect(Collectors.toList())
                : new ArrayList<>();
        return new MCategoryResponse(category.getId(), category.getName(), category.getStatus(), children);
    }
}
