package tenten.types;

/**
 * Class used to hold all the label's texts that will have to change based on
 * the selection of the language.
 */
public enum LanguageType {

    ITA("Grandezza della griglia: ",
            "IMPOSTAZIONI",
            "NEGOZIO",
            "Volume principale",
            "Lingua",
            "Applica",
            "Indietro",
            "Monete",
            "Punteggio",
            "Riprendi",
            "Ricomincia",
            "Menu",
            "Si",
            "No",
            "Salva ed esci",
            "Esci",
            "Riprendi la partita",
            "Cancella la partita",
            "Cosa vuoi fare?",
            "Ricominciare?",
            "(La partita corrente verra' sovrascritta)",
            "Esci e vai al menu principale?",
            "(Scegli se salvare la partita)",
            "Acquistato e selezionato",
            "Acquistato ma non selezionato",
            "NON PUOI ACQUISTARE QUESTO OGGETTO",
            "Compra",
            "Seleziona",
            "Vuoi comprare questo oggetto per ",
            "Vuoi selezionare questo tema?",
            "Vai al menu"),

    ENG("Grid size: ",
            "SETTINGS",
            "SHOP",
            "Master volume",
            "Language",
            "Apply",
            "Back",
            "Coins",
            "Score",
            "Resume",
            "Restart",
            "Menu",
            "Yes",
            "No",
            "Save and quit",
            "Quit",
            "Resume the game",
            "Delete the game",
            "What do you want to do?",
            "Restart?",
            "(The current game will be overwritten)",
            "Quit and go to main menu?",
            "(Choose whether to save the game)",
            "Purchased and selected",
            "Purchased but not selected",
            "YOU CANNOT AFFORD THIS ITEM!",
            "Buy",
            "Set",
            "Do you want to buy this item for ",
            "Do you want to set this theme?",
            "Back to menu");

    private String gridSize;
    private String settings;
    private String shop;
    private String volume;
    private String language;
    private String apply;
    private String back;
    private String coins;
    private String score;
    private String resume;
    private String restart;
    private String menu;
    private String yes;
    private String no;
    private String saveAndQuit;
    private String quit;
    private String resumeTheGame;
    private String deleteTheGame;
    private String savedGameLabel;
    private String dialogRestartLabel1;
    private String dialogRestartLabel2;
    private String dialogMenuLabel1;
    private String dialogMenuLabel2;
    private String purchasedSelected;
    private String purchasedNotSelected;
    private String alertLabel;
    private String buy;
    private String set;
    private String shopQuestionBuyItem;
    private String shopQuestionSetTheme;
    private String backToMenu;

    LanguageType(String gridSize,
            String settings,
            String shop,
            String volume,
            String language,
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
            String deleteTheGame,
            String savedGameLabel,
            String dialogRestartLabel1,
            String dialogRestartLabel2,
            String dialogMenuLabel1,
            String dialogMenuLabel2,
            String purchasedSelected,
            String purchasedNotSelected,
            String alertLabel,
            String buy,
            String set,
            String shopQuestionBuyItem,
            String shopQuestionSetTheme,
            String backToMenu) {
        this.gridSize = gridSize;
        this.settings = settings;
        this.shop = shop;
        this.volume = volume;
        this.language = language;
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
        this.savedGameLabel = savedGameLabel;
        this.dialogRestartLabel1 = dialogRestartLabel1;
        this.dialogRestartLabel2 = dialogRestartLabel2;
        this.dialogMenuLabel1 = dialogMenuLabel1;
        this.dialogMenuLabel2 = dialogMenuLabel2;
        this.purchasedSelected = purchasedSelected;
        this.purchasedNotSelected = purchasedNotSelected;
        this.alertLabel = alertLabel;
        this.buy = buy;
        this.set = set;
        this.shopQuestionBuyItem = shopQuestionBuyItem;
        this.shopQuestionSetTheme = shopQuestionSetTheme;
        this.backToMenu = backToMenu;
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

    public String getLanguage() {
        return language;
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

    public String getSavedGameLabel() {
        return savedGameLabel;
    }

    public String getDialogRestartLabel1() {
        return dialogRestartLabel1;
    }

    public String getDialogRestartLabel2() {
        return dialogRestartLabel2;
    }

    public String getDialogMenuLabel1() {
        return dialogMenuLabel1;
    }

    public String getDialogMenuLabel2() {
        return dialogMenuLabel2;
    }

    public String getPurchasedSelected() {
        return purchasedSelected;
    }

    public String getPurchasedNotSelected() {
        return purchasedNotSelected;
    }

    public String getAlertLabel() {
        return alertLabel;
    }

    public String getBuy() {
        return buy;
    }

    public String getSet() {
        return set;
    }

    public String getShopQuestionBuyItem() {
        return shopQuestionBuyItem;

    }

    public String getShopQuestionSetTheme() {
        return shopQuestionSetTheme;
    }

    public String getBackToMenu() {
        return backToMenu;
    }
}
