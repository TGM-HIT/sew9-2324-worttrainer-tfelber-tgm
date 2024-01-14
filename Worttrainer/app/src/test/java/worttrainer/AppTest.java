package worttrainer;

import org.junit.jupiter.api.Test;
import worttrainer.controller.WortController;
import worttrainer.model.WortTrainer;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    public void testActionPerformedInput() throws IOException {
        WortController controller = new WortController();
        String input = "Hund";
        controller.getView().setTextField(input);
        controller.actionPerformed("input");
        assertEquals(1, controller.getView().getGesamt());
    }

    @Test
    public void testActionPerformedRightInput() throws IOException {
        WortController controller = new WortController();
        WortTrainer trainer = controller.getTrainer();
        String input = trainer.getwListe().getwEintrag()[controller.getView().getRandom()].getWord();
        controller.getView().setTextField(input);
        controller.actionPerformed("input");
        assertEquals(1, controller.getView().getGesamt());
        assertEquals(1, controller.getView().getRichtig());
    }

    @Test
    public void testActionPerformedWrongInput() throws IOException {
        WortController controller = new WortController();
        WortTrainer trainer = controller.getTrainer();
        String input = "WrongInput";
        controller.getView().setTextField(input);
        controller.actionPerformed("input");
        assertEquals(1, controller.getView().getGesamt());
        assertEquals(0, controller.getView().getRichtig());
    }

    @Test
    public void testActionPerformedReset() throws IOException {
        WortController controller = new WortController();
        controller.getTrainer().setGeloesteW(1);
        controller.getTrainer().setAbgefragteW(2);
        controller.getView().setGesamt(3);
        controller.getView().setRichtig(1);
        controller.actionPerformed("reset");
        assertEquals(-1, controller.getTrainer().getGeloesteW());
        assertEquals(-1, controller.getTrainer().getAbgefragteW());
        assertEquals(0, controller.getView().getGesamt());
        assertEquals(0, controller.getView().getRichtig());
    }

    /*
    Keine Ahnung wie man JOptionPane Felder testet ohne Mocken,
    welches Overkill für dieses Projekt wäre.
     */
    @Test
    public void testActionPerformedLoad() throws IOException, ClassNotFoundException, SQLException {
        WortController controller = new WortController();
        controller.actionPerformed("save");
        WortController controller1 = new WortController();
        controller1.actionPerformed("load");
        assertNotNull(controller1.getTrainer());
    }
}

