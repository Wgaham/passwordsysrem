package wgaham.javabean;

public class dataBean {
	String[][] tableRecord = null;

	public dataBean() {
		tableRecord = new String[1][1];
	}

	public String[][] getTableRecord() {
		return tableRecord;
	}

	public void setTableRecord(String[][] tableRecord) {
		this.tableRecord = tableRecord;
	}

}
