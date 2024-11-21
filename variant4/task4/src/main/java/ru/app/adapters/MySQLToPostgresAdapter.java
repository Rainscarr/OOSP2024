package ru.app.adapters;

import ru.app.databases.MySQL;
import ru.app.databases.Postgres;

public class MySQLToPostgresAdapter implements DatabaseAdapter {
    private MySQL mySQL;
    private Postgres postgres;

    public MySQLToPostgresAdapter(MySQL mySQL, Postgres postgres) {
        this.mySQL = mySQL;
        this.postgres = postgres;
    }

    @Override
    public void transferData() {
        String data = mySQL.getData();
        postgres.saveData(data);
    }
}
