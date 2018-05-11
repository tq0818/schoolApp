package com.yuxin.wx.model.institution;

public class ClassTypeOnlineFindVo {


    private Integer id; //课程id
    private String name;    //课程名
    private String companyName; //companyName
    private String subjectName; //科目名
    private Integer link;   //是否已经添加   link != null ? true : false
    private Integer rid;    //映射表逐渐id，用于删除在线课程关联用

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getLink() {
        return link;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
