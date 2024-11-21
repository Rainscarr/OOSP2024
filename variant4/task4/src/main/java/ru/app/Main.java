package ru.app;

import ru.app.adapters.DatabaseAdapter;
import ru.app.adapters.MySQLToPostgresAdapter;
import ru.app.databases.MySQL;
import ru.app.databases.Postgres;

public class Main {
    public static void main(String[] args) {
        MySQL mySQL = new MySQL();
        Postgres postgres = new Postgres();

        DatabaseAdapter adapter = new MySQLToPostgresAdapter(mySQL, postgres);
        adapter.transferData();
    }
}
