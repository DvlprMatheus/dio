package com.dvlprmatheus.board;

import com.dvlprmatheus.board.persistence.migration.MigrationStrategy;
import com.dvlprmatheus.board.ui.MainMenu;

import java.sql.SQLException;

import static com.dvlprmatheus.board.persistence.config.ConnectionConfig.getConnection;

public class BoardApplication {
	public static void main(String[] args) throws SQLException {
        try(var connection = getConnection()){
            new MigrationStrategy(connection).executeMigration();
        }
        new MainMenu().execute();
    }
}
