package com.example.restwsejbdemo.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ComputerResponse {
    private List<Computer> computers;

    public List<Computer> getBook() {
        return computers;
    }

    public void setBook(List<Computer> computers) {
        this.computers = computers;
    }
}
