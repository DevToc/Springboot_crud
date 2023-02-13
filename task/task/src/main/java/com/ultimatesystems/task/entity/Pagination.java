package com.ultimatesystems.task.entity;

public class Pagination {

  public Integer pageNo;
  public Integer pageSize;
  public String sortKey;

  public Pagination(Integer pagen, Integer pages, String sortk) {
    this.pageNo = pagen;
    this.pageSize = pages;
    this.sortKey = sortk;
  }

}