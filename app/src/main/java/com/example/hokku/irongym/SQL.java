package com.example.hokku.irongym;

class SQL {

    private int _id;
    private static String _exercise;
    private static String _weight;
    private static String _reps;

    public void set_id(int _id) {
        this._id = _id;
    }

    static void set_exercise(String exercise) {
        _exercise = exercise;
    }

    static void set_weight(String weight) {
        _weight = weight;
    }

    static void set_reps(String reps) {
        _reps = reps;
    }



    public int get_id() {
        return _id;
    }

    static String getExercise() {
        return _exercise;
    }

    static String getWeight() {
        return _weight;
    }

    static String getReps() {
        return _reps;
    }
}
