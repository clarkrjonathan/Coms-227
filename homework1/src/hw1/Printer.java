package hw1;

/**
 * A printer object models pages being printed from a simple printer.
 */
public class Printer {
	
	/**
	 * Max capacity of printer tray.
	 */
	private int trayCapacity;
	
	/**
	 * Sheets available to the printer at any given time
	 */
	private int sheetsAvailable;
	
	/**
	 * Sheets contained in the tray
	 */
	private int sheetsInTray;
	
	/**
	 * Total pages printed since initializing
	 */
	private int totalPages;
	
	/**
	 * The next page number of the document that will be printed
	 */
	private int nextPage;
	
	/**
	 * Number of pages in document to be printed
	 */
	private int documentPages;
	
	/**
	 * Constructs a new printer with the given tray capacity.
	 * Initially the tray is empty and the printer has not printed any pages.
	 * 
	 * @param trayCapacity
	 * 		maximum number of pages the printers tray can hold
	 */
	public Printer(int trayCapacity) {
		this.trayCapacity = trayCapacity;
		
		sheetsAvailable = 0;
		sheetsInTray = 0;
		
		totalPages =  0;
		nextPage = 0;
		
		//dummy value to make it clear there's an error in case printPage() is called before startPrintJob()
		documentPages = -1;
	}
	
	/**
	 * Starts a new print job to make copies of a document that is a specified page length documentPages.
	 * updates the next page to print as page 0.
	 * 
	 * @param documentPages
	 * 		number of pages in document to be printed
	 */
	public void startPrintJob(int documentPages) {
		nextPage = 0;
		this.documentPages = documentPages;
	}
	
	/**
	 * Returns the number of sheets available for printing.
	 * @return
	 * 		number of sheets available for printing
	 */
	public int getSheetsAvailable() {
		return sheetsAvailable;
	}
	
	/**
	 * Returns the next page number of the document that will be printed.
	 * @return
	 * 		the next page number of the document that will be printed
	 */
	public int getNextPage() {
		return nextPage;
	}
	
	/**
	 * Returns the count of all pages printed by the printer since its construction.
	 * @return
	 * 		total number of pages printed by printer since initialized
	 */
	public int getTotalPages() {
		return totalPages;
	}
	
	/**
	 * Simulates the printer printing a page. The number pages printed is either one or zero depending on whether there is at
	 * least one sheet of paper available to the printer. Increments the total page count of the printer by the number
	 * of pages printed. Advances the next page to print by the number of pages printed wrapping around to 0 if the number exceeds
	 * the number of pages in the document. Also updates the number of pages available to the printer and contained in the tray.
	 */
	public void printPage() {
		
		//sets pages printed to 1 if sheetsAvailable is 1 or greater, and 0 if sheetsAvailable is 0
		int pagesPrinted = Math.min(1, sheetsAvailable);
		
		nextPage = (nextPage + pagesPrinted) % (documentPages);
		
		sheetsAvailable -= pagesPrinted;
		sheetsInTray -= pagesPrinted;
		
		totalPages += pagesPrinted;
	}
	
	/**
	 * Removes the paper tray from the printer; that is, makes the sheets available to the printer zero.
	 */
	public void removeTray() {
		sheetsAvailable = 0;
	}
	
	/**
	 * Replaces the tray in the printer; that is, makes the sheets available to the printer the same as the number of sheets in the tray.
	 */
	public void replaceTray() {
		sheetsAvailable = sheetsInTray;
	}
	
	/**
	 * Simulates removing the tray (if not already removed), adding the given number of sheets (up to the maximum capacity of the tray), and replacing the tray in the printer.
	 * @param sheets
	 * 		number of sheets to be added to printer tray
	 */
	public void addPaper(int sheets) {

		sheetsInTray += sheets;
		sheetsInTray = Math.min(sheetsInTray, trayCapacity);

		
		sheetsAvailable = sheetsInTray;
	}
	
	/**
	 * Simulates removing the tray (if not already removed), removing the given number of sheets (but not allowing the sheets to go below zero), and replacing the tray in the printer.
	 * @param sheets
	 * 		number of sheets to be removed from printer tray
	 */
	public void removePaper(int sheets) {

		sheetsAvailable = Math.max(sheetsInTray - sheets, 0);
		
		sheetsInTray = sheetsAvailable;
	}
	

}
