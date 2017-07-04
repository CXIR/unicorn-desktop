package model;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Micka on 03/07/2017.
 */
public class ReadExcel {
    private ArrayList<User> data;

    public ReadExcel(){

    }

    public void ReadExcel() throws IOException {
        data = new ArrayList<>();
        File fileWorkbook = new File("resources/plan_de_tri_interpac.xls");

        Workbook workbook;
        WorkbookSettings set = new WorkbookSettings();
        set.setEncoding("cp1252");

    }
}
