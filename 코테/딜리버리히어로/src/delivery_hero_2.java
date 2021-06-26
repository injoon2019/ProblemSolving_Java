public class delivery_hero_2 {

    public static void main(String[] args) {
        String S = "root r-x delete-this.xls\n" +
                "  root r-- bug-report.pdf\n" +
                "  root r-- doc.xls\n" +
                "  root r-- podcast.flac\n" +
                " alice r-- system.xls\n" +
                "  root --x invoices.pdf\n" +
                " admin rwx SETUP.PY";
        System.out.println(solution(S));
    }

    private static String solution(String S) {
        String[] commands = S.split("\\r?\\n");
        int minLength = Integer.MAX_VALUE;
        for (String command : commands) {
            command = command.trim();
            String[] splittedCommand = command.split(" ");
            String owner = splittedCommand[0].trim();
            if (owner.equals("root")) { // 권한자가 root인 경우만
                if (splittedCommand[1].equals("r--")) {
                    String fileName = splittedCommand[2];
                    String[] splittedfileName = fileName.split("\\.");
                    String fileExtension = splittedfileName[splittedfileName.length - 1]; //마지막 점 뒤가 확장자
                    if (fileExtension.equals("doc") || fileExtension.equals("xls") || fileExtension.equals("pdf")) {
                        minLength = Math.min(minLength, fileName.length());
                    }
                }
            }

        }
        if (minLength == Integer.MAX_VALUE) {
            return "NO FILES";
        } else {
            return Integer.toString(minLength);
        }
    }
}
