package worttrainer.model;

import java.io.IOException;

public interface WortTrainerSpeichernImpl {
    public WortTrainer load(String txt) throws IOException, NumberFormatException;
    public void save(String txt, WortTrainer wTrainer) throws IOException;
}
