package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;


public class RecentFilesUtil {
    public static Preferences rFPrefs = Preferences.userRoot().node("/RecentFiles");

    public static List<String> RecentFilesList;

    public static Map<String, String> RecentFilesMap; //Hash map used to obtain text for buttons

    static {
        RecentFilesList = new ArrayList<String>();
        RecentFilesMap = new HashMap<String, String>();
        updateList();
        updateHashMap();
    }

    private static void updateHashMap(){
        for(int i = 0; i < RecentFilesList.size(); i++)
        {
            try {
                String[] pathKeys = rFPrefs.keys();
                String path = rFPrefs.get(pathKeys[i], "error finding file"); 
                
                String text = path.substring(path.lastIndexOf("/") + 1);
                RecentFilesMap.put(text, pathKeys[i]);
            } catch (BackingStoreException e) {
                e.printStackTrace();
            }
        }

    }

    private static void updateList() {
        try {
            String[] pathKeys = rFPrefs.keys();

            for (int i = 0; i < pathKeys.length; i++) {
                RecentFilesList.add(i, pathKeys[i]);
            }

        } catch (BackingStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void putRecentFile(String fPath) {
        String filePath = fPath;
        try {
            String[] ogPaths = rFPrefs.keys();

            if (ogPaths.length > 1) {
                for (int i = 0; i < ogPaths.length; i++) {
                    String tempPath = rFPrefs.get(ogPaths[i], "no file found");

                    rFPrefs.put(Integer.toString(i + 1), tempPath);

                    if (i == 5) {
                        break;
                    }
                }
            } else if (ogPaths.length == 1) {
                String tempPath = rFPrefs.get("0", "no file found");
                rFPrefs.put("1", tempPath);
            }
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }

        rFPrefs.put("0", filePath);
        updateList();
        updateHashMap();
    }

    public static List<String> getRecentFilesList() {
        return RecentFilesList;
    }
}