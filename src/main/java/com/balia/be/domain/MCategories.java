package com.balia.be.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * A m_categories.
 */
@Entity
@Table(name = "m_categories")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCategories implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "parent_id") // Self-referencing foreign key
    private MCategories parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MCategories> children = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public MCategories getParent() {
        return parent;
    }

    public void setParent(MCategories parent) {
        this.parent = parent;
    }

    public List<MCategories> getChildren() {
        return children;
    }

    public void setChildren(List<MCategories> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MCategories mCategories = (MCategories) o;
        if (mCategories.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCategories.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCategories{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", createdBy='" + createdBy + '\'' + ", createdDate=" + createdDate + ", lastModifiedBy='" + lastModifiedBy + '\'' + ", lastModifiedDate=" + lastModifiedDate + ", status=" + status + '}';
    }
}
