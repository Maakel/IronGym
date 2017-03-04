package com.example.hokku.irongym;

class SQL {

    private int _id;
    private static String _exercise;
    private static String _weight;
    private static String _reps;
    private static String _dateTime;
    private static Boolean _showHistory;


    public void setId(int _id) {
        this._id = _id;
    }

    static void setExercise(String exercise) {
        _exercise = exercise;
    }

    static void setWeight(String weight) {
        _weight = weight;
    }

    static void setReps(String reps) { _reps = reps; }

    static void setDateTime(String dateTime){
        _dateTime = dateTime;
    }

    public static void setShowHistory(Boolean showHistory) { _showHistory = showHistory; }



    public int getId() {
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

    static String getDateTime() {
        return _dateTime;
    }

    public static Boolean getShowHistory() { return _showHistory; }
}
