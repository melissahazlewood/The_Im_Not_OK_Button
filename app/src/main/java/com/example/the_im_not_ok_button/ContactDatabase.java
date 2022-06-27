package com.example.the_im_not_ok_button;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    private static ContactDatabase instance; //static because we want to use the same instance of the database throughout the app

    public abstract ContactDao contactDao(); //abstract because we don't define the body of the fxn

    public static synchronized ContactDatabase getInstance(Context context) { //synched means that only one thread can access this method at a time to avoid accidentally creating two instances of this database
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDatabase.class, "contact_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    //TODO: consider deleting or corresponding with the tutorial?
    //Everything below this line til the end of PopulateDbAsyncTask's definition is just for populating one contact (me) at the DB's creation
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ContactDao contactDao;

        private PopulateDbAsyncTask(ContactDatabase db) {
            contactDao = db.contactDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.insert(new Contact("Melissa", "Hazlewood", "+15628812240"));
            return null;
        }
    }
}
