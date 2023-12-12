package tenten.types;

/**
 * Class used to hold all the label's texts that will have to change based on
 * the selection of the language.
 */
public enum LanguageType {

    /**
     * Define all the italian strings used in the app.
     */
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

    /**
     * Define all the english strings used in the app.
     */
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

    LanguageType(final String gridSize,
            final String settings,
            final String shop,
            final String volume,
            final String language,
            final String apply,
            final String back,
            final String coins,
            final String score,
            final String resume,
            final String restart,
            final String menu,
            final String yes,
            final String no,
            final String saveAndQuit,
            final String quit,
            final String resumeTheGame,
            final String deleteTheGame,
            final String savedGameLabel,
            final String dialogRestartLabel1,
            final String dialogRestartLabel2,
            final String dialogMenuLabel1,
            final String dialogMenuLabel2,
            final String purchasedSelected,
            final String purchasedNotSelected,
            final String alertLabel,
            final String buy,
            final String set,
            final String shopQuestionBuyItem,
            final String shopQuestionSetTheme,
            final String backToMenu) {
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

    /**
     * Getter of gridSize string.
     * 
     * @return gridSize string.
     */
    public String getGridSize() {
        return gridSize;
    }

    /**
     * Getter of settings string.
     * 
     * @return settings string.
     */
    public String getSettings() {
        return settings;
    }

    /**
     * Getter of shop string.
     * 
     * @return shop string.
     */
    public String getShop() {
        return shop;
    }

    /**
     * Getter of volume string.
     * 
     * @return volume string.
     */
    public String getVolume() {
        return volume;
    }

    /**
     * Getter of language string.
     * 
     * @return language string.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Getter of apply string.
     * 
     * @return apply string.
     */
    public String getApply() {
        return apply;
    }

    /**
     * Getter of back string.
     * 
     * @return back string.
     */
    public String getBack() {
        return back;
    }

    /**
     * Getter of coins string.
     * 
     * @return coins string.
     */
    public String getCoins() {
        return coins;
    }

    /**
     * Getter of score string.
     * 
     * @return score string.
     */
    public String getScore() {
        return score;
    }

    /**
     * Getter of resume string.
     * 
     * @return resume string.
     */
    public String getResume() {
        return resume;
    }

    /**
     * Getter of restart string.
     * 
     * @return restart string.
     */
    public String getRestart() {
        return restart;
    }

    /**
     * Getter of menu string.
     * 
     * @return menu string.
     */
    public String getMenu() {
        return menu;
    }

    /**
     * Getter of yes string.
     * 
     * @return yes string.
     */
    public String getYes() {
        return yes;
    }

    /**
     * Getter of no string.
     * 
     * @return no string.
     */
    public String getNo() {
        return no;
    }

    /**
     * Getter of save and quit string.
     * 
     * @return save and quit string.
     */
    public String getSaveAndQuit() {
        return saveAndQuit;
    }

    /**
     * Getter of quit string.
     * 
     * @return quit string.
     */
    public String getQuit() {
        return quit;
    }

    /**
     * Getter of resume the game string.
     * 
     * @return resume the game string.
     */
    public String getResumeTheGame() {
        return resumeTheGame;
    }

    /**
     * Getter of delete the game string.
     *
     * @return delete the game string.
     */
    public String getDeleteTheGame() {
        return deleteTheGame;
    }

    /**
     * Getter of saved game label string.
     * 
     * @return saved game label string.
     */
    public String getSavedGameLabel() {
        return savedGameLabel;
    }

    /**
     * Getter of dialog restart label 1 string.
     * 
     * @return dialog restart label 1 string.
     */
    public String getDialogRestartLabel1() {
        return dialogRestartLabel1;
    }

    /**
     * Getter of dialog restart label 2 string.
     * 
     * @return dialog restart label 2 string.
     */
    public String getDialogRestartLabel2() {
        return dialogRestartLabel2;
    }

    /**
     * Getter of dialog menu label 1 string.
     * 
     * @return dialog menu label 1 string.
     */
    public String getDialogMenuLabel1() {
        return dialogMenuLabel1;
    }

    /**
     * Getter of dialog menu label 2 string.
     * 
     * @return dialog menu label 2 string.
     */
    public String getDialogMenuLabel2() {
        return dialogMenuLabel2;
    }

    /**
     * Getter of purchased selected string.
     * 
     * @return purchased selected string.
     */
    public String getPurchasedSelected() {
        return purchasedSelected;
    }

    /**
     * Getter of purchased not selected string.
     * 
     * @return purchased not selected string.
     */
    public String getPurchasedNotSelected() {
        return purchasedNotSelected;
    }

    /**
     * Getter of alert label string.
     * 
     * @return alert label string.
     */
    public String getAlertLabel() {
        return alertLabel;
    }

    /**
     * Getter of buy string.
     * 
     * @return buy string.
     */
    public String getBuy() {
        return buy;
    }

    /**
     * Getter of set string.
     * 
     * @return set string.
     */
    public String getSet() {
        return set;
    }

    /**
     * Getter of shop question buy item string.
     * 
     * @return shop question buy item string.
     */
    public String getShopQuestionBuyItem() {
        return shopQuestionBuyItem;

    }

    /**
     * Getter of shop question set theme string.
     * 
     * @return shop question set theme string.
     */
    public String getShopQuestionSetTheme() {
        return shopQuestionSetTheme;
    }

    /**
     * Getter of back to menu string.
     * 
     * @return back to menu string.
     */
    public String getBackToMenu() {
        return backToMenu;
    }
}
