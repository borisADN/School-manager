package org.example.school_manager.Interfaces;

import org.example.school_manager.models.Students;

import java.util.List;

public interface StudentInterface {
    void create(Students student) ;
    List<Students> read(int id);
    void update(Students student);
    void delete(int id);
}
