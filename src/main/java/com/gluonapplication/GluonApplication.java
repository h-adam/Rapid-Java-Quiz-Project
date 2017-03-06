package com.gluonapplication;

import com.gluonapplication.views.ChapterSelectionView;
import com.gluonapplication.views.CreatAccount;
import com.gluonapplication.views.Start_PageView;
import com.gluonapplication.views.LogIn;
import com.gluonapplication.views.Question;
import com.gluonapplication.views.SectionsView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.gluonapplication.views.Question_Selection;
import com.gluonapplication.views.Result;

public class GluonApplication extends MobileApplication {

    public static final String START_PAGE_VIEW = HOME_VIEW;
    public static final String SECONDARY_VIEW = "Log In";
    public static final String Third_VIEW = "CreatAccount";
    public static final String Fourth_VIEW = "Result";
    public static final String MENU_LAYER = "Side Menu";
    public static final String CHAPTER_VIEW = "Chapter Selection";
    public static final String SECTIONS = "SECTIONS";
    public static final String QUESTION = "Question";
    public static final String QUESTION2 = "Question2";
    public static final String QUESTION_SELECTION = "Question Selection";

    @Override
    public void init() {
        addViewFactory(START_PAGE_VIEW, () -> new Start_PageView(START_PAGE_VIEW).getView());
        addViewFactory(SECONDARY_VIEW, () -> new LogIn(SECONDARY_VIEW).getView());
        addViewFactory(Third_VIEW, () -> new CreatAccount(Third_VIEW).getView());
        addViewFactory(Fourth_VIEW, () -> new Result(Fourth_VIEW).getView());
        addViewFactory(CHAPTER_VIEW, () -> new ChapterSelectionView(CHAPTER_VIEW).getView());
        addViewFactory(SECTIONS, () -> new SectionsView(SECTIONS).getView());
        addViewFactory(QUESTION, () -> new Question(QUESTION).getView());
        addViewFactory(QUESTION2, () -> new Question(QUESTION2).getView());
        addViewFactory(QUESTION_SELECTION, () -> new Question_Selection(QUESTION_SELECTION).getView());

    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(GluonApplication.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(GluonApplication.class.getResourceAsStream("/icon.png")));
    }
}
