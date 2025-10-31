package raf.graffito.dsw;


import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.GraffRepository;
import repository.GraffRepositoryImplementation;

import javax.swing.*;

public class AppCore {

    public static void main(String[] args) {
        GraffRepository graffRepository = new GraffRepositoryImplementation();
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        appCore.getInstance().initialize(graffRepository);
    }
}