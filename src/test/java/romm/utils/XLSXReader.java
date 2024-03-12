package romm.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class XLSXReader {
    public String sourceFileName;

    public XLSXReader(String fileName) {
        this.sourceFileName = fileName;
    }

    private XSSFSheet getSheet(String sheetName) {
        try (FileInputStream fileInputStream = new FileInputStream(sourceFileName)){
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            return workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Row> getRowsFromSheet(String sheetName) {
        XSSFSheet searchPageSheet = this.getSheet(sheetName);
        Iterator<Row> rows = searchPageSheet.iterator();
        List<Row> rowsList = new ArrayList<>();
        while (rows.hasNext()) {
            rowsList.add(rows.next());
        }
        return rowsList;
    }

    public Map<Integer, List<String>> getCellsByRows(String sheetName) {
        List<Row> rowsList = this.getRowsFromSheet(sheetName);
        Map<Integer, List<String>> rowsCells = new HashMap<>();
        for (int i = 0; i < rowsList.size(); i++) {
            Row currentRow = rowsList.get(i);
            Iterator<Cell> cells = currentRow.cellIterator();
            List<String> currentRowCells = new ArrayList<>();
            while (cells.hasNext()) {
                Cell currentCell = cells.next();
                CellType currentCellType = currentCell.getCellType();
                if (currentCellType.equals(CellType.STRING)) {
                    currentRowCells.add(currentCell.getStringCellValue());
                } else if (currentCellType.equals(CellType.NUMERIC)) {
                    currentRowCells.add(NumberToTextConverter.toText(currentCell.getNumericCellValue()));
                }
            }
            rowsCells.put(i, currentRowCells);
        }
        return rowsCells;
    }

    public Object[][] getDataForDataProvider(String sheetName) {
        Map<Integer, List<String>> map = getCellsByRows(sheetName);
        int numRows = map.size();
        int maxNumCols = 0;
        for (List<String> list : map.values()) {
            maxNumCols = Math.max(maxNumCols, list.size());
        }
        Object[][] result = new Object[numRows][maxNumCols];
        int rowIndex = 0;
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            List<String> list = entry.getValue();
            int colIndex = 0;
            for (String item : list) {
                result[rowIndex][colIndex] = item;
                colIndex++;
            }
            rowIndex++;
        }
        return result;
    }
}
