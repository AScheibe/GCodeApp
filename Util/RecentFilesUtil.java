package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.crypto.spec.RC2ParameterSpec;

public class RecentFilesUtil {
    private static Preferences rFPrefs = Preferences.userRoot().node("/RecentFiles");

    private static List<String> recentFilesList;

    static {
        recentFilesList = new ArrayList<String>(1);
        updateList();
    }

    private static void updateList() {
        try {
            String[] pathKeys = rFPrefs.keys();

            System.out.println(pathKeys.length);


                for (int i = 0; i < pathKeys.length; i++) {
                    recentFilesList.add(i, rFPrefs.get(pathKeys[i], "file not found"));
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

            if(ogPaths.length > 1) {
                for (int i = 0; i < ogPaths.length - 1; i++) {
                    String tempPath = rFPrefs.get(ogPaths[i], "no file found");

                    rFPrefs.put(Integer.toString(i + 1), tempPath);

                    if (i == 5) {
                        break;
                    }
                }
            }
            else if(ogPaths.length == 1){
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
        return recentFilesList;
    }
}