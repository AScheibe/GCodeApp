package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;


public class RecentFilesUtil {
    public static Preferences rFPrefs = Preferences.userRoot().node("/RecentFiles");

    public static List<String> RecentFilesList;

    static {
        RecentFilesList = new ArrayList<String>(1);
        updateList();
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
                for (int i = 0; i < ogPaths.length - 1; i++) {
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
    }

    public static List<String> getRecentFilesList() {
        return RecentFilesList;
    }
}