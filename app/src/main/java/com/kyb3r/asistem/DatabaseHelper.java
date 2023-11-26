package com.kyb3r.asistem;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_LIVES = "lives";
    private static final String COLUMN_LIVES_COUNT = "lives_count";

    private static final String TABLE_COURSES = "courses";
    private static final String COLUMN_COURSES_NAME = "name";
    private static final String COLUMN_COURSES_PROGRESS = "progress";

    public DatabaseHelper(Context context) {
        super(context, "ASISTEM.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create lives table
        String createTableLivesQuery = "CREATE TABLE " + TABLE_LIVES + " (" +
                COLUMN_LIVES_COUNT + " INTEGER)";
        db.execSQL(createTableLivesQuery);

        // Create courses table
        String createTableCoursesQuery = "CREATE TABLE " + TABLE_COURSES + " (" +
                COLUMN_COURSES_NAME + " TEXT, " +
                COLUMN_COURSES_PROGRESS + " INTEGER)";
        db.execSQL(createTableCoursesQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIVES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);
    }

    // Methods for lives table
    public void setLivesCount(int livesCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LIVES_COUNT, livesCount);

        // Update the existing row with the new lives count
        int rowsAffected = db.update(TABLE_LIVES, values, null, null);

        // If no rows were affected, insert a new row
        if (rowsAffected == 0) {
            db.insert(TABLE_LIVES, null, values);
        }

        db.close();
    }


    public int getLivesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_LIVES, null);

        int livesCount = 0;
        if (cursor.moveToFirst()) {
            livesCount = cursor.getInt(cursor.getColumnIndex(COLUMN_LIVES_COUNT));
        } else {
            livesCount = 5;
        }

        cursor.close();
        db.close();
        return livesCount;
    }

    // Methods for course table
    public boolean isCourseExists(String courseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_COURSES,
                null,
                COLUMN_COURSES_NAME + " = ?",
                new String[]{courseName},
                null,
                null,
                null
        );

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }

    public void addCourse(String courseName, int progress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSES_NAME, courseName);
        values.put(COLUMN_COURSES_PROGRESS, progress);
        db.insert(TABLE_COURSES, null, values);
        db.close();
    }

    public void setCourseProgress(String courseName, int progress) {
        // Max progress
        if (progress >= 4) { return; }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSES_PROGRESS, progress);

        String whereClause = COLUMN_COURSES_NAME + " = ?";
        String[] whereArgs = {courseName};

        db.update(TABLE_COURSES, values, whereClause, whereArgs);
        db.close();
    }

    public int getCourseProgress(String courseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int progress = 0;

        String selection = COLUMN_COURSES_NAME + " = ?";
        String[] selectionArgs = {courseName};

        Cursor cursor = db.query(
                TABLE_COURSES,
                new String[]{COLUMN_COURSES_PROGRESS},
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            progress = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSES_PROGRESS));
        }

        cursor.close();
        db.close();

        return progress;
    }
}
