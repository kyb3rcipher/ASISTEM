package com.kyb3r.asistem;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_BANDS = "bands";
    private static final String COLUMN_BANDS_COUNT = "bands_count";

    private static final String TABLE_COURSES = "courses";
    private static final String COLUMN_COURSES_NAME = "name";
    private static final String COLUMN_COURSES_PROGRESS = "progress";

    public DatabaseHelper(Context context) {
        super(context, "ASISTEM.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create bands table
        String createTableBandsQuery = "CREATE TABLE " + TABLE_BANDS + " (" +
                COLUMN_BANDS_COUNT + " INTEGER)";
        db.execSQL(createTableBandsQuery);

        // Create courses table
        String createTableCoursesQuery = "CREATE TABLE " + TABLE_COURSES + " (" +
                COLUMN_COURSES_NAME + " TEXT, " +
                COLUMN_COURSES_PROGRESS + " INTEGER)";
        db.execSQL(createTableCoursesQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);
    }

    // Methods for bands table
    public void setBandsCount(int bandsCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BANDS_COUNT, bandsCount);

        // Update the existing row with the new bands count
        int rowsAffected = db.update(TABLE_BANDS, values, null, null);

        // If no rows were affected, insert a new row
        if (rowsAffected == 0) {
            db.insert(TABLE_BANDS, null, values);
        }

        db.close();
    }


    public int getBandsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BANDS, null);

        int bandsCount = 0;
        if (cursor.moveToFirst()) {
            bandsCount = cursor.getInt(cursor.getColumnIndex(COLUMN_BANDS_COUNT));
        } else {
            bandsCount = 5;
        }

        cursor.close();
        db.close();
        return bandsCount;
    }

    public void addOneBand() {
        int bandsNumber = getBandsCount();
        if (bandsNumber < 5) {
            bandsNumber++;
            setBandsCount(bandsNumber);
        }
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
