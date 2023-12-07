package oop23_1010.types;

public enum LanguageType {

    ITA("Grandezza della griglia: ",
        "IMPOSTAZIONI",
        "NEGOZIO",
        "Volume principale",
        "Applica",
        "Indietro",
        "Monete",
        "Punteggio",
        "Riprendi",
        "Ricomincia",
        "Menù",
        "Si",
        "No",
        "Salva ed esci",
        "Esci",
        "Ripredni la partita",
        "Cancella la partita"),
    
    ENG("Grid size: ",
        "SETTINGS",
        "SHOP",
        "Master volume",
        "Apply",
        "Back",
        "Coins",
        "Score",
        "Resume",
        "Restart",
        "Menù",
        "Yes",
        "No",
        "Save and quit",
        "Quit",
        "Resume the game",
        "Delete the game");

    String gridSize;
    String settings;
    String shop;
    String volume;
    String apply;
    String back;
    String coins;
    String score;
    String resume;
    String restart;
    String menu;
    String yes;
    String no;
    String saveAndQuit;
    String quit;
    String resumeTheGame;
    String deleteTheGame;

    LanguageType(   String gridSize,
                    String settings,
                    String shop,
                    String volume,
                    String apply,
                    String back,
                    String coins,
                    String score,
                    String resume,
                    String restart,
                    String menu,
                    String yes,
                    String no,
                    String saveAndQuit,
                    String quit,
                    String resumeTheGame,
                    String deleteTheGame){
        this.gridSize = gridSize;
        this.settings = settings;
        this.shop = shop;
        this.volume = volume;
        this.apply = apply;
        this.back = back;
        this.coins = coins;
        this.score = score;
        this.resume = resume;
        this.restart = restart;
        this.menu = menu;
        this.yes = yes;
        this.no = no;
        this.saveAndQuit = saveAndQuit;
        this.quit = quit;
        this.resumeTheGame = resumeTheGame;
        this.deleteTheGame = deleteTheGame;
    }

    public String getGridSize() {
        return gridSize;
    }

    public String getSettings() {
        return settings;
    }

    public String getShop() {
        return shop;
    }

    public String getVolume() {
        return volume;
    }

    public String getApply() {
        return apply;
    }

    public String getBack() {
        return back;
    }

    public String getCoins() {
        return coins;
    }

    public String getScore() {
        return score;
    }

    public String getResume() {
        return resume;
    }

    public String getRestart() {
        return restart;
    }

    public String getMenu() {
        return menu;
    }

    public String getYes() {
        return yes;
    }

    public String getNo() {
        return no;
    }

    public String getSaveAndQuit() {
        return saveAndQuit;
    }

    public String getQuit() {
        return quit;
    }

    public String getResumeTheGame() {
        return resumeTheGame;
    }

    public String getDeleteTheGame() {
        return deleteTheGame;
    }
}
