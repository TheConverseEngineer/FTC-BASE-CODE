// I still don't know what package this is

// I don't even know what import the other static class is
// Idk the other imports either

public class TeleGUI {
  private String[][] strMap;
  private int[] currentSelected;
  
  private List<telemetry.Item> guiLines;
  private int selected;
  
  private static final String START_SELECTED = "◂";
  private static final String END_SELECTED   = "▸";
  
  public TeleGUI(String[][] _map) {
    strMap = _map; 
    selected = 0;
    currentSelected = new int[strMap.length];
    createGUI();
  }  
  
  private void createGUI () {
    telemetry.clearAll();
    
    for (int i = 0; i < strMap.length; i++) {
      telemetry.Item genItem = telemetry.AddData(map[i][0], fmtData(i, 2));
      guiLines.add(genItem);
      currentSelected[i] = 0;
    }
    
    telemetry.update();
  }
  
  private String fmtData (int line, int opt) {
    if (selected == line) {
      return (START_SELECTED + strMap[line][opt] + END_SELECTED); 
    } else if (selected == line) {
      return (" " + strMap[line][opt] + " "); 
    }
  }
  
  private void updateGUI () {
    for (int i = 0; i < strMap.length; i++) {
      guiLines[i].setValue(fmtData(i, currentSelected[i] + 2));
    }
    telemetry.update();
  }
                           
  private void moveDown() {
    selected = (selected + 1 == strMap.length) ? 0 : selected + 1;
    updateGUI();
  }
                           
                           
  private void moveUp() {
    selected = (selected == 0) ? strMap.length - 1 : selected - 1;
    updateGUI();
  }
                           
  private void moveRight() {
    if (strMap[selected][1].isEquals("INT")) {
      currentSelected++;
      currentSelected = (currentSelected > Integer.parseInt(strMap[selected][3])) ? Integer.parseInt(strMap[selected][2]) : currentSelected; 
    }
  }
}
