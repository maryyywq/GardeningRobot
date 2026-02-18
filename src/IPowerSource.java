interface IPowerSource {
    void charge();
    double getLevel();
    void switchToBackup();
}
