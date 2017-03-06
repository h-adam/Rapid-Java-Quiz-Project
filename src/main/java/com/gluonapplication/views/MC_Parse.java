package com.gluonapplication.views;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MC_Parse {

    public static ArrayList<String> info = new ArrayList<>();

    public static void initinfo() {
        info.add("0");
        info.add("0");
        info.add("0");
    }

    public static void setChapter(String a) {
        info.set(0, ("C" + a));
    }

    public static void setSection(String a) {
        info.set(1, ("S" + a));
    }

    public static void setQuestion(String a) {
        info.set(2, ("Q" + a));
    }

    public static class MCQuestion {

        String chapter;
        String chapterDescription;
        String section;
        String sectionDescription;
        String question;
        String questionText;
        String aText;
        String bText;
        String cText;
        String dText;
        String eText;
        String key;
        String hint;

        public MCQuestion() {
            this.chapter = "";
            this.chapterDescription = "";
            this.section = "";
            this.sectionDescription = "";
            this.question = "";
            this.questionText = "";
            this.aText = "";
            this.bText = "";
            this.cText = "";
            this.dText = "";
            this.eText = "";
            this.key = "";
            this.hint = "";
        }

        public MCQuestion(String chapter, String chapterDescription,
                String section, String sectionDescription, String question,
                String questionText, String aText, String bText, String cText,
                String dText, String eText, String key, String hint) {

            this.chapter = chapter;
            this.chapterDescription = chapterDescription;
            this.section = section;
            this.sectionDescription = sectionDescription;
            this.question = question;
            this.questionText = questionText;
            this.aText = aText;
            this.bText = bText;
            this.cText = cText;
            this.dText = dText;
            this.eText = eText;
            this.key = key;
            this.hint = hint;
        }

        public String getChapter() {
            return this.chapter;
        }

        public String getChapterDes() {
            return this.chapterDescription;
        }

        public String getSection() {
            return this.section;
        }

        public String getSectionDes() {
            return this.sectionDescription;
        }

        public String getQuestionNum() {
            return this.question;
        }

        public String getQuestionText() {
            return this.questionText;
        }

        public String getAText() {
            return this.aText;
        }

        public String getBText() {
            return this.bText;
        }

        public String getCText() {
            return this.cText;
        }

        public String getDText() {
            return this.dText;
        }

        public String getEText() {
            return this.eText;
        }

        public String getKey() {
            return this.key;
        }

        public String getHint() {
            return this.hint;
        }

    }

    public static Map createMap() throws IOException {

        Map questionCollection = new HashMap();

        Pattern pattern = Pattern.compile("^(\\d+.*|-\\d+.*)");
        String chapter = "";
        String chapterDescription = "";
        String section = "";
        String sectionDescription = "";
        String question = "";
        String questionText = "";
        String aText = "";
        String bText = "";
        String cText = "";
        String dText = "";
        String eText = "";
        String key = "";
        String hint = "";

        int ct = 1;
        int questionNumber = 1;
        int chapterCounter = 1;
        int sectionCounter = 1;
        boolean sectionNew = false;
        while (ct <= 44) {

            try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\mcquestions\\chapter" + ct + ".txt"))) {
                Matcher matcher;
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    matcher = pattern.matcher(line);
                    StringBuilder questionTxt = new StringBuilder();

                    if (line.startsWith("Chapter ")) {
                        String[] splited = line.split("\\s+");
                        chapter = splited[1];
                        StringBuilder description = new StringBuilder();

                        int count = 1;

                        for (String get : splited) {
                            if (count >= 3) {
                                description.append(get).append(" ");
                            }
                            count++;
                        }
                        chapterDescription = description.toString();
                    }

                    if (line.startsWith("Section")) {
                        String[] splited = line.split("\\s+");
                        if (!section.equals(splited[1]) && !section.equals("")) {
                            sectionNew = true;
                            sectionCounter++;
                        }

                        section = splited[1];
                        StringBuilder description = new StringBuilder();

                        int count = 1;
                        for (String get : splited) {
                            if (count >= 3) {
                                description.append(get).append(" ");
                            }
                            count++;
                        }
                        sectionDescription = description.toString();
                    }

                    if (matcher.matches()) {
                        while (line != null && !line.startsWith("a.") && !line.startsWith("A.")) {
                            questionTxt.append(line).append("\n");
                            line = br.readLine();
                        }
                        String[] splited = questionTxt.toString().split("\\s+");

                        if (sectionNew) {
                            questionNumber = 1;
                            sectionNew = false;
                        }

                        question = Integer.toString(questionNumber);

                        StringBuilder description = new StringBuilder();

                        int count = 0;
                        for (String get : splited) {
                            if (count >= 1) {
                                description.append(get).append(" ");
                            }
                            count++;
                        }
                        questionText = description.toString();
                    }

                    if (line.startsWith("a.") || line.startsWith("A.")) {
                        String a = (line.substring(3));
                        aText = a;
                        line = br.readLine();
                        if (line.startsWith("b.") || line.startsWith("B.")) {
                            String b = (line.substring(3));
                            bText = b;
                            line = br.readLine();

                            if (line.startsWith("c.") || line.startsWith("C.")) {
                                String c = (line.substring(3));
                                cText = c;
                                line = br.readLine();
                                if (line.startsWith("d.") || line.startsWith("D.")) {
                                    String d = (line.substring(3));
                                    dText = d;
                                    line = br.readLine();
                                    if (line.startsWith("e.") || line.startsWith("E.")) {
                                        String e = (line.substring(3));
                                        eText = e;
                                        line = br.readLine();
                                    } else {
                                        String e = ("");
                                        eText = e;
                                    }
                                } else {
                                    String d = ("");
                                    dText = d;
                                }
                            } else {
                                String c = ("");
                                cText = c;
                            }
                        } else {
                            String b = ("");
                            bText = b;
                        }
                    }

                    if (line.startsWith("Key:")) {
                        key = "";
                        String[] splited = line.split("\\s+");
                        String[] answer = splited[0].substring(4).split("(?!^)");
                        for (String get : answer) {
                            key += get + " ";
                        }

                        StringBuilder hintText = new StringBuilder();
                        int count = 0;
                        for (String get : splited) {
                            if (count >= 1) {
                                hintText.append(get).append(" ");
                            }
                            count++;
                        }
                        hint = hintText.toString();
                    }

                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();

                    if (!question.equals("")) {
                        MCQuestion questionContent = new MCQuestion(chapter, chapterDescription,
                                section, sectionDescription, question,
                                questionText, aText, bText, cText,
                                dText, eText, key, hint);
                        questionCollection.put("C" + chapterCounter + "S"
                                + sectionCounter + "Q" + question,
                                questionContent);
                        questionNumber++;
                    }

                    question = "";
                    questionText = "";
                    aText = "";
                    bText = "";
                    cText = "";
                    dText = "";
                    eText = "";
                    key = "";
                }

            } finally {
                ct++;
                sectionCounter = 1;
                chapterCounter++;
            }
        }
        return questionCollection;

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Map questionCollection;
        questionCollection = createMap();

        //print all question from section 1
        int cnum = 1;
        int qnum = 1;
        int sec = 1;
        while (questionCollection.containsKey("C" + cnum + "S" + sec + "Q" + qnum)) {
            MCQuestion questiontest = (MCQuestion) questionCollection.get("C" + cnum + "S" + sec + "Q" + qnum);
            System.out.println(questiontest.getQuestionNum() + " " + questiontest.getQuestionText());
            qnum++;
        }
    }
}
