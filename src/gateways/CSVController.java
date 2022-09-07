package gateways;

import useCases.CSVUseCases.DatabaseFilePath;
import useCases.DataInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract sealed class CSVController implements DataInterface
permits AdminCSVController, BuyersCSVController, FavouritesCSVController, HistoriesCSVController,
ListingsCSVController, MessagesCSVController, SellersCSVController
{
    protected final DatabaseFilePath filepath;

    public CSVController(String fileName) {
        filepath =  new DatabaseFilePath(fileName);
    }

    @Override
    public List<String[]> read() throws IOException {
        List<String[]> data = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filepath.getFilePath()));
        String line;
        while((line = reader.readLine()) != null){
            data.add(line.split(","));
        }
        reader.close();

        return data;
    }

    @Override
    public abstract void write() throws IOException;
}
