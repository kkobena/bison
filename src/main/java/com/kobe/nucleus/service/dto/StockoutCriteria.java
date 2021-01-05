package com.kobe.nucleus.service.dto;

import java.io.Serializable;
import java.util.Objects;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;

/**
 * Criteria class for the {@link com.kobe.nucleus.domain.Stockout} entity. This class is used
 * in {@link com.kobe.nucleus.web.rest.StockoutResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /stockouts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class StockoutCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter mvtDay;

    private IntegerFilter qty;

    private LongFilter produitId;

    public StockoutCriteria() {
    }

    public StockoutCriteria(StockoutCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.mvtDay = other.mvtDay == null ? null : other.mvtDay.copy();
        this.qty = other.qty == null ? null : other.qty.copy();
        this.produitId = other.produitId == null ? null : other.produitId.copy();
    }

    @Override
    public StockoutCriteria copy() {
        return new StockoutCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getMvtDay() {
        return mvtDay;
    }

    public void setMvtDay(LocalDateFilter mvtDay) {
        this.mvtDay = mvtDay;
    }

    public IntegerFilter getQty() {
        return qty;
    }

    public void setQty(IntegerFilter qty) {
        this.qty = qty;
    }

    public LongFilter getProduitId() {
        return produitId;
    }

    public void setProduitId(LongFilter produitId) {
        this.produitId = produitId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StockoutCriteria that = (StockoutCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(mvtDay, that.mvtDay) &&
            Objects.equals(qty, that.qty) &&
            Objects.equals(produitId, that.produitId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        mvtDay,
        qty,
        produitId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockoutCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (mvtDay != null ? "mvtDay=" + mvtDay + ", " : "") +
                (qty != null ? "qty=" + qty + ", " : "") +
                (produitId != null ? "produitId=" + produitId + ", " : "") +
            "}";
    }

}
