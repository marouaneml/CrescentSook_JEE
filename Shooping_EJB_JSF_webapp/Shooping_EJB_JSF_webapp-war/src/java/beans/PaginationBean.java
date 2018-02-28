package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UICommand;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;

/**
 *
 * @author MAROUANE
 */
@ManagedBean(name="paginationBean")
@RequestScoped
public class PaginationBean implements Serializable{
    
    private transient HtmlDataTable  paginationDmdDataTable;
    
    private int totalRows;
    private int firstRow;
    private int rowsPerPage = 10;
    private int totalPages;
    private int pageRange = 10;
    private Integer[] pages ;
    private int currentPage;
    public void loadData(){
        totalRows = paginationDmdDataTable.getRowCount();
        currentPage = (totalRows / rowsPerPage) - ((totalRows - firstRow) / rowsPerPage) + 1;
        totalPages = (totalRows / rowsPerPage) + ((totalRows % rowsPerPage != 0) ? 1 : 0);
        int pagesLength = Math.min(pageRange, totalPages);  
        pages = new Integer[pagesLength];
        int firstPage = Math.min(Math.max(0, currentPage - (pageRange / 2)), totalPages - pagesLength);
        for (int i = 0; i < pagesLength; i++) {
            pages[i] = ++firstPage;
        }
    }
 
    public void page(ActionEvent event) {
        System.out.println((Integer) ((UICommand) event.getComponent()).getValue());
        page(((Integer) ((UICommand) event.getComponent()).getValue() - 1) * rowsPerPage);
    }
 
    private void page(int firstRow) {
        this.firstRow = firstRow;
        loadData();
    }
    public Integer[] getPages() {
       loadData();
       return pages;
    }

    public HtmlDataTable getPaginationDmdDataTable() {
        return paginationDmdDataTable;
    }

    public void setPaginationDmdDataTable(HtmlDataTable paginationDmdDataTable) {
        this.paginationDmdDataTable = paginationDmdDataTable;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
}
