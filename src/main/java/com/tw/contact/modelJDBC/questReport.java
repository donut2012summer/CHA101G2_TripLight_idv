package com.tw.contact.modelJDBC;

import java.util.List;

public interface questReport {
    public void insert(questReportVO qRVO);

    void delete(int id);

    public void update(questReportVO qRVO);

    public List<questReportVO> getAll();

}