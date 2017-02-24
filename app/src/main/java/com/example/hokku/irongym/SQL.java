package com.example.hokku.irongym;

public class SQL {

    private int _id;
    private String _exercise;
    private String _weight;
    private String _reps;

    public SQL(){

    }

    public SQL(String exercise) {
        this._exercise = exercise;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_exercise(String _exercise) {
        this._exercise = _exercise;
    }

    public void set_weight(String _weight) {
        this._weight = _weight;
    }

    public void set_reps(String _reps) {
        this._reps = _reps;
    }



    public int get_id() {
        return _id;
    }

    public String get_exercise() {
        return _exercise;
    }

    public String get_weight() {
        return _weight;
    }

    public String get_reps() {
        return _reps;
    }
}
