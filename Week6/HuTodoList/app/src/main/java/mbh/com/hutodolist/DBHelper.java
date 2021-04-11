package mbh.com.hutodolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mbh.com.hutodolist.ui.main.Todo;

public final class DBHelper {
    //Set up your columns names and indexes
    private static final String LOGTAG = "DBHelper";
    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tododata";

    public static final String KEY_ID= "_id";
    public static final String KEY_TITLE= "title";
    public static final String KEY_DESCRIPTION = "name";
    public static final String KEY_DUEDATE = "location";
    public static final String KEY_ADDITIONAL = "information";
    public static final String KEY_TYPE = "type";

    public static final int COLUMN_ID = 0;
    public static final int COLUMN_TITLE = 1;
    public static final int COLUMN_DESCRIPTION = 2;
    public static final int COLUMN_DUEDATE = 3;
    public static final int COLUMN_ADDITIONAL = 4;
    public static final int COLUMN_TYPE = 5;
    // Column Names

    private Context context;
    private SQLiteDatabase db;
    //Set up statements to allow inserting of data into the database.
    private SQLiteStatement insertStmt;
    private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(" +
            KEY_TITLE + ", " +
            KEY_DESCRIPTION + ", " +
            KEY_DUEDATE + ", " +
            KEY_ADDITIONAL +", " +
            KEY_TYPE + ") values (?, ?, ?, ?, ?)";

    public DBHelper (Context context) throws Exception {
        this.context = context;
        try {
            OpenHelper openHelper = new OpenHelper(this.context); // Open a database for reading and writing
            db = openHelper.getWritableDatabase();
            // compile a sqlite insert statement into re-usable statement object.
            insertStmt = db.compileStatement(INSERT);
        } catch (Exception e) {
            Log.e(LOGTAG, " DBHelper constructor: could not get database " + e);
            throw (e);
        }
    }

    public long insert (Todo todoInfo) {
        // bind values to the pre-compiled SQL statement "inserStmt"
        insertStmt.bindString(COLUMN_TITLE, todoInfo.getTitle());
        insertStmt.bindString(COLUMN_DESCRIPTION, todoInfo.getDescription());
        insertStmt.bindString(COLUMN_DUEDATE, todoInfo.getDueDate());
        insertStmt.bindString(COLUMN_ADDITIONAL, todoInfo.getInformation());
        insertStmt.bindString(COLUMN_TYPE, todoInfo.getType());
        long value = -1;
        try {
            // Execute the sqlite statement.
            value = insertStmt.executeInsert();
        } catch (Exception e) {
            Log.e(LOGTAG, " executeInsert problem: " + e);
        }
        Log.d(LOGTAG, "value=" + value);
        return value;
    }

    public void deleteAll() {
        db.delete(TABLE_NAME, null, null);
    }

    // delete a row in the database
    public boolean deleteRecord(long rowId) {
        return db.delete(TABLE_NAME, KEY_ID + "=" + rowId, null) > 0;
    }

    // Creates a list of animal info retrieved from the sqlite database.
    public List<Todo> selectAll()
    {
        List<Todo> list = new ArrayList<Todo>();
        // query takes the following parameters
        // dbName : the table name
        // columnNames: a list of which table columns to return
        // whereClause: filter of selection of data; null selects all data
        // selectionArg: values to fill in the ? if any are in the whereClause
        // group by: Filter specifying how to group rows, null means no grouping
        // having: filter for groups, null means none
        // orderBy: Table columsn used to order the data, null means no order.
        // A Cursor provides read-write access to the result set returned by a database query.
        // A Cursor represents the result of the query and points to one row of the query result.
        Cursor cursor = db.query(TABLE_NAME,
                new String[] { KEY_ID, KEY_TITLE, KEY_DESCRIPTION, KEY_DUEDATE, KEY_ADDITIONAL, KEY_TYPE }, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do
            {
                Todo todoInfo = new Todo();
                todoInfo.setTitle(cursor.getString(COLUMN_TITLE));
                todoInfo.setDescription(cursor.getString(COLUMN_DESCRIPTION));
                todoInfo.setDueDate(cursor.getString(COLUMN_DUEDATE));
                todoInfo.setInformation(cursor.getString(COLUMN_ADDITIONAL));
                todoInfo.setType(cursor.getString(COLUMN_TYPE));
                todoInfo.setId(cursor.getLong(COLUMN_ID));
                list.add(todoInfo);
            }
            while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }


    // Helper class for DB creation/update
    // SQLiteOpenHelper provides getReadableDatabase() and getWriteableDatabase() methods
    // to get access to an SQLiteDatabase object; either in read or write mode.
    // A key defined as INTEGER PRIMARY KEY will autoincrement.
    private static class OpenHelper extends SQLiteOpenHelper {
        private static final String LOGTALOGTAGG = "OpenHelper";
        private static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME +
                "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_TITLE + " TEXT, " +
                KEY_DESCRIPTION + " TEXT, " + KEY_DUEDATE + " TEXT, " + KEY_ADDITIONAL + " TEXT, " + KEY_TYPE + " TEXT);";


        OpenHelper (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        /**
         * Creates the tables.

         } }
         }
         * This function is only run once or after every Clear Data
         */
        @Override
        public void onCreate (SQLiteDatabase db) {
            Log.d(LOGTAG, " onCreate");
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e ) {
                Log.e(LOGTAG, " onCreate: Could not create SQL database: " + e);
            }
        }
        /**
         * called, if the database version is increased in your application code.
         * This method updating an existing database schema or dropping the existing database * and recreating it via the onCreate() method.
         */
        @Override
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(LOGTAG, "Upgrading database, this will drop tables and recreate.");
            try {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
            } catch (Exception e) {
                Log.e(LOGTAG, " onUpgrade: Could not update SQL database: " + e);
            }
        }


    }
}
