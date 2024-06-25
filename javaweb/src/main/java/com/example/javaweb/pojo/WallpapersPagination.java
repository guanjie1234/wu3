package com.example.javaweb.pojo;

public class WallpapersPagination {
  private int userid;//用户id
  private int recordsperpage;//每页记录数
  private int page;//页码

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRecordsPerPage() {
        return recordsperpage;
    }
    public void setRecordsperpage(int recordsperpage) {
        this.recordsperpage = recordsperpage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsperpage = recordsPerPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public WallpapersPagination(int userid, int recordsPerPage, int page) {
        this.userid = userid;
        this.recordsperpage = recordsPerPage;
        this.page = page;
    }

    public WallpapersPagination() {
    }

}
