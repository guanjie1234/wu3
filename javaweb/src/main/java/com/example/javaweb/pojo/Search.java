package com.example.javaweb.pojo;

public class Search {
    private String searchvalue; //搜索
    private int recordsperpage;//每页记录数
    private int page;//页码

    public String getSearchvalue() {
        return searchvalue;
    }

    public void setSearchvalue(String searchvalue) {
        this.searchvalue = searchvalue;
    }

    public int getRecordsperpage() {
        return recordsperpage;
    }

    public void setRecordsperpage(int recordsperpage) {
        this.recordsperpage = recordsperpage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
