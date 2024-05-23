package org.example.school_manager.Interfaces;

import org.example.school_manager.models.Classroom;

import java.util.List;

public interface ClassroomInterface {
    void create(Classroom classroom);
    List<Classroom> read(int id);
    void update(Classroom classroom);
    void delete(int id);
}
